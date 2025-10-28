# 1. Use uma imagem base oficial do GlassFish compatível com Jakarta EE 10 e Java 11
#    (Verifique as tags disponíveis no Docker Hub se precisar de outra versão: https://hub.docker.com/_/glassfish)
FROM glassfish:latest-jdk11

# 2. (Opcional mas recomendado) Defina a porta que o GlassFish usa (8080 por padrão)
EXPOSE 8080

# 3. Copie o arquivo .war gerado pelo Maven para a pasta de autodeploy do GlassFish
#    Certifique-se que o nome 'pizzariaWeb-1.0-SNAPSHOT.war' corresponde ao nome no seu pom.xml
COPY target/pizzariaWeb-1.0-SNAPSHOT.war ${DEPLOY_DIR}

# 4. (Opcional) O comando para iniciar o GlassFish geralmente já está configurado na imagem base.
#    Você não precisa de um CMD a menos que precise de opções de inicialização específicas.

# CMD ["asadmin", "start-domain", "--verbose", "domain1"] # Exemplo se necessário
