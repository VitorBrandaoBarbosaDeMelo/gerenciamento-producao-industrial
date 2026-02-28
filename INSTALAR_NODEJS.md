# Instalação do Node.js (Frontend)

## Objetivo

Preparar o ambiente para executar o frontend Vue com Vite.

## Opção 1: Instalação manual (recomendada)

1. Acesse `https://nodejs.org/`.
2. Baixe a versão LTS (Node.js 20+).
3. Execute o instalador com opções padrão.
4. Feche e reabra o terminal ou VS Code.

## Validação

```powershell
node --version
npm --version
```

Se os comandos retornarem versão, a instalação está concluída.

## Executar frontend após instalação

```powershell
cd frontend
npm install
npm run dev
```

Acesso: `http://localhost:5173`

## Problemas comuns

### `npm` não reconhecido
- reinicie terminal/VS Code;
- se necessário, reinicie a máquina para atualizar PATH.

### Falha ao instalar dependências

```powershell
cd frontend
Remove-Item node_modules -Recurse -Force
Remove-Item package-lock.json -ErrorAction SilentlyContinue
npm install
```

### Porta do Vite em uso
O Vite usará outra porta disponível e mostrará a URL no terminal.
