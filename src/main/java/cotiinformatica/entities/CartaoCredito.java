package cotiinformatica.entities;

public class CartaoCredito extends Pagamento {

    private Integer parcelas;
    private Double taxaProcessamento;

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }

    public Double getTaxaProcessamento() {
        return taxaProcessamento;
    }

    public void setTaxaProcessamento(Double taxaProcessamento) {
        this.taxaProcessamento = taxaProcessamento;
    }

    @Override
    public Double calcularValorFinal() {
        return null;
    }
}
