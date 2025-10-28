# 1. Usar a imagem base oficial do Java 11
FROM eclipse-temurin:11-jdk-jammy

# 2. Definir variáveis de ambiente para a instalação do GlassFish
ENV GLASSFISH_VERSION=7.0.14
ENV GLASSFISH_HOME=/opt/glassfish7
ENV PATH=$PATH:${GLASSFISH_HOME}/bin

# 3. Instalar ferramentas, baixar e instalar o GlassFish
RUN apt-get update && \
    apt-get install -y --no-install-recommends wget unzip && \
    wget https://download.eclipse.org/ee4j/glassfish/glassfish-${GLASSFISH_VERSION}.zip -O /tmp/glassfish.zip && \
    unzip /tmp/glassfish.zip -d /opt && \
    rm /tmp/glassfish.zip && \
    apt-get purge -y --auto-remove wget unzip && \
    rm -rf /var/lib/apt/lists/*

# 4. Expor a porta HTTP
EXPOSE 8080

# --- CORREÇÃO AQUI ---
# 5. Copiar o arquivo .war RENOMEANDO para ROOT.war
#    Isso faz com que o GlassFish o implante como a aplicação padrão (no "/")
COPY target/pizzariaWeb-1.0-SNAPSHOT.war ${GLASSFISH_HOME}/glassfish/domains/domain1/autodeploy/ROOT.war

# 6. Comando para iniciar o GlassFish
CMD ["asadmin", "start-domain", "--verbose", "domain1"]
