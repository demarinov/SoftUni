package com.dido.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class MainJDBC {

    public static void main(String[] args) throws SQLException, IOException {
        // write your code here
        Scanner sc = new Scanner(System.in);

        Properties props = new Properties();
        String appConfigPath = MainJDBC.class.getClassLoader()
                .getResource("db.properties").getPath();
        props.load(new FileInputStream(appConfigPath));

        // 1. Load jdbc driver (optional)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Driver loaded successfully.");

        try (
             Connection connection =
                        DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/diablo?useSSL=false", props);
                PreparedStatement stmt =
                        connection.prepareStatement("SELECT U.user_name, U.first_name, U.last_name ," +
                                "count(U.id) as 'games_played' FROM users U " +
                                "join users_games UG on UG.user_id = U.id where U.user_name = ? " +
                                "group by U.id ")) {

            System.out.println("Connected successfully.");

            System.out.printf("Enter user: ");
            String user = sc.nextLine().trim();
            stmt.setString(1, user);
            // 3. Execute query
            ResultSet rs = stmt.executeQuery();

            String firstName = "";
            String lastName = "";

            // 4. Process results
            int count = 0;
            while (rs.next()) {
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                count = rs.getInt("games_played");
            }


            if (count == 0) {
                System.out.println("No such user exists");
            } else {
                System.out.println("User: " + user);
                System.out.printf("%s %s has played %d games",
                        firstName,
                        lastName,
                        count);
            }
        }
    }
}
