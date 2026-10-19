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

        //Verificar se o boleto está vencido
        if (LocalDate.now().isAfter(vencimento)) {
            //Aplicar a multa
            return getValor() * multa;
        }

        return  getValor();
    }

    @Override
    public String obterDescricao() {
        return """
               PAGAMENENTO EM BOLETO REALIZADO COM SUCESSO!
               ID: %s
               VALOR: %s
               MULTA: %s
               VENCIMENTO: %s
               VALOR FINAL: %s 
               """.formatted(
                       getId(),
                       getValor(),
                       multa,
                       vencimento,
                       calcularValorFinal()
                    );
    }
}
