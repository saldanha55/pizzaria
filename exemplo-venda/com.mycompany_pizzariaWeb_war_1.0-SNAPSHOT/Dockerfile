# 1. Use a imagem oficial da Eclipse Foundation para GlassFish 7 com JDK 11
#    (Verifique tags em: https://hub.docker.com/r/eclipse/glassfish/tags)
FROM eclipse/glassfish:7.0.11-jdk11

# 2. (Opcional) A porta padrão 8080 já costuma estar exposta, mas podemos garantir
EXPOSE 8080
#    (GlassFish também usa a porta 4848 para o Admin Console, mas não precisamos expô-la para a aplicação rodar)
# EXPOSE 4848

# 3. Copie o arquivo .war gerado pelo Maven para a pasta de autodeploy
#    O diretório de autodeploy na imagem eclipse/glassfish costuma ser este:
COPY target/pizzariaWeb-1.0-SNAPSHOT.war /opt/glassfish/glassfish/domains/domain1/autodeploy/

# 4. O comando de inicialização já está na imagem base, não precisa de CMD
