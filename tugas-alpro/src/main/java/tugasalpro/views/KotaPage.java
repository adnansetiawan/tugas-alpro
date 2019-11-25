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
    Scanner scanner, keyb;

    public KotaPage() {

        kotaManager = new KotaManager();
        scanner = new Scanner(System.in);
        keyb = new Scanner(System.in);

    }

    public void showMenu() {

        int pilihan = 0;
        do {
            System.out.println("#Menu Kota#");
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
        if (kota.getKodeKota().compareTo("99")!=0 && kota.getNamaKota().compareTo("99")!=0)
        {
            Kota kotaByKode; 
            kotaByKode = kotaManager.GetByKodeKota(kota.getKodeKota());
            if (kotaByKode != null)
            {
                System.out.println("Kode Kota : "+kota.getKodeKota()+" sudah ada.");
                System.out.println("Mohon masukkan Kode Kota yang berbeda atau 99 untuk membatalkan penambahan Kota.");
            }
            else
            {
                Kota kotaByNama; 
                kotaByNama = kotaManager.GetByNamaKota(kota.getNamaKota());
                if (kotaByNama != null)
                {
                    System.out.println("Nama Kota : "+kota.getNamaKota()+" sudah ada.");
                    System.out.println("Mohon masukkan Nama Kota yang berbeda atau 99 untuk membatalkan penambahan Kota.");
                }
                else
                {
                    kotaManager.add(kota);
                }
            }

        }
        
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
        Kota kotaByKode, kotaByNama, kota;
        Rute listRute;
        System.out.print("Kode Kota yang akan diubah : ");
        kodeKota = keyb.nextLine();
        if (kodeKota.compareTo("99")==0)
        {
            System.out.println("Ubah Kode Kota dibatalkan.");
        }
        else
        {
            kotaByKode = kotaManager.GetByKodeKota(kodeKota);
            if (kotaByKode != null)
            {
                String kodeKotaLama = kotaByKode.getKodeKota();
                String namaKotaLama = kotaByKode.getNamaKota();
                String kotaAsal = kotaByKode.getNamaKota();
                listRute = new RuteManager().GetByNamaKotaAsal(kotaAsal);
                if (listRute != null)
                {
                    System.out.println("Kode kota "+kodeKota+" sudah digunakan di Data Rute, tidak boleh diubah.");
                }
                else
                {
                    String kotaTujuan = kotaByKode.getNamaKota();
                    listRute = new RuteManager().GetByNamaKotaTujuan(kotaTujuan);
                    if (listRute != null)
                    {
                        System.out.println("Kode kota "+kodeKota+" sudah digunakan di Data Rute, tidak boleh diubah.");
                    }
                    else
                    {
                        int status=0;
                        kota = new Kota();
                        System.out.print("Kode Kota : ");
                        kota.setKodeKota(keyb.next());
                        System.out.print("Nama Kota : ");
                        kota.setNamaKota(keyb.next());
                        if (kota.getKodeKota().compareTo("99")!=0 && kota.getNamaKota().compareTo("99")!=0 && ((kota.getKodeKota().compareTo(kodeKotaLama)!=0 || kota.getNamaKota().compareTo(namaKotaLama)!=0)))
                        {
                            if (kota.getKodeKota().compareTo(kodeKotaLama)!=0)
                            {
                                kotaByKode = kotaManager.GetByKodeKota(kota.getKodeKota());
                                if (kotaByKode != null)
                                {
                                    System.out.println("Kode Kota "+kota.getKodeKota()+" sudah ada.");
                                    System.out.println("Mohon masukkan Kode Kota yang berbeda atau 99 untuk membatalkan penambahan Kota.");
                                    status = 1;
                                }
                            }
                            if (kota.getNamaKota().compareTo(namaKotaLama)!=0)
                            {
                                kotaByNama = kotaManager.GetByNamaKota(kota.getNamaKota());
                                if (kotaByNama != null)
                                {
                                    System.out.println("Nama Kota "+kota.getNamaKota()+" sudah ada.");
                                    System.out.println("Mohon masukkan Nama Kota yang berbeda atau 99 untuk membatalkan penambahan Kota.");
                                    status = 1;
                                }
                            }
                            if (status==0)
                            {
                                kotaManager.delete(kotaManager.GetByKodeKota(kodeKota));
                                kotaManager.add(kota);
                                System.out.println("Ubah kode kota "+kodeKotaLama+" ("+namaKotaLama+") menjadi kode kota "+kota.getKodeKota()+" ("+kota.getNamaKota()+") berhasil.");
                            }
                        }
                        else
                        {
                            System.out.println("Ubah kode kota "+kodeKotaLama+" dibatalkan.");
                        }
                    }
                }
            }
            else
            {
                System.out.println("Kode Kota "+kodeKota+" tidak ditemukan.");
            }
        }

        /* Non aktif script lama
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
        */        
    }

   private void menuHapus() {
        menuTampil();
        String kodeKota = null;
        Kota kotaByKode; 
        Rute listRute;
        System.out.print("Kode Kota yang akan dihapus : ");
        kodeKota = keyb.nextLine();
        if (kodeKota.compareTo("99")==0)
        {
            System.out.println("Hapus Kode Kota "+kodeKota+" dibatalkan.");
        }
        else
        {
            kotaByKode = kotaManager.GetByKodeKota(kodeKota);
            if (kotaByKode != null)
            {
                String kotaAsal = kotaByKode.getNamaKota();
                listRute = new RuteManager().GetByNamaKotaAsal(kotaAsal);
                if (listRute != null)
                {
                    System.out.println("Kode kota "+kodeKota+" sudah digunakan di Data Rute, tidak boleh dihapus.");
                }
                else
                {
                    String kotaTujuan = kotaByKode.getNamaKota();
                    listRute = new RuteManager().GetByNamaKotaTujuan(kotaTujuan);
                    if (listRute != null)
                    {
                        System.out.println("Kode kota "+kodeKota+" sudah digunakan di Data Rute, tidak boleh dihapus.");
                    }
                    else
                    {
                        String yaTidak;
                        System.out.print("Apakah Anda yakin akan menghapus Kode Kota "+kodeKota+" (Y/N) ? ");
                        yaTidak = keyb.nextLine();
                        if (yaTidak.compareTo("Y")==0)
                        {
                            kotaManager.delete(kotaByKode);
                            System.out.println("Kode Kota "+kodeKota+" berhasil dihapus.");
                        }
                    }
                }
            }
            else
            {
                System.out.println("Kode Kota "+kodeKota+" tidak ditemukan.");
            }
        }
        /* Non aktifkan script lama
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
        */
    }
}