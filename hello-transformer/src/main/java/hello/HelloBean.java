package hello;

import javax.annotation.security.PermitAll;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebService;

@Stateless
@Remote(Hello.class)
@PermitAll
@WebService
public class HelloBean implements Hello {

    private static final MyLogger logger = new MyLogger();

    public String sayHello(String caller) {
        String logMsg = String.format("HelloBean.sayHello(%s)", caller);
        System.out.println(logMsg);
        logger.info(logMsg);
        return String.format("Hello %s", caller);
    }
}
