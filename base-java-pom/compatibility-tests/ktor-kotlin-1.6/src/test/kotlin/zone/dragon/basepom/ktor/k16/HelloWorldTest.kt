package zone.dragon.basepom.ktor.k16

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class HelloWorldTest {

    @Test
    fun testHelloEndpoint() = testApplication {
        application {
            module()
        }
        val response = client.get("/hello")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Hello, World!", response.bodyAsText())
    }
}
