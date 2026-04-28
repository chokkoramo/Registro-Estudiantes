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

cd /azp

echo "Eliminando configuración previa..."
./config.sh remove --unattended --auth pat --token "$AZP_TOKEN" || true

rm -rf .agent .credentials .credentials_rsaparams || true
rm -rf _work _diag _tasks _tool || true

echo "Configurando agente..."

./config.sh --unattended \
  --agent "$(hostname)-$(date +%s)" \
  --url "$AZP_URL" \
  --auth pat \
  --token "$AZP_TOKEN" \
  --pool "$AZP_POOL" \
  --work "_work" \
  --replace

echo "Iniciando agente..."

export VSO_AGENT_IGNORE=AZP_TOKEN

./run.sh --once