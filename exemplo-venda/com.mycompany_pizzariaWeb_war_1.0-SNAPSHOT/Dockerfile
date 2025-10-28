# 1. Usar uma imagem base oficial do Java 11 (Eclipse Temurin é uma boa escolha)
FROM eclipse-temurin:11-jdk-jammy

# 2. Definir variáveis de ambiente para a instalação do GlassFish
ENV GLASSFISH_VERSION=7.0.14
ENV GLASSFISH_HOME=/opt/glassfish7
ENV PATH=$PATH:${GLASSFISH_HOME}/bin

# 3. Instalar ferramentas necessárias (wget, unzip) e baixar/instalar o GlassFish
#    Usamos uma URL de download direto do site da Eclipse Foundation.
#    Verifique se a versão 7.0.14 ainda é a mais recente em https://glassfish.org/download.html se necessário.
RUN apt-get update && \
    apt-get install -y --no-install-recommends wget unzip && \
    wget https://download.eclipse.org/ee4j/glassfish/glassfish-${GLASSFISH_VERSION}.zip -O /tmp/glassfish.zip && \
    unzip /tmp/glassfish.zip -d /opt && \
    rm /tmp/glassfish.zip && \
    apt-get purge -y --auto-remove wget unzip && \
    rm -rf /var/lib/apt/lists/*

# 4. Expor as portas padrão do GlassFish (HTTP e Admin)
EXPOSE 8080
EXPOSE 4848

# 5. Copiar o arquivo .war para a pasta de autodeploy
COPY target/pizzariaWeb-1.0-SNAPSHOT.war ${GLASSFISH_HOME}/glassfish/domains/domain1/autodeploy/

# 6. Comando para iniciar o GlassFish no modo verbose (mostra mais logs)
CMD ["asadmin", "start-domain", "--verbose", "domain1"]
