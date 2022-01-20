package com.dido.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class MainPrintMinionNames {

    public static void main(String[] args) throws IOException, SQLException {
	// write your code here
        String appConfigPath = MainPrintMinionNames.class.getClassLoader().getResource("db.properties")
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

            PreparedStatement stmt = connection.
                    prepareStatement("select M.name from minions M");
            ResultSet rs = stmt.executeQuery();

            List<String> minionNames = new ArrayList<>();
            while (rs.next()) {

                String minionName = rs.getString("name");
                minionNames.add(minionName);
            }

            int minionNamesSize = minionNames.size();
            for (int i = 0; i < minionNamesSize/2; i++) {

                if (i == (minionNamesSize-i-1)) {
                    System.out.println(minionNames.get(i));
                } else {
                    System.out.println(minionNames.get(i));
                    System.out.println(minionNames.get(minionNamesSize - i - 1));
                }
            }
        }
    }
}
