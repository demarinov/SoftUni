package com.dido.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class MainAddMinion {

    public static void main(String[] args) throws IOException, SQLException {
	    // write your code here

        Scanner sc = new Scanner(System.in);
        String appConfigPath = MainAddMinion.class.getClassLoader().getResource("db.properties")
                .getPath();
        Properties properties = new Properties();
        properties.load(new FileInputStream(appConfigPath));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Driver class loaded...");

        String[] minionData = sc.nextLine().split("\\s");
        String minionName = minionData[1];
        int ageMinion = Integer.parseInt(minionData[2]);
        String town = minionData[3];
        String[] villainData = sc.nextLine().split("\\s");

        try (Connection connection = DriverManager.
                getConnection("jdbc:mysql://localhost:3306/minionsDB?useSSL=false",properties)) {

            int townId = getTownId(connection, town);

//            System.out.println(townId);

            if (townId < 0) {
                insertTown(connection, town);
                System.out.printf("Town %s was added to the database.%n",town);
                townId = getTownId(connection, town);
            }

            insertMinion(connection, minionName, ageMinion, townId);

            int villainId = -2;
            String villainName = villainData[1];
            villainId = getVillainId(connection, villainName);

            if (villainId < 0) {
                insertVillain(connection, villainName);
                System.out.printf("Villain %s was added to the database.%n",villainName);
                villainId = getVillainId(connection, villainName);
            }


            int minionId = getMinionId(connection, minionName);

            linkMinionVillain(connection, minionId, villainId);
            System.out.printf("Successfully added %s to be minion of %s.%n",minionName, villainName);
        }
    }

    public static void insertTown(Connection connection, String town) throws SQLException {
        PreparedStatement insertTownStatement = connection.
                prepareStatement("insert into towns(name) values (?)");
        insertTownStatement.setString(1,town);
        insertTownStatement.executeUpdate();
    }

    public static int getTownId(Connection connection, String town) throws SQLException {
        PreparedStatement checkTownStatement = connection.
                prepareStatement("select id as 'id' from towns where name = ?");
        checkTownStatement.setString(1,town);
        ResultSet rs = checkTownStatement.executeQuery();

        int townId = -2;
        while (rs.next()) {

            townId = rs.getInt("id");
        }

        return townId;
    }

    public static void insertMinion(Connection connection,
                                    String minionName,
                                    int ageMinion,
                                    int townId) throws SQLException {
        PreparedStatement insertMinionStatement = connection.
                prepareStatement("insert into minions(name,age,town_id) " +
                "values(?,?,?)");

        insertMinionStatement.setString(1, minionName);
        insertMinionStatement.setInt(2, ageMinion);
        insertMinionStatement.setInt(3, townId);

        insertMinionStatement.executeUpdate();

    }

    public static int getMinionId(Connection connection, String minionName) throws SQLException {
        PreparedStatement checkMinionStatement = connection.
                prepareStatement("select id from minions where name = ?");
        checkMinionStatement.setString(1, minionName);
        ResultSet rs = checkMinionStatement.executeQuery();

        int minionId = -2;
        while(rs.next()) {
            minionId = rs.getInt("id");
        }

        return minionId;
    }

    public static void linkMinionVillain(Connection connection, int minionId, int villainId) throws SQLException {
        PreparedStatement linkMinionVillainStatement = connection.
                prepareStatement("insert into villains_minions values(?,?)");

        linkMinionVillainStatement.setInt(1, minionId);
        linkMinionVillainStatement.setInt(2, villainId);

        linkMinionVillainStatement.executeUpdate();

    }

    public static void insertVillain(Connection connection, String villainName) throws SQLException {
        PreparedStatement insertVillainStatement = connection.
                prepareStatement("insert into villains(name, evilness_factor) values(?,?)");
        insertVillainStatement.setString(1,villainName);
        insertVillainStatement.setString(2, "evil");

        insertVillainStatement.executeUpdate();
    }

    public static int getVillainId(Connection connection, String villainName) throws SQLException {
        PreparedStatement checkVillainStatement = connection.prepareStatement("select id from villains where name = ?");
        checkVillainStatement.setString(1,villainName);

       ResultSet rs = checkVillainStatement.executeQuery();

       int villainId = -2;
       while(rs.next()) {

           villainId = rs.getInt("id");
       }

       return villainId;
    }
}
