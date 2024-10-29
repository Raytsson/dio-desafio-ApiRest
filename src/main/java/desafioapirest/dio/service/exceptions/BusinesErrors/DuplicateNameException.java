package desafioapirest.dio.service.exceptions.BusinesErrors;

import desafioapirest.dio.service.exceptions.BusinessException;

public class DuplicateNameException extends BusinessException {
    public DuplicateNameException(String mensagem) {
        super(mensagem);
    }
}