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

        return getValor() * taxaProcessamento;
    }

    @Override
    public String obterDescricao() {
        return """
               PAGAMENENTO EM CARTÂO DE CRÉDITO REALIZADO COM SUCESSO!
               ID: %s
               VALOR: %s
               PARCELAS: %s
               TAXA DE PROCESSAMENTO: %s
               VALOR FINAL: %s 
               """.formatted(
                getId(),
                getValor(),
                parcelas,
                taxaProcessamento,
                calcularValorFinal()
        );
    }
}
