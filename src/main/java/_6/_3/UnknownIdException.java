package _6._3;

public class UnknownIdException extends InvalidIdException {
    public UnknownIdException() {
    }
    public UnknownIdException(String message,int id) {
        super(message,id);
    }
}
