# Tutorial Completo de Execução

Este guia descreve o setup completo do projeto para desenvolvimento local.

## 1. Pré-requisitos

- Java 21
- Maven 3.9+
- Node.js 20+ (LTS)
- npm 10+

Validação rápida:

```powershell
java -version
mvn -version
node --version
npm --version
```

## 2. Executar Backend

```powershell
cd backend
mvn spring-boot:run
```

Backend disponível em `http://localhost:8080`.

## 3. Executar Frontend

```powershell
cd frontend
npm install
npm run dev
```

Frontend disponível em `http://localhost:5173`.

## 4. Fluxo funcional sugerido

1. Cadastre matérias-primas.
2. Cadastre produtos com composição.
3. Acesse a aba de otimização e execute o cálculo.

## 5. Persistência de dados

O backend está configurado para persistência local com H2 em arquivo.

- JDBC URL: `jdbc:h2:file:./data/productiondb`
- arquivo físico: `backend/data/productiondb.mv.db`

Os dados permanecem após reiniciar serviços e editor.

## 6. Console H2

URL: `http://localhost:8080/h2-console`

Credenciais:
- JDBC URL: `jdbc:h2:file:./data/productiondb`
- User Name: `sa`
- Password: *(em branco)*

## 7. Executar testes

```powershell
cd backend
mvn test
```

## 8. Solução de problemas

### `npm` não reconhecido
Feche e reabra o terminal (ou VS Code) após instalar o Node.js.

### Porta 8080 em uso
Finalize o processo da porta e execute o backend novamente.

### Frontend sem dados
Verifique se `http://localhost:8080/api/materias-primas` está respondendo.

### Erro de dependências do frontend

```powershell
cd frontend
Remove-Item node_modules -Recurse -Force
Remove-Item package-lock.json -ErrorAction SilentlyContinue
npm install
```

## 9. Estrutura mínima de operação

Durante uso normal, mantenha dois terminais:
- terminal 1: backend
- terminal 2: frontend
