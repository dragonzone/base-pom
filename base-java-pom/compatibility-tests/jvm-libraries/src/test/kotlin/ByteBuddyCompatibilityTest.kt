import net.bytebuddy.ClassFileVersion
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ByteBuddyCompatibilityTest {

    @Test
    fun byteBuddySupportsCurrentJdkVersion() {
        val currentVersion = ClassFileVersion.ofThisVm()
        val jdk25 = ClassFileVersion.ofJavaVersion(25)
        assertThat(currentVersion.isAtLeast(jdk25)).isTrue()
    }
}
