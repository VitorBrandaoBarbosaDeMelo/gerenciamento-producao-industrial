# Registro de Implementação

## Projeto

Production Optimizer — Sistema de gerenciamento de insumos, produtos e otimização de produção.

Data de referência: 27-28/02/2026.

## Objetivo

Entregar uma aplicação full stack com:
- CRUD de matérias-primas;
- CRUD de produtos com composição;
- cálculo de sugestão de produção para maximizar retorno.

## Stack Final

### Backend
- Java 21
- Spring Boot 3.2.2
- Spring Data JPA
- H2 Database
- Maven
- JUnit 5 + Mockito

### Frontend
- Vue 3
- Vite
- Axios

## Evolução Implementada

### 1) Upgrade técnico
- migração de Java 17 para Java 21;
- ajustes de build e validação para execução com JDK 21.

### 2) Frontend
- atualização visual com layout moderno;
- padronização em português;
- melhorias de navegação e apresentação das telas.

### 3) Persistência
- alteração de H2 em memória para H2 em arquivo;
- configuração aplicada em `application.properties`:
  - `spring.datasource.url=jdbc:h2:file:./data/productiondb`
  - `spring.jpa.hibernate.ddl-auto=update`

Resultado: dados permanecem após reinicialização da aplicação.

## Arquitetura

Estrutura em camadas no backend:
- controllers para API REST;
- services para regras de negócio;
- repositories para persistência;
- models e DTOs para domínio e transporte.

Frontend organizado por views e serviço de API.

## Persistência e armazenamento

Arquivo físico do banco:
- `backend/data/productiondb.mv.db`

Console H2:
- `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:file:./data/productiondb`

## Testes e validação

- suíte de testes backend executável via Maven;
- validação funcional de CRUD e cálculo de otimização;
- validação de execução integrada backend + frontend.

## Melhorias Implementadas (28/02/2026)

### 4) Sistema de Precificação Automática

**Backend:**
- Adicionado campo `unidadeMedida` (String, obrigatório) em MateriaPrima
  - 10 opções disponíveis: Unidades, Quilogramas, Gramas, Litros, Metros, Metros², Metros³, Barras, Chapas, Galões
- Adicionado campo `pesoPorUnidade` (Double, opcional) em MateriaPrima
  - Para controle logístico e gestão de transporte
- Adicionado campo `valorUnidade` (Double, opcional) em MateriaPrima
  - Representa o custo unitário de aquisição da matéria-prima
- Adicionado campo `margemLucro` (Double, padrão 50%) em Produto
  - Percentual de margem aplicado sobre o custo total
- Implementados métodos de cálculo automático em Produto:
  - `calcularCustoTotal()`: soma (valorUnidade × quantidade) de todas as matérias-primas
  - `calcularValorComMargem()`: aplica margem de lucro sobre o custo
  - `atualizarValorAutomatico()`: atualiza o preço automaticamente
- ProdutoService modificado para calcular valor automaticamente em create/update
- ProdutoDTO expandido com campos `margemLucro` e `custoTotal`

**Frontend:**
- MateriaPrimaView:
  - Dropdown de unidade de medida com 10 opções
  - Campo numérico para peso por unidade (step 0.001)
  - Campo numérico para valor unitário (step 0.01)
  - Tabela com 7 colunas: Código, Nome, Estoque, Unidade, Peso/Unidade, Valor/Unidade, Ações
  - CSS scoped para centralização de conteúdo das células
- ProdutoView:
  - Campo para configurar margem de lucro (%, padrão 50%)
  - Remoção do campo manual de valor (agora calculado automaticamente)
  - Resumo visual em tempo real mostrando:
    * Custo Total das Matérias-Primas
    * Margem de Lucro aplicada
    * Valor de Venda resultante
  - Tabela expandida com 7 colunas: Código, Nome, Custo Total, Margem, Valor Venda, Composição, Ações
  - Função `calcularCustoTotal()`: busca valorUnidade de cada matéria-prima
  - Função `calcularValorVenda()`: aplica margem sobre custo
  - Função `calcularValor()`: sincroniza form.valor antes de salvar

**Testes:**
- MateriaPrimaServiceTest: construtores atualizados com 7 parâmetros (id, codigo, nome, qtd, unidade, peso, valor)
- ProducaoServiceTest: dados realistas adicionados (Aço: 7.8kg, R$50; Plástico: 0.95kg, R$15)
- Todos os testes validados e passando

**Validações:**
- `@NotBlank` em unidadeMedida
- `@PositiveOrZero` em pesoPorUnidade e valorUnidade
- `@PositiveOrZero` em margemLucro
- Campos opcionais: pesoPorUnidade e valorUnidade (nullable)

**Resultado:**
- Sistema agora calcula automaticamente o preço de venda dos produtos
- Gestão transparente de custos e margens de lucro
- Interface moderna com feedback visual em tempo real
- Dados persistidos em H2 file-based database

## Estado atual

- backend funcional em `http://localhost:8080`;
- frontend funcional em `http://localhost:5173`;
- persistência local habilitada e validada;
- sistema de precificação automática implementado e testado;
- interface aprimorada com visualização de custos e margens.

## Próximos incrementos sugeridos

1. autenticação/autorização;
2. testes automatizados de frontend;
3. containerização com Docker;
4. pipeline CI/CD;
5. banco gerenciado para ambiente produtivo.
