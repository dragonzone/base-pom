package zone.dragon.basepom.ktor.k24

import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

private val logger = KotlinLogging.logger {}

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }
    routing {
        get("/hello") {
            logger.info { "Handling /hello request" }
            call.respond(Message(text = "Hello, World!", recipient = "World"))
        }
    }
}

fun main() {
    embeddedServer(Netty, port = 8080) {
        module()
    }.start(wait = true)
}
