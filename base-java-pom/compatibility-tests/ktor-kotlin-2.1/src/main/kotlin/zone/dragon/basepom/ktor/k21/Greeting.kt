package zone.dragon.basepom.ktor.k21

data class Greeting(val javaGreeting: JavaGreeting) {
    fun format(): String = "Greeting: ${javaGreeting.message}"
}
