package zone.dragon.basepom.dwbundle1x;

import java.util.HashSet;
import java.util.function.Supplier;
import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

public class MockitoCompatibilityTest {

    @Test
    public void mockitoCanMockJdkInterfaces() {
        Supplier<?> mock = Mockito.mock(Supplier.class);
        assertThat(mock).isNotNull();
        assertThat(mock.get()).isNull();
    }

    @Test
    public void mockitoCanMockJdkClasses() {
        HashSet<?> mock = Mockito.mock(HashSet.class);
        assertThat(mock).isNotNull();
        assertThat(mock.size()).isEqualTo(0);
    }
}
