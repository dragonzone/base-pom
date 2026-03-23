package zone.dragon.basepom.ktor.k17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CrossLanguageTest {

    @Test
    void javaReferencesKotlinType() {
        JavaGreeting javaGreeting = new JavaGreeting("Hello");
        Greeting greeting = new Greeting(javaGreeting);
        assertEquals("Greeting: Hello", greeting.format());
    }
}
