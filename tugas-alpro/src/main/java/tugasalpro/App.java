package tugasalpro;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    
    public static void main(String[] args) {
        
        LoginPage loginPage = new LoginPage();
        loginPage.showLogin();
    }
}
