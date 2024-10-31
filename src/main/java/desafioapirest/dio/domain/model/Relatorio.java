package desafioapirest.dio.domain.model;

import java.math.BigDecimal;
import java.util.List;

public class Relatorio {

    private List<Receitas> receitas;
    private List<Despesas> despesas;
    private BigDecimal totalReceitas;
    private BigDecimal totalDespesas;
    private BigDecimal saldo;

    public Relatorio(List<Receitas> receitas, List<Despesas> despesas, BigDecimal totalReceitas, BigDecimal totalDespesas) {
        this.receitas = receitas;
        this.despesas = despesas;
        this.totalReceitas = totalReceitas;
        this.totalDespesas = totalDespesas;
        this.saldo = totalReceitas.subtract(totalDespesas);
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public List<Receitas> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<Receitas> receitas) {
        this.receitas = receitas;
    }

    public List<Despesas> getDespesas() {
        return despesas;
    }

    public void setDespesas(List<Despesas> despesas) {
        this.despesas = despesas;
    }

    public BigDecimal getTotalReceitas() {
        return totalReceitas;
    }

    public void setTotalReceitas(BigDecimal totalReceitas) {
        this.totalReceitas = totalReceitas;
    }

    public BigDecimal getTotalDespesas() {
        return totalDespesas;
    }

    public void setTotalDespesas(BigDecimal totalDespesas) {
        this.totalDespesas = totalDespesas;
    }
}
