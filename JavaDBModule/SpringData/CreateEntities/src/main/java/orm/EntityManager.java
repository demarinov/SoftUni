package orm;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class EntityManager<E> implements DbContext<E> {

    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean persist(E entity) throws IllegalAccessException, SQLException {

        Field primary = this.getId(entity.getClass());
        primary.setAccessible(true);
        Object value = primary.get(entity);

        if (value == null || (int)value<=0) {
            return this.doInsert(entity, primary);
        }

        return this.doUpdate(entity, primary);
    }

    private boolean doUpdate(E entity, Field primary) throws SQLException, IllegalAccessException {

        String tableName = this.getTableName(entity.getClass());
        String query = "UPDATE "+tableName+" SET ";

        List<Field> columnsList = getColumns(entity.getClass());

        Map<String, Object> columnMap = new LinkedHashMap<>();
        List<String> columnTypes = new ArrayList<>();

        for (Field column : columnsList) {
            try {
                Column columnAnnotation = column.getAnnotation(Column.class);
                column.setAccessible(true);
                columnTypes.add(column.getType().getSimpleName());
                columnMap.putIfAbsent(columnAnnotation.name(),column.get(entity));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        int counter = 0;
        for (Map.Entry<String, Object> columnEntry : columnMap.entrySet()) {
            query += columnEntry.getKey();
            query += "=";

            switch(columnTypes.get(counter)) {
                case "int":
                    query += columnEntry.getValue();
                    break;
                default:
                    // treat as String
                    query += "'";
                    query += columnEntry.getValue();
                    query += "'";
                    break;

            }

            query += ",";

            counter++;

        }

        query = query.substring(0, query.length()-1);
        query += " where id = "+ primary.get(entity);

        return connection.prepareStatement(query).execute();
    }

    public List<Field> getColumns(Class entityClass) {

        List<Field> columnList = Arrays.stream(entityClass.getDeclaredFields())
        .filter(f -> f.isAnnotationPresent(Column.class))
        .collect(Collectors.toList());

        return columnList;
    }

    public String getTableName(Class tableClass) {

        Annotation tableAnnotation = tableClass.getAnnotation(Entity.class);

        return ((Entity)tableAnnotation).name();
    }

    private boolean doInsert(E entity, Field primary) throws SQLException {

        String tableName = this.getTableName(entity.getClass());
        String query = "INSERT INTO "+tableName+"(";

        List<Field> columnsList = getColumns(entity.getClass());

        Map<String, Object> columnMap = new LinkedHashMap<>();
        List<String> columnTypes = new ArrayList<>();

        for (Field column : columnsList) {
            try {
                Column columnAnnotation = column.getAnnotation(Column.class);
                column.setAccessible(true);
                columnTypes.add(column.getType().getSimpleName());
                columnMap.putIfAbsent(columnAnnotation.name(),column.get(entity));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        StringBuilder queryColumns = new StringBuilder();
        StringBuilder queryValues = new StringBuilder();
        int counter = 0;
        for (Map.Entry<String, Object> columnEntry : columnMap.entrySet()) {
            queryColumns.append(columnEntry.getKey());
            queryColumns.append(",");

            switch(columnTypes.get(counter)) {
                case "int":
                    queryValues.append(columnEntry.getValue());
                    break;
                default:
                    // treat as String
                    queryValues.append("'");
                    queryValues.append(columnEntry.getValue());
                    queryValues.append("'");
                    break;

            }

            queryValues.append(",");

            counter++;

        }

        query += queryColumns.substring(0, queryColumns.length()-1);
        query += ") values (";
        query += queryValues.substring(0, queryValues.length()-1);
        query += ");";

        return connection.prepareStatement(query).execute();
    }

    @Override
    public Iterable<E> find(Class<E> table) throws SQLException, IllegalAccessException, InstantiationException, ParseException {
        List<E> results = new ArrayList<>();

        Statement statement = connection.createStatement();
        String query = "Select * from "+this.getTableName(table) +";";

        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            E entity = table.newInstance();
            this.fillEntity(table, resultSet, entity);
            results.add(entity);
        }

        return results;

    }

    @Override
    public Iterable<E> find(Class<E> table, String where) throws SQLException, InstantiationException, IllegalAccessException, ParseException {
        List<E> results = new ArrayList<>();

        Statement statement = connection.createStatement();
        String query = "Select * from "+this.getTableName(table) +
                " WHERE 1 "+ (where != null ? " AND "+where:"")+";";

        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            E entity = table.newInstance();
            this.fillEntity(table, resultSet, entity);
            results.add(entity);
        }

        return results;
    }

    @Override
    public E findFirst(Class<E> table) throws SQLException, IllegalAccessException, InstantiationException, ParseException {
        Statement statement = connection.createStatement();
        String query = "Select * from "+this.getTableName(table)+ " LIMIT 1;";

        ResultSet resultSet = statement.executeQuery(query);
        E entity = table.newInstance();

        resultSet.next();
        this.fillEntity(table, resultSet, entity);
        return entity;

    }

    @Override
    public E findFirst(Class<E> table, String where) throws SQLException, InstantiationException, IllegalAccessException, ParseException {

        Statement statement = connection.createStatement();
        String query = "Select * from "+this.getTableName(table) +
                " WHERE 1 "+ (where != null ? " AND "+where:"")+" LIMIT 1;";

        ResultSet resultSet = statement.executeQuery(query);
        E entity = table.newInstance();
        resultSet.next();
        this.fillEntity(table, resultSet, entity);
        return entity;
    }

    @Override
    public <E> void doDelete(Class table, String where) throws SQLException {
        String query = "delete from "+this.getTableName(table) +
                " WHERE "+ (where != null ? where:"")+";";

        this.connection.prepareStatement(query).execute();
    }

    private void fillEntity(Class<E> table, ResultSet resultSet, E entity) throws SQLException, IllegalAccessException, ParseException {
        Field[] fields = table.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            if ("id".equals(name)) {
                this.fillField(field, entity, resultSet, name);
            } else {
                this.fillField(field, entity, resultSet, field.getAnnotation(Column.class).name());
            }
        }
    }

    private void fillField(Field field, E entity, ResultSet resultSet, String name) throws SQLException, IllegalAccessException, ParseException {

        field.setAccessible(true);

        if (field.getType() == int.class || field.getType() == Integer.class) {
            field.set(entity,resultSet.getInt(name));
        } else if (field.getType() == long.class || field.getType() == Long.class) {
            field.set(entity, resultSet.getLong(name));
        } else if (field.getType() == String.class) {
            field.set(entity, resultSet.getString(name));
        } else if (field.getType() == Date.class) {
            SimpleDateFormat formatterDate=new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");

            field.set(entity, formatterDate.parse(resultSet.getString(name)));
        }
    }

    private Field getId(Class entity) {

        return Arrays.stream(entity.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Entity does not have primary key"));
    }

    @Override
    public <E> void doCreate(Class entity) throws SQLException {
        String tableName = this.getTableName(entity);
        String query = "CREATE TABLE "+tableName + "( ";
        String columns = "";

        Field[] fields = entity.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {

            Field field = fields[i];
            field.setAccessible(true);

            if (field.isAnnotationPresent(Id.class)) {
                columns += "id int not null primary key auto_increment";
            } else if (field.isAnnotationPresent(Column.class)) {
                String dbType = getDBType(field);
                Column name = field.getAnnotation(Column.class);
                columns += name.name() + " "+dbType;
            }

            if (i < fields.length - 1) {
                columns += ", ";
            }
        }

        query += columns + ")";

        connection.prepareStatement(query).execute();
    }

    public String getDBType(Field field) {
        field.setAccessible(true);

        String fieldType = field.getType().getSimpleName();

        switch(fieldType) {

            case "int":
            case "Integer":
                return "int";
            default:
                return "varchar(200)";
        }
    }

    @Override
    public <E> void doAlter(Class entity) throws SQLException {
        String tableName = this.getTableName(entity);
        String query = "ALTER TABLE "+tableName;

        // Get entity fields
        Field[] fields = entity.getDeclaredFields();

        String columns = "";
        // Check if field exists in table
        for (int i = 0; i < fields.length; i++) {

            Field field = fields[i];
            if (field.isAnnotationPresent(Id.class)) {
                // skip the id
                continue;
            }

            if (!checkIfFieldExistsInDatabase(entity, field)) {
                // alter
                String fieldName = field.getAnnotation(Column.class).name();
                String fieldType = getDBType(field);

                columns += " ADD "+fieldName+ " "+fieldType+",";

            }
        }

        // Add new table columns to query
        if (!columns.isEmpty()) {
            query += columns.substring(0, columns.length() - 1) + ";";
        }

        this.connection.prepareStatement(query).execute();
    }

    private boolean checkIfFieldExistsInDatabase(Class entity, Field field) throws SQLException {
        String fieldName = field.getAnnotation(Column.class).name();


        // Get table name
        String tableName = this.getTableName(entity);
        // Get columns from information schema
        ResultSet rs =
                connection.
                        prepareStatement("select count(*) as column_found " +
                                "from information_schema.columns"+
                                " where table_name = '"+tableName + "' and "+
                                "table_schema = 'test_db'"+" and column_name = '"+fieldName+"';")
                        .executeQuery();
        rs.next();
        int columnFound = rs.getInt("column_found");
        // Perform checks for existence of field
        if (columnFound == 0) {
            return false;
        }

        return true;
    }
}

