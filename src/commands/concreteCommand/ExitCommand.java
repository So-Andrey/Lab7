package commands.concreteCommand;

import commands.Command;
import commands.Invoker;
import exceptions.InvalidCommandException;

public class ExitCommand implements Command {
    /**Метод, завершающий программу*/
    @Override
    public void execute() {
        try {
            if(Invoker.getSplit().length != 1){
                throw new InvalidCommandException();
            }
            Invoker.setProgramRunning(false);
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }

    @Override
    public String description() {
        return "exit : завершить программу (без сохранения в файл)";
    }
}