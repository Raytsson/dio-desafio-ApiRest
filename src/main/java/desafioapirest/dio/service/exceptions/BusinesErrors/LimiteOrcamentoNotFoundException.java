package desafioapirest.dio.service.exceptions.BusinesErrors;

import desafioapirest.dio.service.exceptions.BusinessException;

public class LimiteOrcamentoNotFoundException extends BusinessException {
    public LimiteOrcamentoNotFoundException(String mensagem) {
        super(mensagem);
    }
}