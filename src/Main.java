import allForDragons.*;
import commands.Invoker;
import database.DatabaseConnection;
import database.UserAuthentication;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        try {
            DatabaseConnection.createTablesIfNotExist();
            UserAuthentication.userAuthentication();
            if (UserAuthentication.getCurrentUser() != null) {
                DragonsCollection.putDragonsFromDB();
                System.out.println("Из базы данных добавлено объектов в коллекцию: " + DragonsCollection.getDragons().size());
                Invoker.invoker();
            } else {
                System.out.println("Выполнение команд неавторизованными пользователями запрещено, работа программы остановлена");
            }
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println("Неверный ввод, перезапустите программу");
        }
    }
}