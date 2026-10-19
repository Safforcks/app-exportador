package cotiinformatica;

import cotiinformatica.services.PagamentoService;

public class Main {
    public static void main(String[] args) {

        try {
            var service = new PagamentoService();
            service.operarPagamento();
        } catch (Exception e) {
            System.out.println("\nERRO: " + e.getMessage());
        }
    }
}