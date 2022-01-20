package com.dido.main;

import entities.User;
import orm.Column;
import orm.Connector;
import orm.EntityManager;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class MainEntities {

    public static void main(String[] args) throws SQLException, NoSuchFieldException, IllegalAccessException, InstantiationException, ParseException {
	// write your code here

        Connector.createConnection("root","admin007",
                "test_db");
        Connection connection = Connector.getConnection();
        EntityManager<User> entityManager = new EntityManager<>(connection);

        User user = new User("Patrick","Bamford",30,new Date());

//        user.setId(1);

        System.out.println(entityManager.getTableName(user.getClass()));

        List<Field> columnsList = entityManager.getColumns(user.getClass());

        for (Field column : columnsList) {
            try {
                Column columnAnnotation = column.getAnnotation(Column.class);

                System.out.println(columnAnnotation.name());
                column.setAccessible(true);
                System.out.println(column.getType().getSimpleName());
                System.out.println(column.get(user));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if (!checkDBTableExists(connection)) {
            entityManager.doCreate(user.getClass());
        }

        entityManager.doAlter(user.getClass());

        boolean persisted = entityManager.persist(user);

        System.out.println("Is user persisted? "+ persisted);

        User foundFirstUser = entityManager.findFirst(User.class, " age > 18");

        System.out.println(foundFirstUser);

        List<User> usersList = (List<User>) entityManager.find(User.class,
                " convert(right(registration_date,4),decimal) > 2010 AND age >= 18 ");

        usersList.forEach(e -> System.out.println(e));

        entityManager.doDelete(user.getClass(),"age <= 10");

    }

    public static boolean checkDBTableExists(Connection connection) throws SQLException {

        String query = "select count(*) as users_found " +
                "from information_schema.tables where table_name like 'users' and table_schema = 'test_db';";
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(query);

        rs.next();
        int found = rs.getInt("users_found");

        return found == 1 ? true : false;
    }
}
