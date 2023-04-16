package commands.concreteCommand;

import allForDragons.*;
import commands.Command;
import commands.CommandArgsChecker;
import commands.Invoker;
import database.DatabaseConnection;
import database.UserAuthentication;
import exceptions.IllegalValueOfXException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UpdateCommand implements Command {
    /**Метод, обновляющий имя дракона
     * @param dragon дракон, у которого меняется имя*/
    private void updateName(Scanner scanner, Dragon dragon) {
        boolean i = true;
        while (i) {
            System.out.println("Введите имя дракона");
            String name = scanner.nextLine().trim();
            if (!name.trim().isEmpty()) {
                DatabaseConnection.executeStatement("update dragons set name = '" + name + "' where id = " + dragon.getId());
                i = false;
            } else {
                System.out.println("Неверный тип данных");
            }
        }
    }
    /**Метод, обновляющий возраст дракона
     * @param dragon дракон, у которого меняется возраст*/
    private void updateAge(Scanner scanner, Dragon dragon) {
        boolean i = true;
        while (i) {
            System.out.println("Введите новый возраст дракона");
            try {
                DatabaseConnection.executeStatement("update dragons set age = '" + Long.parseLong(scanner.nextLine().trim()) + "' where id = " + dragon.getId());
                i = false;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Неверный тип данных");
            }
        }
    }
    /**Метод, обновляющий тип дракона
     * @param dragon дракон, у которого меняется тип*/
    private void updateType(Scanner scanner, Dragon dragon) {
        boolean i = true;
        while (i) {
            System.out.println("Введите новый тип дракона (Цифру или полное название) 1 - WATER, 2 - UNDERGROUND, 3 - FIRE");
            String type = scanner.nextLine().trim();
            if (type.matches("[1-3]") || type.equals("WATER") || type.equals("UNDERGROUND") || type.equals("FIRE")) {
                switch (type) {
                    case "1" -> type = "WATER";
                    case "2" -> type = "UNDERGROUND";
                    case "3" -> type = "FIRE";
                }
                DatabaseConnection.executeStatement("update dragons set type = '" + type + "' where id = " + dragon.getId());
                i = false;
            } else {
                System.out.println("Неверный тип данных");
            }
        }
    }
    /**Метод, обновляющий цвет дракона
     * @param dragon дракон, у которого меняется цвет*/
    private void updateColor(Scanner scanner, Dragon dragon) {
        boolean i = true;
        while (i) {
            System.out.println("Введите новый цвет дракона (Цифру или полное название) 1 - GREEN, 2 - ORANGE, 3 - BROWN");
            String color = scanner.nextLine().trim();
            if (color.matches("[1-3]") || color.equals("GREEN") || color.equals("ORANGE") || color.equals("BROWN") || color.isEmpty()) {
                switch (color) {
                    case "1" -> color = "GREEN";
                    case "2" -> color = "ORANGE";
                    case "3" -> color = "BROWN";
                    case "" -> color = "null";
                }
                DatabaseConnection.executeStatement("update dragons set color = '" + color + "' where id = " + dragon.getId());
                i = false;
            } else {
                System.out.println("Неверный тип данных");
            }
        }
    }
    /**Метод, обновляющий характер дракона
     * @param dragon дракон, у которого меняется характер*/
    private void updateCharacter(Scanner scanner, Dragon dragon) {
        boolean i = true;
        while (i) {
            System.out.println("Введите новый характер дракона (Цифру или полное название) 1 - CUNNING, 2 - WISE, 3 - CHAOTIC_EVIL, 4 - FICKLE");
            String character = scanner.nextLine().trim();
            if (character.matches("[1-4]") || character.equals("CUNNING") || character.equals("WISE") || character.equals("CHAOTIC_EVIL") || character.equals("FICKLE") || character.isEmpty()) {
                switch (character) {
                    case "1" -> character = "CUNNING";
                    case "2" -> character = "WISE";
                    case "3" -> character = "CHAOTIC_EVIL";
                    case "4" -> character = "FICKLE";
                    case "" -> character = "null";
                }
                DatabaseConnection.executeStatement("update dragons set character = '" + character + "' where id = " + dragon.getId());
                i = false;
            } else {
                System.out.println("Неверный тип данных");
            }
        }
    }
    /**Метод, обновляющий голову (количество глаз) дракона
     * @param dragon дракон, у которого меняется голова (количество глаз)*/
    private void updateHead(Scanner scanner, Dragon dragon) {
        boolean i = true;
        while (i) {
            System.out.println("Введите новое количество глаз дракона");
            String string = scanner.nextLine().trim();
            try {
                DatabaseConnection.executeStatement("update dragons set eyescount = '" + Double.parseDouble(string) + "' where id = " + dragon.getId());
                i = false;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Неверный тип данных");
            }
        }
    }
    /**Метод, обновляющий координаты дракона
     * @param dragon дракон, у которого меняется координаты
     * @see UpdateCommand#getNewXCoordinate(Scanner)
     * @see UpdateCommand#getNewYCoordinate(Scanner) */
    private void updateCoordinates(Scanner scanner, Dragon dragon) {
        DatabaseConnection.executeStatement("update dragons set x = '" + getNewXCoordinate(scanner) + "' where id = " + dragon.getId());
        DatabaseConnection.executeStatement("update dragons set y = '" + getNewYCoordinate(scanner) + "' where id = " + dragon.getId());
    }
    /**Метод, получающий новую координату х
     * @return возвращает координату х*/
    private long getNewXCoordinate(Scanner scanner) {
        long x = 0;
        boolean i = true;
        while (i) {
            System.out.println("Введите новую координату X дракона");
            String xString = scanner.nextLine().trim();
            try {
                if (xString.matches("([-+]?\\d+)")) {
                    x = Long.parseLong(xString);
                    if (x > 610) {
                        throw new IllegalValueOfXException();
                    } else {
                        i = false;
                    }
                } else {
                    System.out.println("Неверный тип данных");
                }
            } catch (IllegalValueOfXException illegalValueOfXException) {
                System.out.println(illegalValueOfXException.getMessage());
            }
        }
        return x;
    }
    /**Метод, получающий новую координату у
     * @return возвращает координату у*/
    private float getNewYCoordinate(Scanner scanner) {
        float y = 0;
        boolean i = true;
        while (i) {
            System.out.println("Введите новую координату Y дракона");
            String yString = scanner.nextLine().trim();
            try {
                y = Float.parseFloat(yString);
                i = false;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Неверный тип данных");
            }
        }
        return y;
    }
    /**Метод, выводящий варианты параметров для изменения и возвращающий один из них
     * @return возвращает цифру, обозначающую параметр для изменения*/
    private String requestInput(Scanner scanner) {
        boolean i = true;
        String s = "";
        while (i) {
            System.out.println("""
                                    Выберите параметр дракона, который хотите изменить:
                                    Имя - введите  1
                                    Возраст - введите 2
                                    Тип - введите 3
                                    Цвет - введите 4
                                    Характер - введите 5
                                    Количество глаз - введите 6
                                    Координаты - введите 7""");
            s = scanner.nextLine().trim();
            i = false;
        }
        return s;
    }
    /**Метод, вызывающий нужный метод для обновления определенного параметра
     * @param dragon дракон, параметр которого нужно изменить
     * @param s число, обозначающее, какую характеристику дракона надо изменить
     * @see UpdateCommand#updateName(Scanner, Dragon)
     * @see UpdateCommand#updateAge(Scanner, Dragon)
     * @see UpdateCommand#updateType(Scanner, Dragon)
     * @see UpdateCommand#updateColor(Scanner, Dragon)
     * @see UpdateCommand#updateCharacter(Scanner, Dragon)
     * @see UpdateCommand#updateHead(Scanner, Dragon)
     * @see UpdateCommand#updateCoordinates(Scanner, Dragon) */
    private void fieldsUpdater(String s, Scanner scanner, Dragon dragon) {
        switch (s) {
            case "1" -> updateName(scanner, dragon);
            case "2" -> updateAge(scanner, dragon);
            case "3" -> updateType(scanner, dragon);
            case "4" -> updateColor(scanner, dragon);
            case "5" -> updateCharacter(scanner, dragon);
            case "6" -> updateHead(scanner, dragon);
            case "7" -> updateCoordinates(scanner, dragon);
        }
        DragonsCollection.updateFromDB();
        System.out.println("Параметр дракона успешно обновлён");
    }
    /**Метод, обновляющий данные о драконе
     * @param id id дракона, параметр которого нужно изменить
     * @see UpdateCommand#requestInput(Scanner)
     * @see UpdateCommand#fieldsUpdater(String, Scanner, Dragon) */
    private void updateDragon(long id) {
        List<Dragon> matchedDragon = DragonsCollection.getDragons().stream().filter(dragon -> dragon.getId() == id).toList();
        if (matchedDragon.isEmpty()) {
            System.out.println("Такого дракона не существует");
        } else {
            try {
                ResultSet resultSet = DatabaseConnection.executePreparedStatement("select * from dragons where id = " + matchedDragon.get(0).getId() + " and creator = '" + UserAuthentication.getCurrentUser() + "'");
                resultSet.next();
                resultSet.getLong(1);
                Scanner scanner = new Scanner(System.in);
                String s = requestInput(scanner);
                if (!(s.matches("[1-7]"))) {
                    System.out.println("Неверный параметр");
                } else {
                    fieldsUpdater(s, scanner, matchedDragon.get(0));
                }
            } catch (SQLException sqlException) {
                System.out.println("Вы не можете изменить дракона созданного другим пользователем");
            }
        }
    }
    /**Метод, исполняющий команду
     * @see UpdateCommand#updateDragon(long) */
    @Override
    public void execute() {
        CommandArgsChecker.commandArgsChecker(1);
        try {
            if (!DragonsCollection.getDragons().isEmpty()) {
                updateDragon(Long.parseLong(Invoker.getSplit()[1]));
            } else {
                System.out.println("Такого дракона не существует");
            }
        } catch (NumberFormatException ex) {
            throw new NullPointerException();
        }
    }
    /** Метод, обновляющий дракона параметрами из файла
     * @see UpdateCommand#parametersReader(Scanner)
     * @see UpdateCommand#fieldsUpdaterFromFile(String, String, Dragon, Scanner) */
    protected static void updaterFromFile(Scanner scanner) {
        String[] parameters = parametersReader(scanner);
        try {
            if (Invoker.getSplit().length != 2) throw new InputMismatchException();
            try {
                List<Dragon> matchedDragon = DragonsCollection.getDragons().stream().filter(dragon -> dragon.getId() == Long.parseLong(Invoker.getSplit()[1])).toList();
                if (matchedDragon.isEmpty()) {
                    throw new InputMismatchException();
                } else {
                    try {
                        ResultSet resultSet = DatabaseConnection.executePreparedStatement("select * from dragons where id = " + matchedDragon.get(0).getId() + " and creator = '" + UserAuthentication.getCurrentUser() + "'");
                        resultSet.next();
                        resultSet.getLong(1);
                        fieldsUpdaterFromFile(parameters[0], parameters[1], matchedDragon.get(0), scanner);
                    } catch (SQLException sqlException) {
                        throw new InputMismatchException();
                    }
                }
            } catch (NumberFormatException numberFormatException) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException ignored) {}
    }
    /** Метод, считывающий обновляемое поле из фала
     * @return возвращает массив, состоящий из номера обновляемого параметра и его нового значения */
    private static String[] parametersReader(Scanner scanner) {
        String[] parameters = new String[2];
        for (int i = 0; i < parameters.length; ++i) {
            try {
                parameters[i] = scanner.nextLine();
                if (parameters[i].trim().isEmpty()) parameters[i] = null;
            } catch (NoSuchElementException noSuchElementException) {
                parameters[i] = null;
            }
        }
        return parameters;
    }
    /** Метод, обновляющий выбранное поле из файла
     * @see UpdateCommand#updateNameFromFile(String, Dragon)
     * @see UpdateCommand#updateAgeFromFile(String, Dragon)
     * @see UpdateCommand#updateTypeFromFile(String, Dragon)
     * @see UpdateCommand#updateColorFromFile(String, Dragon)
     * @see UpdateCommand#updateCharacterFromFile(String, Dragon)
     * @see UpdateCommand#updateHeadFromFile(String, Dragon)
     * @see UpdateCommand#updateCoordinatesFromFile(String, Scanner, Dragon) */
    private static void fieldsUpdaterFromFile(String parameter, String newValue, Dragon dragon, Scanner scanner) {
        if (parameter.matches(("[1-7]"))) {
            switch (parameter) {
                case "1" -> updateNameFromFile(newValue, dragon);
                case "2" -> updateAgeFromFile(newValue, dragon);
                case "3" -> updateTypeFromFile(newValue, dragon);
                case "4" -> updateColorFromFile(newValue, dragon);
                case "5" -> updateCharacterFromFile(newValue, dragon);
                case "6" -> updateHeadFromFile(newValue, dragon);
                case "7" -> updateCoordinatesFromFile(newValue, scanner, dragon);
            }
            DragonsCollection.putDragonsFromDB();
            System.out.println("Параметр дракона успешно обновлён");
        } else {
            throw new InputMismatchException();
        }
    }
    /** Метод, обновляющий имя дракона на новое из файла */
    private static void updateNameFromFile(String name, Dragon dragon) {
        if (!name.trim().isEmpty()) {
            DatabaseConnection.executeStatement("update dragons set name = '" + name + "' where id = " + dragon.getId());
        } else {
            throw new InputMismatchException();
        }
    }
    /** Метод, обновляющий возраст дракона на новый из файла */
    private static void updateAgeFromFile(String ageString, Dragon dragon) {
        try {
            DatabaseConnection.executeStatement("update dragons set age = '" + Long.parseLong(ageString) + "' where id = " + dragon.getId());
        } catch (NumberFormatException numberFormatException) {
            throw new InputMismatchException();
        }
    }
    /** Метод, обновляющий тип дракона на новый из файла */
    private static void updateTypeFromFile(String type, Dragon dragon) {
        if (type.matches("[1-3]") || type.equals("WATER") || type.equals("UNDERGROUND") || type.equals("FIRE")) {
            switch (type) {
                case "1" -> type = "WATER";
                case "2" -> type = "UNDERGROUND";
                case "3" -> type = "FIRE";
            }
            DatabaseConnection.executeStatement("update dragons set type = '" + type + "' where id = " + dragon.getId());
        } else {
            throw new InputMismatchException();
        }
    }
    /** Метод, обновляющий цвет дракона на новый из файла */
    private static void updateColorFromFile(String color, Dragon dragon) {
        if (color.matches("[1-3]") || color.equals("GREEN") || color.equals("ORANGE") || color.equals("BROWN") || color.isEmpty()) {
            switch (color) {
                case "1" -> color = "GREEN";
                case "2" -> color = "ORANGE";
                case "3" -> color = "BROWN";
                case "" -> color = "null";
            }
            DatabaseConnection.executeStatement("update dragons set color = '" + color + "' where id = " + dragon.getId());
        } else {
            throw new InputMismatchException();
        }
    }
    /** Метод, обновляющий характер дракона на новый из файла */
    private static void updateCharacterFromFile(String character, Dragon dragon) {
        if (character.matches("[1-4]") || character.equals("CUNNING") || character.equals("WISE") || character.equals("CHAOTIC_EVIL") || character.equals("FICKLE") || character.isEmpty()) {
            switch (character) {
                case "1" -> character = "CUNNING";
                case "2" -> character = "WISE";
                case "3" -> character = "CHAOTIC_EVIL";
                case "4" -> character = "FICKLE";
                case "" -> character = "null";
            }
            DatabaseConnection.executeStatement("update dragons set character = '" + character + "' where id = " + dragon.getId());
        } else {
            throw new InputMismatchException();
        }
    }
    /** Метод, обновляющий количество глаз дракона на новое из файла */
    private static void updateHeadFromFile(String eyesCount, Dragon dragon) {
        try {
            DatabaseConnection.executeStatement("update dragons set eyescount = '" + Double.parseDouble(eyesCount) + "' where id = " + dragon.getId());
        } catch (NumberFormatException numberFormatException) {
            throw new InputMismatchException();
        }
    }
    /** Метод, обновляющий координаты дракона на новые из файла */
    private static void updateCoordinatesFromFile(String xString, Scanner scanner, Dragon dragon) {
        try {
            Long.parseLong(xString);
        } catch (NumberFormatException numberFormatException) {
            throw new InputMismatchException();
        }
        long x = Long.parseLong(xString);
        if (x > 610) {
            throw new InputMismatchException();
        }
        try {
            String yString = scanner.nextLine();
            try {
                DatabaseConnection.executeStatement("update dragons set y = '" + Float.parseFloat(yString) + "' where id = " + dragon.getId());
                DatabaseConnection.executeStatement("update dragons set x = '" + x + "' where id = " + dragon.getId());
            } catch (NumberFormatException numberFormatException) {
                throw new InputMismatchException();
            }
        } catch (NoSuchElementException noSuchElementException) {
            throw new InputMismatchException();
        }
    }
    @Override
    public String description() {
        return "update id : обновить значение элемента коллекции, id которого равен заданному";
    }
}
