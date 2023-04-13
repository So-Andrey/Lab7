package commands.concreteCommand;

import commands.Command;
import commands.Invoker;
import exceptions.InvalidCommandException;

public class HelpCommand implements Command {
    /**метод, выводящий справку по командам*/
    @Override
    public void execute() {
        try {
            if(Invoker.getSplit().length != 1){
                throw new InvalidCommandException();
            }
            Invoker.getCommandHashMap().values().forEach(command -> System.out.println(command.description()));
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }

    @Override
    public String description() {
        return "help : вывести справку по доступным командам";
    }
}