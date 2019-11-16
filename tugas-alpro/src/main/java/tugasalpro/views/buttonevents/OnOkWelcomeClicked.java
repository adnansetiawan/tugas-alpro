package tugasalpro.views.buttonevents;

import com.googlecode.lanterna.gui2.*;

import tugasalpro.views.*;

public class OnOkWelcomeClicked implements Runnable {
    private int pilihan;
    private TextBox pilihanText;
    
    public OnOkWelcomeClicked(TextBox pilihanText) {
        this.pilihanText = pilihanText;
        
    }

    @Override
    public void run() {
        pilihan = Integer.parseInt(pilihanText.getText());
        switch (pilihan) {
        case 1:
            LoginPage loginPage = new LoginPage();
            loginPage.showLogin();
            break;
        case 2:
            ProfilePenggunaPage registrasiPenggunaPage = new ProfilePenggunaPage();
            registrasiPenggunaPage.Registrasi();

            break;
        }
    }
}