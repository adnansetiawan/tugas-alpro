package tugasalpro.views;

import java.util.List;
import java.util.Scanner;

import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import tugasalpro.managers.*;
import tugasalpro.models.*;

public class KotaPage {
    private KotaManager kotaManager;
    Scanner scanner;

    public KotaPage() {

        kotaManager = new KotaManager();
        scanner = new Scanner(System.in);

    }

    public void showMenu() {

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

    private void menuTambah() {
        Kota kota = new Kota();
        System.out.print("Tambah Kota : ");
        kota.setKodeKota(scanner.next());
        kota.setNamaKota(scanner.next());
        kotaManager.add(kota);
        
    }

   private void menuTampil()  {
        AsciiTable at = new AsciiTable();
        at.addRule();
        AT_Row row =  at.addRow("N0", "KODE KOTA", "NAMA KOTA");
        row.setTextAlignment(TextAlignment.CENTER);
        at.addRule();
        List<Kota> listKota = kotaManager.GetAll();
        int i = 0;
        for (Kota kota : listKota) {
            i++;
            at.addRow(i,kota.getKodeKota(),kota.getNamaKota());
            at.addRule();
        }
        CWC_LongestLine cwc = new CWC_LongestLine();
        cwc.add(4, 0).add(20, 0).add(50, 0);
        at.getRenderer().setCWC(cwc);
        System.out.println(at.render());
      
      
    }

   private void menuUbah() {
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

   private void menuHapus() {
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