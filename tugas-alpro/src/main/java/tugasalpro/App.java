package tugasalpro;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     * @throws URISyntaxException
     * @throws IOException
     */
    public static void main(String[] args) throws IOException, URISyntaxException {
        
        UserManager userManager = new UserManager();
        userManager.Save(new User("username", "password", new UserInfo("name", "13212314", "08333333")));
        
    }
}
