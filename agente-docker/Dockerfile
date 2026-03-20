FROM ubuntu:22.04

RUN apt-get update && apt-get install -y \
    curl \
    git \
    jq \
    libicu-dev \
    openjdk-21-jdk \
    maven

RUN useradd -m agentuser

WORKDIR /azp

COPY start.sh .
RUN chmod +x start.sh

RUN chown -R agentuser:agentuser /azp

USER agentuser

CMD ["./start.sh"]