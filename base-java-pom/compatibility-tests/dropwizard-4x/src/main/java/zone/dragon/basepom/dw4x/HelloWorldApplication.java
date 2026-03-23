package zone.dragon.basepom.dw4x;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) {
        environment.jersey().register(new HelloWorldResource());
    }
}
