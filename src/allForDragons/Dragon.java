package allForDragons;

import database.DatabaseConnection;
import database.UserAuthentication;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Dragon implements Comparable<Dragon>{
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long age; //Значение поля должно быть больше 0, Поле не может быть null
    private Color color; //Поле может быть null
    private DragonType type; //Поле не может быть null
    private DragonCharacter character; //Поле может быть null
    private DragonHead head;
    private final String creator;
    /** Конструктор для создания нового дракона из консоли */
    public Dragon(String name, Coordinates coordinates, Long age, Color color, DragonType type, DragonCharacter character, DragonHead head) {
        this.id = setId();
        creationDate = new Date();
        this.name = name;
        this.age = age;
        this.coordinates = coordinates;
        this.character = character;
        this.head = head;
        this.type = type;
        this.color = color;
        creator = UserAuthentication.getCurrentUser();
    }
    /** Конструктор для создания нового дракона из базы данных */
    public Dragon(String name, Coordinates coordinates, Long age, Color color, DragonType type, DragonCharacter character, DragonHead head, String creator, Date creationDate, Long id) {
        this.id = id;
        this.creationDate = creationDate;
        this.name = name;
        this.age = age;
        this.coordinates = coordinates;
        this.character = character;
        this.head = head;
        this.type = type;
        this.color = color;
        this.creator = creator;
    }
    /** Метод для генерации id дракона с использованием средств базы данных */
    private long setId() {
        long newId = 0;
        ResultSet resultSet = DatabaseConnection.executePreparedStatement("SELECT nextval(?)", "id");
        try {
            resultSet.next();
            newId = resultSet.getLong(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return newId;
    }
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public Long getAge() {
        return age;
    }
    public Color getColor() {
        return color;
    }
    public DragonType getType() {
        return type;
    }
    public DragonCharacter getCharacter() {
        return character;
    }
    public long getCreationTime() {
        return creationDate.getTime();
    }
    public DragonHead getHead() {
        return head;
    }
    public String getCreator() {
        return creator;
    }
    @Override
    public String toString() {
        return "id дракона: " + id + "\n" +
                "Имя: " + name + "\n" +
                "Возраст: " + age + "\n" +
                "Тип: " + type + "\n" +
                "Цвет: " + color + "\n" +
                "Характер: " + character + "\n" +
                "Количество глаз: " + head.getEyesCount() + "\n" +
                "Координаты: (" + coordinates.getX() + "; " + coordinates.getY() + ")\n" +
                "Создатель: " + creator + "\n" +
                "Дата и время создания: " + creationDate + "\n";
    }
    /** Переопределение метода compareTo для сравнения драконов (по возрасту) */
    @Override
    public int compareTo(Dragon dragon) {
        return (int)Math.signum(this.age - dragon.getAge());
    }
}