package zone.dragon.basepom.ktor.k24

data class Greeting(val javaGreeting: JavaGreeting) {
    fun format(): String = "Greeting: ${javaGreeting.message}"
}
