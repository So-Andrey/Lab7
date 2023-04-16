package allForDragons;

import database.DatabaseConnection;
import java.sql.ResultSet;
import java.util.*;

public class DragonsCollection {
    /** Статическая коллекция драконов, с которой работают все команды в программе */
    private static final LinkedHashSet<Dragon> dragons = new LinkedHashSet<>();
    /** Поле, содержащее дату инициализации коллекции */
    private static final Date dateOfInitialization = new Date();
    public static LinkedHashSet<Dragon> getDragons() {
        return dragons;
    }
    /**Метод, выводящий информацию о коллекции*/
    public static void getInfo() {
        System.out.println("Тип коллекции: " + dragons.getClass().getTypeName().split(".util.")[1] + "\n" +
                "Дата инициализации: " + dateOfInitialization + "\n" +
                "Количество элементов: " + dragons.size() + "\n");
    }
    /** Метод для добавления драконов из базы данных */
    public static void putDragonsFromDB() {
        try {
            ResultSet resultSet = DatabaseConnection.executePreparedStatement("SELECT * FROM DRAGONS");
            while (resultSet.next()) {
                try {
                    long id = resultSet.getLong(1);
                    String creator = resultSet.getString(2);
                    java.sql.Date creationDate = new java.sql.Date(resultSet.getLong(3));
                    String name = resultSet.getString(4);
                    long age = resultSet.getLong(5);
                    String colorString = resultSet.getString(6);
                    Color color;
                    if (colorString.equals("null")) {
                        color = null;
                    } else {
                        color = Color.valueOf(colorString);
                    }
                    DragonType type = DragonType.valueOf(resultSet.getString(7));
                    String characterString = resultSet.getString(8);
                    DragonCharacter character;
                    if (characterString.equals("null")) {
                        character = null;
                    } else {
                        character = DragonCharacter.valueOf(characterString);
                    }
                    double eyesCount = resultSet.getDouble(9);
                    long x = resultSet.getLong(10);
                    float y = resultSet.getFloat(11);
                    DragonsCollection.getDragons().add(new Dragon(name, new Coordinates(x, y), age, color, type, character, new DragonHead(eyesCount), creator, creationDate, id));
                } catch (Exception ignored) {}
            }
        } catch (Exception ignored) {}
    }
    public static void updateFromDB() {
        dragons.clear();
        putDragonsFromDB();
    }
}