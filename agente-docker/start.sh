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

mkdir /azp
cd /azp

echo "Descargando agente..."
curl -LsS https://vstsagentpackage.azureedge.net/agent/3.236.1/vsts-agent-linux-x64-3.236.1.tar.gz | tar -xz

echo "Configurando agente..."
./config.sh --unattended \
  --agent "$(hostname)" \
  --url "$AZP_URL" \
  --auth pat \
  --token "$AZP_TOKEN" \
  --pool "$AZP_POOL" \
  --work "_work" \
  --replace

echo "Iniciando agente..."
./run.sh