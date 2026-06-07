package zone.dragon.basepom.ktor.k24

/** A simple collaborator that can be mocked with mockito-kotlin in tests. */
interface GreetingRepository {
    fun greetingFor(name: String): String
}
