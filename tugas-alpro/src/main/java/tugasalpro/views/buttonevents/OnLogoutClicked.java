package tugasalpro.views.buttonevents;
import tugasalpro.views.LoginPage;

public class OnLogoutClicked implements Runnable {
    private LoginPage loginPage;
    public OnLogoutClicked( LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    @Override
    public void run() {
        loginPage.logout();
        
    }
   
    
}