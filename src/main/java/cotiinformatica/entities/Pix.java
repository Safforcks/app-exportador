package cotiinformatica.entities;

public class Pix extends Pagamento {

    private String chavePix;
    private Double desconto;

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    @Override
    public Double calcularValorFinal() {

        return getValor() - (getValor() * desconto);
    }

    @Override
    public String obterDescricao() {
        return """
               \nPAGAMENENTO EM PIX REALIZADO COM SUCESSO!
                    ID: %s
                    VALOR: %s
                    CHAVE PIX: %s
                    DESCONTO: %s
                    VALOR FINAL: %s 
               """.formatted(
                getId(),
                getValor(),
                chavePix,
                desconto,
                calcularValorFinal()
        );
    }
}
