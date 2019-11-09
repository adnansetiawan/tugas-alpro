package tugasalpro;

import java.util.Scanner;

public class LoginPage
{
    private LoginManager loginManager;
    private MenuManager menuManager;
    Scanner scanner;
      
    public LoginPage()
    {
        loginManager = new LoginManager();
        menuManager = new MenuManager();
       
    }

    private void login()
    {
        String username;
        String password;
        do
        {
            scanner= new Scanner(System.in);
            System.out.print("username :");
            username = scanner.nextLine();
            System.out.print("password :");
            password = scanner.nextLine();
            loginManager.Login(username, password);
            
        }while(!loginManager.HasLogin());
        if(loginManager.HasLogin())
        {
            if(ApplicationSession.getLoggedUser().isAdmin())
            {
                menuManager.ShowMenuAdmin();
        
            }else
            {
                menuManager.ShowMenuPengguna();
            }
        }
    }
    public void showLogin()
    {
        System.out.println("#LOGIN SISTEM#");
        int pilihan=-1;
        scanner= new Scanner(System.in);
        ///Using default user: admin, password admin for login as admin
        do
        {
            System.out.println("1. Login");
            System.out.println("2. Registrasi");
            System.out.print("Pilihan :");
            pilihan = scanner.nextInt();
        }while(pilihan < 0 || pilihan > 2) ;
        switch(pilihan)
        {
            case 1:
                login();
                break;
            case 2:
                ProfilePenggunaPage registrasiPenggunaPage = new ProfilePenggunaPage();
                registrasiPenggunaPage.Registrasi();
                break;
        }
    }

}