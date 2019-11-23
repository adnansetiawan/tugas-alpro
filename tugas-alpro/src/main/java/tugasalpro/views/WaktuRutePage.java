package tugasalpro.views;

import java.util.List;
import java.util.Scanner;
import tugasalpro.managers.*;
import tugasalpro.models.*;

public class WaktuRutePage
{
    Scanner scanner;
    Scanner keyb;

    public WaktuRutePage()
    {
        new WaktuRuteManager();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int pilihan = 0;
        do {
            System.out.println("#KELOLA WAKTU BERDASARKAN RUTE#");
            System.out.println("Menu Waktu Rute");
            System.out.println("1. Tambah Waktu Pada Rute");
            System.out.println("2. Lihat Waktu Pada Rute");
            System.out.println("3. Delete Waktu Pada Rute");
            System.out.println("99. Keluar");
            System.out.print("Pilihan : ");
            pilihan = scanner.nextInt();
            if (pilihan == 1) {
                menuTambah();
            } else if (pilihan == 2) {
                menuTampil();
            } else if (pilihan == 3) {
                menuHapus();
            }

        } while (pilihan != 99);
    }

    private void menuTambah()
    {
        System.out.println("#KELOLA WAKTU BERDASARKAN RUTE#");
        System.out.println();
        new WaktuRuteManager().tambahRute();
    }

    private void menuTampil()
    {
        keyb = new Scanner(System.in);
        int i = 1;
        int statusPrint = 1;
        String textKodeRute="";
        String cariKodeRute="";
        System.out.println("#LIHAT WAKTU BERDASARKAN RUTE#");
        System.out.print("Kode Rute : ");
        cariKodeRute = keyb.nextLine();
        System.out.println();
        System.out.println("Waktu Available Untuk Rute");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("No \t Kode Waktu Rute \t Kode Rute \t Waktu Tersedia Rute");
        List<WaktuRute> listWaktuRute = new WaktuRuteManager().GetAll();
        for (WaktuRute waktuRute : listWaktuRute)
        {
            statusPrint = 1;
            if (cariKodeRute.compareTo("")!=0 && cariKodeRute.compareTo(waktuRute.getKodeRute())!=0)
            {
                statusPrint = 0;
            }
            if (statusPrint==1)
            {
                if (textKodeRute.compareTo(waktuRute.getKodeRute())!=0)
                {
                    textKodeRute = waktuRute.getKodeRute();
                    System.out.print(i);
                    System.out.print(" \t "+waktuRute.getKodeWaktuRute()+" \t \t \t "+waktuRute.getKodeRute());
                    i=i+1;
                }
                else
                {
                    System.out.print("  \t \t \t \t \t");
                }
                Waktu textWaktu = new WaktuManager().GetByKodeWaktu(waktuRute.getArrWaktu());
                if (textWaktu != null)
                {
                    System.out.print(" \t - " + textWaktu.getWaktu());
                    System.out.println();
                }
            }
        }
        System.out.println("-----------------------------------------------------------------");

    }

    private void menuTampil2()
    {
        int i = 1;
        int statusPrint = 1;
        String textKodeRute="";
        String cariKodeRute="";
        System.out.println("#LIHAT WAKTU BERDASARKAN RUTE#");
        System.out.println();
        System.out.println("Waktu Available Untuk Rute");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("No \t Kode Waktu Rute \t Kode Rute \t Waktu Tersedia Rute");
        List<WaktuRute> listWaktuRute = new WaktuRuteManager().GetAll();
        for (WaktuRute waktuRute : listWaktuRute)
        {
            statusPrint = 1;
            if (cariKodeRute.compareTo("")!=0 && cariKodeRute.compareTo(waktuRute.getKodeRute())!=0)
            {
                statusPrint = 0;
            }
            if (statusPrint==1)
            {
                if (textKodeRute.compareTo(waktuRute.getKodeRute())!=0)
                {
                    textKodeRute = waktuRute.getKodeRute();
                    System.out.print(i);
                    System.out.print(" \t "+waktuRute.getKodeWaktuRute()+" \t \t \t "+waktuRute.getKodeRute());
                    i=i+1;
                }
                else
                {
                    System.out.print("  \t \t \t \t \t");
                }
                Waktu textWaktu = new WaktuManager().GetByKodeWaktu(waktuRute.getArrWaktu());
                if (textWaktu != null)
                {
                    System.out.print(" \t - " + textWaktu.getWaktu());
                    System.out.println();
                }
            }
        }
        System.out.println("-----------------------------------------------------------------");

    }

    private void menuHapus()
    {
        menuTampil2();
        new WaktuRuteManager().hapusRute();
    }
}