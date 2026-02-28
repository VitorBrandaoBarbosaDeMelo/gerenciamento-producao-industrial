<template>
  <div>
    <h2>Matérias-Primas</h2>
    <button @click="openCreateModal" class="btn btn-primary">➕ Nova Matéria-Prima</button>

    <div v-if="error" class="error">{{ error }}</div>
    <div v-if="success" class="success">{{ success }}</div>

    <table class="table-materias-primas" v-if="materiasPrimas.length > 0">
      <thead>
        <tr>
          <th>Código</th>
          <th>Nome</th>
          <th>Estoque</th>
          <th>Peso por unidade</th>
          <th>Unidade de Medida</th>
          <th>Valor da Unidade</th>
          <th>Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="mp in materiasPrimas" :key="mp.id">
          <td>{{ mp.codigo }}</td>
          <td>{{ mp.nome }}</td>
          <td>{{ mp.quantidadeEstoque }}</td>
          <td>{{ mp.pesoPorUnidade || '-' }}</td>
          <td>{{ mp.unidadeMedida }}</td>
          <td>{{ mp.valorUnidade ? `R$ ${mp.valorUnidade.toFixed(2)}` : '-' }}</td>
          <td class="acoes-cell">
            <button @click="openEditModal(mp)" class="btn btn-warning">✏️ Editar</button>
            <button @click="deleteMp(mp.id)" class="btn btn-danger">🗑️ Excluir</button>
          </td>
        </tr>
      </tbody>
    </table>

    <div v-if="showModal" class="modal" @click.self="closeModal">
      <div class="modal-content">
        <h3>{{ editMode ? 'Editar' : 'Nova' }} Matéria-Prima</h3>
        
        <div class="form-group">
          <label>Código:</label>
          <input v-model="form.codigo" type="text" required>
        </div>

        <div class="form-group">
          <label>Nome:</label>
          <input v-model="form.nome" type="text" required>
        </div>

        <div class="form-group">
          <label>Quantidade em Estoque:</label>
          <input v-model="form.quantidadeEstoque" type="number" step="0.01" required>
        </div>

        <div class="form-group">
          <label>Unidade de Medida:</label>
          <select v-model="form.unidadeMedida" required>
            <option value="">Selecione...</option>
            <option value="Unidades">Unidades</option>
            <option value="Quilogramas">Quilogramas</option>
            <option value="Gramas">Gramas</option>
            <option value="Litros">Litros</option>
            <option value="Metros">Metros</option>
            <option value="Metros²">Metros²</option>
            <option value="Metros³">Metros³</option>
            <option value="Barras">Barras</option>
            <option value="Chapas">Chapas</option>
            <option value="Galões">Galões</option>
          </select>
        </div>

        <div class="form-group">
          <label>Peso por unidade.</label>
          <input v-model="form.pesoPorUnidade" type="number" step="0.001" placeholder="0.000">
        </div>

        <div class="form-group">
          <label>Valor da Unidade (R$):</label>
          <input v-model="form.valorUnidade" type="number" step="0.01" placeholder="0.00">
        </div>

        <div class="actions">
          <button @click="save" class="btn btn-success">💾 Salvar</button>
          <button @click="closeModal" class="btn btn-danger">❌ Cancelar</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'
import { materiaPrimaService } from '../services/api'

export default {
  name: 'MateriaPrimaView',
  setup() {
    const materiasPrimas = ref([])
    const showModal = ref(false)
    const editMode = ref(false)
    const error = ref('')
    const success = ref('')
    const form = ref({
      id: null,
      codigo: '',
      nome: '',
      quantidadeEstoque: 0,
      unidadeMedida: '',
      pesoPorUnidade: null,
      valorUnidade: null
    })

    const loadData = async () => {
      try {
        const response = await materiaPrimaService.getAll()
        materiasPrimas.value = response.data
      } catch (err) {
        error.value = 'Erro ao carregar matérias-primas'
      }
    }

    const openCreateModal = () => {
      editMode.value = false
      form.value = { id: null, codigo: '', nome: '', quantidadeEstoque: 0, unidadeMedida: '', pesoPorUnidade: null, valorUnidade: null }
      showModal.value = true
      error.value = ''
      success.value = ''
    }

    const openEditModal = (mp) => {
      editMode.value = true
      form.value = { ...mp }
      showModal.value = true
      error.value = ''
      success.value = ''
    }

    const closeModal = () => {
      showModal.value = false
      form.value = { id: null, codigo: '', nome: '', quantidadeEstoque: 0, unidadeMedida: '', pesoPorUnidade: null, valorUnidade: null }
    }

    const save = async () => {
      try {
        if (editMode.value) {
          await materiaPrimaService.update(form.value.id, form.value)
          success.value = 'Matéria-prima atualizada com sucesso!'
        } else {
          await materiaPrimaService.create(form.value)
          success.value = 'Matéria-prima criada com sucesso!'
        }
        closeModal()
        loadData()
        setTimeout(() => success.value = '', 3000)
      } catch (err) {
        error.value = err.response?.data?.message || 'Erro ao salvar matéria-prima'
      }
    }

    const deleteMp = async (id) => {
      if (confirm('Tem certeza que deseja excluir?')) {
        try {
          await materiaPrimaService.delete(id)
          success.value = 'Matéria-prima excluída com sucesso!'
          loadData()
          setTimeout(() => success.value = '', 3000)
        } catch (err) {
          error.value = 'Erro ao excluir matéria-prima'
        }
      }
    }

    onMounted(loadData)

    return {
      materiasPrimas,
      showModal,
      editMode,
      error,
      success,
      form,
      openCreateModal,
      openEditModal,
      closeModal,
      save,
      deleteMp
    }
  }
}
</script>

<style scoped>
.table-materias-primas th,
.table-materias-primas td {
  text-align: center;
  vertical-align: middle;
}

.table-materias-primas .acoes-cell {
  text-align: center;
  white-space: nowrap;
}
</style>