package commands.concreteCommand;

import allForDragons.Dragon;
import allForDragons.DragonsCollection;
import commands.Command;
import commands.Invoker;
import exceptions.InvalidCommandException;
import java.util.ArrayList;
import java.util.Collections;

public class PrintAscendingCommand implements Command {

    /**Метод, выводящий драконов в порядке возрастания значения возраста*/
    private void ascendingPrinter() {
        ArrayList<Dragon> dragons = new ArrayList<>(DragonsCollection.getDragons());
        Collections.sort(dragons);
        //TODO lambda
        //DragonsCollection.getDragons().stream().sorted().forEachOrdered(System.out::println);
        for (Dragon dragon : dragons) {
            System.out.println(dragon);
        }
    }
    /**Метод, выполняющий команду с помощью ascendingPrinter
     * @see PrintAscendingCommand#ascendingPrinter() */
    @Override
    public void execute() {
        try {
            if(Invoker.getSplit().length != 1){
                throw new InvalidCommandException();
            }
            if (DragonsCollection.getDragons().size() == 0) {
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