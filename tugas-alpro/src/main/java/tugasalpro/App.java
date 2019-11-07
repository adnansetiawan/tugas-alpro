package tugasalpro;

import main.java.tugasalpro.LoginManager;

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
        LoginManager loginManager = new LoginManager();
        loginManager.Login("username", "password");
        System.out.println(loginManager.HasLogin());
        loginManager.Logout();
        System.out.println(loginManager.HasLogin());
    }
}
