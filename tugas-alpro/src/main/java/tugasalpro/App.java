package tugasalpro;
//import tugasalpro.views.BookingPage;
import tugasalpro.views.LoginPage;
import tugasalpro.views.PageComponent;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    
    public static void main(String[] args) {
        
        PageComponent.Init();
        LoginPage loginPage = new LoginPage();
        loginPage.showWelcome();
        //BookingPage bp = new BookingPage();
        //bp.showInput();
    }
}
