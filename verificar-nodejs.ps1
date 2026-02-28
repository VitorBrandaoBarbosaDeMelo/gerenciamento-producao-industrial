# ===============================================
# VERIFICAR INSTALAÇÃO DO NODE.JS
# ===============================================

Write-Host "`n================================" -ForegroundColor Cyan
Write-Host "  VERIFICANDO NODE.JS" -ForegroundColor Cyan
Write-Host "================================`n" -ForegroundColor Cyan

# Verificar Node.js
Write-Host "Node.js Version:" -ForegroundColor Yellow
node --version

Write-Host "`nNPM Version:" -ForegroundColor Yellow
npm --version

Write-Host "`n================================" -ForegroundColor Green
Write-Host "  ✅ INSTALAÇÃO OK!" -ForegroundColor Green
Write-Host "================================`n" -ForegroundColor Green

Write-Host "Próximo passo:" -ForegroundColor Cyan
Write-Host "Execute o arquivo: " -NoNewline
Write-Host "iniciar-frontend.ps1" -ForegroundColor Yellow
Write-Host ""

Read-Host "Pressione ENTER para sair"
