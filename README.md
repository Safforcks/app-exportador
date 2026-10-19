# app-exportador

Aplicação de console desenvolvida em **Java 21** para simular pagamentos e exportar seus dados em **JSON** ou **XML**. O projeto demonstra conceitos de Programação Orientada a Objetos (POO), com classes abstratas, herança, encapsulamento, polimorfismo e interfaces.

## Finalidade

Permitir que o usuário informe um pagamento, escolha a modalidade e selecione o formato de exportação. A aplicação calcula o valor final conforme as regras da modalidade, grava os dados em arquivo e apresenta um resumo no console.

O projeto é uma simulação didática: não realiza transações bancárias nem se integra a instituições financeiras.

## Tecnologias

| Tecnologia | Uso no projeto |
| --- | --- |
| Java 21 | Linguagem e versão de compilação definidas no projeto |
| Maven | Gerenciamento das dependências e compilação |
| Jackson Databind 2.21.3 | Serialização dos objetos em JSON |
| Jackson Dataformat XML 2.21.3 | Serialização dos objetos em XML |
| Jackson Datatype JSR310 2.21.3 | Suporte à serialização de datas, como `LocalDate` |

As versões estão definidas no [pom.xml](pom.xml).

## Organização do código

O código está no pacote `cotiinformatica`, dentro de `src/main/java`.

| Pasta ou arquivo | Responsabilidade |
| --- | --- |
| [Main.java](src/main/java/cotiinformatica/Main.java) | Inicia a aplicação, chama o serviço e apresenta mensagens de erro quando ocorre uma exceção. |
| [entities](src/main/java/cotiinformatica/entities) | Contém `Pagamento`, `Pix`, `CartaoCredito` e `Boleto`, com seus atributos e regras de cálculo. |
| [interfaces](src/main/java/cotiinformatica/interfaces) | Define o contrato `PagamentoRepository` para exportação de pagamentos. |
| [repositories](src/main/java/cotiinformatica/repositories) | Implementa a gravação dos dados em arquivos JSON e XML. |
| [services](src/main/java/cotiinformatica/services) | Contém `PagamentoService`, que coordena a leitura dos dados, a criação dos objetos e a exportação. |

Essa separação organiza as responsabilidades: as entidades representam os pagamentos, o serviço conduz a operação e os repositórios cuidam da saída em arquivo.

## Funcionamento

1. A classe `Main` cria um `PagamentoService` e chama `operarPagamento()`.
2. O serviço solicita o valor do pagamento.
3. O usuário escolhe a modalidade: **1 — Pix**, **2 — cartão de crédito** ou **3 — boleto**.
4. O usuário escolhe a exportação: **1 — XML** ou **2 — JSON**.
5. O serviço seleciona a implementação de `PagamentoRepository` e cria a entidade correspondente, atribuindo um identificador com `UUID.randomUUID()`.
6. São preenchidos os dados específicos da modalidade, e o repositório exporta o objeto.
7. A aplicação apresenta a descrição do pagamento e o valor final no console.

### Regras de cálculo

| Modalidade | Dados e comportamento | Cálculo do valor final |
| --- | --- | --- |
| Pix | Solicita a chave Pix e aplica desconto de 5%. | `valor - (valor * 0.05)` |
| Cartão de crédito | Solicita a quantidade de parcelas e aplica acréscimo de 8%. | `valor * 1.08` |
| Boleto | Define vencimento para 15 dias após a data atual. Aplica acréscimo de 15% somente se a data atual for posterior ao vencimento. | Em dia: `valor`; vencido: `valor * 1.15` |

Por exemplo, para um pagamento de **R$ 100,00**, o Pix resulta em **R$ 95,00**, o cartão em **R$ 108,00** e um boleto em dia em **R$ 100,00**.

No fluxo atual, o boleto é criado com vencimento futuro, portanto a multa não é aplicada no momento de sua criação. A quantidade de parcelas do cartão é registrada e exibida, mas não altera a taxa nem gera um cálculo de valor por parcela.

## Pilares de POO no projeto

| Conceito | Aplicação no código |
| --- | --- |
| **Abstração** | `Pagamento` é uma classe abstrata que reúne `id` e `valor` e declara os métodos `calcularValorFinal()` e `obterDescricao()`. A interface `PagamentoRepository` define o contrato de exportação. |
| **Encapsulamento** | Os atributos são privados e acessados por getters e setters. |
| **Herança** | `Pix`, `CartaoCredito` e `Boleto` estendem `Pagamento`, herdando os dados comuns. |
| **Polimorfismo** | Cada modalidade sobrescreve os métodos abstratos. Na exportação, a variável do tipo `PagamentoRepository` pode referenciar uma implementação JSON ou XML. |

Por exemplo, o serviço pode usar o mesmo contrato para exportar em formatos diferentes:

```java
PagamentoRepository repository = new PagamentoJsonRepository();
repository.exportar(pix);
```

A implementação escolhida determina como o arquivo será gravado. O método `exportar(Pagamento pagamento)` também aceita as diferentes subclasses de `Pagamento`.

## Diagramas

### Classe abstrata e herança

<img width="1012" height="501" alt="Diagrama de herança: Pagamento como classe abstrata e CartaoCredito, Pix e Boleto como especializações" src="https://github.com/user-attachments/assets/40ece496-aa65-4ebc-a686-bc2862330d42" />

O primeiro diagrama apresenta a generalização dos dados e comportamentos comuns em `Pagamento` e a especialização em três modalidades. As setas com triângulo apontam para a superclasse.

Cada subclasse implementa seu próprio `calcularValorFinal()`. Além do método mostrado no diagrama, o código também declara e implementa `obterDescricao()`, responsável pelo resumo exibido no console.

### Interface e implementações de exportação

<img width="967" height="467" alt="Diagrama da interface PagamentoRepository e suas implementações para exportação em XML e JSON" src="https://github.com/user-attachments/assets/2aa1df33-83c9-4473-97d1-5327a01dc8db" />

O segundo diagrama apresenta o contrato `exportar(Pagamento pagamento)` da interface `PagamentoRepository` e suas duas implementações:

- `PagamentoXmlRepository`: utiliza `XmlMapper` para gravar `pagamento.xml`.
- `PagamentoJsonRepository`: utiliza `ObjectMapper` para gravar `pagamento.json`.

Os dois repositórios registram `JavaTimeModule` e desativam a gravação de datas como timestamps, permitindo exportar o vencimento do boleto como uma data textual.

## Como executar

### Requisitos

- JDK 21.
- Maven instalado ou disponível pela IDE.
- Acesso às dependências declaradas no `pom.xml`.

### Passos

1. Clone o repositório:

   ```bash
   git clone https://github.com/Safforcks/app-exportador.git
   cd app-exportador
   ```

2. Abra a pasta como um projeto Maven em uma IDE Java, como IntelliJ IDEA, e configure o JDK 21.
3. Aguarde a resolução das dependências. Para compilar pelo terminal, execute na raiz:

   ```bash
   mvn compile
   ```

4. Execute o método `main` da classe `cotiinformatica.Main` pela IDE e preencha as opções no console.

Para números decimais, utilize ponto, por exemplo: `100.50`.

## Arquivos exportados

Os arquivos são gravados no diretório de execução da aplicação. Ao executar com a raiz do projeto como diretório de trabalho, eles aparecem junto ao `pom.xml`.

Cada operação gera apenas o formato escolhido. Uma nova exportação para o mesmo formato **sobrescreve o arquivo anterior**.

Exemplo ilustrativo de um Pix de R$ 100,00 exportado em JSON:

```json
{
  "id": "00000000-0000-0000-0000-000000000001",
  "valor": 100.0,
  "chavePix": "chave-exemplo",
  "desconto": 0.05
}
```

O identificador é gerado automaticamente em cada operação. O valor final e a descrição são calculados por métodos e exibidos no console; eles não são incluídos na exportação atual.

## Escopo atual

A aplicação realiza uma operação por execução e utiliza arquivos locais, sem banco de dados. Opções numéricas fora do menu são informadas como inválidas; erros de conversão ou de exportação chegam ao tratamento de exceções da classe `Main`.

Os valores monetários usam `Double`, e o código ainda não valida valores negativos, quantidade de parcelas ou formato da chave Pix.

## Referências do código

Esta documentação foi elaborada a partir da implementação do próprio repositório:

- [Entidades e regras de cálculo](src/main/java/cotiinformatica/entities)
- [Fluxo de operação do pagamento](src/main/java/cotiinformatica/services/PagamentoService.java)
- [Contrato de exportação](src/main/java/cotiinformatica/interfaces/PagamentoRepository.java)
- [Implementações JSON e XML](src/main/java/cotiinformatica/repositories)
- [Configuração do projeto e dependências](pom.xml)
