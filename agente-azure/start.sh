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
AZP_AGENT_NAME=${AZP_AGENT_NAME:-$(hostname)}

cd /azp

# Solo configurar si no existe
if [ ! -f .agent ]; then
  echo "Configurando agente..."

  ./config.sh --unattended \
    --agent "$AZP_AGENT_NAME" \
    --url "$AZP_URL" \
    --auth pat \
    --token "$AZP_TOKEN" \
    --pool "$AZP_POOL" \
    --work "_work" \
    --replace \
    --ephemeral
else
  echo "Agente ya configurado, reutilizando..."
fi

echo "Iniciando agente..."

export VSO_AGENT_IGNORE=AZP_TOKEN

./run.sh