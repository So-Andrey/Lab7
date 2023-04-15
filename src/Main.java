import commands.Invoker;
import database.UserAuthentication;

public class Main {
    public static void main(String[] args) {
        UserAuthentication.userAuthentication();
        if (UserAuthentication.getCurrentUser() != null) {
            Invoker.invoker();
        } else {
            System.out.println("Выполнение команд неавторизованными пользователями запрещено, работа программы остановлена");
        }
    }
}