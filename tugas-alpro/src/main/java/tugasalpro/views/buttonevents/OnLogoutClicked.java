package tugasalpro.views.buttonevents;
import tugasalpro.views.LoginPage;

public class OnLogoutClicked implements Runnable {
    public OnLogoutClicked() {
       
    }

    @Override
    public void run() {
        LoginPage loginPage = new LoginPage();
        loginPage.logout();
        
    }
   
    
}