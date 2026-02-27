import axios from 'axios'

const api = axios.create({
  baseURL: 'http://localhost:8080/api',
  headers: {
    'Content-Type': 'application/json'
  }
})

export const materiaPrimaService = {
  getAll: () => api.get('/materias-primas'),
  getById: (id) => api.get(`/materias-primas/${id}`),
  create: (data) => api.post('/materias-primas', data),
  update: (id, data) => api.put(`/materias-primas/${id}`, data),
  delete: (id) => api.delete(`/materias-primas/${id}`)
}

export const produtoService = {
  getAll: () => api.get('/produtos'),
  getById: (id) => api.get(`/produtos/${id}`),
  create: (data) => api.post('/produtos', data),
  update: (id, data) => api.put(`/produtos/${id}`, data),
  delete: (id) => api.delete(`/produtos/${id}`)
}

export const producaoService = {
  otimizar: () => api.get('/producao/otimizar')
}
