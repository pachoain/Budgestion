/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author luc
 */
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

            } else {
                System.out.println("charlatant tu essaies de m'usurper");
                this.id = -1;
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
            System.out.println("ca doit pas passer ca");
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
        Statement state = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT * FROM flux WHERE user_id = ?";

        PreparedStatement prepare = conn.prepareStatement(query);

        prepare.setInt(1, this.id);

        ResultSet res = state.executeQuery(prepare.toString());
        while (res.next()) {
            System.out.println(res.getInt("id"));
            System.out.println(res.getFloat("amount"));
            System.out.println(res.getDate("date"));
            System.out.println(res.getInt("category"));
            System.out.println(res.getBoolean("type"));
        }
    }

    public int getID() {
        return id;
    }
}
