package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/** Класс, содержащий статический блок кода для получения данных для подключения к базе данных из файла "db.cfg" */
public class DatabasePropertiesGetter {
    /** URL базы данных */
    private static String url;
    /** Имя пользователя базы данных */
    private static String user;
    /** Пароль пользователя базы данных */
    private static String password;
    protected static String getUrl() {
        return url;
    }
    protected static String getUser() {
        return user;
    }
    protected static String getPassword() {
        return password;
    }
    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(System.getProperty("user.dir") + System.getProperty("file.separator") + "db.cfg"));
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }
}