package zone.dragon.basepom.ktor.k24

import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import kotlin.test.Test
import kotlin.test.assertEquals

class GreetingServiceMockitoTest {

    @Test
    fun usesMockedRepository() {
        val repository = mock<GreetingRepository> {
            on { greetingFor("World") } doReturn "Hello, World!"
        }

        val message = GreetingService(repository).greet("World")

        assertEquals(Message(text = "Hello, World!", recipient = "World"), message)
        verify(repository).greetingFor("World")
    }
}
