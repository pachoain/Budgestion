/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.security.MessageDigest;

/**
 *
 * @author onyze
 */
public class Budgestion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver O.K.");

            String url = "jdbc:postgresql://localhost:5432/Budgestion";
            String user = "isen";
            String passwd = "isencir";

            Connection conn = DriverManager.getConnection(url, user, passwd);
            conn.setAutoCommit(false);
            System.out.println("Connexion effective !");

            //Création d'un objet Statement
            Statement state = conn.createStatement();

            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            if ("1".equals(str)) {
                System.out.println("Veuillez saisir votre nom d'utilisateur");
                String username = sc.nextLine();
                System.out.println("Veuillez saisir votre mot de passe");
                String password = sc.nextLine();
                signIn(username, password, conn);
            } else {
                if ("2".equals(str)) {
                    System.out.println("Veuillez saisir votre prénom");
                    String first_name = sc.nextLine();
                    System.out.println("Veuillez saisir votre nom");
                    String last_name = sc.nextLine();
                    System.out.println("Veuillez saisir votre nom d'utilisateur");
                    String username = sc.nextLine();
                    System.out.println("Veuillez saisir votre mot de passe");
                    String password = sc.nextLine();
                    signUp(first_name, last_name, username, password, conn, state);
                }
            }

            /**
             * ***** Requête d'insertion ******
             */
            /*System.out.println(state.executeUpdate("INSERT INTO flux (id, user_id, amount, date, category, type) VALUES(2, 1, 200, '2017-04-06', 1, FALSE)"));
            conn.commit();//Ne pas oublier sinon l'insertion ne se retrouve pas dans la BDD
             */
            /**
             * ***** Requête de SELECT ******
             */
            /*
            //L'objet ResultSet contient le résultat de la requête SQL
            ResultSet result = state.executeQuery("SELECT * FROM flux");
            //On récupère les MetaData
            ResultSetMetaData resultMeta = result.getMetaData();

            System.out.println("\n**********************************");
            //On affiche le nom des colonnes
            for (int i = 1; i <= resultMeta.getColumnCount(); i++) {
                System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");
            }

            System.out.println("\n**********************************");

            while (result.next()) {
                for (int i = 1; i <= resultMeta.getColumnCount(); i++) {
                    System.out.print("\t" + result.getObject(i).toString() + "\t |");
                }

                System.out.println("\n---------------------------------");

            }

            result.close();
            state.close();
             */
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void connection(String username, String password) {

    }

    public static void signUp(String fn, String ln, String un, String pw, Connection conn, Statement state) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO users (first_name, last_name, username, password) VALUES(?, ?, ?, ?)");
        pstmt.setString(1, fn);
        pstmt.setString(2, ln);
        pstmt.setString(3, un);
        pstmt.setString(4, pw.getInstance("MD5"));

        int rows = pstmt.executeUpdate();
        conn.commit();
    }

    public static void signIn(String username, String password, Connection conn) throws SQLException {

        Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT password FROM users";
        query += " WHERE username = ?";

        PreparedStatement prepare = conn.prepareStatement(query);

        prepare.setString(1, username);
        System.out.println(prepare.toString());

        ResultSet res = state.executeQuery(prepare.toString());
        while (res.next()) {
            String pswd = res.getString("password");
            if (pswd.equals(password)) {
                System.out.println("connecté");

            } else {
                System.out.println("charlatant tu essaies de m'usurper");
            }
        }
        prepare.close();
 
        state.close();
    }

}
