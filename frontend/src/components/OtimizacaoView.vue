<template>
  <div>
    <h2>Otimização de Produção</h2>
    <p style="margin-bottom: 20px;">
      Análise do estoque atual e sugestão de produtos para maximizar o valor de venda.
    </p>

    <button @click="calcularOtimizacao" class="btn btn-success">🎯 Calcular Otimização</button>

    <div v-if="error" class="error">{{ error }}</div>
    <div v-if="loading" style="margin-top: 20px; text-align: center;">
      Calculando melhor estratégia de produção...
    </div>

    <div v-if="sugestoes.length > 0" style="margin-top: 20px;">
      <h3>Sugestões de Produção</h3>
      <table>
        <thead>
          <tr>
            <th>Código</th>
            <th>Produto</th>
            <th>Valor Unitário</th>
            <th>Quantidade a Produzir</th>
            <th>Valor Total</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="sugestao in sugestoes" :key="sugestao.produtoId">
            <td>{{ sugestao.produtoCodigo }}</td>
            <td>{{ sugestao.produtoNome }}</td>
            <td>R$ {{ sugestao.valorUnitario.toFixed(2) }}</td>
            <td>{{ sugestao.quantidadeProduzir }}</td>
            <td style="font-weight: bold; color: #48bb78;">
              R$ {{ sugestao.valorTotal.toFixed(2) }}
            </td>
          </tr>
        </tbody>
        <tfoot>
          <tr style="background: #f7fafc; font-weight: bold;">
            <td colspan="4" style="text-align: right;">VALOR TOTAL DE VENDAS:</td>
            <td style="color: #48bb78; font-size: 1.2em;">
              R$ {{ valorTotalVendas.toFixed(2) }}
            </td>
          </tr>
        </tfoot>
      </table>

      <div style="margin-top: 20px; padding: 15px; background: #e6fffa; border-left: 4px solid #48bb78; border-radius: 5px;">
        <strong>📊 Estratégia de Produção:</strong>
        <ul style="margin-top: 10px;">
          <li>Produtos priorizados por maior valor de venda</li>
          <li>Otimização baseada no estoque disponível</li>
          <li>{{ sugestoes.length }} produto(s) recomendado(s)</li>
          <li>Valor total potencial: R$ {{ valorTotalVendas.toFixed(2) }}</li>
        </ul>
      </div>
    </div>

    <div v-if="!loading && sugestoes.length === 0 && tentouCalcular" style="margin-top: 20px; padding: 15px; background: #fff5f5; border-left: 4px solid #f56565; border-radius: 5px;">
      ⚠️ Nenhuma sugestão de produção disponível. Verifique se há produtos cadastrados e estoque de matérias-primas.
    </div>
  </div>
</template>

<script>
import { ref, computed } from 'vue'
import { producaoService } from '../services/api'

export default {
  name: 'OtimizacaoView',
  setup() {
    const sugestoes = ref([])
    const loading = ref(false)
    const error = ref('')
    const tentouCalcular = ref(false)

    const valorTotalVendas = computed(() => {
      return sugestoes.value.reduce((total, s) => total + s.valorTotal, 0)
    })

    const calcularOtimizacao = async () => {
      loading.value = true
      error.value = ''
      tentouCalcular.value = true
      try {
        const response = await producaoService.otimizar()
        sugestoes.value = response.data
      } catch (err) {
        error.value = 'Erro ao calcular otimização'
      } finally {
        loading.value = false
      }
    }

    return {
      sugestoes,
      loading,
      error,
      tentouCalcular,
      valorTotalVendas,
      calcularOtimizacao
    }
  }
}
</script>
