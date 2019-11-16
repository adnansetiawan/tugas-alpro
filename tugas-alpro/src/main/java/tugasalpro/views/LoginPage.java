package tugasalpro.views;

import java.util.Arrays;

import java.util.regex.Pattern;


import com.googlecode.lanterna.*;
import com.googlecode.lanterna.gui2.*;

import com.googlecode.lanterna.gui2.Window.Hint;


import tugasalpro.managers.*;
import tugasalpro.views.buttonevents.OnLoginClicked;
import tugasalpro.views.buttonevents.OnLogoutClicked;
import tugasalpro.views.buttonevents.OnOkWelcomeClicked;

public class LoginPage extends BasePage {
    private LoginManager loginManager;
    
    public LoginPage() {
        loginManager = new LoginManager();
   
    }

    public void logout() {
        loginManager.Logout();
        showWelcome();
    }

    public void showLogin() {
        Panel panel = new Panel();
        WindowBasedTextGUI gui = new MultiWindowTextGUI(getScreen(), new DefaultWindowManager(),
        new EmptySpace(TextColor.ANSI.BLUE));
        BasicWindow window = new BasicWindow("LOGIN");
        panel.setLayoutManager(new GridLayout(3));
        addEmptySpace(panel, 3);

        panel.addComponent(new Label("Username  :"));
        final TextBox usernameTextBox = new TextBox();
        usernameTextBox.setLayoutData(GridLayout.createLayoutData(
            GridLayout.Alignment.BEGINNING,
            GridLayout.Alignment.BEGINNING,
            true,
            false,
            2,
            1));
        usernameTextBox.setPreferredSize(new TerminalSize(28,1));
        usernameTextBox.addTo(panel);
        
        addEmptySpace(panel, 3);

        panel.addComponent(new Label("Password  :"));
        final TextBox passwordTextBox = new TextBox();
        passwordTextBox.setPreferredSize(new TerminalSize(28,1));
        passwordTextBox.setLayoutData(GridLayout.createLayoutData(
            GridLayout.Alignment.BEGINNING,
            GridLayout.Alignment.BEGINNING,
            true,
            false,
            2,
            1));
        passwordTextBox.setMask('*');
        passwordTextBox.addTo(panel);
        addEmptySpace(panel, 3);
        addEmptySpace(panel, 1);
        Panel btnPanel = new Panel();
        btnPanel.setLayoutManager(new GridLayout(2));
       
        Button btnLogout = new Button("Logout", new OnLogoutClicked());
        btnLogout.setLayoutData(GridLayout.createLayoutData(
            GridLayout.Alignment.BEGINNING,
            GridLayout.Alignment.BEGINNING));
        Button btnLogin = new Button("Login", new OnLoginClicked(gui, usernameTextBox, passwordTextBox));
        btnLogin.setLayoutData(GridLayout.createLayoutData(
            GridLayout.Alignment.END,
            GridLayout.Alignment.BEGINNING));
        btnLogin.addTo(btnPanel);
        btnLogout.addTo(btnPanel);
        btnPanel.addTo(panel);
       
        panel.addComponent(new EmptySpace());
        // Create gui and start gui
        window.setComponent(panel);
        window.setHints(Arrays.asList(Hint.CENTERED));
        gui.addWindowAndWait(window);
    }

    public void showWelcome() {

        // Create panel to hold components
        MultiWindowTextGUI gui = new MultiWindowTextGUI(getScreen(), new DefaultWindowManager(),
        new EmptySpace(TextColor.ANSI.BLUE));
        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(1));
        addEmptySpace(panel, 1);
        panel.addComponent(new Label("1. Login"));
        panel.addComponent(new Label("2. Registrasi"));
        addEmptySpace(panel, 1);

        Panel panel2 = new Panel();
        panel2.setLayoutManager(new GridLayout(3));

        panel2.addComponent(new Label("Pilih    :"));
        panel2.addComponent(AnimatedLabel.createClassicSpinningLine());

        final TextBox pilihanText = new TextBox().setValidationPattern(Pattern.compile("[0-9]*"));
        pilihanText.addTo(panel2);
        addEmptySpace(panel2, 5);

        Button btnLogin =  new Button("OK", new OnOkWelcomeClicked(pilihanText));
        btnLogin.addTo(panel2);

        panel.addComponent(panel2);

        // Create window to hold the panel
        BasicWindow window = new BasicWindow("WELCOME KELOMPOK 3");
        // Create gui and start gui
        window.setComponent(panel);
        window.setHints(Arrays.asList(Hint.CENTERED));
       
        gui.addWindowAndWait(window);

    }

}




