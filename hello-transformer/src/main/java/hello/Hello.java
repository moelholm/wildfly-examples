package hello;

import javax.jws.WebService;

@WebService
public interface Hello {

    String sayHello(String caller);

}
