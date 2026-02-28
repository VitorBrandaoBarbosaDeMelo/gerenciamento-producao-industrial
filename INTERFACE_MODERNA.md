# Interface Moderna

## Objetivo

Padronizar a experiência do usuário com visual moderno, linguagem em português e navegação direta entre os três módulos do sistema.

## Alterações Implementadas

### Estrutura visual
- header com identidade do produto;
- navegação por abas: Matérias-Primas, Produtos e Otimização;
- cards e tabelas com hierarquia clara;
- padronização de espaçamento e tipografia.

### Estilo e usabilidade
- tema visual contemporâneo com gradientes e contraste adequado;
- feedback de ações (sucesso/erro) de forma clara;
- transições suaves entre visões;
- consistência de botões de ação (novo, editar, excluir, salvar).

### Conteúdo
- textos e rótulos em português por padrão;
- mensagens orientadas ao fluxo de cadastro e operação.

## Como visualizar

```bash
cd frontend
npm install
npm run dev
```

Acesse: `http://localhost:5173`

## Observações

- o frontend depende do backend ativo em `http://localhost:8080`;
- caso a API esteja indisponível, a interface exibirá mensagens de erro de carregamento.
