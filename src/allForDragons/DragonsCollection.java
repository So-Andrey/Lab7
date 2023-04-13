package allForDragons;

import commands.Invoker;
import java.io.File;
import java.io.FileNotFoundException;
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
    /**Метод для добавления драконов из файла в коллекцию
     * @return возвращает boolean в зависимости от того, существует файл или нет */
    public static boolean putDragonsFromFile() {
        try {
            Scanner scanner = new Scanner(new File(Invoker.getFile()));
            String name;
            long x;
            float y;
            long age;
            Color color;
            DragonType type;
            DragonCharacter character;
            double eyesCount;
            while (scanner.hasNext()) {
                try {
                    String[] dragon = scanner.nextLine().split(", ");
                    if (dragon.length == 8) {
                        try {
                            if (!dragon[0].trim().isEmpty()) {
                                name = dragon[0];
                            } else {
                                throw new InputMismatchException();
                            }
                            age = Long.parseLong(dragon[1]);
                            x = Long.parseLong(dragon[2]);
                            y = Float.parseFloat(dragon[3]);
                            if (dragon[4].equals("GREEN") || dragon[4].equals("ORANGE") || dragon[4].equals("BROWN") || dragon[4].equals("null") | dragon[5].equals("WATER") || dragon[5].equals("UNDERGROUND") || dragon[5].equals("FIRE") || dragon[6].equals("CUNNING") || dragon[6].equals("CHAOTIC_EVIL") || dragon[6].equals("WISE") || dragon[6].equals("FICKLE")) {
                                color = Color.valueOf(dragon[4]);
                                type = DragonType.valueOf(dragon[5]);
                                character = DragonCharacter.valueOf(dragon[6]);
                            } else {
                                throw new InputMismatchException();
                            }
                            eyesCount = Double.parseDouble(dragon[7]);
                            dragons.add(new Dragon(name, new Coordinates(x, y), age, color, type, character, new DragonHead(eyesCount)));
                        } catch (Exception ignored) {}
                    }
                } catch (Exception ignored) {}
            }
            scanner.close();
            System.out.println("Из файла добавлено объектов в коллекцию: " + dragons.size());
            return true;
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Файл не найден");
            return false;
        }
    }
}