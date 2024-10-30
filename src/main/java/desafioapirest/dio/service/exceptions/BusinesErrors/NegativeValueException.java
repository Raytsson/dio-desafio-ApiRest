package desafioapirest.dio.service.exceptions.BusinesErrors;

public class NegativeValueException extends RuntimeException {
    public NegativeValueException(String message) {
        super(message);
    }
}