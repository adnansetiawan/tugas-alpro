package tugasalpro;

import java.util.Scanner;

public class LoginPage
{
    private LoginManager loginManager;
    public LoginPage()
    {
        loginManager = new LoginManager();
    }
    public void ShowLogin()
    {
        System.out.println("#LOGIN SISTEM#");
        String username;
        String password;
        Scanner scanner = new Scanner(System.in);
        do
        {
            System.out.print("username :");
            username = scanner.nextLine();
            System.out.print("password :");
            password = scanner.nextLine();
            loginManager.Login(username, password);
            
        }while(!loginManager.HasLogin());
        if(loginManager.HasLogin())
        {
            MenuManager.ShowMenuPengguna();
        }
    }

}