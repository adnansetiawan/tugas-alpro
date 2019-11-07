package tugasalpro;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        User user = new User("username", "password");
        System.out.println("Hello World!");
    }
}
