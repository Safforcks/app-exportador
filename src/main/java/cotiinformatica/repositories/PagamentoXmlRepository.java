package cotiinformatica.repositories;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import cotiinformatica.entities.Pagamento;
import cotiinformatica.interfaces.PagamentoRepository;

import java.io.File;

public class PagamentoXmlRepository implements PagamentoRepository {

    @Override
    public void exportar(Pagamento pagamento) throws Exception {

        //Criando um objeto para salvar dados em formato JSON
        XmlMapper mapper = new XmlMapper();

        //COnfigurando o modo de gravação de dados do tipo data
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        //Exportando o gravando o arquivo XML
        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(
                        new File("pagamento.xml"),
                        pagamento
                );
    }
}
