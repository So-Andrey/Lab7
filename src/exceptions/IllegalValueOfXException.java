package exceptions;

public class IllegalValueOfXException extends Exception{
    public IllegalValueOfXException() {
        super("Максимальное значение координаты Х: 610");
    }
}
