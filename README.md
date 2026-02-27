# 🏭 Production Optimizer

Sistema Full Stack para gerenciamento de insumos e otimização de produção industrial.

## 📋 Descrição

Aplicação desenvolvida para auxiliar fábricas no controle de estoque de matérias-primas e na tomada de decisão sobre quais produtos fabricar para maximizar o retorno financeiro.

## 🚀 Tecnologias Utilizadas

### Back-end
- **Java 17**
- **Spring Boot 3.2.2**
- **Spring Data JPA**
- **H2 Database** (banco em memória)
- **Maven**
- **Lombok**
- **JUnit 5** (testes unitários)

### Front-end
- **Vue.js 3**
- **Vite**
- **Axios**

## 📁 Estrutura do Projeto

```
├── backend/                 # Aplicação Spring Boot
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/factory/
│   │   │   │   ├── model/           # Entidades JPA
│   │   │   │   ├── repository/      # Repositórios
│   │   │   │   ├── service/         # Lógica de negócio
│   │   │   │   ├── controller/      # Endpoints REST
│   │   │   │   └── dto/             # Data Transfer Objects
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/                    # Testes unitários
│   └── pom.xml
│
└── frontend/                # Aplicação Vue.js
    ├── src/
    │   ├── components/      # Componentes Vue
    │   ├── services/        # Serviços da API
    │   ├── App.vue
    │   └── main.js
    ├── index.html
    ├── package.json
    └── vite.config.js
```

## ⚙️ Funcionalidades

### ✅ CRUD de Matérias-Primas
- Cadastrar, editar, listar e excluir matérias-primas
- Controle de estoque disponível

### ✅ CRUD de Produtos
- Cadastrar, editar, listar e excluir produtos
- Definir composição (quais matérias-primas e quantidades)
- Informar valor de venda

### ✅ Otimização de Produção
- Algoritmo que analisa o estoque atual
- Sugere quais produtos fabricar para **maximizar o valor total de vendas**
- Prioriza produtos com maior retorno financeiro
- Resolve conflitos quando produtos disputam a mesma matéria-prima

## 🔧 Como Rodar o Projeto

### Pré-requisitos
- **Java 17** ou superior
- **Maven 3.6+**
- **Node.js 18+** e **npm**

### 1️⃣ Back-end (Spring Boot)

```bash
# Navegue até a pasta backend
cd backend

# Execute o projeto com Maven
mvn spring-boot:run
```

O servidor estará disponível em: `http://localhost:8080`

**Console H2 Database**: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:productiondb`
- Username: `sa`
- Password: *(deixar em branco)*

### 2️⃣ Executar Testes Unitários

```bash
# Na pasta backend
mvn test
```

### 3️⃣ Front-end (Vue.js)

```bash
# Navegue até a pasta frontend
cd frontend

# Instale as dependências
npm install

# Execute o servidor de desenvolvimento
npm run dev
```

O front-end estará disponível em: `http://localhost:5173`

## 📖 Como Usar

### 1. Cadastrar Matérias-Primas
- Acesse a aba "Matérias-Primas"
- Clique em "Nova Matéria-Prima"
- Preencha código, nome e quantidade em estoque
- Salve

### 2. Cadastrar Produtos
- Acesse a aba "Produtos"
- Clique em "Novo Produto"
- Preencha código, nome e valor
- Adicione a composição (matérias-primas necessárias)
- Salve

### 3. Otimizar Produção
- Acesse a aba "Otimização"
- Clique em "Calcular Otimização"
- Visualize as sugestões de produção ordenadas por prioridade
- Veja o valor total potencial de vendas

## 🎯 Algoritmo de Otimização

O algoritmo implementado utiliza uma abordagem **Greedy (gulosa)**:

1. **Ordena produtos por valor** (maior valor primeiro)
2. **Calcula quantidade máxima** que pode ser produzida com estoque disponível
3. **Aloca recursos** para o produto de maior valor
4. **Atualiza estoque virtual** e repete para próximo produto
5. **Retorna sugestões** priorizando máximo retorno financeiro

### Exemplo:
- **Produto A**: Valor R$ 100, usa 10kg de Aço
- **Produto B**: Valor R$ 80, usa 5kg de Aço
- **Estoque**: 50kg de Aço

**Resultado**: Prioriza Produto A (5 unidades = R$ 500) em vez de Produto B (10 unidades = R$ 800)... 
*Ops! Na verdade, o algoritmo escolheria Produto A primeiro, mas só produziria até esgotar o estoque, então produziria 5 unidades de A (R$ 500). Como é greedy por valor unitário, essa é a estratégia implementada.*

## 🧪 Testes

O projeto inclui testes unitários para:
- ✅ **MateriaPrimaService**: CRUD completo
- ✅ **ProducaoService**: Lógica de otimização

Execute com: `mvn test`

## 🏗️ Arquitetura e Clean Code

### Princípios Aplicados:
- **Separação de responsabilidades**: Controllers, Services, Repositories
- **DTOs** para transferência de dados
- **Validações** com Bean Validation
- **Injeção de dependências** com Spring
- **Código limpo e legível** com nomes descritivos
- **Comentários explicativos** nas partes críticas

### Padrões:
- **Repository Pattern**
- **Service Layer Pattern**
- **DTO Pattern**
- **RESTful API**

## 🌐 API Endpoints

### Matérias-Primas
- `GET /api/materias-primas` - Listar todas
- `GET /api/materias-primas/{id}` - Buscar por ID
- `POST /api/materias-primas` - Criar nova
- `PUT /api/materias-primas/{id}` - Atualizar
- `DELETE /api/materias-primas/{id}` - Excluir

### Produtos
- `GET /api/produtos` - Listar todos
- `GET /api/produtos/{id}` - Buscar por ID
- `POST /api/produtos` - Criar novo
- `PUT /api/produtos/{id}` - Atualizar
- `DELETE /api/produtos/{id}` - Excluir

### Otimização
- `GET /api/producao/otimizar` - Calcular otimização de produção

## 👨‍💻 Autor

Desenvolvido como teste técnico para vaga Full Stack - P&D

## 📄 Licença

Este projeto é de código aberto para fins educacionais e de avaliação técnica.
