
package commands.concreteCommand;

import commands.Command;
import allForDragons.*;
import commands.Invoker;
import exceptions.InvalidCommandException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AddCommand implements Command {
    /**Метод, добавляющий в коллекцию нового дракона
     * @see DragonAdder#dragonAdder() */
    @Override
    public void execute() throws InputMismatchException {
        try {
            if(Invoker.getSplit().length != 1){
                throw new InvalidCommandException();
            }
            DragonsCollection.getDragons().add(DragonAdder.dragonAdder());
            System.out.println("Новый элемент коллекции добавлен");
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }
    /** Метод, выполняющий команду add из файла
     * @see DragonAdder#dragonFromFileAdder(Scanner) */
    protected static void adderFromFile(Scanner scanner) {
        try {
            DragonsCollection.getDragons().add(DragonAdder.dragonFromFileAdder(scanner));
            System.out.println("Новый элемент коллекции добавлен");
        } catch (InputMismatchException ignored) {}
    }
    @Override
    public String description() {
        return "add {element} : добавить новый элемент в коллекцию";
    }
}
