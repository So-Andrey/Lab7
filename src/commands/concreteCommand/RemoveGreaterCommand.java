package commands.concreteCommand;

import allForDragons.*;
import commands.Command;
import commands.Invoker;
import exceptions.InvalidCommandException;
import java.util.ArrayList;
import java.util.Collections;

public class RemoveGreaterCommand implements Command {

    /**Метод, удаляющий из коллекции всех драконов старше заданного
     * @param dragonExists параметр существования заданного дракона в коллекции
     * @param thisDragon заданный дракон*/
    private void removerGreater(boolean dragonExists, Dragon thisDragon) {
        int countOfDragons = 0;
        if (dragonExists) {
            boolean isThereYoungerDragons = true;
            do {
                //TODO lambda
                ArrayList<Dragon> dragons = new ArrayList<>(DragonsCollection.getDragons());
                Collections.sort(dragons);
                Dragon dragon = Collections.max(dragons);
                if (dragon != thisDragon) {
                    DragonsCollection.getDragons().remove(dragon);
                    ++countOfDragons;
                } else {
                    isThereYoungerDragons = false;
                }
            } while (isThereYoungerDragons);
        } else {
            System.out.println("Заданного дракона не существует");
        }
        if (countOfDragons != 0) {
            System.out.println("Количество удалённых драконов " + countOfDragons);
        } else if (dragonExists) {
            System.out.println("Драконов старше заданного не существует");
        }
    }
    /**Метод, находящий заданного дракона в коллекции и вызывающий метод removerGreater
     * @see RemoveGreaterCommand#removerGreater(boolean, Dragon) */
    @Override
    public void execute() {
        try {
            if (Invoker.getSplit().length != 2) {
                throw new InvalidCommandException();
            }
            try {
                Long.parseLong(Invoker.getSplit()[1]);
            } catch (NumberFormatException ex) {
                throw new InvalidCommandException();
            }
            long id = Long.parseLong(Invoker.getSplit()[1]);
            boolean dragonExists = false;
            Dragon thisDragon = new Dragon("", new Coordinates(0,0), Long.parseLong("0"),Color.ORANGE, DragonType.WATER, DragonCharacter.FICKLE,new DragonHead(Double.parseDouble("0")));
            if (!DragonsCollection.getDragons().isEmpty()) {
                //TODO lambda
                for (Dragon dragon : DragonsCollection.getDragons()) {
                    if (dragon.getId() == id) {
                        dragonExists = true;
                        thisDragon = dragon;
                    }
                }
                removerGreater(dragonExists, thisDragon);
            } else {
                System.out.println("Коллекция пуста, заданного дракона не существует");
            }
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }
    @Override
    public String description() {
        return "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный";
    }
}