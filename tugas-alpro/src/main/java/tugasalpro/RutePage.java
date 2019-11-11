package tugasalpro;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

public class RutePage {
    private RuteManager ruteManager;
    private KotaManager kotaManager;
    Scanner scanner;

    public RutePage() {

        ruteManager = new RuteManager();
        kotaManager = new KotaManager();
        scanner = new Scanner(System.in);

    }

    void showMenu() throws IOException, URISyntaxException {

        int pilihan = 0;
        do {
            System.out.println("Menu Rute");
            System.out.println("1. Tambah Rute");
            System.out.println("2. Ubah Rute");
            System.out.println("3. Tampilkan Rute");
            System.out.println("4. Hapus Rute");
            System.out.println("99. Keluar");
            System.out.print("Pilihan : ");
            pilihan = scanner.nextInt();
            if (pilihan == 1) {
                menuTambah();
            } else if (pilihan == 2) {
                menuUbah();
            } else if (pilihan == 3) {
                menuTampil();
            } else if (pilihan == 4) {
                menuHapus();
            }

        } while (pilihan != 99);
    }

    void menuTambah() throws IOException, URISyntaxException {
        Rute rute = new Rute();
        String kotaAsal,kotaTujuan;
        int hrgBisnis,hrgPremium;
        System.out.print("Tambah Rute : ");
        kotaAsal = scanner.next();
        kotaTujuan = scanner.next();
        hrgBisnis = scanner.nextInt();
        hrgPremium = scanner.nextInt();
        rute.setKotaAsal(kotaManager.GetByNamaKota(kotaAsal));
        rute.setKotaTujuan(kotaManager.GetByNamaKota(kotaTujuan));
        rute.setHargaBisnis(hrgBisnis);
        rute.setHargaPremium(hrgPremium);
        rute.setKodeRute(rute.getKotaAsal().getKodeKota()+"-"+rute.getKotaTujuan().getKodeKota());
        rute.setKeretaRute(null);
        ruteManager.add(rute);
        
    }

    void menuTampil() throws IOException, URISyntaxException {
        System.out.println("-------------------------------------------------------");
        System.out.println("No \t Keberangkatan \t Tujuan \t Kode_Rute \t Bisnis \t Premium");
        List<Rute> listRute = ruteManager.GetAll();
        int i = 0;
        for (Rute rute : listRute) {
            i++;
            System.out.println(i+" \t "+rute.getKotaAsal().getNamaKota()+" \t\t "
                +rute.getKotaTujuan().getNamaKota()+" \t "
                +rute.getKodeRute()+" \t "
                +rute.getHargaBisnis()+" \t "
                +rute.getHargaPremium());
        }
        System.out.println("-------------------------------------------------------");

    }

    void menuUbah() throws IOException, URISyntaxException {
        menuTampil();
        String kodeRute = null;
        Rute rute = null;
        boolean flagIterate = true;

        String kotaAsal,kotaTujuan;
        int hrgBisnis,hrgPremium;
        do {
            System.out.print("Edit Rute : ");
            kodeRute = scanner.next();
            if (kodeRute=="99") {
                flagIterate = false;
            } else {
                kodeRute = kodeRute.substring(5);
                rute = ruteManager.GetByKodeRute(kodeRute);
                if (rute!=null) {
                    ruteManager.delete(rute);
                    flagIterate = false;
                }
            }
        } while (flagIterate);

        rute = new Rute();
        System.out.print("Kota Asal : ");
        kotaAsal = scanner.next();
        System.out.print("Kota Tujuan : ");
        kotaTujuan = scanner.next();
        System.out.print("Harga Bisnis : ");
        hrgBisnis = scanner.nextInt();
        System.out.print("Harga Premium: ");
        hrgPremium = scanner.nextInt();
        rute.setKotaAsal(kotaManager.GetByNamaKota(kotaAsal));
        rute.setKotaTujuan(kotaManager.GetByNamaKota(kotaTujuan));
        rute.setHargaBisnis(hrgBisnis);
        rute.setHargaPremium(hrgPremium);
        rute.setKodeRute(rute.getKotaAsal().getKodeKota()+"-"+rute.getKotaTujuan().getKodeKota());
        rute.setKeretaRute(null);
        ruteManager.add(rute);
    }

    void menuHapus() throws IOException, URISyntaxException {
        menuTampil();
        String kodeRute = null;
        Rute delRute = null;
        boolean flagIterate = true;
        do {
            System.out.print("Hapus Rute : ");
            kodeRute = scanner.next();
            if (kodeRute=="99") {
                flagIterate = false;
            } else {
                kodeRute = kodeRute.substring(7);
                delRute = ruteManager.GetByKodeRute(kodeRute);
                if (delRute!=null) {
                    ruteManager.delete(delRute);
                    flagIterate = false;
                }
            }
        } while (flagIterate);
    }
}