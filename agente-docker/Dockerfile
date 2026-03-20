FROM ubuntu:22.04

RUN apt-get update && apt-get install -y \
    curl \
    git \
    jq \
    libicu-dev \
    openjdk-17-jdk \
    maven

WORKDIR /azp

COPY start.sh .
RUN chmod +x start.sh

CMD ["./start.sh"]