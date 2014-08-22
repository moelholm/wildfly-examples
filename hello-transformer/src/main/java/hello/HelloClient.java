package hello;

import java.net.MalformedURLException;
import java.net.URL;

import javax.naming.NamingException;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.log4j.Logger;

public class HelloClient {

    public static void main(String[] args) throws NamingException, MalformedURLException {

        String wsdlUrl = "http://localhost:8080/hello-transformer-1.0.0-SNAPSHOT/HelloBean?wsdl";

        Hello webServiceClient = createClient(wsdlUrl, Hello.class);

        String result = webServiceClient.sayHello("client");

        Logger.getLogger(HelloClient.class).info(String.format("Result from server: %s", result));

    }

    private static <T> T createClient(String wsdl, Class<T> intf) throws MalformedURLException {
        URL wsdlUrl = new URL(wsdl);
        String namespace = String.format("http://%s/", intf.getPackage().getName());
        QName service = new QName(namespace, String.format("%sBeanService", intf.getSimpleName()));
        return (T) new Service(wsdlUrl, service) {
        }.getPort(new QName(namespace, String.format("%sBeanPort", intf.getSimpleName())), intf);
    }
}
