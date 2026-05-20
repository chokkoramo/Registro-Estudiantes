FROM eclipse-temurin:21-jdk-jammy

RUN apt-get update && apt-get install -y \
    curl \
    git \
    jq \
    unzip \
    libicu-dev \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /azp

RUN useradd -m agentuser

# Azure Agent
RUN ARCH=$(uname -m) && \
    if [ "$ARCH" = "aarch64" ]; then \
      AGENT_URL="https://download.agent.dev.azure.com/agent/4.270.0/vsts-agent-linux-arm64-4.270.0.tar.gz"; \
    else \
      AGENT_URL="https://download.agent.dev.azure.com/agent/4.270.0/vsts-agent-linux-x64-4.270.0.tar.gz"; \
    fi && \
    curl -fLsS "$AGENT_URL" -o agent.tar.gz && \
    tar -xzf agent.tar.gz && \
    rm agent.tar.gz

COPY start.sh .
RUN chmod +x start.sh

RUN chown agentuser:agentuser /azp

USER agentuser

CMD ["./start.sh"]