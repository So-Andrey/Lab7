package commands.concreteCommand;

import allForDragons.DragonsCollection;
import commands.Command;
import commands.Invoker;
import exceptions.InvalidCommandException;

public class ClearCommand implements Command {
    /**Метод, очищающий коллекцию*/
    @Override
    public void execute() {
        try {
            if(Invoker.getSplit().length != 1){
                throw new InvalidCommandException();
            }
            DragonsCollection.getDragons().clear();
            System.out.println("Коллекция очищена");
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }
    @Override
    public String description() {
        return "clear : очистить коллекцию";
    }
}
