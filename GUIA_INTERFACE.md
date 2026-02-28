# Guia de Interface

## Acesso

- Interface principal: `http://localhost:5173`
- API backend: `http://localhost:8080`
- Console H2: `http://localhost:8080/h2-console`

## Fluxo recomendado

1. Cadastre matérias-primas.
2. Cadastre produtos e composições.
3. Rode a otimização para obter sugestões de produção.

## Módulos da aplicação

### Matérias-Primas
- cadastro, edição e exclusão;
- visualização em tabela;
- controle de estoque disponível.

### Produtos
- cadastro de código, nome e valor;
- composição por matérias-primas;
- edição e exclusão.

### Otimização
- execução do cálculo de produção;
- lista de sugestões geradas;
- total de valor potencial.

## Requisitos para funcionamento

- backend ativo em `http://localhost:8080`;
- frontend ativo em `http://localhost:5173`.

## Persistência

Os dados não são temporários: permanecem salvos no arquivo local do H2.

- arquivo: `backend/data/productiondb.mv.db`

## Diagnóstico rápido

- se a tela mostrar erro ao carregar dados, valide primeiro o backend;
- se o frontend não abrir, verifique se `npm run dev` está em execução;
- se H2 abrir e API não responder, revisar logs do backend.
