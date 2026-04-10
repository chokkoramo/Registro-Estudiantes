#!/bin/bash
set -e

if [ -z "$AZP_URL" ]; then
  echo "Error: Falta AZP_URL"
  exit 1
fi

if [ -z "$AZP_TOKEN" ]; then
  echo "Error: Falta AZP_TOKEN"
  exit 1
fi

AZP_POOL=${AZP_POOL:-Default}

# Nos aseguramos de estar en el directorio correcto
cd /azp

# Si no existe el archivo .agent, significa que no se ha configurado para este contenedor
if [ ! -f .agent ]; then
  echo "Configurando agente por primera vez..."

  ./config.sh --unattended \
    --agent "$(hostname)" \
    --url "$AZP_URL" \
    --auth pat \
    --token "$AZP_TOKEN" \
    --pool "$AZP_POOL" \
    --work "_work" \
    --replace
else
  echo "El agente ya está configurado. Omitiendo configuración..."
fi

echo "Iniciando agente..."
./run.sh