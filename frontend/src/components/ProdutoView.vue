<template>
  <div>
    <h2>Produtos</h2>
    <button @click="openCreateModal" class="btn btn-primary">➕ Novo Produto</button>

    <div v-if="error" class="error">{{ error }}</div>
    <div v-if="success" class="success">{{ success }}</div>

    <table v-if="produtos.length > 0">
      <thead>
        <tr>
          <th>Código</th>
          <th>Nome</th>
          <th>Valor</th>
          <th>Composição</th>
          <th>Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="produto in produtos" :key="produto.id">
          <td>{{ produto.codigo }}</td>
          <td>{{ produto.nome }}</td>
          <td>R$ {{ produto.valor.toFixed(2) }}</td>
          <td>
            <div v-for="comp in produto.composicao" :key="comp.materiaPrimaId">
              {{ comp.materiaPrimaNome }}: {{ comp.quantidade }}
            </div>
          </td>
          <td>
            <button @click="openEditModal(produto)" class="btn btn-warning">✏️ Editar</button>
            <button @click="deleteProduto(produto.id)" class="btn btn-danger">🗑️ Excluir</button>
          </td>
        </tr>
      </tbody>
    </table>

    <div v-if="showModal" class="modal" @click.self="closeModal">
      <div class="modal-content">
        <h3>{{ editMode ? 'Editar' : 'Novo' }} Produto</h3>
        
        <div class="form-group">
          <label>Código:</label>
          <input v-model="form.codigo" type="text" required>
        </div>

        <div class="form-group">
          <label>Nome:</label>
          <input v-model="form.nome" type="text" required>
        </div>

        <div class="form-group">
          <label>Valor:</label>
          <input v-model="form.valor" type="number" step="0.01" required>
        </div>

        <h4>Composição</h4>
        <div v-for="(comp, index) in form.composicao" :key="index" style="border: 1px solid #ccc; padding: 10px; margin: 10px 0; border-radius: 5px;">
          <div class="form-group">
            <label>Matéria-Prima:</label>
            <select v-model="comp.materiaPrimaId">
              <option v-for="mp in materiasPrimas" :key="mp.id" :value="mp.id">
                {{ mp.nome }} ({{ mp.codigo }})
              </option>
            </select>
          </div>
          <div class="form-group">
            <label>Quantidade:</label>
            <input v-model="comp.quantidade" type="number" step="0.01">
          </div>
          <button @click="removeComposicao(index)" class="btn btn-danger">Remover</button>
        </div>
        <button @click="addComposicao" class="btn btn-primary">➕ Adicionar Matéria-Prima</button>

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
import { produtoService, materiaPrimaService } from '../services/api'

export default {
  name: 'ProdutoView',
  setup() {
    const produtos = ref([])
    const materiasPrimas = ref([])
    const showModal = ref(false)
    const editMode = ref(false)
    const error = ref('')
    const success = ref('')
    const form = ref({
      id: null,
      codigo: '',
      nome: '',
      valor: 0,
      composicao: []
    })

    const loadData = async () => {
      try {
        const [produtosRes, mpsRes] = await Promise.all([
          produtoService.getAll(),
          materiaPrimaService.getAll()
        ])
        produtos.value = produtosRes.data
        materiasPrimas.value = mpsRes.data
      } catch (err) {
        error.value = 'Erro ao carregar dados'
      }
    }

    const openCreateModal = () => {
      editMode.value = false
      form.value = { id: null, codigo: '', nome: '', valor: 0, composicao: [] }
      showModal.value = true
      error.value = ''
      success.value = ''
    }

    const openEditModal = (produto) => {
      editMode.value = true
      form.value = JSON.parse(JSON.stringify(produto))
      showModal.value = true
      error.value = ''
      success.value = ''
    }

    const closeModal = () => {
      showModal.value = false
    }

    const addComposicao = () => {
      form.value.composicao.push({ materiaPrimaId: null, quantidade: 0 })
    }

    const removeComposicao = (index) => {
      form.value.composicao.splice(index, 1)
    }

    const save = async () => {
      try {
        if (editMode.value) {
          await produtoService.update(form.value.id, form.value)
          success.value = 'Produto atualizado com sucesso!'
        } else {
          await produtoService.create(form.value)
          success.value = 'Produto criado com sucesso!'
        }
        closeModal()
        loadData()
        setTimeout(() => success.value = '', 3000)
      } catch (err) {
        error.value = err.response?.data?.message || 'Erro ao salvar produto'
      }
    }

    const deleteProduto = async (id) => {
      if (confirm('Tem certeza que deseja excluir?')) {
        try {
          await produtoService.delete(id)
          success.value = 'Produto excluído com sucesso!'
          loadData()
          setTimeout(() => success.value = '', 3000)
        } catch (err) {
          error.value = 'Erro ao excluir produto'
        }
      }
    }

    onMounted(loadData)

    return {
      produtos,
      materiasPrimas,
      showModal,
      editMode,
      error,
      success,
      form,
      openCreateModal,
      openEditModal,
      closeModal,
      addComposicao,
      removeComposicao,
      save,
      deleteProduto
    }
  }
}
</script>
