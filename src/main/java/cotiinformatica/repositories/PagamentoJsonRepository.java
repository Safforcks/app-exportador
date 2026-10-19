package cotiinformatica.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import cotiinformatica.entities.Pagamento;
import cotiinformatica.interfaces.PagamentoRepository;

import java.io.File;

public class PagamentoJsonRepository implements PagamentoRepository {

    @Override
    public void exportar(Pagamento pagamento) throws Exception {

        //Criando um objeto para fazer a exportação em JSON
        var objectMapper = new ObjectMapper();

        //Configuração para geração de datas
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        //Gravar os dados do pagamento no arquivo
        objectMapper.writerWithDefaultPrettyPrinter()
                .writeValue(
                        new File("pagamento.json"),
                        pagamento
                );
    }
}
