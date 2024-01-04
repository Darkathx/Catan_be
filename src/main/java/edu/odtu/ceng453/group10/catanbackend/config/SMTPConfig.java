package edu.odtu.ceng453.group10.catanbackend.config;

import jakarta.mail.Session;

import java.util.Properties;

/**
 * Configuration utility for setting up SMTP properties for email functionality.
 * Defines the properties necessary for connecting to an SMTP server and provides a session for sending emails.
 */
public final class SMTPConfig {
    /**
     * Properties configuration for the SMTP server.
     */
    public static Properties PROP = new Properties();
    /**
     * URL endpoint for resetting the password.
     */
    public static String URL = "https://catan-backend-ds1e.onrender.com/userAccount/resetPassword";
    static {
        PROP.put("mail.smtp.auth", true);
        PROP.put("mail.smtp.starttls.enable", "true");
        PROP.put("mail.smtp.host", "localhost");
        PROP.put("mail.smtp.port", "25");
        PROP.put("mail.smtp.ssl.trust", "");
    }

    /**
     * Creates a new mail Session with the defined properties.
     *
     * @return A Session object ready for sending emails.
     */
    public static Session getSession() {
        return Session.getDefaultInstance(PROP);
    }
}
