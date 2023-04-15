package commands.concreteCommand;

import allForDragons.Dragon;
import allForDragons.DragonsCollection;
import commands.Command;
import commands.CommandArgsChecker;
import commands.Invoker;
import java.util.List;

public class RemoveByIdCommand implements Command {
    /**Метод, удаляющий дракона по значению id
     * @param id дракона, которого надо удалить*/
    private void removerById(long id) {
        List<Dragon> matchedDragon = DragonsCollection.getDragons().stream().filter((dragon -> dragon.getId() == id)).toList();
        if (matchedDragon.isEmpty()) {
            System.out.println("Такого дракона не существует");
        } else {
            DragonsCollection.getDragons().remove(matchedDragon.get(0));
            System.out.println("Дракон успешно удалён");
        }
    }
    /**Выполняет команду с помощью removerById
     * @see RemoveByIdCommand#removerById(long) */
    @Override
    public void execute() {
        CommandArgsChecker.commandArgsChecker(1);
        try {
            removerById(Long.parseLong(Invoker.getSplit()[1]));
        } catch (NumberFormatException ex) {
            throw new NullPointerException();
        }
    }
    @Override
    public String description() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}