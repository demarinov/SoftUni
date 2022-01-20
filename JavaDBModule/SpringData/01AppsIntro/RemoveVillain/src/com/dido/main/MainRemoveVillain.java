package com.dido.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class MainRemoveVillain {

    public static void main(String[] args) throws IOException, SQLException {
	    // write your code here

        Scanner sc = new Scanner(System.in);

        String appConfigPath = MainRemoveVillain.class.getClassLoader().getResource("db.properties")
                .getPath();
        Properties properties = new Properties();
        properties.load(new FileInputStream(appConfigPath));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Driver class loaded...");

        try (Connection connection = DriverManager.
                getConnection("jdbc:mysql://localhost:3306/minionsDB?useSSL=false",properties)) {

            connection.setAutoCommit(false);
            int villainId = Integer.parseInt(sc.nextLine().trim());
            int resultOne = 0;
            int resultTwo = 0;

            PreparedStatement getVillainNameStatement = connection.
                    prepareStatement("select name from villains where id = ?");

            getVillainNameStatement.setInt(1, villainId);
            ResultSet rs = getVillainNameStatement.executeQuery();

            String villainName = "";
            while(rs.next()) {

                villainName = rs.getString("name");
            }

            PreparedStatement deleteVillainMinionStatement = connection.
                    prepareStatement("delete from villains_minions where villain_id = ?");

            deleteVillainMinionStatement.setInt(1, villainId);
            resultOne = deleteVillainMinionStatement.executeUpdate();

            PreparedStatement deleteVillainStatement = connection.
                    prepareStatement("delete from villains where id = ?");

            deleteVillainStatement.setInt(1, villainId);
            resultTwo = deleteVillainStatement.executeUpdate();

            if (resultTwo > 0 && resultOne > 0) {
                System.out.printf("%s was deleted%n",villainName);
                System.out.printf("%d minions released",resultOne);
                connection.commit();
            } else {
                System.out.println("No such villain was found");
                connection.rollback();
            }

        }
    }
}
