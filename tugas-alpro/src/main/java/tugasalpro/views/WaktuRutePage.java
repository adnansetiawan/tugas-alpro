package tugasalpro.views;

import java.util.List;
import java.util.Scanner;
import tugasalpro.*;
import tugasalpro.managers.*;
import tugasalpro.models.*;
import tugasalpro.views.*;

public class WaktuRutePage
{
    Scanner scanner;

    public WaktuRutePage()
    {
        new WaktuRuteManager();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int pilihan = 0;
        do {
            System.out.println("Menu Waktu Rute");
            System.out.println("1. Tambah Waktu Rute");
            System.out.println("2. Tampilkan Waktu Rute");
            System.out.println("3. Hapus Waktu Rute");
            System.out.println("99. Keluar");
            System.out.print("Pilihan : ");
            pilihan = scanner.nextInt();
            if (pilihan == 1) {
                Tambah();
            } else if (pilihan == 2) {
                Tampil();
            } else if (pilihan == 3) {
                Hapus();
            }

        } while (pilihan != 99);
    }

    private void Tambah()
    {
        new WaktuRuteManager().tambahRute();
    }

    private void Tampil()
    {
        int i = 1;
        String textKodeRute="";
        System.out.println("-----------------------------------------------------------------");
        System.out.println("No \t Kode Waktu Rute \t Kode Rute \t Waktu Tersedia Rute");
        List<WaktuRute> listWaktuRute = new WaktuRuteManager().GetAll();
        for (WaktuRute waktuRute : listWaktuRute)
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
        System.out.println("-----------------------------------------------------------------");

    }

    private void Hapus()
    {
        Tampil();
        new WaktuRuteManager().hapusRute();
    }
}