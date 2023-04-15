package commands.concreteCommand;

import allForDragons.*;
import commands.Command;
import commands.CommandArgsChecker;
import commands.Invoker;
import database.DatabaseConnection;
import java.util.List;

public class RemoveGreaterCommand implements Command {
    /**Метод, удаляющий из коллекции всех драконов старше заданного
     * @param thisDragon заданный дракон
     * @see DragonsCollection#updateFromDB()
     * @see DatabaseConnection#executeStatement(String) */
    private void removerGreater(Dragon thisDragon) {
        List<Dragon> greaterDragons = DragonsCollection.getDragons().stream().filter(dragon -> dragon.getAge() > thisDragon.getAge()).toList();
        if (greaterDragons.isEmpty()) {
            System.out.println("Драконов старше заданного не существует");
        } else {
            greaterDragons.forEach(dragon -> DatabaseConnection.executeStatement("delete from dragons where id = " + dragon.getId()));
            DragonsCollection.updateFromDB();
            System.out.println("Количество удалённых драконов: " + greaterDragons.size());
        }
    }
    /**Метод, находящий заданного дракона в коллекции и вызывающий метод removerGreater
     * @see RemoveGreaterCommand#removerGreater(Dragon) */
    @Override
    public void execute() {
        CommandArgsChecker.commandArgsChecker(1);
        try {
            List<Dragon> matchedDragons = DragonsCollection.getDragons().stream().filter(dragon -> dragon.getId() == Long.parseLong(Invoker.getSplit()[1])).toList();
            if (matchedDragons.isEmpty()) System.out.println("Заданного дракона не существует");
            else removerGreater(matchedDragons.get(0));
        } catch (NumberFormatException ex) {
            throw new NullPointerException();
        }
    }
    @Override
    public String description() {
        return "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный";
    }
}