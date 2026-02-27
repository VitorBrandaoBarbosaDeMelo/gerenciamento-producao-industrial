# 🚀 GUIA RÁPIDO DE INÍCIO

## ⚡ Iniciar a Aplicação em 3 Passos

### 1️⃣ Executar o Back-end (Terminal 1)

```bash
cd backend
mvn spring-boot:run
```

✅ Servidor rodando em: **http://localhost:8080**

### 2️⃣ Executar o Front-end (Terminal 2)

```bash
cd frontend
npm install
npm run dev
```

✅ Aplicação acessível em: **http://localhost:5173**

### 3️⃣ Usar a Aplicação

1. Abra o navegador em **http://localhost:5173**
2. Cadastre algumas matérias-primas
3. Cadastre produtos com suas composições
4. Acesse "Otimização" e clique em "Calcular Otimização"
5. Veja as sugestões de produção!

---

## 📊 Exemplo de Teste Rápido

### Passo 1: Cadastrar Matérias-Primas
- **MP001** - Aço - Estoque: 500kg
- **MP002** - Plástico - Estoque: 300kg
- **MP003** - Alumínio - Estoque: 200kg

### Passo 2: Cadastrar Produtos
- **Produto A**
  - Código: P001
  - Nome: Cadeira Premium
  - Valor: R$ 250,00
  - Composição: 10kg Aço + 5kg Plástico

- **Produto B**
  - Código: P002
  - Nome: Mesa Executiva
  - Valor: R$ 400,00
  - Composição: 20kg Aço + 8kg Alumínio

- **Produto C**
  - Código: P003
  - Nome: Armário Compacto
  - Valor: R$ 180,00
  - Composição: 15kg Aço

### Passo 3: Calcular Otimização
Acesse a aba "Otimização" e clique em "Calcular Otimização"

**Resultado Esperado:**
- Mesa Executiva (maior valor): X unidades
- Cadeira Premium: Y unidades
- Armário Compacto: Z unidades
- **Valor Total de Vendas: R$ XXXX,XX**

---

## 🧪 Executar Testes

```bash
cd backend
mvn test
```

---

## 🔍 Acessar Console do Banco H2

**URL:** http://localhost:8080/h2-console

**Configurações:**
- JDBC URL: `jdbc:h2:mem:productiondb`
- Username: `sa`
- Password: *(deixar vazio)*

---

## ❓ Problemas Comuns

### Backend não inicia
- Verifique se tem Java 17+ instalado: `java -version`
- Verifique se a porta 8080 está livre

### Frontend não inicia
- Verifique se tem Node.js instalado: `node -v`
- Execute `npm install` antes de `npm run dev`

### Erro de CORS
- Certifique-se que o backend está rodando na porta 8080
- Certifique-se que o frontend está rodando na porta 5173

---

Pronto para usar! 🎉
