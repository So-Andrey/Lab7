package commands.concreteCommand;

import allForDragons.*;
import commands.Command;
import commands.CommandArgsChecker;
import commands.Invoker;
import java.util.List;

public class RemoveLowerCommand implements Command {
    /**Метод, удаляющий из коллекции всех драконов младше заданного
     * @param thisDragon заданный дракон*/
    private void removerLower(Dragon thisDragon) {
        List<Dragon> greaterDragons = DragonsCollection.getDragons().stream().filter(dragon -> dragon.getAge() < thisDragon.getAge()).toList();
        if (greaterDragons.isEmpty()) {
            System.out.println("Драконов младше заданного не существует");
        } else {
            greaterDragons.forEach(dragon -> DragonsCollection.getDragons().remove(dragon));
            System.out.println("Количество удалённых драконов " + greaterDragons.size());
        }
    }
    /**Метод, находящий заданного дракона в коллекции и вызывающий метод removerLower
     * @see RemoveLowerCommand#removerLower(Dragon)  */
    @Override
    public void execute() {
        CommandArgsChecker.commandArgsChecker(1);
        try {
            List<Dragon> matchedDragons = DragonsCollection.getDragons().stream().filter(dragon -> dragon.getId() == Long.parseLong(Invoker.getSplit()[1])).toList();
            if (matchedDragons.isEmpty()) System.out.println("Заданного дракона не существует");
            else removerLower(matchedDragons.get(0));
        } catch (NumberFormatException ex) {
            throw new NullPointerException();
        }
    }
    @Override
    public String description() {
        return "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }
}