package tugasalpro;

import java.util.Scanner;

public class MenuManager
{
    public static void ShowMenuPengguna()
    {
        Scanner scanner = new Scanner(System.in);
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
        }while(pilihan > -1 && pilihan < 4);
       


    }

}