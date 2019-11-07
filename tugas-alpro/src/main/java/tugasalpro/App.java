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
        userManager.Save(new User("user1", "password", new UserInfo("name1", "123xxx", "08333333")));
        userManager.Save(new User("user2", "password", new UserInfo("name2", "24xxxx", "08333322")));
       
    }
}
