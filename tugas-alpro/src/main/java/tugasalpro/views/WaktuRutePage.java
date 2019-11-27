package tugasalpro.views;

import java.util.ArrayList;
import java.util.Scanner;

import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import tugasalpro.managers.RuteManager;
import tugasalpro.managers.WaktuManager;
import tugasalpro.managers.WaktuRuteManager;
import tugasalpro.models.Rute;
import tugasalpro.models.Waktu;
import tugasalpro.models.WaktuRute;

public class WaktuRutePage
{
    Scanner scanner;
    WaktuRuteManager waktuRuteManager;
    RuteManager ruteManager;
    WaktuManager waktuManager;
    Scanner keyb;

    public WaktuRutePage()
    {
        waktuRuteManager = new WaktuRuteManager();
        ruteManager = new RuteManager();
        waktuManager = new WaktuManager();
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
        String kodeRute = null;
        Rute rute = null;
        Waktu waktu = null;
        ArrayList<Waktu> listWaktu = new ArrayList<Waktu>();
        boolean flagIterate = true;
        int no = 1;
        String kodeWaktu = null;
        WaktuRute waktuRute = new WaktuRute();
        // get last waktu rute
        int lastNumber =  0;
        if (waktuRuteManager.getByLast() != null) {
            lastNumber = Integer.parseInt(waktuRuteManager.getByLast().getKodeWaktuRute().substring(2));
        }



        System.out.println("#KELOLA WAKTU BERDASARKAN RUTE#");
        try {
            System.out.print("Kode Rute : ");
            kodeRute = scanner.next();
            rute = ruteManager.GetByKodeRute(kodeRute);
            if (rute == null ) {
                System.out.println("Rute tidak ditemukan");
            } else {
                do {
                    System.out.print("Waktu "+no+" : ");
                    try {
                        kodeWaktu = scanner.next();
                        waktu = waktuManager.GetByKodeWaktu(kodeWaktu);
                    } catch (Exception e) {
                        System.out.println("Format masukan harus benar");
                    }
                    if (waktu!=null) {
                        listWaktu.add(waktu);
                        no++;
                    } else if (waktu==null && kodeWaktu.equals("99")) {
                        flagIterate = false;
                    }
                } while (flagIterate);
                lastNumber++;
                waktuRute.setArrWaktu(listWaktu);
                waktuRute.setRute(rute);
                waktuRute.setKodeWaktuRute("WR"+("000000"+lastNumber).substring((""+lastNumber).length()));
                waktuRuteManager.add(waktuRute);
            }
        } catch (Exception e) {
            System.out.println("format input salah");
        }
            
        
        

        //new WaktuRuteManager().tambahRute();
    }

    private void showAll() {
       
        ArrayList<Waktu> listWaktu = new ArrayList<Waktu>();
        
        System.out.println("#LIHAT WAKTU SEMUA#");
        System.out.print("Kode Rute : ");
      
        

        System.out.println();
        System.out.println("Waktu Available Untuk Rute");
        AsciiTable at = new AsciiTable();
        at.addRule();
        AT_Row row =  at.addRow("N0", "KODE WAKTU RUTE", "KODE RUTE", "WAKTU TERSEDIA RUTE");
        row.setTextAlignment(TextAlignment.CENTER);
        at.addRule();
        for (WaktuRute waktuRute : waktuRuteManager.GetAll()) {
            if (waktuRute!=null) {
                String strWaktu = "";
                listWaktu = waktuRute.getArrWaktu();
                for (Waktu wkt : listWaktu) {
                    strWaktu = strWaktu + "- "+ wkt.getWaktu()+"\n"; 
                }            
                at.addRow(1,waktuRute.getKodeWaktuRute(),waktuRute.getRute().getKodeRute(),strWaktu);
                at.addRule();
            }
        }
        
        CWC_LongestLine cwc = new CWC_LongestLine();
        cwc.add(4, 0).add(20, 0).add(50, 0);
        at.getRenderer().setCWC(cwc);
        System.out.println(at.render());
    }

    private void menuTampil()
    {
        int i = 1;
        int statusPrint = 1;
        String textKodeRute="";
        String kodeRute="";
        WaktuRute waktuRute = null;
        ArrayList<Waktu> listWaktu = new ArrayList<Waktu>();
        
        System.out.println("#LIHAT WAKTU BERDASARKAN RUTE#");
        System.out.print("Kode Rute : ");
        kodeRute = scanner.next();
        waktuRute = waktuRuteManager.getByRute(ruteManager.GetByKodeRute(kodeRute));

        System.out.println();
        System.out.println("Waktu Available Untuk Rute");
        AsciiTable at = new AsciiTable();
        at.addRule();
        AT_Row row =  at.addRow("N0", "KODE WAKTU RUTE", "KODE RUTE", "WAKTU TERSEDIA RUTE");
        row.setTextAlignment(TextAlignment.CENTER);
        at.addRule();
        if (waktuRute!=null) {
            String strWaktu = "";
            listWaktu = waktuRute.getArrWaktu();
            for (Waktu wkt : listWaktu) {
                strWaktu = strWaktu + "- "+ wkt.getWaktu()+"\n"; 
            }            
            at.addRow(1,waktuRute.getKodeWaktuRute(),waktuRute.getRute().getKodeRute(),strWaktu);
            at.addRule();
        }
        CWC_LongestLine cwc = new CWC_LongestLine();
        cwc.add(4, 0).add(20, 0).add(50, 0);
        at.getRenderer().setCWC(cwc);
        System.out.println(at.render());

        
        /*
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
        */
    }

    private void menuHapus()
    {
        showAll();
        String kodeWaktuRute = null;
        WaktuRute waktuRute = null;
        System.out.print("Kode Waktu Rute : ");
        kodeWaktuRute = scanner.next();
        waktuRute = waktuRuteManager.getByKodeWaktuRute(kodeWaktuRute);
        waktuRuteManager.delete(waktuRute);
        //new WaktuRuteManager().hapusRute();
    }
}