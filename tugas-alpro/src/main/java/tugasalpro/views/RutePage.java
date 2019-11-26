package tugasalpro.views;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import tugasalpro.managers.JalurRuteManager;
import tugasalpro.managers.KeretaRuteManager;
import tugasalpro.managers.KotaManager;
import tugasalpro.managers.RuteManager;
import tugasalpro.managers.WaktuRuteManager;
import tugasalpro.models.JalurRute;
import tugasalpro.models.KeretaRute;
import tugasalpro.models.Rute;
import tugasalpro.utilities.ScreenUtility;
import tugasalpro.utilities.StringUtility;


public class RutePage {
    private RuteManager ruteManager;
    private KotaManager kotaManager;
    Scanner scanner, keyb;

    public RutePage() {

        ruteManager = new RuteManager();
        kotaManager = new KotaManager();
        scanner = new Scanner(System.in);
        keyb = new Scanner(System.in);
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
            System.out.println("#Menu Rute#");
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
        Rute listRute;
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
            //Pengecekan rute sudah ada atau belum
            listRute = new RuteManager().GetByKodeRute(rute.getKodeRute());
            if (listRute != null)
            {
                System.out.println("Rute "+rute.getKodeRute()+" sudah ada.");
            }
            //Rute sudah ada
            else
            {
                ruteManager.add(rute);
            }
        } catch(Exception er) {
            System.out.println("Data gagal ditambahkan");
        }
    }

  public  void menuTampil() {
        ScreenUtility.ClearScreen();
        AsciiTable at = new AsciiTable();
        at.addRule();
        AT_Row row =  at.addRow("NO","KEBERANGKATAN","TUJUAN","KODE RUTE","BISNIS","PREMIUM");
        row.setTextAlignment(TextAlignment.CENTER);
        at.addRule();
        List<Rute> listRute = ruteManager.GetAll();
        int i = 0;
        for (Rute rute : listRute) {
            i++;
            at.addRow(i,rute.getKotaAsal().getNamaKota(),
                rute.getKotaTujuan().getNamaKota(),
                rute.getKodeRute(),
                StringUtility.getCurrencyFormat(rute.getHargaBisnis()),
                StringUtility.getCurrencyFormat(rute.getHargaPremium()));
            at.addRule();
        }
        CWC_LongestLine cwc = new CWC_LongestLine();
        cwc.add(4, 0).add(20, 0).add(20, 0);
        at.getRenderer().setCWC(cwc);
        System.out.println(at.render());
    }

   public void menuUbah() {
        menuTampil();
        /*Script baru, asumsi ubah Rute hanya untuk harga tiket saja
        Karena kalau akan ganti Rute (Kota Asal ke Tujuan) pastinya hapus dulu yang salah
        Baru input lagi*/
        String kotaAsal,kotaTujuan;
        int hrgBisnis,hrgPremium;
        String kodeRute;
        Rute rute = new Rute();
        System.out.print("Kode Rute yang akan diubah : ");
        kodeRute = keyb.nextLine();
        if (kodeRute.compareTo("99")==0)
        {
            System.out.println("Ubah data dibatalkan.");
        }
        else
        {
            Rute listRute;
            listRute = ruteManager.GetByKodeRute(kodeRute);
            if (listRute != null)
            {
                
                kotaAsal = listRute.getKotaAsal().getNamaKota();
                kotaTujuan = listRute.getKotaTujuan().getNamaKota();
                System.out.println("Kota Asal : "+kotaAsal);
                System.out.println("Kota Tujuan : "+kotaTujuan);
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
                ruteManager.delete(listRute);
                ruteManager.add(rute);
                System.out.println("Kode Rute "+kodeRute+" berhasil diubah.");
            }
            else
            {
                System.out.println("Kode Rute "+kodeRute+" tidak ditemukan.");
            }
        }


        /* Nonaktifkan script
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
        */
    }

   public void menuHapus()  {
        menuTampil();
        int status = 0;
        String kodeRute = null;
        System.out.print("Kode Rute yang akan dihapus : ");
        kodeRute = keyb.nextLine();
        if (kodeRute.compareTo("99")==0)
        {
            System.out.println("Hapus data dibatalkan.");
        }
        else
        {
            Rute listRute;
            listRute = ruteManager.GetByKodeRute(kodeRute);
            if (listRute != null)
            {
                //Pengecekan ke Jalur Stasiun
                JalurRute listJalur = new JalurRuteManager().getByKodeRute(kodeRute);
                if (listJalur != null)
                {
                    status = 1;
                    System.out.println("Kode Rute "+kodeRute+" sudah digunakan di Jalur Rute, tidak boleh dihapus.");
                }
                //Pengecekan ke Kereta Rute
                if (status ==0)
                {
                    KeretaRute listKeretaRute = new KeretaRuteManager().getByKodeRute(kodeRute);
                    if (listKeretaRute != null)
                    {
                        status = 1;
                        System.out.println("Kode Rute "+kodeRute+" sudah digunakan di Kereta Rute, tidak boleh dihapus.");
                    }
                }
                if (status==0)
                {
                    String yaTidak;
                    System.out.print("Andak akan yakin akan menghapus Kode Rute "+kodeRute+" (Y/N) ? ");
                    yaTidak = keyb.nextLine();
                    if (yaTidak.compareTo("Y")==0)
                    {
                        ruteManager.delete(listRute);
                        System.out.println("Kode Rute "+kodeRute+" berhasil dihapus.");
                    }
                    else
                    {
                        System.out.println("Hapus kode rute "+kodeRute+" dibatalkan.");
                    }
                }
            }
            else
            {
                System.out.println("Kode Rute "+kodeRute+" tidak ditemukan.");
            }
        }

        /* Nonaktifkan script
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
        */
    }
}