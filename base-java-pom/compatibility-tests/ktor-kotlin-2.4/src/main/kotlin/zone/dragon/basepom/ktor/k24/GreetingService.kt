package zone.dragon.basepom.ktor.k24

/** Builds [Message]s by delegating lookups to a [GreetingRepository]; mocked in tests via mockito-kotlin. */
class GreetingService(private val repository: GreetingRepository) {
    fun greet(name: String): Message {
        val text = repository.greetingFor(name)
        return Message(text = text, recipient = name)
    }
}
