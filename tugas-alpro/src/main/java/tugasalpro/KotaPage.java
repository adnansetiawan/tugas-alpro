package tugasalpro;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

public class KotaPage {
    private KotaManager kotaManager;
    Scanner scanner;

    public KotaPage() {

        kotaManager = new KotaManager();
        scanner = new Scanner(System.in);

    }

    void showMenu() throws IOException, URISyntaxException {

        int pilihan = 0;
        do {
            System.out.println("Menu Kota");
            System.out.println("1. Tambah Kota");
            System.out.println("2. Ubah Kota");
            System.out.println("3. Tampilkan Kota");
            System.out.println("4. Hapus Kota");
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
        Kota kota = new Kota();
        System.out.print("Tambah Kota : ");
        kota.setKodeKota(scanner.next());
        kota.setNamaKota(scanner.next());
        kotaManager.add(kota);
        
    }

    void menuTampil() throws IOException, URISyntaxException {
        System.out.println("-------------------------------------------------------");
        System.out.println("No \t Kode Kota \t Nama Kota");
        List<Kota> listKota = kotaManager.GetAll();
        int i = 0;
        for (Kota kota : listKota) {
            i++;
			System.out.println(i+" \t "+kota.getKodeKota()+" \t\t "+kota.getNamaKota());
        }
        System.out.println("-------------------------------------------------------");

    }

    void menuUbah() throws IOException, URISyntaxException {
        menuTampil();
        String kodeKota = null;
        Kota kota = null;
        boolean flagIterate = true;
        do {
            System.out.print("Edit Kota : ");
            kodeKota = scanner.next();
            if (kodeKota=="99") {
                flagIterate = false;
            } else {
                kodeKota = kodeKota.substring(5);
                System.out.println(kodeKota);
                kota = kotaManager.GetByKodeKota(kodeKota);
                if (kota!=null) {
                    kotaManager.delete(kota);
                    flagIterate = false;
                }
            }
        } while (flagIterate);

        kota = new Kota();
        System.out.print("Kode Kota : ");
        kota.setKodeKota(scanner.next());
        System.out.print("Nama Kota : ");
        kota.setNamaKota(scanner.next());
        kotaManager.add(kota);
    }

    void menuHapus() throws IOException, URISyntaxException {
        menuTampil();
        String kodeKota = null;
        Kota delKota = null;
        boolean flagIterate = true;
        do {
            System.out.print("Hapus Kota : ");
            kodeKota = scanner.next();
            if (kodeKota=="99") {
                flagIterate = false;
            } else {
                kodeKota = kodeKota.substring(7);
                delKota = kotaManager.GetByKodeKota(kodeKota);
                if (delKota!=null) {
                    kotaManager.delete(delKota);
                    flagIterate = false;
                }
            }
        } while (flagIterate);
    }
}