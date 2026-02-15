package zone.dragon.basepom

import com.google.protobuf.Timestamp
import com.google.protobuf.timestamp
import zone.dragon.base_pom.RuntimeMessage
import zone.dragon.base_pom.runtimeMessage

/**
 * @author dandroid
 * @date 2/4/2026
 */
class Test {

    fun testFunction() {
        println("Test function executed")
    }

    fun testFunction2() {
        val message = runtimeMessage {
            expiry = timestamp {

            }
        }

    }

}
