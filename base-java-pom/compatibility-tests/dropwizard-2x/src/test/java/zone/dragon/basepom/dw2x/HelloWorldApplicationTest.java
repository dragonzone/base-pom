package zone.dragon.basepom.dw2x;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import javax.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(DropwizardExtensionsSupport.class)
class HelloWorldApplicationTest {

    private static final DropwizardAppExtension<HelloWorldConfiguration> APP =
            new DropwizardAppExtension<>(HelloWorldApplication.class,
                    ResourceHelpers.resourceFilePath("config.yml"));

    @Test
    void helloEndpointReturnsOk() {
        Response response = APP.client()
                .target(String.format("http://localhost:%d/application/hello", APP.getLocalPort()))
                .request()
                .get();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.readEntity(String.class)).isEqualTo("Hello, World!");
    }
}
