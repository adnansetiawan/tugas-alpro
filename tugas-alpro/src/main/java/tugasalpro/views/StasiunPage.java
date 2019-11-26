package tugasalpro.views;

import java.util.List;
import java.util.Scanner;

import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import tugasalpro.managers.*;
import tugasalpro.models.*;

public class StasiunPage{
    private final StasiunManager stasiunManager;
    Scanner scanner;
      
    public StasiunPage(){
        stasiunManager = new StasiunManager();
        scanner = new Scanner(System.in);
    }

    public void showMenu(){
        System.out.println("#KELOLA DATA STASIUN#");
        int pilihan;
        do{
            System.out.println("1.  Tambah Data Stasiun");
            System.out.println("2.  Lihat Data Stasiun");
            System.out.println("3.  Ubah Data Stasiun");
            System.out.println("4.  Hapus Data Stasiun");
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
                    menuUbah();
                    break;
                case 4:
                    menuHapus();
                    break;
                case 99:
                    MenuPage menuPage = new MenuPage();
                    menuPage.showMenuAdmin();
                    break;
            }
        }while(pilihan!=99);
    }

    public void menuTambah(){
        System.out.println("#TAMBAH DATA STASIUN#");
        Stasiun stasiun = new Stasiun();
        String kodeStasiun;
        String namaStasiun;
        boolean flagIterate=true;
        do{
            System.out.print("Tambah Data Stasiun : ");
            kodeStasiun = scanner.next();
            if(stasiunManager.getIndexByKodeStasiun(kodeStasiun)!=-1){
                namaStasiun=scanner.nextLine();
                System.out.println("Stasiun dengan kode stasiun "+kodeStasiun+" sudah ada.");
                System.out.println("Mohon masukkan kode stasiun yang berbeda, atau “99” untuk membatalkan penambahan stasiun.");
            }else{
                if(kodeStasiun.equals("99")){
                    namaStasiun=scanner.nextLine();
                    System.out.println("Penambahan stasiun dibatalkan.");
                    flagIterate=false;
                }else{
                    namaStasiun=scanner.nextLine();
                    if(stasiunManager.getIndexByNamaStasiun(namaStasiun.substring(1))!=-1){
                        System.out.println("Stasiun dengan nama stasiun "+namaStasiun.substring(1)+" sudah ada.");
                        System.out.println("Mohon masukkan nama stasiun yang berbeda, atau “99” untuk membatalkan penambahan stasiun.");
                    }else{
                        if(namaStasiun.equals("99")){
                            System.out.println("Penambahan stasiun dibatalkan.");
                        }else{
                            stasiun.setKodeStasiun(kodeStasiun);
                            stasiun.setNamaStasiun(namaStasiun.substring(1));
                            stasiunManager.add(stasiun);
                            System.out.println("Stasiun berhasil ditambahkan.");
                        }
                        flagIterate=false;
                    }
                }
            }
        }while(flagIterate);
        showMenu();
    }

    public void menuTampil(){
        System.out.println("#LIHAT DATA STASIUN#");
        System.out.println("Data Lengkap Stasiun");
        System.out.println("----------------------------------------");
        AsciiTable at = new AsciiTable();
        at.addRule();
        AT_Row row =  at.addRow("No", "Kode Stasiun", "Nama Stasiun");
        row.setTextAlignment(TextAlignment.CENTER);
        at.addRule();
        List<Stasiun> listStasiun = stasiunManager.getAll();
        int i = 0;
        for (Stasiun stasiun : listStasiun) {
            i++;
            row=at.addRow(i,stasiun.getKodeStasiun(),stasiun.getNamaStasiun());
            row.setTextAlignment(TextAlignment.CENTER);
            at.addRule();
        }
        CWC_LongestLine cwc = new CWC_LongestLine();
        cwc.add(4, 0).add(20, 0).add(50, 0);
        at.getRenderer().setCWC(cwc);
        System.out.println(at.render());
        System.out.println("----------------------------------------");
        showMenu();
    }

    public void menuUbah(){
        System.out.println("#UBAH DATA STASIUN#");
        String kodeStasiun=null;
        String kodeStasiunB=null;
        String namaStasiun=null;
        Stasiun stasiun=null;
        Stasiun stasiunB=new Stasiun();
        boolean flagIterate=true;
        boolean flagIterateB=true;
        do{
            System.out.print("Edit Stasiun : ");
            kodeStasiun=scanner.next();
            if(!kodeStasiun.equals("99")){
                stasiun=stasiunManager.getByKodeStasiun(kodeStasiun);
                if(stasiun!=null){
                    do{
                        System.out.print("Kode Stasiun : ");
                        kodeStasiunB=scanner.next();
                        if(kodeStasiunB.equals("99")){
                            System.out.println("Pengubahan stasiun dibatalkan.");
                            flagIterateB=false;
                            flagIterate=false;
                        }else{
                            System.out.print("Nama stasiun : ");
                            namaStasiun=scanner.next();
                            if(stasiunManager.getIndexByNamaStasiun(namaStasiun)!=-1){
                                if(stasiunManager.getIndexByKodeStasiun(kodeStasiunB)!=-1){
                                    System.out.println("Stasiun dengan kode stasiun "+kodeStasiunB+" dan nama stasiun "+namaStasiun+" sudah ada.");
                                    System.out.println("Mohon masukkan kode stasiun atau nama stasiun yang berbeda, atau “99” untuk membatalkan pengubahan stasiun.");
                                }else{
                                    stasiunManager.delete(stasiun);
                                    stasiunB.setKodeStasiun(kodeStasiunB);
                                    stasiunB.setNamaStasiun(namaStasiun);
                                    stasiunManager.add(stasiunB);
                                    System.out.println("Stasiun berhasil diubah.");
                                    flagIterateB=false;
                                    flagIterate=false;
                                }
                            }else{
                                if(namaStasiun.equals("99")){
                                    System.out.println("Pengubahan stasiun dibatalkan.");
                                    flagIterateB=false;
                                    flagIterate=false;
                                }else{
                                    stasiunManager.delete(stasiun);
                                    stasiunB.setKodeStasiun(kodeStasiunB);
                                    stasiunB.setNamaStasiun(namaStasiun);
                                    stasiunManager.add(stasiunB);
                                    System.out.println("Stasiun berhasil diubah.");
                                    flagIterateB=false;
                                    flagIterate=false;
                                }
                            }
                        }
                    }while(flagIterateB);
                }else{
                    System.out.println("Stasiun dengan kode stasiun "+kodeStasiun+" tidak ada.");
                    System.out.println("Mohon masukkan kode stasiun yang berbeda, atau “99” untuk membatalkan pengubahan stasiun.");
                }
            }else{
                System.out.println("Pengubahan stasiun dibatalkan.");
                flagIterate=false;
            }
        }while(flagIterate);
    }

    public void menuHapus(){
        System.out.println("#HAPUS DATA STASIUN#");
        String kodeStasiun=null;
        Stasiun stasiun=null;
        boolean flagIterate=true;
        do{
            System.out.print("Edit Stasiun : ");
            kodeStasiun=scanner.next();
            if(!kodeStasiun.equals("99")){
                stasiun=stasiunManager.getByKodeStasiun(kodeStasiun);
                if(stasiun!=null){
                    stasiunManager.delete(stasiun);
                }else{
                    System.out.println("Stasiun dengan kode stasiun "+kodeStasiun+" tidak ada.");
                    System.out.println("Mohon masukkan kode stasiun yang berbeda, atau “99” untuk membatalkan pengubahan stasiun.");
                }
            }else{
                System.out.println("Pengubahan stasiun dibatalkan.");
                flagIterate=false;
            }
        }while(flagIterate);
    }
}