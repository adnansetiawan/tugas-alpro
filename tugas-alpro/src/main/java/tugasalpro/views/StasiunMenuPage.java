package tugasalpro.views;

import java.util.List;
import java.util.Scanner;

import tugasalpro.*;
import tugasalpro.models.*;
import tugasalpro.managers.*;

public class StasiunMenuPage{
    private final StasiunManager stasiunManager;
    Scanner scanner;
      
    public StasiunMenuPage(){
        stasiunManager = new StasiunManager();
        scanner = new Scanner(System.in);
    }

    public void showMenu(){
        System.out.println("\n#KELOLA DATA STASIUN#");
        int pilihan;
        do{
            System.out.println("\n1.  Tambah Data Stasiun");
            System.out.println("2.  Lihat Data Stasiun");
            System.out.println("3.  Edit Data Stasiun");
            System.out.println("4.  Delete Data Stasiun");
            System.out.println("99. Menu Utama\n");
            System.out.print("Pilihan : ");
            pilihan = scanner.nextInt();
            switch(pilihan){
                case 1:
                    menuTambah();
                    break;
                case 2:
                    showMenuTampil();
                    break;
                case 3:
                    menuUbah();
                    break;
                case 4:
                    menuHapus();
                    break;
                case 99:
                    UserMenuPage menu = new UserMenuPage();
                    menu.ShowMenuAdmin();
                    break;
            }
        }while(pilihan!=99);
    }

    public void menuTambah(){
        System.out.println("\n#TAMBAH DATA STASIUN#");
        Stasiun stasiun = new Stasiun();
        String kodeStasiun;
        do{
            System.out.print("Tambah Data Stasiun : ");
            kodeStasiun = scanner.next();
            if(stasiunManager.getIndexByKodeStasiun(kodeStasiun)!=-1){
                System.out.println("Kode Stasiun Tidak Tersedia");
            }
        }while(stasiunManager.getIndexByKodeStasiun(kodeStasiun)!=-1);
        stasiun.setKodeStasiun(kodeStasiun);
        String namaStasiun = scanner.nextLine();
        stasiun.setNamaStasiun(namaStasiun);
        stasiunManager.add(stasiun);
        System.out.println("----------------------------------------");
        System.out.println("Stasiun Berhasil Ditambahkan");
        System.out.println("----------------------------------------");
        showMenu();
    }
    
    public void menuTampil(){
        System.out.println("Data Lengkap Stasiun");
        System.out.println("----------------------------------------");
        System.out.println("No \t Kode Stasiun \t Nama Stasiun");
        List<Stasiun> listStasiun = stasiunManager.getAll();
        int i = 0;
        for(Stasiun stasiun : listStasiun){
            i++;
            System.out.println(i+" \t "+stasiun.getKodeStasiun()+"\t\t"+stasiun.getNamaStasiun());
        }
        System.out.println("\n----------------------------------------");
    }

    public void showMenuTampil(){
        System.out.println("\n#LIHAT DATA STASIUN#");
        menuTampil();
        showMenu();
    }

    public void menuUbah(){
        System.out.println("\n#EDIT DATA STASIUN#");
        menuTampil();
        String kodeStasiun=null;
        String perintah;
        Stasiun stasiun=null;
        boolean flagIterate=true;
        do{
            System.out.print("Edit Stasiun : ");
            kodeStasiun=scanner.next();
            if(!kodeStasiun.equals("99")){
                perintah=kodeStasiun.substring(0,Math.min(kodeStasiun.length(),5));
                while(!perintah.equals("EDIT_")){
                    System.out.print("Format Salah\nEdit Stasiun : ");
                    kodeStasiun=scanner.next();
                    perintah=kodeStasiun.substring(0,Math.min(kodeStasiun.length(),5));
                }
                if(perintah.equals("EDIT_")){
                    kodeStasiun=kodeStasiun.substring(5);
                    stasiun=stasiunManager.getByKodeStasiun(kodeStasiun);
                    if(stasiun!=null){
                        stasiunManager.delete(stasiun);
                        flagIterate=false;
                    }else{
                        System.out.println("Stasiun Tidak Tersedia");
                    }
                }
            }else{
                flagIterate=false;
            }
        }while(flagIterate);
        stasiun = new Stasiun();
        System.out.print("Kode Stasiun : ");
        stasiun.setKodeStasiun(scanner.next());
        System.out.print("Nama stasiun : ");
        stasiun.setNamaStasiun(scanner.next());
        stasiunManager.add(stasiun);
    }

    public void menuHapus(){
        System.out.println("\n#DELETE DATA STASIUN#");
        menuTampil();
        String kodeStasiun=null;
        String perintah;
        Stasiun stasiun=null;
        boolean flagIterate=true;
        do{
            System.out.print("Delete Stasiun : ");
            kodeStasiun=scanner.next();
            if(!kodeStasiun.equals("99")){
                perintah=kodeStasiun.substring(0,Math.min(kodeStasiun.length(),7));
                while(!perintah.equals("DELETE_")){
                    System.out.print("Format Salah\nDelete Stasiun : ");
                    kodeStasiun=scanner.next();
                    perintah=kodeStasiun.substring(0,Math.min(kodeStasiun.length(),7));
                }
                if(perintah.equals("DELETE_")){
                    kodeStasiun=kodeStasiun.substring(7);
                    stasiun=stasiunManager.getByKodeStasiun(kodeStasiun);
                    if(stasiun!=null){
                        stasiunManager.delete(stasiun);
                        flagIterate=false;
                    }else{
                        System.out.println("Stasiun Tidak Tersedia");
                    }
                }
            }else{
                flagIterate=false;
            }
        }while(flagIterate);
    }
}