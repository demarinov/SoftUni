package com.dido.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class MainChangeTownNames {

    public static void main(String[] args) throws IOException, SQLException {

        Scanner sc = new Scanner(System.in);
	// write your code here
        String appConfigPath = MainChangeTownNames.class.getClassLoader().
                getResource("db.properties")
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

            String country = sc.nextLine().trim();

            PreparedStatement updateStatement = connection.
                    prepareStatement("update towns set name = upper(name) where country_id = \n" +
                            "(select id from countries where name = ?)");

            updateStatement.setString(1,country);
            updateStatement.executeUpdate();

            PreparedStatement townNameStatement = connection.
                    prepareStatement("select name from towns where country_id = \n" +
                            "(select id from countries where name = ?)");

            townNameStatement.setString(1, country);

            ResultSet rs = townNameStatement.executeQuery();

            List<String> townNames = new LinkedList<>();
            while(rs.next()) {

                townNames.add(rs.getString("name"));
            }

            if (townNames.isEmpty()) {
                System.out.print("No town names were affected.");
            } else {
                System.out.printf("%d town names were affected.%n",townNames.size());
                System.out.printf("[%s]",
                        townNames.stream().map(s -> String.format(",%s",s)).
                                reduce("",String::concat).substring(1));
            }
        }
    }
}
