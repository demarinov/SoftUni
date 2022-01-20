package com.dido.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class MainMinionNames {

    public static void main(String[] args) throws IOException, SQLException {

        Scanner sc = new Scanner(System.in);

        // write your code here
        String appConfigPath = MainMinionNames.class.getClassLoader().getResource("db.properties")
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
                getConnection("jdbc:mysql://localhost:3306/minionsDB?useSSL=false", properties)) {

            PreparedStatement stmt = connection.
                    prepareStatement("select V.name as 'villain_name', " +
                            "M.name as 'minion_name', M.age as 'minion_age' " +
                            "from minions M \n" +
                            "join villains_minions VM on VM.minion_id = M.id\n" +
                            "join villains V on V.id = VM.villain_id\n" +
                            "where V.id = ?");
            System.out.printf("Villain id: ");
            Integer villainId = Integer.parseInt(sc.nextLine());
            stmt.setInt(1, villainId);
            ResultSet rs = stmt.executeQuery();

            String villainName = "";
            List<Map.Entry<String, Integer>> minions = new ArrayList<>();
            while(rs.next()) {

                villainName = rs.getString("villain_name");
                String minionName = rs.getString("minion_name");
                Integer age = rs.getInt("minion_age");

                minions.add(Map.entry(minionName, age));
            }

            if (villainName != null && !villainName.isEmpty()) {
                System.out.println("Villain: " + villainName);

                int count = 0;
                for (Map.Entry<String, Integer> minionEntry : minions) {
                    System.out.printf("%d. %s %d%n", ++count, minionEntry.getKey(), minionEntry.getValue());
                }
            } else {
                System.out.printf("No villain with ID %d exists in the database.",villainId);
            }
        }
    }
}
