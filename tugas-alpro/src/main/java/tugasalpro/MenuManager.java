package tugasalpro;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class MenuManager {
    private Scanner scanner;

    public MenuManager() {
        scanner = new Scanner(System.in);

    }

    public void ShowMenuPengguna() {
        User user = ApplicationSession.getLoggedUser();
        System.out.println("#MENU PEGGUNA#");
        System.out.println("Welcome," + user.getUsername());
        System.out.println("1. Booking Tiket");
        System.out.println("2. Kelola Profile");
        System.out.println("3. History Pembelian");
        System.out.println("0. Logout");
        int pilihan = -1;
        do {
            System.out.print("Pilihan :");
            pilihan = scanner.nextInt();
        } while (pilihan < 0 || pilihan > 3);
        switch (pilihan) {
            case 0:
            LoginManager loginManager = new LoginManager();
            loginManager.Logout();
            break;
        case 2:
            ProfilePenggunaPage profilePenggunaPage = new ProfilePenggunaPage();
            profilePenggunaPage.ShowUpdatePenggunaPage();
            break;
        }

    }

    public void ShowMenuAdmin()
    {
        System.out.println("#MENU ADMIN#");
        System.out.println("Welcome, Admin");
        System.out.println("1. Kelola Akun");
        System.out.println("2. Kelola Data Kota");
        System.out.println("3. Generate Waktu");
        System.out.println("4. Kelola Rute");
        System.out.println("0. Logout");
        int pilihan = -1;
        do
        {
            System.out.print("Pilihan :");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 0:
                LoginManager loginManager = new LoginManager();
                loginManager.Logout();
                case 1:
                    ProfilePenggunaPage profilePenggunaPage = new ProfilePenggunaPage();
                    profilePenggunaPage.ShowUpdatePenggunaPage();
                    break;
                case 2:
                    KotaPage kotaPage = new KotaPage();
                    try {
                        kotaPage.showMenu();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    
                    break;
                case 4:
                    RutePage rutePage = new RutePage();
                    try {
                        rutePage.showMenu();
                    } catch (IOException | URISyntaxException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
            
                default:
                    break;
            }

        }while(pilihan < 0 || pilihan > 4);
      
    }

}