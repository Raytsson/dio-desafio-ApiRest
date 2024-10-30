package desafioapirest.dio.service.exceptions.BusinesErrors;

public class InvalidTransactionDateException extends RuntimeException {
    public InvalidTransactionDateException(String message) {
        super(message);
    }
}