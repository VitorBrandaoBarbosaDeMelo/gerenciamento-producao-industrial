<template>
  <div id="app">
    <header>
      <h1>🏭 Production Optimizer</h1>
      <p>Sistema de Gerenciamento de Insumos e Otimização de Produção</p>
    </header>

    <nav>
      <button @click="currentView = 'materias-primas'" :class="{ active: currentView === 'materias-primas' }">
        Matérias-Primas
      </button>
      <button @click="currentView = 'produtos'" :class="{ active: currentView === 'produtos' }">
        Produtos
      </button>
      <button @click="currentView = 'otimizacao'" :class="{ active: currentView === 'otimizacao' }">
        Otimização
      </button>
    </nav>

    <main>
      <MateriaPrimaView v-if="currentView === 'materias-primas'" />
      <ProdutoView v-if="currentView === 'produtos'" />
      <OtimizacaoView v-if="currentView === 'otimizacao'" />
    </main>
  </div>
</template>

<script>
import { ref } from 'vue'
import MateriaPrimaView from './components/MateriaPrimaView.vue'
import ProdutoView from './components/ProdutoView.vue'
import OtimizacaoView from './components/OtimizacaoView.vue'

export default {
  name: 'App',
  components: {
    MateriaPrimaView,
    ProdutoView,
    OtimizacaoView
  },
  setup() {
    const currentView = ref('materias-primas')
    return { currentView }
  }
}
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  background: #f5f5f5;
}

#app {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

header {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  padding: 30px;
  border-radius: 10px;
  margin-bottom: 20px;
  text-align: center;
}

header h1 {
  font-size: 2.5em;
  margin-bottom: 10px;
}

nav {
  display: flex;
  gap: 10px;
  margin-bottom: 20px;
}

nav button {
  flex: 1;
  padding: 15px;
  border: none;
  background: white;
  color: #667eea;
  font-size: 1em;
  font-weight: bold;
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.3s;
  box-shadow: 0 2px 5px rgba(0,0,0,0.1);
}

nav button:hover {
  background: #667eea;
  color: white;
  transform: translateY(-2px);
  box-shadow: 0 4px 10px rgba(0,0,0,0.2);
}

nav button.active {
  background: #667eea;
  color: white;
}

main {
  background: white;
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
}

.btn {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 0.9em;
  transition: all 0.3s;
}

.btn-primary {
  background: #667eea;
  color: white;
}

.btn-primary:hover {
  background: #5568d3;
}

.btn-success {
  background: #48bb78;
  color: white;
}

.btn-success:hover {
  background: #38a169;
}

.btn-danger {
  background: #f56565;
  color: white;
}

.btn-danger:hover {
  background: #e53e3e;
}

.btn-warning {
  background: #ed8936;
  color: white;
}

.btn-warning:hover {
  background: #dd6b20;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

table th,
table td {
  padding: 12px;
  text-align: left;
  border-bottom: 1px solid #e2e8f0;
}

table th {
  background: #f7fafc;
  font-weight: bold;
  color: #2d3748;
}

table tr:hover {
  background: #f7fafc;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
  color: #2d3748;
}

.form-group input,
.form-group select {
  width: 100%;
  padding: 10px;
  border: 1px solid #e2e8f0;
  border-radius: 5px;
  font-size: 1em;
}

.form-group input:focus,
.form-group select:focus {
  outline: none;
  border-color: #667eea;
}

.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background: white;
  padding: 30px;
  border-radius: 10px;
  max-width: 600px;
  width: 90%;
  max-height: 80vh;
  overflow-y: auto;
}

.actions {
  display: flex;
  gap: 10px;
  margin-top: 20px;
}

.error {
  background: #fed7d7;
  color: #c53030;
  padding: 10px;
  border-radius: 5px;
  margin-bottom: 15px;
}

.success {
  background: #c6f6d5;
  color: #2f855a;
  padding: 10px;
  border-radius: 5px;
  margin-bottom: 15px;
}
</style>
