package tugasalpro.views;

import java.util.Scanner;

import tugasalpro.*;
import tugasalpro.managers.*;

public class LoginPage {
    private LoginManager loginManager;
    private MenuPage menuPage;
    Scanner scanner;

    public LoginPage() {
        loginManager = new LoginManager();
        menuPage = new MenuPage();

    }
    public void Logout()
    {
        loginManager.Logout();
        System.out.println("logout success");
        showWelcome();
    }
    private void showLogin()
    {
        System.out.println("#LOGIN#");
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
            if(!loginManager.HasLogin()){
                System.out.println("Username dan/atau password yang Anda masukkan salah.");
                System.out.println("Masukkan username dan password yang benar.");
            }
        }while(!loginManager.HasLogin());
        if(loginManager.HasLogin())
        {
            if(ApplicationSession.getLoggedUser().isAdmin())
            {
                menuPage.showMenuAdmin();
        
            }else
            {
                menuPage.showMenuPengguna();
            }
        }
    }
    
    public void showWelcome()
    {
        System.out.println("#SELAMAT DATANG#");
        int pilihan=-1;
        scanner= new Scanner(System.in);
        ///Using default user: admin, password admin for login as admin
        do
        {
            System.out.println("1. Login");
            System.out.println("2. Registrasi");
            System.out.println("0. Keluar");
            System.out.print("Pilihan : ");
            pilihan = scanner.nextInt();
        }while(pilihan < 0 || pilihan > 2) ;
        switch(pilihan)
        {
            case 0:
                System.exit(0);
                break;
            case 1:
                showLogin();
                break;
            case 2:
                UserPage userPage = new UserPage();
                userPage.registrasi();
                break;
        }
    }

}