FROM ubuntu:22.04

# Instalamos las dependencias base
RUN apt-get update && apt-get install -y \
    curl \
    git \
    jq \
    libicu-dev \
    openjdk-21-jdk \
    maven \
    && rm -rf /var/lib/apt/lists/*

# Creamos el directorio de trabajo y el usuario
WORKDIR /azp
RUN useradd -m agentuser

# Descargamos el agente dinámicamente según la arquitectura durante el BUILD
RUN ARCH=$(uname -m) && \
    if [ "$ARCH" = "aarch64" ]; then \
      echo "Detectada arquitectura ARM64..." && \
      AGENT_URL="https://download.agent.dev.azure.com/agent/4.270.0/vsts-agent-linux-arm64-4.270.0.tar.gz"; \
    else \
      echo "Detectada arquitectura AMD64..." && \
      AGENT_URL="https://download.agent.dev.azure.com/agent/4.270.0/vsts-agent-linux-x64-4.270.0.tar.gz"; \
    fi && \
    curl -fLsS "$AGENT_URL" -o agent.tar.gz && \
    tar -xzvf agent.tar.gz && \
    rm agent.tar.gz

# Instalamos dependencias adicionales requeridas por el agente (recomendado)
RUN ./bin/installdependencies.sh

# Copiamos el script de inicio
COPY start.sh .
RUN chmod +x start.sh

# Aseguramos que el usuario agentuser sea el dueño de los archivos
RUN chown -R agentuser:agentuser /azp

# Cambiamos al usuario sin privilegios
USER agentuser

CMD ["./start.sh"]