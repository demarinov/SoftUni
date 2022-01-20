package orm;

import java.sql.SQLException;
import java.text.ParseException;

public interface DbContext<E> {
    boolean persist(E entity) throws IllegalAccessException, SQLException;
    Iterable<E> find(Class<E> table) throws SQLException, IllegalAccessException, InstantiationException, ParseException;
    Iterable<E> find(Class<E> table, String where) throws SQLException, InstantiationException, IllegalAccessException, ParseException;
    E findFirst(Class<E> table) throws SQLException, IllegalAccessException, InstantiationException, ParseException;
    E findFirst(Class<E> table, String where) throws SQLException, InstantiationException, IllegalAccessException, ParseException;

    <E2> void doDelete(Class table, String where) throws SQLException;

    <E2> void doCreate(Class entity) throws SQLException;

    <E2> void doAlter(Class entity) throws SQLException;
}
