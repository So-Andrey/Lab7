package commands.concreteCommand;

import allForDragons.*;
import commands.Command;
import commands.Invoker;
import exceptions.InvalidCommandException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AddIfMinCommand implements Command {
    /**Метод, добавляющий в коллекцию нового дракона, если его возраст меньше имеющихся в коллекции
     * @see DragonAdder#dragonAdder()*/
    @Override
    public void execute() {
        try {
            if (Invoker.getSplit().length != 1) {
                throw new InvalidCommandException();
            }
            ArrayList<Dragon> dragons = new ArrayList<>(DragonsCollection.getDragons());
            Dragon dragon = DragonAdder.dragonAdder();
            if (dragons.size() == 0) {
                DragonsCollection.getDragons().add(dragon);
                System.out.println("Новый элемент коллекции добавлен");
            } else {
                //TODO lambda
                Collections.sort(dragons);
                if (dragon.getAge() < dragons.get(0).getAge()) {
                    DragonsCollection.getDragons().add(dragon);
                    System.out.println("Новый элемент коллекции добавлен");
                } else {
                    System.out.println("Новый элемент не добавлен, так как возраст заданного дракона слишком большой");
                }
            }
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }
    /** Метод, выполняющий команду add_if_min из файла
     * @see DragonAdder#dragonFromFileAdder(Scanner) */
    protected static void adderIfMinFromFile(Scanner scanner) {
        try {
            Dragon dragon = DragonAdder.dragonFromFileAdder(scanner);
            ArrayList<Dragon> dragons = new ArrayList<>(DragonsCollection.getDragons());
            if (dragons.size() == 0) {
                DragonsCollection.getDragons().add(dragon);
                System.out.println("Новый элемент коллекции добавлен");
            } else {
                //TODO lambda
                Collections.sort(dragons);
                if (dragon.getAge() < dragons.get(0).getAge()) {
                    DragonsCollection.getDragons().add(dragon);
                    System.out.println("Новый элемент коллекции добавлен");
                } else {
                    System.out.println("Новый элемент не добавлен, так как возраст заданного дракона слишком большой");
                }
            }
        } catch (InputMismatchException ignored) {}
    }
    @Override
    public String description() {
        return "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";
    }
}
