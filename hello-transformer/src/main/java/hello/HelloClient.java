package hello;

import java.net.MalformedURLException;
import java.net.URL;

import javax.naming.NamingException;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 * A SOAP based client to the HelloBean EJB.<br>
 * <br>
 *
 * @author nickymolholm
 */
public class HelloClient {

    public static void main(String[] args) throws NamingException, MalformedURLException {

        String wsdlUrl = "http://localhost:8080/hello-transformer-1.0.0-SNAPSHOT/HelloBean?wsdl";

        Hello webServiceClient = createClient(wsdlUrl, Hello.class);

        String result = webServiceClient.sayHello("client");

        System.out.printf("Result from server: %s%n", result);

    }

    /**
     * A handy horribly looking utility method that generates a dynamic proxy that can talk SOAP with wildfly.<br>
     * <br>
     * ( I don't know if it counts...but it's kind of cool that we can omit the JAX-WS generated code that would otherwise be
     * necessary, don't you think? :) )
     */
    private static <T> T createClient(String wsdl, Class<T> intf) throws MalformedURLException {
        URL wsdlUrl = new URL(wsdl);
        String namespace = String.format("http://%s/", intf.getPackage().getName());
        QName service = new QName(namespace, String.format("%sBeanService", intf.getSimpleName()));
        return (T) new Service(wsdlUrl, service) {
        }.getPort(new QName(namespace, String.format("%sBeanPort", intf.getSimpleName())), intf);
    }
}
