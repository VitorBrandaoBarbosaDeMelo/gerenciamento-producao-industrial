# Sistema Otimizador de Produção

Aplicação full stack para gestão de matérias-primas, cadastro de produtos e sugestão de produção com foco em retorno financeiro.

## Visão Geral

O sistema permite:
- cadastrar e manter matérias-primas;
- cadastrar produtos e suas composições;
- executar um cálculo de otimização de produção com base no estoque atual;
- persistir os dados localmente em banco H2 baseado em arquivo.

## Stack Tecnológica

### Backend
- Java 21
- Spring Boot 3.2.2
- Spring Data JPA
- H2 Database (modo arquivo)
- Maven
- JUnit 5 + Mockito

### Frontend
- Vue 3
- Vite
- Axios

## Arquitetura

Projeto organizado em camadas:
- `controller`: endpoints REST;
- `service`: regras de negócio;
- `repository`: acesso a dados;
- `model` e `dto`: entidades e contratos de API.

## Funcionalidades

### Matérias-primas
- criar, listar, editar e excluir;
- controle de estoque por item.

### Produtos
- criar, listar, editar e excluir;
- composição por matérias-primas e quantidades;
- definição de valor de venda.

### Otimização
- cálculo de sugestão de produção com abordagem gulosa (greedy);
- priorização por valor de produto;
- uso do estoque disponível como restrição;
- total de valor potencial estimado.

## Persistência de Dados

O projeto está configurado com persistência em arquivo local:
- `spring.datasource.url=jdbc:h2:file:./data/productiondb`
- arquivo gerado: `backend/data/productiondb.mv.db`

Com isso, os dados permanecem após reiniciar backend, frontend ou VS Code.

## Pré-requisitos

- Java 21
- Maven 3.9+
- Node.js 20+ (LTS recomendado)
- npm 10+

## Como Executar

### 1) Subir backend

```bash
cd backend
mvn spring-boot:run
```

Backend: `http://localhost:8080`

### 2) Subir frontend

```bash
cd frontend
npm install
npm run dev
```

Frontend: `http://localhost:5173`

### 3) Rodar testes backend

```bash
cd backend
mvn test
```

## Console H2

URL: `http://localhost:8080/h2-console`

Configuração para login:
- JDBC URL: `jdbc:h2:file:./data/productiondb`
- User Name: `sa`
- Password: *(em branco)*

## Endpoints Principais

### Matérias-primas
- `GET /api/materias-primas`
- `GET /api/materias-primas/{id}`
- `POST /api/materias-primas`
- `PUT /api/materias-primas/{id}`
- `DELETE /api/materias-primas/{id}`

### Produtos
- `GET /api/produtos`
- `GET /api/produtos/{id}`
- `POST /api/produtos`
- `PUT /api/produtos/{id}`
- `DELETE /api/produtos/{id}`

### Otimização
- `GET /api/producao/otimizar`

## Troubleshooting

- **Porta 8080 em uso**: encerrar processo da porta antes de subir backend.
- **`npm` não reconhecido**: reiniciar terminal/VS Code após instalar Node.js.
- **Erro de conexão no frontend**: confirmar backend ativo em `http://localhost:8080`.

## Status do Projeto

- Upgrade de runtime Java realizado para Java 21.
- Frontend atualizado com layout moderno e conteúdo em português.
- Persistência local habilitada via H2 em arquivo.

## Licença

Uso educacional e de avaliação técnica.
