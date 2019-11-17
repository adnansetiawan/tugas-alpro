package tugasalpro.views.buttonevents;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.*;

import tugasalpro.ApplicationSession;
import tugasalpro.managers.LoginManager;
import tugasalpro.views.UserMenuPage;

public class OnLoginClicked implements Runnable {
    private TextBox usernameTextBox;
    private TextBox passwordTextBox;
    private LoginManager loginManager;
    private WindowBasedTextGUI window;
    public OnLoginClicked(WindowBasedTextGUI window, TextBox usernameTextBox, TextBox passwordTextBox) {
        this.usernameTextBox = usernameTextBox;
        this.passwordTextBox = passwordTextBox;
        this.window = window;
        this.loginManager = new LoginManager();
    }

    @Override
    public void run() {
        loginManager.Login(this.usernameTextBox.getText(), this.passwordTextBox.getText());
        if (loginManager.HasLogin()) {
            UserMenuPage userMenuPage = new UserMenuPage();
            if (ApplicationSession.getLoggedUser().isAdmin()) {
                userMenuPage.ShowMenuAdmin();

            } else {
                userMenuPage.ShowMenuPengguna();
            }
        } else {
            MessageDialog.showMessageDialog(this.window, "Error", "username atau password yang anda masukan salah", MessageDialogButton.OK);
            usernameTextBox.takeFocus();
       }
    }
   
    
}