package commands.concreteCommand;

import allForDragons.DragonsCollection;
import commands.Command;
import commands.Invoker;
import exceptions.InvalidCommandException;

public class PrintAscendingCommand implements Command {
    /**Метод, выводящий драконов в порядке возрастания значения возраста*/
    private void ascendingPrinter() {
        DragonsCollection.getDragons().stream().sorted().forEachOrdered(System.out::println);
    }
    /**Метод, выполняющий команду с помощью ascendingPrinter
     * @see PrintAscendingCommand#ascendingPrinter() */
    @Override
    public void execute() {
        try {
            if (Invoker.getSplit().length != 1) {
                throw new InvalidCommandException();
            }
            if (DragonsCollection.getDragons().isEmpty()) {
                System.out.println("Коллекция пуста");
            } else {
                ascendingPrinter();
            }
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }
    @Override
    public String description() {
        return "print_ascending : вывести элементы коллекции в порядке возрастания";
    }
}