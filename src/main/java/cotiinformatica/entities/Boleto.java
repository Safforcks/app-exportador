package cotiinformatica.entities;

import java.time.LocalDate;

public class Boleto extends Pagamento{

    private LocalDate vencimento;
    private Double multa;

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public Double getMulta() {
        return multa;
    }

    public void setMulta(Double multa) {
        this.multa = multa;
    }

    @Override
    public Double calcularValorFinal() {
        return null;
    }
}
