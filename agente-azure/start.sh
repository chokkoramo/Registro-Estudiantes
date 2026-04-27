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

echo "Limpiando configuración previa del agente..."

if [ -f .agent ]; then
  ./config.sh remove --unattended --auth pat --token "$AZP_TOKEN" || true
fi

# Limpia cache de tasks
rm -rf _work/_tasks || true
rm -rf _work/_tool || true

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

export VSO_AGENT_IGNORE=AZP_TOKEN

./run.sh