package desafioapirest.dio.service.exceptions.BusinesErrors;

public class InvalidValueException extends RuntimeException {
    public InvalidValueException(String message) {
        super(message);
    }
}
