package commands.concreteCommand;

import allForDragons.DragonsCollection;
import commands.Command;
import commands.CommandArgsChecker;
import database.DatabaseConnection;

public class ClearCommand implements Command {
    /**Метод, очищающий коллекцию
     * @see CommandArgsChecker#commandArgsChecker(int)
     * @see DatabaseConnection#executeStatement(String)
     * @see DragonsCollection#updateFromDB() */
    @Override
    public void execute() {
        CommandArgsChecker.commandArgsChecker(0);
        DatabaseConnection.executeStatement("delete from dragons");
        DragonsCollection.updateFromDB();
        System.out.println("Коллекция очищена");
    }
    @Override
    public String description() {
        return "clear : очистить коллекцию";
    }
}
