package _6._3;

public class InvalidIdException extends RuntimeException {
    private int id;

    public InvalidIdException() {
    }
    public InvalidIdException(String message,int id) {
        super(message);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
