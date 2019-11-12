package tugasalpro;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

public class StasiunPage{
    private StasiunManager stasiunManager;
    Scanner scanner;
      
    public StasiunPage(){
        stasiunManager = new StasiunManager();
        scanner = new Scanner(System.in);
    }

    public void showStasiunPage(){
        System.out.println("#KELOLA DATA STASIUN#");
        showMenu();
    }

    public void showMenu()
    {
        int pilihan = 0;
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
                    MenuManager menu = new MenuManager();
                    menu.ShowMenuAdmin();
                    break;
            }
        }while(pilihan!=99);
    }

    public void menuTambah() 
    {
        System.out.println("#TAMBAH DATA STASIUN#");
        Stasiun stasiun = new Stasiun();
        System.out.print("Tambah Data Stasiun : ");
        stasiun.setKodeStasiun(scanner.next());
        stasiun.setNamaStasiun(scanner.next());
        stasiunManager.add(stasiun);
        System.out.println("----------------------------------------");
        System.out.println("Stasiun Berhasil Ditambahkan");
        System.out.println("----------------------------------------");
<<<<<<< HEAD
        showMenu();
=======
        showMenuTampil();
>>>>>>> 72454c351bfd297603cb62ea4168dab0dcdac0ca
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
        System.out.println("#LIHAT DATA STASIUN#");
        menuTampil();
        showMenu();
    }

    public void menuUbah(){
        System.out.println("#EDIT DATA STASIUN#");
        menuTampil();
        String kodeStasiun=null;
        Stasiun stasiun=null;
        boolean flagIterate=true;
        String kodeStasiun = null;
        do{
            System.out.print("Edit Stasiun : ");
            kodeStasiun=scanner.next();
            if(kodeStasiun =="99"){
                flagIterate=false;
            }else{
                kodeStasiun=kodeStasiun.substring(5);
                System.out.println(kodeStasiun);
                stasiun=stasiunManager.getByKodeStasiun(kodeStasiun);
                if(stasiun!=null){
                    stasiunManager.delete(stasiun);
                    flagIterate=false;
                }
            }
        }while(flagIterate);
        stasiun = new Stasiun();
        System.out.print("Kode Stasiun : ");
        stasiun.setKodeStasiun(scanner.next());
        System.out.print("Nama stasiun : ");
        stasiun.setNamaStasiun(scanner.next());
        stasiunManager.add(stasiun);
    }

    public void menuHapus() 
    {
        System.out.println("#DELETE DATA STASIUN#");
        menuTampil();
        String kodeStasiun=null;
        Stasiun stasiun=null;
        boolean flagIterate=true;
        String kodeStasiun = null;
        do{
            System.out.print("Edit Stasiun : ");
            kodeStasiun=scanner.next();
            if(kodeStasiun=="99"){
                flagIterate=false;
            }else{
                kodeStasiun=kodeStasiun.substring(7);
                System.out.println(kodeStasiun);
                stasiun=stasiunManager.getByKodeStasiun(kodeStasiun);
                if(stasiun!=null){
                    stasiunManager.delete(stasiun);
                    flagIterate=false;
                }
            }
        }while(flagIterate);
    }
}