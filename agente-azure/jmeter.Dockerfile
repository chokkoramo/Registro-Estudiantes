FROM eclipse-temurin:21-jdk-jammy

RUN apt-get update && apt-get install -y \
    curl \
    git \
    jq \
    unzip \
    libicu-dev \
    && rm -rf /var/lib/apt/lists/*

# JMeter
RUN curl -L https://archive.apache.org/dist/jmeter/binaries/apache-jmeter-5.6.3.tgz \
    -o jmeter.tgz && \
    tar -xzf jmeter.tgz && \
    mv apache-jmeter-5.6.3 /opt/jmeter && \
    rm jmeter.tgz

ENV JMETER_HOME=/opt/jmeter
ENV PATH=$JMETER_HOME/bin:$PATH
ENV JVM_ARGS="-Xms512m -Xmx2g"

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