package commands.concreteCommand;

import allForDragons.DragonsCollection;
import commands.Command;
import commands.Invoker;
import exceptions.InvalidCommandException;

public class MaxByHeadCommand implements Command {

    /**Метод, выводящий дракона с максимальным значением head */
    private void maxByHeadPrinter() {
        System.out.println(DragonsCollection.getDragons().stream().max((dragon1, dragon2) -> (int)Math.signum(dragon1.getHead().getEyesCount() - dragon2.getHead().getEyesCount())).get());
    }
    /**Метод, выполняющий команду с помощью maxByHeadPrinter
     * @see MaxByHeadCommand#maxByHeadPrinter() */
    @Override
    public void execute() {
        try {
            if(Invoker.getSplit().length != 1){
                throw new InvalidCommandException();
            }
            if (DragonsCollection.getDragons().size() != 0) {
                maxByHeadPrinter();
            } else {
                System.out.println("Коллекция пуста");
            }
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }
    @Override
    public String description() {
        return "max_by_head : вывести любой объект из коллекции, значение поля head которого является максимальным";
    }
}