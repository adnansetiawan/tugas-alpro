package tugasalpro.views;

import java.util.List;
import java.util.Scanner;

import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import tugasalpro.managers.*;
import tugasalpro.models.*;
import tugasalpro.utilities.ScreenUtility;

public class JalurRutePage{
    private JalurRuteManager jalurRuteManager;
    private RuteManager ruteManager;
    private StasiunManager stasiunManager;
    Scanner scanner;
    
    public JalurRutePage(){
        jalurRuteManager=new JalurRuteManager();
        ruteManager=new RuteManager();
        stasiunManager=new StasiunManager();
        scanner=new Scanner(System.in);
    }

    public void showMenu(){
        System.out.println("#KELOLA STASIUN BERDASARKAN RUTE#");
        int pilihan;
        do{
            System.out.println("1.  Tambah Jalur Stasiun pada Rute");
            System.out.println("2.  Lihat Jalur Stasiun pada Rute");
            System.out.println("3.  Hapus Jalur Stasiun pada Rute");
            System.out.println("99. Menu Utama");
            System.out.print("Pilihan : ");
            pilihan = scanner.nextInt();
            switch(pilihan){
                case 1:
                    menuTambah();
                    break;
                case 2:
                    menuTampil();
                    break;
                case 3:
                    menuHapus();
                    break;
                case 99:
                    ScreenUtility.ClearScreen();
                    MenuPage menuPage = new MenuPage();
                    menuPage.showMenuAdmin();
                    break;
            }
        }while(pilihan!=99);
    }

    public void menuTambah(){
        System.out.println("#TAMBAH JALUR STASIUN PADA RUTE#");
        JalurRute jalurRute = new JalurRute();
        String kodeJalur,kodeRute=null;
        boolean flagIterate=true;
        do{
            System.out.print("Kode Jalur : ");
            kodeJalur=scanner.next();
            if(jalurRuteManager.getIndexByKodeJalur(kodeJalur)!=-1){
                System.out.println("Jalur rute dengan kode jalur "+kodeJalur+" sudah ada.");
                System.out.println("Mohon masukkan kode rute yang berbeda, atau “99” untuk membatalkan penambahan jalur rute.");
            }else{
                if(kodeJalur.equals("99")){
                    System.out.println("Penambahan jalur rute dibatalkan.");
                }else{
                    jalurRute.setKodeJalur(kodeJalur);
                }
                flagIterate=false;
            }
        }while(flagIterate);
        if(!kodeJalur.equals("99")){
            System.out.print("Kode Rute : ");
            kodeRute=scanner.next();
            if(ruteManager.GetIndexByKodeRute(kodeRute)==-1){
                if(kodeRute.equals("99")){
                    System.out.println("Penambahan jalur rute dibatalkan.");
                    flagIterate=false;
                }else{
                    System.out.println("Rute dengan kode rute "+kodeRute+" tidak ada.");
                    System.out.println("Mohon masukkan kode rute yang berbeda, atau “99” untuk membatalkan penambahan jalur rute.");
                }
            }else{
                jalurRute.setRuteJalur(ruteManager.GetByKodeRute(kodeRute));
                flagIterate=false;
            }
        }
        if(!kodeRute.equals("99")){
            flagIterate=true;
            System.out.println("Stasiun Awal Sampai Stasiun Akhir\n----------------------------------------");
            JalurStasiun jalurStasiun;
            String namaStasiunAsal=null;
            String namaStasiunTujuan=null;
            int i = 1, durasiJalurStasiun=0;
            do{
                System.out.print("Jalur "+i+" : ");
                namaStasiunAsal=scanner.next();
                if(stasiunManager.getIndexByNamaStasiun(namaStasiunAsal)==-1){
                    if(namaStasiunAsal.equals("99")){
                        System.out.println("----------------------------------------");
                        System.out.println("Jalur stasiun yang dilewati berdasarkan rute berhasil ditambahkan.");
                        System.out.println("----------------------------------------");
                        flagIterate=false;
                    }else{
                        namaStasiunTujuan=scanner.next();
                        durasiJalurStasiun=scanner.nextInt();
                        System.out.println("Stasiun dengan nama stasiun "+namaStasiunAsal+" tidak ada.");
                        System.out.println("Mohon ulangi masukan nama stasiun asal yang berbeda, atau “99” untuk mengakhiri penambahan jalur stasiun yang dilewati berdasarkan rute.");
                    }
                }else{
                    namaStasiunTujuan=scanner.next();
                    durasiJalurStasiun=scanner.nextInt();
                    if(stasiunManager.getIndexByNamaStasiun(namaStasiunTujuan)==-1){
                        System.out.println("Stasiun dengan nama stasiun "+namaStasiunTujuan+" tidak ada.");
                        System.out.println("Mohon ulangi masukan dengan nama stasiun tujuan yang berbeda, atau “99” untuk mengakhiri penambahan jalur stasiun yang dilewati berdasarkan rute.");
                    }else{
                        if(durasiJalurStasiun<=0){
                            System.out.println("Durasi harus lebih dari 0 menit.");
                            System.out.println("Mohon ulangi masukan dengan durasi perjalanan dengan benar.");
                        }else{
                            jalurStasiun=new JalurStasiun();
                            jalurStasiun.setStasiunAsal(stasiunManager.getByNamaStasiun(namaStasiunAsal));
                            jalurStasiun.setStasiunTujuan(stasiunManager.getByNamaStasiun(namaStasiunTujuan));
                            jalurStasiun.setDurasi(durasiJalurStasiun);
                            jalurRute.addJalurStasiun(jalurStasiun);
                            i++;
                        }
                    }
                }
            }while(flagIterate);
            jalurRuteManager.add(jalurRute);
        }
        showMenu();
    }

    public void menuTampil(){
        ScreenUtility.ClearScreen();
        System.out.println("#LIHAT JALUR STASIUN BERDASARKAN RUTE#");
        System.out.println("-------------------------------------------------------");
        AsciiTable at=new AsciiTable();
        at.addRule();
        AT_Row row=at.addRow("No","Kode Jalur","Kode Rute","Jalur yang Dilewati","Durasi");
        row.setTextAlignment(TextAlignment.CENTER);
        at.addRule();
        List<JalurRute> listJalurRute=jalurRuteManager.getAll();
        int i=0;
        for (JalurRute jalurRute:listJalurRute){
            i++;
            row=at.addRow(i,jalurRute.getKodeJalur(),jalurRute.getRuteJalur().getKodeRute(),jalurRute.printJalurStasiun(),jalurRute.getDurasi());
            row.setTextAlignment(TextAlignment.CENTER);
            at.addRule();
        }
        CWC_LongestLine cwc=new CWC_LongestLine();
        cwc.add(4,0).add(20, 0).add(20,0).add(35,0).add(10,0);
        at.getRenderer().setCWC(cwc);
        System.out.println(at.render());
        System.out.println("-------------------------------------------------------");
        showMenu();
    }

    public void menuHapus(){
        String kodeJalur=null;
        boolean flagIterate=true;
        System.out.println("#HAPUS JALUR STASIUN BERDASARKAN RUTE#");
        System.out.println("-------------------------------------------------------");
        do {
            System.out.print("Hapus Jalur : ");
            kodeJalur = scanner.next();
            if (kodeJalur.equals("99")) {
                System.out.println("Penghapusan jalur rute dibatalkan.");
                flagIterate = false;
            } else {
                if(jalurRuteManager.getIndexByKodeJalur(kodeJalur)!=-1){
                    jalurRuteManager.delete(jalurRuteManager.getByKodeJalur(kodeJalur));
                    System.out.println("Jalur rute berhasil dihapus.");
                }else{
                    System.out.println("Jalur rute dengan kode jalur "+kodeJalur+" tidak ada.");
                    System.out.println("Mohon masukkan kode jalur yang berbeda, atau “99” untuk membatalkan penghapusan jalur rute.");
                }
            }
        } while (flagIterate);
        showMenu();
    }
}