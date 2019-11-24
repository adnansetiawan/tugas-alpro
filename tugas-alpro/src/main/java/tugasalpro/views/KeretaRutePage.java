package tugasalpro.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tugasalpro.managers.KeretaManager;
import tugasalpro.managers.KeretaRuteManager;
import tugasalpro.managers.RuteManager;
import tugasalpro.models.Kereta;
import tugasalpro.models.KeretaRute;
import tugasalpro.models.Rute;

public class KeretaRutePage {
    private RuteManager ruteManager;
    private KeretaManager keretaManager;
    private KeretaRuteManager keretaRuteManager;
    Scanner scanner;

    public KeretaRutePage() {
        ruteManager = new RuteManager();
        keretaManager = new KeretaManager();
        keretaRuteManager = new KeretaRuteManager();
        scanner = new Scanner(System.in);

    }

    public void showMenu()  {

        int pilihan = 0;
        do {
            System.out.println("Menu Kereta pada Rute");
            System.out.println("1. Tambah Kereta Rute");
            System.out.println("2. Lihat Kereta Rute");
            System.out.println("3. Hapus Kereta Rute");
            System.out.println("99. Keluar");
            System.out.print("Pilihan : ");
            try {
                pilihan = scanner.nextInt();
                if (pilihan == 1) {
                    menuTambah();
                } else if (pilihan == 2) {
                    menuTampil();
                } else if (pilihan == 3) {
                    menuHapus();
                }
            } catch (Exception e) {
                System.out.println("Format input salah");
            }
            

        } while (pilihan != 99);
    }

    void menuTambah() {
        KeretaRute keretaRute = new KeretaRute();
        Kereta kereta = null;
        String kodeRute = null;
        Rute rutePilihan = null;
        String kodeKereta = null;
        int i = 1;
        boolean flagIterate = true;
        List<KeretaRute> listKeretaRute = keretaRuteManager.GetAll();
        int no;
        System.out.println("#KELOLA KERETA API BERDASARKAN RUTE#");
        System.out.print("Kode Rute : ");
        kodeRute = scanner.next();
        rutePilihan = ruteManager.GetByKodeRute(kodeRute);
        if (rutePilihan!=null) {
            keretaRute.setRuteKereta(rutePilihan);
            do {
                System.out.print("Kereta "+i+" : ");
                kodeKereta = scanner.next();
                if (kodeKereta=="99") {
                    flagIterate = false;
                } else {
                    kereta = keretaManager.getByKodeKereta(kodeKereta);
                    if (kereta!=null) {
                        i++;
                        keretaRute.getKeretaTersedia().add(kereta);
                    } else {
                        System.out.println("Kode Kereta "+kodeKereta+" tidak ditemukan ");
                        flagIterate = false;
                    }
                }
            } while (flagIterate);
            // ambil no terakhir
            no = listKeretaRute.size();
            if (no>9) {
                keretaRute.setKodeKeretaRute("KR"+no);
            } else {
                keretaRute.setKodeKeretaRute("KR0"+no);
            }
        } else {
            System.out.println("Rute tidak ditemukan - Keluar");
        }

        keretaRuteManager.add(keretaRute);

       
        /*rute.setKotaAsal(kotaManager.GetByNamaKota(kotaAsal));
        rute.setKotaTujuan(kotaManager.GetByNamaKota(kotaTujuan));
        rute.setHargaBisnis(hrgBisnis);
        rute.setHargaPremium(hrgPremium);
        rute.setKodeRute(rute.getKotaAsal().getKodeKota()+"-"+rute.getKotaTujuan().getKodeKota());
        rute.setKeretaRute(null);
        ruteManager.add(rute);
        */
    }

    public void menuTampil()  {
        System.out.println("-------------------------------------------------------");
        System.out.println("No \t Kode Kereta Rute \t Kode Rute \t Kereta Tersedia Pada Rute");
        List<KeretaRute>listKeretaRute = keretaRuteManager.GetAll();
        int i = 0;
        for (KeretaRute keretaRute : listKeretaRute) {
            ArrayList<Kereta> kereta = keretaRute.getKeretaTersedia();
            i++;
            System.out.println(i+" \t "+keretaRute.getKodeKeretaRute()+" \t\t\t "
                +keretaRute.getRuteKereta().getKodeRute()+" \t "
                +kereta.get(0).getKodeKereta());
            int j = 0;
            for (Kereta listKereta : kereta) {
                if (j!=0) {
                    System.out.println("\t \t \t\t \t\t "+listKereta.getKodeKereta());
                }
                j++;
            }
        }
        System.out.println("-------------------------------------------------------");

    }

    public void menuHapus() {
        menuTampil();
        String kodeKeretaRute = null;
        KeretaRute delKeretaRute = null;
        boolean flagIterate = true;
        do {
            System.out.print("Hapus Rute : ");
            kodeKeretaRute = scanner.next();
            if (kodeKeretaRute=="99") {
                flagIterate = false;
            } else {
                kodeKeretaRute = kodeKeretaRute.substring(7);
                delKeretaRute = keretaRuteManager.getByKodeKeretaRute(kodeKeretaRute);
                if (delKeretaRute!=null) {
                    keretaRuteManager.delete(delKeretaRute);
                    flagIterate = false;
                }
            }
        } while (flagIterate);
    }
}