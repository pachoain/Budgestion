package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

public class Account {

    private int id;
    private Connection conn;
    private ArrayList<Operation> spendings;
    private ArrayList<Operation> incomes;

    public Account(String... infos) {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver O.K.");
            String url = "jdbc:postgresql://localhost:5432/Budgestion";
            String user = "isen";
            String passwd = "isencir";

            this.conn = DriverManager.getConnection(url, user, passwd);
            conn.setAutoCommit(false);
            System.out.println("Connexion effective !");
            if (infos.length == 2) {
                signIn(infos);
            } else {
                signUp(infos);
            }
            load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signIn(String[] infos) throws SQLException {
        this.id = -1;
        Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT password FROM users WHERE username = ?";

        PreparedStatement prepare = conn.prepareStatement(query);

        prepare.setString(1, infos[0]);

        ResultSet res = state.executeQuery(prepare.toString());
        while (res.next()) {
            String pswd = res.getString("password");
            if (pswd.equals(infos[1])) {
                System.out.println("connecté");
                setId(infos[0]);

            }
        }
        prepare.close();

        state.close();
    }

    public void signUp(String[] infos) throws SQLException {
        Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT id FROM users WHERE username = ?";

        PreparedStatement prepare = conn.prepareStatement(query);

        prepare.setString(1, infos[2]);

        ResultSet res = state.executeQuery(prepare.toString());
        if (res.next()) {
            this.id = -1;
        } else {
            PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO users (first_name, last_name, username, password) VALUES(?, ?, ?, ?)");
            pstmt.setString(1, infos[0]);
            pstmt.setString(2, infos[1]);
            pstmt.setString(3, infos[2]);
            pstmt.setString(4, infos[3]);

            int rows = pstmt.executeUpdate();
            conn.commit();
            setId(infos[0]);
        }

    }

    public void setId(String username) throws SQLException {
        Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT id FROM users WHERE username = ?";

        PreparedStatement prepare = conn.prepareStatement(query);

        prepare.setString(1, username);

        ResultSet res = state.executeQuery(prepare.toString());
        while (res.next()) {
            this.id = res.getInt("id");
        }
    }

    public void load() throws SQLException {
        Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT * FROM flux WHERE user_id = ?";

        PreparedStatement prepare = conn.prepareStatement(query);
        this.spendings = new ArrayList<>();
        this.incomes = new ArrayList<>();
        prepare.setInt(1, this.id);

        ResultSet res = state.executeQuery(prepare.toString());
        while (res.next()) {
            float amount = res.getFloat("amount");
            Date date = res.getDate("date");
            int category = res.getInt("category");
            Operation operation = new Operation(amount, date, category);
            if (amount < 0) {
                spendings.add(operation);
            } else {
                incomes.add(operation);
            }
        }
    }

    public float[] getMonth(Date date) {
        float[] month = new float[7];
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentYear = calendar.get(Calendar.YEAR);
        for (Operation spending : this.spendings) {
            calendar.setTime(spending.getDate());
            if ((calendar.get(Calendar.MONTH) == currentMonth) && (calendar.get(Calendar.YEAR) == currentYear)) {
                month[spending.getCategory()] += spending.getValue();
            }
        }
        return month;
    }

    /*public float[] getYear(Date date) {
        float[] year = new float[7];
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int currentYear = calendar.get(Calendar.YEAR);
        for (Operation spending : this.spendings) {
            calendar.setTime(spending.getDate());
            if (calendar.get(Calendar.YEAR) == currentYear) {
                year[spending.getCategory()] += spending.getValue();
            }
        }
        return year;
    }*/

    public float[] getSpendingsByYear(int year) {
        float[] tot_spending = new float[12];
        Calendar calendar = Calendar.getInstance();
        for (Operation spending : this.spendings) {
            calendar.setTime(spending.getDate());
            if (calendar.get(Calendar.YEAR) == year) {
                tot_spending[calendar.get(Calendar.MONTH)] += spending.getValue();
            }
        }
        return tot_spending;
    }

    public float[] getIncomesByYear(int year) {
        float[] tot_incomes = new float[12];
        Calendar calendar = Calendar.getInstance();
        for (Operation spending : this.spendings) {
            calendar.setTime(spending.getDate());
            if (calendar.get(Calendar.YEAR) == year) {
                tot_incomes[calendar.get(Calendar.MONTH)] += spending.getValue();
            }
        }
        return tot_incomes;
    }

    public float[] getOperationByYear(int year) {
        float[] income = getIncomesByYear(year);
        float[] spending = getSpendingsByYear(year);
        float[] total = new float[12];

        for (int i = 0; i < 12; i++) {
            total[i] = income[i] - spending[i];
        }
        return total;
    }

    public float[] getTotalOperationByYear(int year) {
        float[] income = getIncomesByYear(year);
        float[] spending = getSpendingsByYear(year);
        float sum = 0;
        float[] total = new float[12];

        for (int i = 0; i < 12; i++) {
            sum = sum + income[i] - spending[i];
            total[i] += sum;
        }
        return total;
    }

    public ArrayList getPossibleDate() {
        ArrayList<Date> date = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (Operation spending : spendings) {
            calendar.setTime(spending.getDate());
            if (!findDate(date, calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR))) {
                date.add((Date) spending.getDate());
            }
        }
        for (Date d : date) {
            System.out.println(d.toString());
        }
        return date;
    }

    public boolean findDate(ArrayList<Date> date, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        for (Date d : date) {
            calendar.setTime(d);
            if ((calendar.get(Calendar.YEAR) == year) && (calendar.get(Calendar.MONTH) == month)) {
                return true;
            }
        }
        return false;
    }

    public void addOperation(float amount, Date date, int category) throws SQLException {
        if (amount >= 0) {
            incomes.add(new Operation(amount, date, category));
        } else {
            spendings.add(new Operation(amount, date, category));
        }
        PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO flux (user_id, amount, date, category) VALUES(?, ?, ?, ?)");
        pstmt.setInt(1, this.id);
        pstmt.setFloat(2, amount);
        pstmt.setDate(3, (java.sql.Date) date);
        pstmt.setInt(4, category);

        int rows = pstmt.executeUpdate();
        conn.commit();
    }

    public int getID() {
        return id;
    }
}
