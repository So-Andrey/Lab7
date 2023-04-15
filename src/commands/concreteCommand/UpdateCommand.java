package commands.concreteCommand;

import allForDragons.*;
import commands.Command;
import commands.CommandArgsChecker;
import commands.Invoker;
import exceptions.IllegalValueOfXException;
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
            String name = scanner.nextLine();
            if (!name.trim().isEmpty()) {
                dragon.setName(name);
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
            String string = scanner.nextLine();
            if (string.matches("([-+]?\\d+)")) {
                long age = Long.parseLong(string);
                dragon.setAge(age);
                i = false;
            } else {
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
            String type = scanner.nextLine();
            if (type.matches("[1-3]") || type.equals("WATER") || type.equals("UNDERGROUND") || type.equals("FIRE")) {
                switch (type) {
                    case "1", "WATER" -> dragon.setType(DragonType.WATER);
                    case "2", "UNDERGROUND" -> dragon.setType(DragonType.UNDERGROUND);
                    case "3", "FIRE" -> dragon.setType(DragonType.FIRE);
                }
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
            String color = scanner.nextLine();
            if (color.matches("[1-3]") || color.equals("GREEN") || color.equals("ORANGE") || color.equals("BROWN") || color.isEmpty()) {
                switch (color) {
                    case "1", "GREEN" -> dragon.setColor(Color.GREEN);
                    case "2", "ORANGE" -> dragon.setColor(Color.ORANGE);
                    case "3", "BROWN" -> dragon.setColor(Color.BROWN);
                    case "" -> dragon.setColor(null);
                }
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
            String character = scanner.nextLine();
            if (character.matches("[1-4]") || character.equals("CUNNING") || character.equals("WISE") || character.equals("CHAOTIC_EVIL") || character.equals("FICKLE")) {
                switch (character) {
                    case "1", "CUNNING" -> dragon.setCharacter(DragonCharacter.CUNNING);
                    case "2", "WISE" -> dragon.setCharacter(DragonCharacter.WISE);
                    case "3", "CHAOTIC_EVIL" -> dragon.setCharacter(DragonCharacter.CHAOTIC_EVIL);
                    case "4", "FICKLE" -> dragon.setCharacter(DragonCharacter.FICKLE);
                }
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
            String string = scanner.nextLine();
            try {
                dragon.getHead().setEyesCount(Double.parseDouble(string));
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
        dragon.getCoordinates().setX(getNewXCoordinate(scanner));
        dragon.getCoordinates().setY(getNewYCoordinate(scanner));
    }
    /**Метод, получающий новую координату х
     * @return возвращает координату х*/
    private long getNewXCoordinate(Scanner scanner) {
        long x = 0;
        boolean i = true;
        while (i) {
            System.out.println("Введите новую координату X дракона");
            String xString = scanner.nextLine();
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
            String yString = scanner.nextLine();
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
            s = scanner.nextLine();
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
            Scanner scanner = new Scanner(System.in);
            String s = requestInput(scanner);
            if (!(s.matches("[1-7]"))) {
                System.out.println("Неверный параметр");
            } else {
                fieldsUpdater(s, scanner, matchedDragon.get(0));
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
                    fieldsUpdaterFromFile(parameters[0], parameters[1], matchedDragon.get(0), scanner);
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
            System.out.println("Параметр дракона успешно обновлён");
        } else {
            throw new InputMismatchException();
        }
    }
    /** Метод, обновляющий имя дракона на новое из файла */
    private static void updateNameFromFile(String name, Dragon dragon) {
        if (!name.trim().isEmpty()) {
            dragon.setName(name);
        } else {
            throw new InputMismatchException();
        }
    }
    /** Метод, обновляющий возраст дракона на новый из файла */
    private static void updateAgeFromFile(String ageString, Dragon dragon) {
        try {
            long age = Long.parseLong(ageString);
            dragon.setAge(age);
        } catch (NumberFormatException numberFormatException) {
            throw new InputMismatchException();
        }
    }
    /** Метод, обновляющий тип дракона на новый из файла */
    private static void updateTypeFromFile(String type, Dragon dragon) {
        if (type.matches("[1-3]") || type.equals("WATER") || type.equals("UNDERGROUND") || type.equals("FIRE")) {
            switch (type) {
                case "1", "WATER" -> dragon.setType(DragonType.WATER);
                case "2", "UNDERGROUND" -> dragon.setType(DragonType.UNDERGROUND);
                case "3", "FIRE" -> dragon.setType(DragonType.FIRE);
            }
        } else {
            throw new InputMismatchException();
        }
    }
    /** Метод, обновляющий цвет дракона на новый из файла */
    private static void updateColorFromFile(String color, Dragon dragon) {
        if (color.matches("[1-3]") || color.equals("GREEN") || color.equals("ORANGE") || color.equals("BROWN") || color.isEmpty()) {
            switch (color) {
                case "1", "GREEN" -> dragon.setColor(Color.GREEN);
                case "2", "ORANGE" -> dragon.setColor(Color.ORANGE);
                case "3", "BROWN" -> dragon.setColor(Color.BROWN);
                case "" -> dragon.setColor(null);
            }
        } else {
            throw new InputMismatchException();
        }
    }
    /** Метод, обновляющий характер дракона на новый из файла */
    private static void updateCharacterFromFile(String character, Dragon dragon) {
        if (character.matches("[1-4]") || character.equals("CUNNING") || character.equals("WISE") || character.equals("CHAOTIC_EVIL") || character.equals("FICKLE")) {
            switch (character) {
                case "1", "CUNNING" -> dragon.setCharacter(DragonCharacter.CUNNING);
                case "2", "WISE" -> dragon.setCharacter(DragonCharacter.WISE);
                case "3", "CHAOTIC_EVIL" -> dragon.setCharacter(DragonCharacter.CHAOTIC_EVIL);
                case "4", "FICKLE" -> dragon.setCharacter(DragonCharacter.FICKLE);
            }
        } else {
            throw new InputMismatchException();
        }
    }
    /** Метод, обновляющий количество глаз дракона на новое из файла */
    private static void updateHeadFromFile(String eyesCount, Dragon dragon) {
        try {
            dragon.getHead().setEyesCount(Double.parseDouble(eyesCount));
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
        dragon.getCoordinates().setX(x);
        try {
            String yString = scanner.nextLine();
            try {
                float y = Float.parseFloat(yString);
                dragon.getCoordinates().setY(y);
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
