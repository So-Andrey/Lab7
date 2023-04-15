package commands.concreteCommand;

import allForDragons.DragonsCollection;
import commands.Command;
import commands.CommandArgsChecker;

public class ClearCommand implements Command {
    /**Метод, очищающий коллекцию*/
    @Override
    public void execute() {
        CommandArgsChecker.commandArgsChecker(0);
        DragonsCollection.getDragons().clear();
        System.out.println("Коллекция очищена");
    }
    @Override
    public String description() {
        return "clear : очистить коллекцию";
    }
}
