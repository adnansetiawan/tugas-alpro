package tugasalpro.views;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import tugasalpro.managers.KotaManager;
import tugasalpro.managers.RuteManager;
import tugasalpro.models.Rute;


public class RutePage {
    private RuteManager ruteManager;
    private KotaManager kotaManager;
    Scanner scanner;

    public RutePage() {

        ruteManager = new RuteManager();
        kotaManager = new KotaManager();
        scanner = new Scanner(System.in);

    }

    boolean isValid(Rute rute) {
        if (rute.getKotaAsal().equals(null) && rute.getKotaTujuan().equals(null)) {
            return false;
        } else {
            return true;
        }
    }

    public void showMenu(){

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

   public void menuTambah() {
        Rute rute = new Rute();
        String kotaAsal,kotaTujuan;
        int hrgBisnis,hrgPremium;
        System.out.print("Tambah Rute : ");
        try {
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
        } catch(Exception er) {
            System.out.println("Data gagal ditambahkan");
        }
        
        
        
    }

  public  void menuTampil() {
        AsciiTable at = new AsciiTable();
        at.addRule();
        AT_Row row =  at.addRow("No","Keberangkatan","Tujuan","Kode_Rute","Bisnis","Premium");
        row.setTextAlignment(TextAlignment.CENTER);
        at.addRule();
        List<Rute> listRute = ruteManager.GetAll();
        int i = 0;
        for (Rute rute : listRute) {
            i++;
            at.addRow(i,rute.getKotaAsal().getNamaKota(),
                rute.getKotaTujuan().getNamaKota(),
                rute.getKodeRute(),
                rute.getHargaBisnis(),
                rute.getHargaPremium());
            at.addRule();
        }
        CWC_LongestLine cwc = new CWC_LongestLine();
        cwc.add(4, 0).add(20, 0).add(20, 0);
        at.getRenderer().setCWC(cwc);
        System.out.println(at.render());
    }

   public void menuUbah() {
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

   public void menuHapus()  {
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