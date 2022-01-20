package com.dido.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class MainVillainNames {

    public static void main(String[] args) throws IOException, SQLException {
	// write your code here

        String appConfigPath = MainVillainNames.class.getClassLoader().getResource("db.properties")
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
                    prepareStatement("select V.name as 'villain_name', count(M.id) as 'count_minions' from minions M\n" +
                            "join villains_minions VM on M.id = VM.minion_id\n" +
                            "join villains V on V.id = VM.villain_id\n" +
                            "group by V.id having `count_minions` > 15 order by `count_minions` desc");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                String villainName = rs.getString("villain_name");
                int countMinions = rs.getInt("count_minions");
                System.out.println(villainName + " "+countMinions);
            }
        }
    }
}
