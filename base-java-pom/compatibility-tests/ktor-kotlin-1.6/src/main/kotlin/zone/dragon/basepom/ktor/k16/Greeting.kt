package zone.dragon.basepom.ktor.k16

data class Greeting(val javaGreeting: JavaGreeting) {
    fun format(): String = "Greeting: ${javaGreeting.message}"
}
