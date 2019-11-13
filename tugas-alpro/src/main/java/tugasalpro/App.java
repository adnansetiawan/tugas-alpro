package tugasalpro;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    
    public static void main(String[] args) {
        
        LoginPage loginPage = new LoginPage();
        loginPage.showWelcome();
      
    }
}
