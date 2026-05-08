import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.objectweb.asm.ClassReader
import org.objectweb.asm.Opcodes

class AsmCompatibilityTest {

    @Test
    fun asmCanReadJdkClass() {
        val reader = ClassReader("java.util.HashSet")
        assertThat(reader.className).isEqualTo("java/util/HashSet")
        assertThat(reader.superName).isEqualTo("java/util/AbstractSet")
        assertThat(reader.access and Opcodes.ACC_PUBLIC).isNotEqualTo(0)
    }

    @Test
    fun asmSupportsCurrentJdkVersion() {
        assertThat(Opcodes.V26).isGreaterThan(0)
    }
}
