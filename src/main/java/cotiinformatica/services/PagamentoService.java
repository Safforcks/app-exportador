package cotiinformatica.services;

import cotiinformatica.entities.Boleto;
import cotiinformatica.entities.CartaoCredito;
import cotiinformatica.entities.Pix;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class PagamentoService {

    //Método para inserir um pagamento
    public void  operarPagamento() {

        System.out.println("\nNOVO PAGAMENTO:\n");

        var scanner = new Scanner(System.in);

        System.out.print("\nINFORME O VALOR...: ");
        var valor = Double.parseDouble(scanner.nextLine());

        System.out.println("\nOPÇÕES DE PAGAMENTO:");
        System.out.println("\t (1) PIX");
        System.out.println("\t (2) CARTÃO DE CRÉDITO");
        System.out.println("\t (3) BOLETO");

        System.out.print("\nINFORME A OPÇÃO DESEJADA: ");
        var opcaoPagamento = scanner.nextLine();

        System.out.println("\nOPÇÕES DE EXPORTAÇÃO DOS DADOS:");
        System.out.println("\t(1) ARQUIVO XML");
        System.out.println("\t(2) ARQUIVO JSON");

        System.out.print("\nINFORME A OPÇÃO DESEJADA: ");
        var opcaoExportacao = scanner.nextLine();

        switch (Integer.parseInt(opcaoPagamento)) {
            case 1:
                var pix = new Pix();

                pix.setId(UUID.randomUUID());
                pix.setValor(valor);
                pix.setDesconto(0.05);

                System.out.print("INFORME A CHAVE PIX...: ");
                pix.setChavePix(scanner.nextLine());

                //TODO

                break;
            case 2:
                var cartaoCredito = new CartaoCredito();

                cartaoCredito.setId(UUID.randomUUID());
                cartaoCredito.setValor(valor);
                cartaoCredito.setTaxaProcessamento(1.08); // 8%

                System.out.print("QTD DE PARCELAS.......: ");
                cartaoCredito.setParcelas(Integer.parseInt(scanner.nextLine()));

                //TODO

                break;
            case 3:
                var boleto = new Boleto();

                boleto.setId(UUID.randomUUID());
                boleto.setValor(valor);
                boleto.setMulta(1.15); // 15%
                boleto.setVencimento(LocalDate.now().plusDays(15)); // 15 dias

                //TODO

                break;
            default:
                System.out.println("\nOPÇÃO INVÁLIDA!");
                break;
        }
    }
}
