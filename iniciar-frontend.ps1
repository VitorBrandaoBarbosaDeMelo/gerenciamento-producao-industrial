# ===============================================
# INICIAR FRONTEND - SISTEMA DE PRODUCAO
# ===============================================

Write-Host ""
Write-Host "================================" -ForegroundColor Cyan
Write-Host "  INICIANDO FRONTEND" -ForegroundColor Cyan
Write-Host "================================" -ForegroundColor Cyan
Write-Host ""

# Ir para pasta do frontend
Set-Location "c:\Users\Vitor Brandao\Desktop\Desktop\VSCODE_2026\Desafio GUPY JAVA\frontend"

# Verificar se node_modules existe
if (-not (Test-Path "node_modules")) {
    Write-Host "Instalando dependencias (primeira vez)..." -ForegroundColor Yellow
    Write-Host "Isso pode levar 1-2 minutos..." -ForegroundColor Gray
    Write-Host ""
    npm install
    Write-Host ""
    Write-Host "Dependencias instaladas!" -ForegroundColor Green
} else {
    Write-Host "Dependencias ja instaladas!" -ForegroundColor Green
}

Write-Host ""
Write-Host "================================" -ForegroundColor Magenta
Write-Host "  ABRINDO NAVEGADOR" -ForegroundColor Magenta
Write-Host "================================" -ForegroundColor Magenta
Write-Host ""

Write-Host "Frontend iniciando em:" -ForegroundColor Yellow
Write-Host "http://localhost:5173" -ForegroundColor Cyan
Write-Host ""

Write-Host "Para parar o servidor:" -ForegroundColor Gray
Write-Host "Pressione CTRL+C" -ForegroundColor Gray
Write-Host ""

# Aguardar 3 segundos e abrir navegador
Start-Sleep -Seconds 3
Start-Process "http://localhost:5173"

# Iniciar servidor Vite
npm run dev
