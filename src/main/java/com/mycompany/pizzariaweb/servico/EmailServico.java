package com.mycompany.pizzariaweb.servico;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailServico {

    // --- CONFIGURAÇÕES DO SEU SERVIDOR DE E-MAIL (EXEMPLO COM GMAIL) ---
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "465"; // Porta TLS
    private static final String SMTP_USER = "charlotte.xyzs2@gmail.com"; // SEU E-MAIL
    private static final String SMTP_PASSWORD = "oqwwbfklbbvngmkq"; // SUA SENHA DE APP (NÃO a senha normal)

    public void enviarEmailRedefinicao(String destinatario, String token) {
        Properties props = new Properties();
        // --- CONFIGURAÇÃO CORRETA E MODERNA PARA GMAIL (TLS) ---
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "false"); // Essencial para a porta 587
        
        // --- CORREÇÃO PARA O ERRO PKIX ---
        // Adiciona o host do SMTP à lista de hosts confiáveis (trust)
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USER, SMTP_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SMTP_USER));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject("Reis di Napoli - Redefinição de Senha");

            // Corpo do E-mail em HTML
            String link = "http://localhost:8080/pizzariaWeb-1.0-SNAPSHOT/redefinir-senha.jsp?token=" + token;
            String corpoHtml = "<h1>Redefinição de Senha</h1>"
                             + "<p>Olá,</p>"
                             + "<p>Você solicitou a redefinição da sua senha no sistema da pizzaria Reis di Napoli.</p>"
                             + "<p>Clique no link abaixo para criar uma nova senha:</p>"
                             + "<p><a href=\"" + link + "\">Redefinir Minha Senha</a></p>"
                             + "<p>Se você não solicitou isso, por favor, ignore este e-mail.</p>"
                             + "<br>"
                             + "<p>Atenciosamente,</p>"
                             + "<p>Equipe Reis di Napoli</p>";

            message.setContent(corpoHtml, "text/html; charset=utf-8");

            Transport.send(message);

            System.out.println("E-mail de redefinição enviado com sucesso para " + destinatario);

        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao enviar e-mail: " + e.getMessage());
        }
    }
}
