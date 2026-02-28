<template>
  <div id="app">
    <!-- Header Moderno -->
    <header class="header-container">
      <div class="header-content">
        <div class="logo-section">
          <div class="logo-icon">🏭</div>
          <div>
            <h1>Sistema de Produção</h1>
            <p>Gestão Inteligente de Insumos e Otimização</p>
          </div>
        </div>
        <div class="header-stats">
          <div class="stat-card">
            <span class="stat-value">💼</span>
            <span class="stat-label">Produção</span>
          </div>
        </div>
      </div>
    </header>

    <!-- Navegação com Tabs Modernas -->
    <nav class="navigation">
      <div class="nav-container">
        <button 
          @click="currentView = 'materias-primas'" 
          :class="{ active: currentView === 'materias-primas' }"
          class="nav-tab">
          <span class="tab-icon">📦</span>
          <span class="tab-text">Matérias-Primas</span>
        </button>
        <button 
          @click="currentView = 'produtos'" 
          :class="{ active: currentView === 'produtos' }"
          class="nav-tab">
          <span class="tab-icon">🎯</span>
          <span class="tab-text">Produtos</span>
        </button>
        <button 
          @click="currentView = 'otimizacao'" 
          :class="{ active: currentView === 'otimizacao' }"
          class="nav-tab">
          <span class="tab-icon">📊</span>
          <span class="tab-text">Otimização</span>
        </button>
      </div>
    </nav>

    <!-- Conteúdo Principal -->
    <main class="main-content">
      <transition name="fade" mode="out-in">
        <div :key="currentView">
          <MateriaPrimaView v-if="currentView === 'materias-primas'" />
          <ProdutoView v-if="currentView === 'produtos'" />
          <OtimizacaoView v-if="currentView === 'otimizacao'" />
        </div>
      </transition>
    </main>

    <!-- Footer -->
    <footer class="footer">
      <p>© 2026 Sistema de Produção • Desenvolvido com ☕ e Java 21</p>
    </footer>
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
/* Reset e Fonte */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: 'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI', sans-serif;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  background-attachment: fixed;
  min-height: 100vh;
}

#app {
  max-width: 1400px;
  margin: 0 auto;
  padding: 20px;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

/* Header Moderno */
.header-container {
  background: rgba(255, 255, 255, 0.98);
  backdrop-filter: blur(10px);
  border-radius: 20px;
  padding: 25px 35px;
  margin-bottom: 25px;
  box-shadow: 0 10px 40px rgba(0, 0, 0, 0.15);
  animation: slideDown 0.6s ease-out;
}

@keyframes slideDown {
  from {
    opacity: 0;
    transform: translateY(-20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.header-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.logo-section {
  display: flex;
  align-items: center;
  gap: 20px;
}

.logo-icon {
  font-size: 3.5em;
  animation: pulse 2s ease-in-out infinite;
}

@keyframes pulse {
  0%, 100% {
    transform: scale(1);
  }
  50% {
    transform: scale(1.05);
  }
}

.header-content h1 {
  font-size: 2.2em;
  font-weight: 800;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  margin-bottom: 5px;
}

.header-content p {
  color: #64748b;
  font-size: 1em;
  font-weight: 500;
}

.header-stats {
  display: flex;
  gap: 15px;
}

.stat-card {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  padding: 15px 25px;
  border-radius: 12px;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 5px;
  box-shadow: 0 4px 15px rgba(102, 126, 234, 0.3);
}

.stat-value {
  font-size: 1.8em;
  font-weight: 700;
  color: white;
}

.stat-label {
  font-size: 0.75em;
  color: rgba(255, 255, 255, 0.9);
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.05em;
}

/* Navegação Moderna */
.navigation {
  margin-bottom: 25px;
  animation: slideDown 0.7s ease-out;
}

.nav-container {
  background: rgba(255, 255, 255, 0.98);
  backdrop-filter: blur(10px);
  border-radius: 16px;
  padding: 8px;
  display: flex;
  gap: 8px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
}

.nav-tab {
  flex: 1;
  padding: 16px 24px;
  border: none;
  background: transparent;
  color: #64748b;
  font-size: 1em;
  font-weight: 600;
  border-radius: 12px;
  cursor: pointer;
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  position: relative;
  overflow: hidden;
}

.nav-tab::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  opacity: 0;
  transition: opacity 0.3s;
  border-radius: 12px;
}

.nav-tab .tab-icon,
.nav-tab .tab-text {
  position: relative;
  z-index: 1;
}

.nav-tab .tab-icon {
  font-size: 1.4em;
}

.nav-tab:hover {
  color: #667eea;
  transform: translateY(-2px);
}

.nav-tab.active {
  color: white;
}

.nav-tab.active::before {
  opacity: 1;
}

/* Conteúdo Principal */
.main-content {
  flex: 1;
  background: rgba(255, 255, 255, 0.98);
  backdrop-filter: blur(10px);
  padding: 35px;
  border-radius: 20px;
  box-shadow: 0 10px 40px rgba(0, 0, 0, 0.15);
  margin-bottom: 20px;
  animation: fadeIn 0.8s ease-out;
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: scale(0.95);
  }
  to {
    opacity: 1;
    transform: scale(1);
  }
}

/* Transições */
.fade-enter-active, .fade-leave-active {
  transition: all 0.3s ease;
}

.fade-enter-from {
  opacity: 0;
  transform: translateX(-20px);
}

.fade-leave-to {
  opacity: 0;
  transform: translateX(20px);
}

/* Botões */
.btn {
  padding: 12px 24px;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  font-size: 0.95em;
  font-weight: 600;
  transition: all 0.3s;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  font-family: 'Inter', sans-serif;
}

.btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
}

.btn:active {
  transform: translateY(0);
}

.btn-primary {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
}

.btn-success {
  background: linear-gradient(135deg, #34d399 0%, #10b981 100%);
  color: white;
}

.btn-danger {
  background: linear-gradient(135deg, #f87171 0%, #ef4444 100%);
  color: white;
}

.btn-warning {
  background: linear-gradient(135deg, #fbbf24 0%, #f59e0b 100%);
  color: white;
}

.btn-info {
  background: linear-gradient(135deg, #60a5fa 0%, #3b82f6 100%);
  color: white;
  margin-left: 8px;
}

/* Tabelas */
table {
  width: 100%;
  border-collapse: separate;
  border-spacing: 0;
  margin-top: 20px;
  overflow: hidden;
  border-radius: 12px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
}

table th,
table td {
  padding: 16px;
  text-align: left;
}

table th {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  font-weight: 700;
  text-transform: uppercase;
  font-size: 0.85em;
  letter-spacing: 0.05em;
}

table tbody tr {
  background: white;
  transition: all 0.3s;
}

table tbody tr:nth-child(even) {
  background: #f8fafc;
}

table tbody tr:hover {
  background: #f1f5f9;
  transform: scale(1.01);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
}

table td {
  border-bottom: 1px solid #e2e8f0;
  color: #334155;
}

table tfoot tr {
  background: linear-gradient(135deg, #f8fafc 0%, #e2e8f0 100%);
  font-weight: 700;
}

table tfoot td {
  border-bottom: none;
  padding: 20px 16px;
}

/* Formulários */
.form-group {
  margin-bottom: 20px;
}

.form-group label {
  display: block;
  margin-bottom: 8px;
  font-weight: 600;
  color: #334155;
  font-size: 0.95em;
}

.form-group input,
.form-group select {
  width: 100%;
  padding: 12px 16px;
  border: 2px solid #e2e8f0;
  border-radius: 10px;
  font-size: 1em;
  font-family: 'Inter', sans-serif;
  transition: all 0.3s;
}

.form-group input:focus,
.form-group select:focus {
  outline: none;
  border-color: #667eea;
  box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
}

/* Modal */
.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(15, 23, 42, 0.7);
  backdrop-filter: blur(4px);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
  animation: fadeIn 0.3s;
}

.modal-content {
  background: white;
  padding: 35px;
  border-radius: 20px;
  max-width: 650px;
  width: 90%;
  max-height: 85vh;
  overflow-y: auto;
  box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
  animation: slideUp 0.4s ease-out;
}

@keyframes slideUp {
  from {
    opacity: 0;
    transform: translateY(30px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.modal-content h3 {
  font-size: 1.6em;
  margin-bottom: 25px;
  color: #1e293b;
  font-weight: 700;
}

.modal-content h4 {
  font-size: 1.2em;
  margin: 25px 0 15px 0;
  color: #334155;
  font-weight: 600;
}

.composition-item {
  border: 2px solid #e2e8f0;
  padding: 20px;
  margin: 15px 0;
  border-radius: 12px;
  background: #f8fafc;
  transition: all 0.3s;
}

.composition-item:hover {
  border-color: #667eea;
  box-shadow: 0 4px 12px rgba(102, 126, 234, 0.1);
}

.actions {
  display: flex;
  gap: 12px;
  margin-top: 25px;
}

/* Alertas */
.error {
  background: linear-gradient(135deg, #fee2e2 0%, #fecaca 100%);
  color: #991b1b;
  padding: 14px 18px;
  border-radius: 10px;
  margin-bottom: 20px;
  font-weight: 600;
  border-left: 4px solid #dc2626;
  animation: shake 0.5s;
}

@keyframes shake {
  0%, 100% { transform: translateX(0); }
  25% { transform: translateX(-5px); }
  75% { transform: translateX(5px); }
}

.success {
  background: linear-gradient(135deg, #d1fae5 0%, #a7f3d0 100%);
  color: #065f46;
  padding: 14px 18px;
  border-radius: 10px;
  margin-bottom: 20px;
  font-weight: 600;
  border-left: 4px solid #10b981;
  animation: slideDown 0.5s;
}

.info-box {
  margin-top: 20px;
  padding: 20px;
  background: linear-gradient(135deg, #dbeafe 0%, #bfdbfe 100%);
  border-left: 4px solid #3b82f6;
  border-radius: 10px;
}

.info-box strong {
  color: #1e40af;
  font-size: 1.1em;
}

.info-box ul {
  margin-top: 10px;
  margin-left: 20px;
  color: #1e3a8a;
}

.info-box li {
  margin: 5px 0;
}

/* Footer */
.footer {
  text-align: center;
  padding: 20px;
  color: rgba(255, 255, 255, 0.9);
  font-size: 0.9em;
  font-weight: 500;
}

/* Scrollbar */
::-webkit-scrollbar {
  width: 10px;
}

::-webkit-scrollbar-track {
  background: #f1f5f9;
  border-radius: 10px;
}

::-webkit-scrollbar-thumb {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border-radius: 10px;
}

::-webkit-scrollbar-thumb:hover {
  background: #5568d3;
}

/* Responsividade */
@media (max-width: 768px) {
  .header-content {
    flex-direction: column;
    gap: 20px;
    text-align: center;
  }

  .logo-section {
    flex-direction: column;
  }

  .nav-container {
    flex-direction: column;
  }

  .nav-tab {
    width: 100%;
  }

  table {
    font-size: 0.85em;
  }

  table th,
  table td {
    padding: 10px;
  }
}
</style>
