import com.google.protobuf.timestamp
import org.junit.jupiter.api.Test
import zone.dragon.base_pom.runtimeMessage

/**
 * @author dandroid
 * @date 2/5/2026
 */
class TestTest {

    @Test
    fun testFunction2() {
        val message = runtimeMessage {
            expiry = timestamp {

            }
        }
    }
}
