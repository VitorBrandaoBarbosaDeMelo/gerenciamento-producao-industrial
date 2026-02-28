# Guia Rápido de Início

## Executar em 3 passos

### 1) Backend (terminal 1)

```bash
cd backend
mvn spring-boot:run
```

API disponível em `http://localhost:8080`.

### 2) Frontend (terminal 2)

```bash
cd frontend
npm install
npm run dev
```

Interface disponível em `http://localhost:5173`.

### 3) Fluxo de uso

1. Cadastre matérias-primas.
2. Cadastre produtos e composição.
3. Execute a otimização.

## Persistência

Os dados são persistidos localmente em:
- `backend/data/productiondb.mv.db`

Ao reiniciar o ambiente, os registros permanecem.

## Console do Banco (H2)

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:file:./data/productiondb`
- User Name: `sa`
- Password: *(em branco)*

## Testes

```bash
cd backend
mvn test
```

## Erros comuns

- `npm` não reconhecido: reinicie terminal/VS Code após instalar Node.js.
- porta 8080 ocupada: finalize o processo em execução e suba o backend novamente.
- frontend sem dados: confirme backend ativo em `http://localhost:8080`.
