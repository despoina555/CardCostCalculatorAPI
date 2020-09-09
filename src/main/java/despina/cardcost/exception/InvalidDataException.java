package despina.cardcost.exception;

public class InvalidDataException extends RuntimeException {

     static String INVALID_DATA_EXC ="Invalid card number";

    public InvalidDataException() {
        super(INVALID_DATA_EXC);
    }
}
