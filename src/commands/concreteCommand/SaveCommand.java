package commands.concreteCommand;

import allForDragons.DragonsCollection;
import commands.Command;
import commands.Invoker;
import exceptions.InvalidCommandException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SaveCommand implements Command {
    /**Метод, сохраняющий коллекцию в файл */
    @Override
    public void execute() {
        try {
            if(Invoker.getSplit().length != 1){
                throw new InvalidCommandException();
            }
            try (PrintWriter writer = new PrintWriter(Invoker.getFile())) {
                DragonsCollection.getDragons().forEach(dragon -> writer.write(dragon.getName() + ", " + dragon.getAge() + ", " + dragon.getCoordinates().getX() + ", " + dragon.getCoordinates().getY() + ", " + dragon.getColor() + ", " + dragon.getType() + ", " + dragon.getCharacter() + ", " + dragon.getHead().getEyesCount() + "\n"));
                writer.flush();
                writer.close();
                System.out.println("Коллекция успешно сохранена в файл");
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("Файл не найден");
            }
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }

    @Override
    public String description() {
        return "save : сохранить коллекцию в файл";
    }
}