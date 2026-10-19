package cotiinformatica.entities;

import java.util.UUID;

public abstract class Pagamento {

    private UUID id;
    private Double valor;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public abstract Double calcularValorFinal();
    public abstract String obterDescricao();
}
