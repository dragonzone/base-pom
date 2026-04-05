package zone.dragon.basepom.dw3x;

import java.util.HashSet;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

class MockitoCompatibilityTest {

    @Test
    void mockitoCanMockJdkInterfaces() {
        Supplier<?> mock = Mockito.mock(Supplier.class);
        assertThat(mock).isNotNull();
        assertThat(mock.get()).isNull();
    }

    @Test
    void mockitoCanMockJdkClasses() {
        HashSet<?> mock = Mockito.mock(HashSet.class);
        assertThat(mock).isNotNull();
        assertThat(mock.size()).isEqualTo(0);
    }
}
