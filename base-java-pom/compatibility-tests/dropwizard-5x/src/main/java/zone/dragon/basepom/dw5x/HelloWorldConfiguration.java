package zone.dragon.basepom.dw5x;

import io.dropwizard.core.Configuration;
import io.dropwizard.core.server.SimpleServerFactory;
import io.dropwizard.jetty.HttpConnectorFactory;

public class HelloWorldConfiguration extends Configuration {

    public HelloWorldConfiguration() {
        super();

        SimpleServerFactory ssf = new SimpleServerFactory();
        HttpConnectorFactory hcf = new HttpConnectorFactory();
        hcf.setPort(0);
        ssf.setConnector(hcf);
        setServerFactory(ssf);
    }
}
