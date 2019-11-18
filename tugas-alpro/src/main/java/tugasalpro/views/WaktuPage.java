package tugasalpro.views;

import java.util.List;
import java.util.Scanner;
import tugasalpro.managers.*;
import tugasalpro.models.*;
import tugasalpro.Repository;
import tugasalpro.models.Waktu;

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
            System.out.println("-----------------------------------------------------------------");
            List<Waktu> listWaktu = waktuManager.GetAll();
            if (listWaktu.size()>0)
            {
                System.out.println("Data Waktu sudah digenerate");
            }
            else
            {
                waktuManager.Generate();
                System.out.println("Generate Waktu Berhasil!");
            }
        }
        ShowMenu();
    }

    public void ShowMenu()
    {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("1. Lihat Data Waktu");
        System.out.println("99. Menu Utama");
        
        int pilihan = scanner.nextInt();
        switch(pilihan)
        {
            case 1 :
                Tampil();
                ShowMenu();
                break;
            case 99 :
                UserMenuPage userMenuPage = new UserMenuPage();
                userMenuPage.ShowMenuAdmin();
                break;
        }
    }

    public void Tampil()
    {
        int i = 1;
        System.out.println("-----------------------------------------------------------------");
        System.out.println("No \t Kode Waktu \t Waktu");
        List<Waktu> listWaktu = new WaktuManager().GetAll();
        if (listWaktu.size()>0)
        {
            for (Waktu waktu : listWaktu)
            {
                System.out.println(i+" \t "+waktu.getKodeWaktu()+" \t \t "+waktu.getWaktu());
                i=i+1;
            }
        }
    }
}