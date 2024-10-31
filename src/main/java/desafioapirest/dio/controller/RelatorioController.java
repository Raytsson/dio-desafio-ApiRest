package desafioapirest.dio.controller;

import desafioapirest.dio.Dtos.RelatorioDto;
import desafioapirest.dio.domain.model.Relatorio;
import desafioapirest.dio.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/mensal")
    public ResponseEntity<RelatorioDto> getRelatorioMensal(
            @RequestParam(value = "ano", required = false) Integer ano,
            @RequestParam(value = "mes", required = false) Integer mes
    ) {
        LocalDate hoje = LocalDate.now();
        int anoAtual = ano != null ? ano : hoje.getYear(); // Usa o ano atual se não for passado
        int mesAtual = mes != null ? mes : hoje.getMonthValue(); // Usa o mês atual se não for passado

        Relatorio relatorio = relatorioService.gerarRelatorio(anoAtual, mesAtual);

        RelatorioDto relatorioDto = new RelatorioDto(
                relatorio.getReceitas(),
                relatorio.getDespesas(),
                relatorio.getTotalReceitas(),
                relatorio.getTotalDespesas(),
                relatorio.getTotalReceitas().subtract(relatorio.getTotalDespesas())
        );

        return ResponseEntity.ok(relatorioDto);
    }
}
