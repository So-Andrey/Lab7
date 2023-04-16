package database;

import java.sql.*;

public class DatabaseConnection {
    private static Connection connection;
    private static synchronized void tryConnection() {
        try {
            connection = DriverManager.getConnection(DatabasePropertiesGetter.getUrl(), DatabasePropertiesGetter.getUser(), DatabasePropertiesGetter.getPassword());
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }
    public static void executeStatement(String query) {
        tryConnection();
        try {
            connection.createStatement().execute(query);
            connection.close();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }
    public static ResultSet executePreparedStatement(String sqlRequest, String... values) {
        tryConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);
            for (int i = 0; i < values.length; ++i) {
                preparedStatement.setString(i + 1, values[i]);
            }
            return preparedStatement.executeQuery();
        } catch (SQLException sqlException) {
            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException sqlException) {
                System.out.println(sqlException.getMessage());
            }
        }
    }
    public static void createTablesIfNotExist() {
        try {
            if (executePreparedStatement("SELECT * FROM DRAGONS") == null) {
                executeStatement("CREATE TABLE DRAGONS (id bigint NOT NULL, creator text NOT NULL, creationDate bigint NOT NULL, name text NOT NULL, age bigint NOT NULL, color text NOT NULL, type text NOT NULL, character text NOT NULL, eyesCount double precision NOT NULL, x bigint NOT NULL, y double precision NOT NULL)");
            }
            if (executePreparedStatement("SELECT * FROM USERS") == null) {
                executeStatement("CREATE TABLE USERS (login text  NOT NULL, hash text NOT NULL, salt text NOT NULL)");
            }
            if (executePreparedStatement("SELECT * FROM id") == null) {
                ResultSet resultSet = executePreparedStatement("SELECT id FROM DRAGONS");
                long maxId = 1;
                while (resultSet.next()) {
                    maxId = Long.max(resultSet.getLong(1), maxId);
                }
                executeStatement("CREATE SEQUENCE id START " + maxId);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }
}