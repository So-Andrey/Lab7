package commands.concreteCommand;

import allForDragons.Dragon;
import allForDragons.DragonsCollection;
import commands.Command;
import commands.Invoker;
import exceptions.InvalidCommandException;
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
        try {
            if (Invoker.getSplit().length != 2) {
                throw new InvalidCommandException();
            }
            try {
                removerById(Long.parseLong(Invoker.getSplit()[1]));
            } catch (NumberFormatException ex) {
                throw new InvalidCommandException();
            }
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }
    @Override
    public String description() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}