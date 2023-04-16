package database;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class UserAuthentication {
    /** Переменная для хранения имени текущего пользователя */
    private static String currentUser = null;
    public static String getCurrentUser() {
        return currentUser;
    }
    /** Метод для аутентификации пользователя
     * @see DatabaseConnection#executePreparedStatement(String, String...)
     * @see UserAuthentication#userRegistration(String, Scanner)
     * @see UserAuthentication#userLoggingIn(String, Scanner) */
    public static void userAuthentication() {
        System.out.println("Введите имя пользователя");
        Scanner scanner = new Scanner(System.in);
        String login = scanner.nextLine().trim();
        try {
            if (Objects.requireNonNull(DatabaseConnection.executePreparedStatement("SELECT * FROM USERS WHERE login = ?", login)).next()) {
                userLoggingIn(login, scanner);
            } else {
                System.out.println("Пользователя с таким именем не существует, если хотите зарегистрироваться, введите 1, если выйти - нажмите любую клавишу");
                if (scanner.nextLine().trim().equals("1")) userRegistration(login, scanner);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /** Метод для регистрации нового пользователя
     * @param login имя нового пользователя
     * @param scanner сканер из консоли для получения пароля нового пользователя
     * @see DatabaseConnection#executePreparedStatement(String, String...)
     * @see MessageDigest#digest(byte[]) метод для получения хэша
     * @see UserAuthentication#saltGetter() */
    private static void userRegistration(String login, Scanner scanner) {
        System.out.println("Введите пароль");
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        String salt = saltGetter();
        byte[] hash = md.digest(("*63&^mVLC(#" + scanner.nextLine().trim() + salt).getBytes(StandardCharsets.UTF_8));
        DatabaseConnection.executePreparedStatement("INSERT INTO USERS (login, hash, salt) VALUES (?, ?, ?)", login, Arrays.toString(hash), salt);
        System.out.println("Вы успешно прошли регистрацию");
        currentUser = login;
    }
    /** Метод для вхождения существующего пользователя в программу
     * @param login имя пользователя
     * @param scanner сканер из консоли для получения пароля пользователя
     * @see DatabaseConnection#executePreparedStatement(String, String...)
     * @see MessageDigest#digest(byte[]) метод для получения хэша
     * @see UserAuthentication#saltGetter() */
    private static void userLoggingIn(String login, Scanner scanner) {
        System.out.println("Введите пароль");
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            ResultSet resultSetSalt = DatabaseConnection.executePreparedStatement("SELECT salt FROM USERS WHERE login = ?", login);
            resultSetSalt.next();
            String salt = resultSetSalt.getString(1);
            ResultSet resultSetHash = DatabaseConnection.executePreparedStatement("SELECT hash FROM USERS WHERE login = ?", login);
            resultSetHash.next();
            byte[] hash = md.digest(("*63&^mVLC(#" + scanner.nextLine().trim() + salt).getBytes(StandardCharsets.UTF_8));
            if (Arrays.toString(hash).equals(resultSetHash.getString(1))) {
                System.out.println("Вы успешно прошли аутентификацию");
                currentUser = login;
            } else {
                System.out.println("Неверный пароль");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /** Метод для получения "соли" для более безопасного хэширования паролей
     * @return возвращает случайный набор из 20 символов */
    private static String saltGetter() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            stringBuilder.append((char) new Random().nextInt(33, 126));
        }
        return stringBuilder.toString();
    }
}
