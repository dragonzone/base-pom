package zone.dragon.basepom.dw1x;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import org.junit.ClassRule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloWorldApplicationTest {

    @ClassRule
    public static final DropwizardAppRule<HelloWorldConfiguration> APP =
            new DropwizardAppRule<>(HelloWorldApplication.class,
                    ResourceHelpers.resourceFilePath("config.yml"));

    @Test
    public void helloEndpointReturnsOk() {
        Client client = ClientBuilder.newClient();
        try {
            Response response = client
                    .target(String.format("http://localhost:%d/application/hello", APP.getLocalPort()))
                    .request()
                    .get();

            assertThat(response.getStatus()).isEqualTo(200);
            assertThat(response.readEntity(String.class)).isEqualTo("Hello, World!");
        } finally {
            client.close();
        }
    }
}
