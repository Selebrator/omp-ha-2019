package _6._3;

public class DuplicateIdException extends InvalidIdException {
    public DuplicateIdException() {
    }
    public DuplicateIdException(String message,int id) {
        super(message,id);
    }
}
