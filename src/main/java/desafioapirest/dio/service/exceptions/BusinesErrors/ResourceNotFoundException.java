package desafioapirest.dio.service.exceptions.BusinesErrors;

import desafioapirest.dio.service.exceptions.BusinessException;

public class ResourceNotFoundException extends BusinessException {
    public ResourceNotFoundException(String mensagem) {
        super(mensagem);
    }
}