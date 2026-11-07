package zone.dragon.basepom.ktor.k24

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.read.ListAppender
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.testing.testApplication
import org.slf4j.Logger.ROOT_LOGGER_NAME
import org.slf4j.LoggerFactory

/**
 * Compatibility smoke test that exercises the standardized Kotlin dependency tree end-to-end to ensure the managed
 * versions are mutually compatible. A single request drives all three libraries together:
 *
 * - **Ktor** serves the request through the test host, client, and routing/engine stack.
 * - **kotlinx.serialization** (via Ktor content negotiation) serializes the response object to JSON on the server and
 *   deserializes it back on the client.
 * - **kotlin-logging** (over SLF4J/Logback) emits a log statement from the handler, captured here to confirm the
 *   logging backend is wired up and compatible.
 *
 * If any of these managed versions drift out of compatibility, this test fails to compile or run.
 */
class HelloWorldTest {

    @Test
    fun testHelloEndpoint() = testApplication {
        application {
            module()
        }
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        // Capture on the root logger so the handler's kotlin-logging statement is recorded during the request.
        val root = LoggerFactory.getLogger(ROOT_LOGGER_NAME) as Logger
        val appender = ListAppender<ILoggingEvent>().apply { start() }
        root.addAppender(appender)
        try {
            val response = client.get("/hello")

            assertEquals(HttpStatusCode.OK, response.status)
            assertEquals(ContentType.Application.Json, response.contentType()?.withoutParameters())
            // The endpoint returns a Message object that ktor serializes to JSON; deserialize it back to verify.
            assertEquals(Message(text = "Hello, World!", recipient = "World"), response.body<Message>())
        } finally {
            root.detachAppender(appender)
        }

        val event = appender.list.single { it.formattedMessage.contains("Handling /hello request") }
        assertEquals(Level.INFO, event.level)
        assertTrue(event.loggerName.startsWith("zone.dragon.basepom.ktor.k24"))
    }
}