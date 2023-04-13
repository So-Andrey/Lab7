package commands.concreteCommand;

import commands.Command;
import allForDragons.*;
import commands.Invoker;
import exceptions.InvalidCommandException;

public class ShowCommand implements Command {

    /**Метод, выводящий все элементы коллекции*/
    @Override
    public void execute() {
        try {
            if(Invoker.getSplit().length != 1){
                throw new InvalidCommandException();
            }
            if (DragonsCollection.getDragons().isEmpty()) {
                System.out.println("Коллекция пуста");
            } else {
                //TODO lambda
                //DragonsCollection.getDragons().forEach(System.out::println);
                for (Dragon dragon : DragonsCollection.getDragons()) {
                    System.out.println(dragon);
                }
            }
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }

    @Override
    public String description() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}