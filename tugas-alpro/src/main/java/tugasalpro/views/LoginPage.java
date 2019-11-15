package tugasalpro.views;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.text.PasswordView;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.TextBox.Style;
import com.googlecode.lanterna.gui2.Window.Hint;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.terminal.*;

import tugasalpro.*;
import tugasalpro.managers.*;

public class LoginPage {
    private LoginManager loginManager;
    private UserMenuPage userMenuPage;
    private Scanner scanner;
    private Terminal terminal;
    private Screen screen;

    public LoginPage() {
        loginManager = new LoginManager();
        userMenuPage = new UserMenuPage();
        try {
            /*terminal = new DefaultTerminalFactory().createTerminalEmulator();
            terminal.setBackgroundColor(TextColor.ANSI.WHITE);

            // terminal.setTi
            screen = new TerminalScreen(terminal);

            screen.startScreen();*/
            terminal = PageComponent.getTerminal();
            screen = PageComponent.getScreen();
        } catch (Exception ex) {

        }

    }

    public void Logout() {
        loginManager.Logout();
        System.out.println("logout success");
        showWelcome();
    }

    public void showLogin() {
        Panel panel = new Panel();
        WindowBasedTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(),
        new EmptySpace(TextColor.ANSI.BLUE));
        BasicWindow window = new BasicWindow("LOGIN");
        
        panel.setLayoutManager(new GridLayout(2));
        addEmptySpace(panel, 2);

        panel.addComponent(new Label("Username  :"));
        final TextBox usernamTextBox = new TextBox();
        usernamTextBox.addTo(panel);
        addEmptySpace(panel, 2);

        panel.addComponent(new Label("Password  :"));
        final TextBox passwordTextBox = new TextBox();
        passwordTextBox.setMask('*');
        passwordTextBox.addTo(panel);
        addEmptySpace(panel, 2);

        BtnOkLogin oLogin = new BtnOkLogin(userMenuPage, loginManager,gui, usernamTextBox, passwordTextBox);
        Button btnLogout = new Button("Logout", new BtnOkLogout(loginManager, this));
        Button btnLogin = new Button("Login", oLogin);
        btnLogout.addTo(panel);
        btnLogin.addTo(panel);
       
       
        panel.addComponent(new EmptySpace());
        // Create gui and start gui
        window.setComponent(panel);
        window.setHints(Arrays.asList(Hint.CENTERED));
        gui.addWindowAndWait(window);
    }

    /*public void showLogin() {
        System.out.println("#LOGIN#");
        String username;
        String password;
        do {
            scanner = new Scanner(System.in);
            System.out.print("username :");
            username = scanner.nextLine();
            System.out.print("password :");
            password = scanner.nextLine();
            loginManager.Login(username, password);

        } while (!loginManager.HasLogin());
        if (loginManager.HasLogin()) {
            if (ApplicationSession.getLoggedUser().isAdmin()) {
                userMenuPage.ShowMenuAdmin();

            } else {
                userMenuPage.ShowMenuPengguna();
            }
        }
    }*/
    private void addEmptySpace(Panel panel, int number)
    {
        for(int i=0;i<number;i++)
        {
            panel.addComponent(new EmptySpace());

        }
    }
    public void showWelcome() {

        // Create panel to hold components
        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(),
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
        BtnOkWelcome onPilihanSelected = new BtnOkWelcome(this, pilihanText);
        addEmptySpace(panel2, 5);

        new Button("OK", onPilihanSelected).addTo(panel2);
        panel.addComponent(panel2);

        // Create window to hold the panel
        BasicWindow window = new BasicWindow("WELCOME KELOMPOK 3");
        // Create gui and start gui
        window.setComponent(panel);
        window.setHints(Arrays.asList(Hint.CENTERED));
       
        gui.addWindowAndWait(window);

        /*
         * System.out.println("#SELAMAT DATANG#"); int pilihan=-1; scanner= new
         * Scanner(System.in); ///Using default user: admin, password admin for login as
         * admin do { System.out.println("1. Login");
         * System.out.println("2. Registrasi"); System.out.print("Pilihan :"); pilihan =
         * scanner.nextInt(); }while(pilihan < 0 || pilihan > 2) ; switch(pilihan) {
         * case 1: showLogin(); break; case 2: ProfilePenggunaPage
         * registrasiPenggunaPage = new ProfilePenggunaPage();
         * registrasiPenggunaPage.Registrasi(); break; }
         */
    }

}

class BtnOkWelcome implements Runnable {
    private int pilihan;
    private TextBox pilihanText;
    private LoginPage loginPage;
    
    public BtnOkWelcome(LoginPage loginPage, TextBox pilihanText) {
        this.pilihanText = pilihanText;
        this.loginPage = loginPage;
        
    }

    @Override
    public void run() {
        pilihan = Integer.parseInt(pilihanText.getText());
        switch (pilihan) {
        case 1:
            try {
                loginPage.showLogin();
            } catch (Exception e) {
                
            }
            break;
        case 2:
            ProfilePenggunaPage registrasiPenggunaPage = new ProfilePenggunaPage();
            registrasiPenggunaPage.Registrasi();

            break;
        }
    }
}

class BtnOkLogin implements Runnable {
    private TextBox usernameTextBox;
    private TextBox passwordTextBox;
    private LoginManager loginManager;
    private WindowBasedTextGUI window;
    private UserMenuPage userMenuPage;
    public BtnOkLogin(UserMenuPage userMenuPage, LoginManager loginManager, WindowBasedTextGUI window, TextBox usernameTextBox, TextBox passwordTextBox) {
        this.usernameTextBox = usernameTextBox;
        this.passwordTextBox = passwordTextBox;
        this.window = window;
        this.loginManager = loginManager;
        this.userMenuPage = userMenuPage;
    }

    @Override
    public void run() {
        loginManager.Login(this.usernameTextBox.getText(), this.passwordTextBox.getText());
        if (loginManager.HasLogin()) {
            if (ApplicationSession.getLoggedUser().isAdmin()) {
                userMenuPage.ShowMenuAdmin();

            } else {
                userMenuPage.ShowMenuPengguna();
            }
        } else {
            MessageDialog.showMessageDialog(this.window, "Error", "username atau password yang anda masukan salah", MessageDialogButton.OK);
            usernameTextBox.setText("");
            passwordTextBox.setText("");
            usernameTextBox.takeFocus();
       }
    }
   
    
}
class BtnOkLogout implements Runnable {
    private LoginManager loginManager;
    private LoginPage loginPage;
    public BtnOkLogout(LoginManager loginManager, LoginPage loginPage) {
        this.loginManager = loginManager;
        this.loginPage = loginPage;
    }

    @Override
    public void run() {
        loginManager.Logout();
        loginPage.showWelcome();
    }
   
    
}