# 1. Use a imagem oficial da Eclipse Foundation para GlassFish 7.0 (última versão) com JDK 11
FROM eclipse/glassfish:7.0-jdk11

# 2. Exponha a porta
EXPOSE 8080

# 3. Copie o arquivo .war gerado pelo Maven para a pasta de autodeploy
COPY target/pizzariaWeb-1.0-SNAPSHOT.war /opt/glassfish/glassfish/domains/domain1/autodeploy/

# (CMD não é necessário)
