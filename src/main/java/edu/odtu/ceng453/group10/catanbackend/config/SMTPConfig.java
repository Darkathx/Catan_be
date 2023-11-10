package edu.odtu.ceng453.group10.catanbackend.config;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;

import java.util.Properties;

public final class SMTPConfig {
    public static Properties PROP = new Properties();
    public static String URL = "";
    static {
        //PROP.put("mail.smtp.auth", true);
        //PROP.put("mail.smtp.starttls.enable", "true");
        PROP.put("mail.smtp.host", "localhost");
        PROP.put("mail.smtp.port", "25");
        //PROP.put("mail.smtp.ssl.trust", "");
    }

    public static Session getSession() {
        return Session.getDefaultInstance(PROP);
    }
}
