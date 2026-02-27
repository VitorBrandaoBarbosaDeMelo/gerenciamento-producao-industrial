# 📝 REGISTRO DE IMPLEMENTAÇÃO - PRODUCTION OPTIMIZER
## Sistema de Gerenciamento de Insumos e Otimização de Produção Industrial

**Data de Criação:** 27 de fevereiro de 2026  
**Desenvolvedor:** Vitor Brandão  
**Propósito:** Teste Prático Full Stack - P&D

---

## 🎯 OBJETIVO DO PROJETO

Construir uma aplicação Full Stack para gerenciamento de insumos e otimização de produção industrial, que permita:
- Gerenciar matérias-primas e produtos
- Calcular automaticamente a melhor estratégia de produção para maximizar lucro
- Interface intuitiva e código limpo

---

## 📦 TECNOLOGIAS E FERRAMENTAS UTILIZADAS

### Back-end
- **Java 17** - Linguagem principal
- **Spring Boot 3.2.2** - Framework para desenvolvimento REST API
- **Spring Data JPA** - Persistência de dados
- **H2 Database** - Banco de dados em memória (facilita desenvolvimento e testes)
- **Lombok** - Redução de código boilerplate
- **Maven** - Gerenciamento de dependências
- **JUnit 5 + Mockito** - Framework de testes unitários

### Front-end
- **Vue.js 3** - Framework JavaScript progressivo
- **Vite** - Build tool rápida
- **Axios** - Cliente HTTP para consumir API REST
- **CSS3** - Estilização responsiva

---

## 🏗️ ARQUITETURA IMPLEMENTADA

### Padrão em Camadas (Layered Architecture)

```
┌─────────────────────────────────────┐
│         FRONTEND (Vue.js)           │
│   - Components (Views)              │
│   - Services (API Calls)            │
└──────────────┬──────────────────────┘
               │ HTTP/REST
┌──────────────▼──────────────────────┐
│       BACKEND (Spring Boot)         │
├─────────────────────────────────────┤
│  Controller Layer                   │
│  - MateriaPrimaController           │
│  - ProdutoController                │
│  - ProducaoController               │
├─────────────────────────────────────┤
│  Service Layer                      │
│  - MateriaPrimaService              │
│  - ProdutoService                   │
│  - ProducaoService (Otimização)     │
├─────────────────────────────────────┤
│  Repository Layer                   │
│  - MateriaPrimaRepository           │
│  - ProdutoRepository                │
├─────────────────────────────────────┤
│  Model Layer                        │
│  - MateriaPrima                     │
│  - Produto                          │
│  - Composicao                       │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│       H2 Database (Memory)          │
└─────────────────────────────────────┘
```

---

## 📂 ESTRUTURA DE ARQUIVOS CRIADOS

### BACKEND (Spring Boot)

#### 1. Configuração Base
- **pom.xml** - Dependências Maven (Spring Boot, H2, Lombok, JUnit)
- **application.properties** - Configurações do H2, JPA, servidor e CORS
- **ProductionOptimizerApplication.java** - Classe principal Spring Boot

#### 2. Camada de Modelo (Model/Entity)
- **MateriaPrima.java**
  - Entidade JPA representando insumos
  - Campos: id, codigo, nome, quantidadeEstoque
  - Validações: código único, quantidade não negativa

- **Produto.java**
  - Entidade JPA representando produtos fabricáveis
  - Campos: id, codigo, nome, valor, composicao
  - Relacionamento OneToMany com Composicao

- **Composicao.java**
  - Entidade que representa a "receita" de um produto
  - Vincula Produto → MateriaPrima com quantidade necessária
  - Relacionamento ManyToOne bidirecional

#### 3. Camada de Repositório (Repository)
- **MateriaPrimaRepository.java**
  - Herda JpaRepository
  - Métodos: findByCodigo, existsByCodigo

- **ProdutoRepository.java**
  - Herda JpaRepository
  - Métodos: findByCodigo, existsByCodigo

#### 4. Camada de Serviço (Service)
- **MateriaPrimaService.java**
  - CRUD completo para matérias-primas
  - Validação de código único
  - Métodos: findAll, findById, create, update, delete

- **ProdutoService.java**
  - CRUD completo para produtos
  - Gerenciamento de composição (matérias-primas)
  - Conversão para DTO
  - Métodos: findAll, findById, create, update, delete, toDTO

- **ProducaoService.java** ⭐ *ALGORITMO DE OTIMIZAÇÃO*
  - **calcularOtimizacao()** - Método principal
  - **Algoritmo Greedy**: Prioriza produtos por maior valor
  - **Lógica implementada:**
    1. Carrega todos os produtos e estoque atual
    2. Ordena produtos por valor (decrescente)
    3. Para cada produto, calcula quantidade máxima produzível
    4. Aloca recursos virtualmente e atualiza estoque disponível
    5. Retorna lista de sugestões com valor total

#### 5. Camada de Controle (Controller)
- **MateriaPrimaController.java**
  - Endpoints REST para matérias-primas
  - CRUD completo com validação
  - CORS habilitado

- **ProdutoController.java**
  - Endpoints REST para produtos
  - CRUD completo com DTOs
  - CORS habilitado

- **ProducaoController.java**
  - Endpoint GET /api/producao/otimizar
  - Retorna sugestões de produção otimizadas

#### 6. DTOs (Data Transfer Objects)
- **ComposicaoDTO.java** - Transferência de dados de composição
- **ProdutoDTO.java** - Transferência de dados de produto
- **SugestaoProducaoDTO.java** - Resultado da otimização

#### 7. Testes Unitários
- **MateriaPrimaServiceTest.java**
  - 6 testes cobrindo CRUD completo
  - Mocks com Mockito
  - Testes de validação e exceções

- **ProducaoServiceTest.java**
  - 3 testes para algoritmo de otimização
  - Validação de priorização por valor
  - Cálculo correto de valor total

---

### FRONTEND (Vue.js)

#### 1. Configuração Base
- **package.json** - Dependências (Vue 3, Axios, Vite)
- **vite.config.js** - Configuração do Vite
- **index.html** - HTML principal
- **main.js** - Entry point da aplicação

#### 2. Serviços
- **api.js**
  - Cliente Axios configurado
  - Serviços organizados:
    - materiaPrimaService (CRUD)
    - produtoService (CRUD)
    - producaoService (otimizar)

#### 3. Componentes Vue
- **App.vue** - Componente raiz
  - Navegação entre views
  - Layout e estilização global
  - Sistema de abas

- **MateriaPrimaView.vue**
  - Listagem de matérias-primas
  - Modal para criar/editar
  - Funcionalidades: adicionar, editar, excluir
  - Feedback visual (success/error)

- **ProdutoView.vue**
  - Listagem de produtos com composição
  - Modal completo para criar/editar
  - Gerenciamento dinâmico de composição
  - Seleção de matérias-primas
  - Funcionalidades: adicionar, editar, excluir

- **OtimizacaoView.vue**
  - Botão para calcular otimização
  - Tabela de sugestões de produção
  - Cálculo de valor total de vendas
  - Resumo da estratégia de produção
  - Feedback quando não há sugestões

---

## 🎨 DESIGN E UX

### Interface
- **Design Moderno**: Gradientes, sombras, transições suaves
- **Cores**: Paleta roxa/violeta (#667eea, #764ba2)
- **Responsividade**: Adaptável a diferentes tamanhos de tela
- **Feedback Visual**: Mensagens de sucesso e erro destacadas

### Experiência do Usuário
- **Navegação Intuitiva**: Sistema de abas claro
- **Modais**: Para criar/editar sem sair da página
- **Confirmações**: Para ações destrutivas (excluir)
- **Loading States**: Indicadores de carregamento
- **Tabelas Organizadas**: Dados bem estruturados

---

## ✅ REQUISITOS ATENDIDOS

### Obrigatórios
✅ **Back-end**: Java com Spring Boot  
✅ **Front-end**: Vue.js  
✅ **Banco de Dados**: Relacional (H2)  
✅ **CRUD**: Matérias-Primas completo  
✅ **CRUD**: Produtos completo  
✅ **Cálculo de Produção**: API + Tela funcional  
✅ **Algoritmo**: Prioriza produtos por maior retorno  
✅ **Clean Code**: Código limpo, bem estruturado, comentado  
✅ **Testes Unitários Back-end**: JUnit + Mockito  

### Diferenciais
⚠️ **Testes Front-end**: Não implementados (fora do escopo mínimo)  
⚠️ **Internacionalização**: Não implementada (fora do escopo mínimo)  

---

## 🧮 ALGORITMO DE OTIMIZAÇÃO - DETALHES

### Abordagem: Greedy Algorithm (Algoritmo Guloso)

**Por que Greedy?**
- Simples de implementar e entender
- Eficiente computacionalmente O(n log n)
- Adequado para o problema proposto
- Fácil de testar e validar

**Lógica Passo a Passo:**

```java
1. Buscar todos os produtos e matérias-primas
2. Criar mapa de estoque disponível
3. Ordenar produtos por VALOR (maior → menor)
4. Para cada produto (em ordem):
   a. Calcular quantidade máxima produzível:
      - Verificar cada matéria-prima da composição
      - Calcular: estoque_disponível / quantidade_necessária
      - Escolher o MÍNIMO (gargalo)
   b. Se quantidade > 0:
      - Criar sugestão de produção
      - Atualizar estoque virtual (descontar materiais)
   c. Próximo produto
5. Retornar lista de sugestões ordenadas
```

**Exemplo Prático:**

Estoque:
- Aço: 100kg
- Plástico: 50kg

Produtos:
- Cadeira: R$ 200 (usa 10kg Aço, 5kg Plástico)
- Mesa: R$ 150 (usa 15kg Aço)

Resultado:
1. Cadeira tem maior valor → prioridade
2. Pode fazer: min(100/10=10, 50/5=10) = 10 cadeiras
3. Estoque após: Aço=0, Plástico=0
4. Mesa não pode ser produzida (sem estoque)

Sugestão: **10 Cadeiras = R$ 2.000**

---

## 🔧 PRINCÍPIOS DE CLEAN CODE APLICADOS

### 1. **Naming (Nomenclatura)**
- Classes: PascalCase descritivo (MateriaPrimaService)
- Métodos: camelCase verboso (calcularOtimizacao)
- Variáveis: camelCase auto-explicativas (estoqueDisponivel)

### 2. **Single Responsibility (Responsabilidade Única)**
- Controllers: Apenas roteamento HTTP
- Services: Lógica de negócio
- Repositories: Acesso a dados
- DTOs: Transferência de dados

### 3. **DRY (Don't Repeat Yourself)**
- Reutilização de métodos (toDTO, getEstoqueAtual)
- Serviços compartilhados no front-end

### 4. **Código Auto-Explicativo**
- Comentários apenas onde necessário
- Nomes descritivos dispensam comentários
- Javadoc em métodos complexos

### 5. **Tratamento de Erros**
- Exceções com mensagens claras
- Validações com Bean Validation
- Feedback adequado ao usuário

---

## 📊 MÉTRICAS DO PROJETO

### Back-end
- **Arquivos Java**: 15
- **Linhas de Código**: ~800 (sem contar testes)
- **Testes Unitários**: 9 testes
- **Endpoints REST**: 11
- **Entidades JPA**: 3

### Front-end
- **Componentes Vue**: 4
- **Linhas de Código**: ~600
- **Requisições API**: Todas integradas

### Documentação
- **README.md**: Completo com instruções detalhadas
- **Arquivo de Registro**: Este documento

---

## 🚀 COMO EXECUTAR

### Backend
```bash
cd backend
mvn spring-boot:run
```
Acesso: http://localhost:8080

### Frontend
```bash
cd frontend
npm install
npm run dev
```
Acesso: http://localhost:5173

### Testes
```bash
cd backend
mvn test
```

---

## 📈 PRÓXIMOS PASSOS (Melhorias Futuras)

1. **Algoritmo mais sofisticado**: Programação Linear ou Branch & Bound
2. **Testes Front-end**: Vitest + Vue Test Utils
3. **Internacionalização**: i18n para PT/EN
4. **Dockerização**: Containers para fácil deploy
5. **CI/CD**: Pipeline automatizado
6. **Banco Persistente**: PostgreSQL em produção
7. **Autenticação**: JWT para segurança
8. **Histórico de Produção**: Rastreamento de decisões anteriores

---

## ✨ DESTAQUES DA IMPLEMENTAÇÃO

### 🎯 Pontos Fortes

1. **Código Limpo e Organizado**
   - Arquitetura em camadas bem definida
   - Separação clara de responsabilidades
   - Fácil manutenção e extensão

2. **Algoritmo de Otimização Funcional**
   - Resolve o problema proposto eficientemente
   - Código testável e validado
   - Lógica clara e documentada

3. **Interface Intuitiva**
   - UX pensada para usuário final
   - Feedback visual adequado
   - Navegação fluida

4. **Testes Abrangentes**
   - Cobertura dos principais fluxos
   - Mocks adequados
   - Validação de regras de negócio

5. **Documentação Completa**
   - README detalhado
   - Registro de implementação
   - Código auto-explicativo

### 🔍 Decisões Técnicas Importantes

1. **H2 Database**: Escolhido pela simplicidade e facilidade de setup
2. **Greedy Algorithm**: Balanceamento entre simplicidade e eficácia
3. **DTOs**: Separação entre modelo de dados e transferência
4. **Vue.js Composition API**: Abordagem moderna e reativa
5. **Axios**: Cliente HTTP confiável e simples

---

## 📝 CONCLUSÃO

Este projeto demonstra a capacidade de:
- ✅ Desenvolver aplicações Full Stack completas
- ✅ Implementar algoritmos de otimização
- ✅ Escrever código limpo e testável
- ✅ Criar interfaces intuitivas
- ✅ Documentar adequadamente
- ✅ Seguir boas práticas de desenvolvimento

**Status Final**: ✅ PROJETO COMPLETO E FUNCIONAL

---

*Desenvolvido com dedicação para o teste técnico Full Stack - P&D*  
*Fevereiro de 2026*
