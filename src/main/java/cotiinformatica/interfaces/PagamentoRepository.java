package cotiinformatica.interfaces;

import cotiinformatica.entities.Pagamento;

public interface PagamentoRepository {

    /*
        Métodos abstratos
     */
    void exportar(Pagamento pagamento) throws Exception;
}
