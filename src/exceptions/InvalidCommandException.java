package exceptions;

public class InvalidCommandException extends Exception{
    public InvalidCommandException() {
        super("Неверная команда");
    }
}