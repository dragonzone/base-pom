package zone.dragon.basepom.ktor.k16;

public class JavaGreeting {

    private final String message;
    private final Greeting kotlinMessage = null;

    public JavaGreeting(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
