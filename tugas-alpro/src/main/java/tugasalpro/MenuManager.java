package tugasalpro;

import java.util.Scanner;

public class MenuManager
{
    private  Scanner scanner;
       
    public MenuManager()
    {
        scanner = new Scanner(System.in);
       
    }
    public  void ShowMenuPengguna()
    {
        User user = ApplicationSession.getLoggedUser();
        System.out.println("#MENU PEGGUNA#");
        System.out.println("Welcome," + user.getUsername());
        System.out.println("1. Booking Tiket");
        System.out.println("2. Kelola Profile");
        System.out.println("3. History Pembelian");
        System.out.println("0. Logout");
        int pilihan = -1;
        do
        {
            System.out.print("Pilihan :");
            pilihan = scanner.nextInt();
        }while(pilihan < 0 || pilihan > 3);
        switch(pilihan)
        {
            case 2:
                ProfilePenggunaPage profilePenggunaPage = new ProfilePenggunaPage();
                profilePenggunaPage.ShowUpdatePenggunaPage();
                break; 
        }
      
    }
    public  void ShowMenuAdmin()
    {
        System.out.println("#MENU ADMIN#");
        System.out.println("Welcome, Admin");
        System.out.println("1. Kelola Akun");
        System.out.println("2. Kelola Data Kota");
        System.out.println("3. Generate Waktu");
        System.out.println("0. Logout");
        int pilihan = -1;
        do
        {
            System.out.print("Pilihan :");
            pilihan = scanner.nextInt();
        }while(pilihan < 0 || pilihan > 3);
      
    }

}