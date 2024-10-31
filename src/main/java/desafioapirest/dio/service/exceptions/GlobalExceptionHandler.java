package desafioapirest.dio.service.exceptions;

import desafioapirest.dio.service.exceptions.BusinesErrors.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseError> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        ResponseError error = new ResponseError("Not Found", HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DuplicateNameException.class)
    public ResponseEntity<ResponseError> handleDuplicateNameException(DuplicateNameException ex, HttpServletRequest request) {
        ResponseError error = new ResponseError("Conflict", HttpStatus.CONFLICT.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(LimiteOrcamentoNotFoundException.class)
    public ResponseEntity<ResponseError> handleLimiteOrcamentoNotFoundException(LimiteOrcamentoNotFoundException ex, HttpServletRequest request) {
        ResponseError error = new ResponseError("Conflict", HttpStatus.CONFLICT.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(NegativeValueException.class)
    public ResponseEntity<ResponseError> handleNegativeValueException(NegativeValueException ex, HttpServletRequest request) {
        ResponseError error = new ResponseError("Bad Request", HttpStatus.BAD_REQUEST.value(), ex.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InvalidTransactionDateException.class)
    public ResponseEntity<ResponseError> handleInvalidTransactionDateException(InvalidTransactionDateException ex, HttpServletRequest request) {
        ResponseError error = new ResponseError("Bad Request", HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InvalidValueException.class)
    public ResponseEntity<ResponseError> handleInvalidValueException(InvalidValueException ex, HttpServletRequest request) {
        ResponseError error = new ResponseError("Bad Request", HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
