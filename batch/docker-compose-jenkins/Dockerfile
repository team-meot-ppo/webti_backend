FROM jenkins/jenkins:lts-jdk17

USER root

RUN apt-get update && \
    apt-get -y install apt-transport-https \
      ca-certificates \
      curl \
      gnupg2 \
      software-properties-common && \
    curl -fsSL get.docker.com -o get-docker.sh && \
    sh get-docker.sh && \
    rm get-docker.sh

RUN groupadd -f docker
RUN usermod -aG docker jenkins

USER jenkins
