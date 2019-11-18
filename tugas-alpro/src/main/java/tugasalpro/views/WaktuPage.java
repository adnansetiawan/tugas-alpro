package tugasalpro.views;

import java.util.Scanner;

import tugasalpro.managers.*;

public class WaktuPage
{
    private WaktuManager waktuManager;
    private Scanner scanner;
    public WaktuPage()
    {
        waktuManager = new WaktuManager();
        scanner = new Scanner(System.in);
    }
    public void generate()
    {
        System.out.print("Apakah anda yakin untuk generate waktu (Y/N)?");
        String input= scanner.nextLine();
        if(input.equals("Y"))
        {
            System.out.println("Generate Waktu Berhasil!");
            waktuManager.Generate();
        }
        System.out.println("1. Lihat Data Kereta");
        System.out.println("99. Menu Utama");
        
        int pilihan = scanner.nextInt();
            switch(pilihan)
            {
                case 1 :
                    KeretaPage keretaPage = new KeretaPage();
                    keretaPage.showMenu();
                    break;
                case 99 :
                    UserMenuPage userMenuPage = new UserMenuPage();
                    userMenuPage.ShowMenuAdmin();
                    break;

            }
        }
}