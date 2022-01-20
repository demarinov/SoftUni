package com.dido.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class MainIncreaseMinion {

    public static void main(String[] args) throws SQLException, IOException {
	// write your code here
        Scanner sc = new Scanner(System.in);

        String appConfigPath = MainIncreaseMinion.class.getClassLoader().getResource("db.properties")
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

            PreparedStatement updateMinionStatement = connection.
                    prepareStatement("update minions set age = age + 1, name = lower(name) " +
                            "where id in (?)");
            int[] minionIdData = Arrays.stream(sc.nextLine().split("\\s"))
                    .mapToInt(s -> Integer.parseInt(s))
                    .toArray();
            for (int i = 0; i < minionIdData.length; i++) {

                updateMinionStatement.setInt(1,minionIdData[i]);
                int result = updateMinionStatement.executeUpdate();
            }

            PreparedStatement getAllMinions = connection.
                    prepareStatement("select name, age from minions");


            ResultSet rs = getAllMinions.executeQuery();

            List<String> names = new ArrayList<>();
            List<Integer> ages = new ArrayList<>();

            while(rs.next()) {

                names.add(rs.getString("name"));
                ages.add(rs.getInt("age"));
            }

            for (int i = 0; i < names.size(); i++) {

                System.out.printf("%s %d%n",names.get(i),ages.get(i));
            }

        }
    }
}
