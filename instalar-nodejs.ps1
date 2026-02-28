# Script de instalação do Node.js
# Execute este script como ADMINISTRADOR

Write-Host "====================================" -ForegroundColor Cyan
Write-Host "   Instalando Node.js LTS 24.x     " -ForegroundColor Cyan
Write-Host "====================================" -ForegroundColor Cyan
Write-Host ""

# Instalar Node.js via Chocolatey
Write-Host "Instalando Node.js via Chocolatey..." -ForegroundColor Yellow
choco install nodejs-lts -y

Write-Host ""
Write-Host "====================================" -ForegroundColor Green
Write-Host "   Instalação Concluída!            " -ForegroundColor Green
Write-Host "====================================" -ForegroundColor Green
Write-Host ""

# Verificar instalação
Write-Host "Verificando instalação..." -ForegroundColor Yellow
node --version
npm --version

Write-Host ""
Write-Host "Próximos passos:" -ForegroundColor Cyan
Write-Host "1. Feche e reabra o PowerShell" -ForegroundColor White
Write-Host "2. Execute: cd frontend" -ForegroundColor White
Write-Host "3. Execute: npm install" -ForegroundColor White
Write-Host "4. Execute: npm run dev" -ForegroundColor White
Write-Host "5. Acesse: http://localhost:5173" -ForegroundColor White
Write-Host ""

Read-Host "Pressione ENTER para sair"
