package coid.bcafinance.pcmtugasakhir.config;


import coid.bcafinance.pcmtugasakhir.security.Crypto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:smtpconfig.properties")
public class SMTPConfig {

    private static String host;
    private static String port;
    private static String username;
    private static String password;
    private static String ssl;
    private static String tls;
    private static String auth;
    private static String startTLSEnable;
    private static String socketFactoryClass;
    private static String timeout;

    public static String getHost() {
        return host;
    }

    @Value("${email.host}")
    private void setHost(String host) {
        this.host = Crypto.performDecrypt(host);
    }

    public static String getPort() {
        return port;
    }

    @Value("${email.port}")
    public void setPort(String port) {
        this.port = port;
    }

    public static String getUsername() {
        return username;
    }

    @Value("${email.username}")
    public void setUsername(String username) {
        this.username = Crypto.performDecrypt(username);
    }

    public static String getPassword() {
        return password;
    }

    @Value("${email.password}")
    public void setPassword(String password) {
        this.password = Crypto.performDecrypt(password);
    }

    public static String getSsl() {
        return ssl;
    }

    @Value("${email.port.ssl}")
    public void setSsl(String ssl) {
        this.ssl = ssl;
    }

    public static String getTls() {
        return tls;
    }

    @Value("${email.port.tls}")
    public void setTls(String tls) {
        this.tls = tls;
    }

    public static String getAuth() {
        return auth;
    }

    @Value("${email.auth}")
    public void setAuth(String auth) {
        this.auth = auth;
    }

    public static String getStartTLSEnable() {
        return startTLSEnable;
    }

    @Value("${email.starttls.enable}")
    public void setStartTLSEnable(String startTLSEnable) {
        this.startTLSEnable = startTLSEnable;
    }

    public static String getSocketFactoryClass() {
        return socketFactoryClass;
    }

    @Value("${email.smtp.socket.factory.class}")
    public void setSocketFactoryClass(String socketFactoryClass) {
        this.socketFactoryClass = socketFactoryClass;
    }

    public static String getTimeout() {
        return timeout;
    }

    @Value("${email.smtp.timeout}")
    public void setTimeout(String timeout) {
        this.timeout = timeout;
//        SMTPConfig.getPassword();
    }
}