package hello;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 * A super simple EJB that exposes a web service <code>view</code> (SOAP/WSDL).<br>
 * <br>
 *
 * @author nickymolholm
 */
@Stateless
@WebService
@PermitAll
public class HelloBean implements Hello {

    public String sayHello(String caller) {

        System.out.println("HelloBean.sayHello()");

        return String.format("Hello %s", caller);
    }
}
