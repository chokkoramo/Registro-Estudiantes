#!/bin/bash
set -e

if [ -z "$AZP_URL" ]; then
  echo "Falta AZP_URL"
  exit 1
fi

if [ -z "$AZP_TOKEN" ]; then
  echo "Falta AZP_TOKEN"
  exit 1
fi

AZP_POOL=${AZP_POOL:-Default}

mkdir -p /azp
cd /azp

echo "Descargando agente..."
curl -fLsS https://download.agent.dev.azure.com/agent/4.270.0/vsts-agent-linux-x64-4.270.0.tar.gz -o agent.tar.gz

tar -xzvf agent.tar.gz

if [ ! -f .agent ]; then
  echo "Configurando agente..."

  ./config.sh --unattended \
    --agent "$(hostname)" \
    --url "$AZP_URL" \
    --auth pat \
    --token "$AZP_TOKEN" \
    --pool "$AZP_POOL" \
    --work "_work" \
    --replace
else
  echo "Agente ya configurado, iniciando..."
fi

echo "Iniciando agente..."
./run.sh