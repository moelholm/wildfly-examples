package hello;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

public class HelloClient {

    public static void main(String[] args) throws NamingException {

        String ejbJndiName = "ejb:/hello-transformer-1.0.0-SNAPSHOT/HelloBean!hello.Hello";
        String host = "localhost";
        String port = "8080";
        String usr = "duke";
        String pwd = "duke";

        Properties env = new Properties();
        env.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        env.put("org.jboss.ejb.client.scoped.context", "true");
        env.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "false");
        env.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");
        env.put("remote.connections", "default");
        env.put("remote.connection.default.host", host);
        env.put("remote.connection.default.port", port);
        env.put("remote.connection.default.username", usr);
        env.put("remote.connection.default.password", pwd);

        Hello hello = (Hello) new InitialContext(env).lookup(ejbJndiName);

        String result = hello.sayHello("Client application");

        Logger.getLogger(HelloClient.class).info(String.format("Result from server: %s", result));

    }
}
