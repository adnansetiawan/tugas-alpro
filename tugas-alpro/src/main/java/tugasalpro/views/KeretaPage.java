package tugasalpro.views;

import java.util.List;
import java.util.Scanner;

import tugasalpro.managers.KeretaManager;
import tugasalpro.models.Kereta;


public class KeretaPage{
    private KeretaManager keretaManager;
    Scanner scanner;

    public KeretaPage(){
        keretaManager = new KeretaManager();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        System.out.println("\n#KELOLA DATA KERETA API#");
        int pilihan=0;
        do {
            System.out.println("\n1. Tambah Data Kereta Api");
            System.out.println("2. Lihat Data Kereta Api");
            System.out.println("3. Edit Data Kereta Api");
            System.out.println("4. Delete Data Kereta Api");
            System.out.println("99. Menu Utama\n");
            System.out.print("Pilihan : ");
            pilihan = scanner.nextInt();
            if(pilihan == 1){
                menuTambah();
            }
            else if(pilihan == 2){
                showMenuTampil();
            }
            else if(pilihan == 3){
                menuUbah();
            }
            else if(pilihan == 4){
                menuHapus();
            }
        } while (pilihan != 99);   
    }
    
    public void menuTambah() {
        System.out.println("\n#TAMBAH DATA KERETA API#");
        Kereta kereta = new Kereta();
        String kodeKereta = null;
        String namaKereta=null;
        int jmlGerbong=0;
        int jmlGBisnis=0;
        int jmlGPremium=0;
        boolean inputNotValid=false;
        do
        {
            System.out.print("Tambah Data Kereta Api: ");
            String dataKereta = scanner.nextLine();
            String[] dataKeretas = dataKereta.split("'");
            try
            {
                kodeKereta = dataKeretas[0].trim();
                namaKereta = dataKeretas[1];
                String[] others = dataKeretas[2].split(" ");
                jmlGerbong = Integer.parseInt(others[1].substring(1, others[1].toCharArray().length));
                jmlGBisnis = Integer.parseInt(others[2].substring(1, others[2].toCharArray().length));
                jmlGPremium = Integer.parseInt(others[3].substring(1, others[3].toCharArray().length));
                inputNotValid = true;
            }
            catch(Exception ex)
            {
                System.out.println("Format Salah");
                inputNotValid = false;
            }
        } while(!inputNotValid);
        
        if(jmlGerbong == (jmlGBisnis + jmlGPremium))
        {
            kereta.setKodeKereta(kodeKereta);
            kereta.setNamaKereta(namaKereta);
            kereta.jmlGerbong(jmlGerbong);
            kereta.jmlGBisnis(jmlGBisnis);
            kereta.jmlGPremium(jmlGPremium);

            keretaManager.add(kereta);
            System.out.println("----------------------------------------");
            System.out.println("Kereta Api Berhasil Ditambahkan");
            System.out.println("----------------------------------------");
            showMenu();
        }
        else{
            System.out.println("----------------------------------------");
            System.out.println("Kereta Api Gagal Ditambahkan");
            System.out.println("----------------------------------------");
            menuTambah();
        }
    } 

   public void menuTampil(){
        System.out.println("Data Lengkap Kereta Api");
        System.out.println("----------------------------------------");
        System.out.println("No \t Kode Kereta \t Nama Kereta \t Gerbong \t Business \t Premium");
        List<Kereta> listKereta = keretaManager.GetAll();

        int i = 0;
        for(Kereta kereta : listKereta){
            i++;
            System.out.println(i+" \t "+kereta.getKodeKereta()+" \t "+kereta.getNamaKereta()+"\t\t"+kereta.getJmlGerbong()+"\t"+kereta.getJmlGBisnis()+"\t"+kereta.getJmlGPremium());
        }
        System.out.println("\n----------------------------------------");
    }

    public void showMenuTampil(){
        System.out.println("\n#LIHAT DATA KERETA API#");
        menuTampil();
        showMenu();
    }
   public void menuUbah()  {
        System.out.println("\n#EDIT DATA KERETA API#");
        menuTampil();
        String kodeKereta = null;
        Kereta kereta = null;
        boolean flagIterate = true;

        String namaKereta;
        int jmlGerbong, jmlGBisnis, jmlGPremium;
        do {
            System.out.print("Edit Kereta Api : ");
            kodeKereta = scanner.next();
            if (kodeKereta == "99") {
                flagIterate = false;
            } else {
                kodeKereta = kodeKereta.substring(5);
                kereta = keretaManager.getByKodeKereta(kodeKereta);
                if (kereta != null) {
                    keretaManager.delete(kereta);
                    flagIterate = false;
                }
            }
        } while (flagIterate);

        kereta = new Kereta();
        System.out.print("Kode Kereta : ");
        kodeKereta = scanner.next();
        System.out.print("Nama Kereta : ");
        namaKereta = scanner.next();
        System.out.print("Gerbong : ");
        jmlGerbong = scanner.nextInt();
        System.out.print("Bussiness : ");
        jmlGBisnis = scanner.nextInt();
        System.out.print("Premium: ");
        jmlGPremium = scanner.nextInt();

        if(jmlGerbong==(jmlGBisnis+jmlGPremium)){
            kereta.setKodeKereta(kodeKereta);
            kereta.setNamaKereta(namaKereta);
            kereta.jmlGerbong(jmlGerbong);
            kereta.jmlGBisnis(jmlGBisnis);
            kereta.jmlGPremium(jmlGPremium);

            keretaManager.add(kereta);
            System.out.println("----------------------------------------");
            System.out.println("Kereta Api Berhasil Diedit");
            System.out.println("----------------------------------------");
            showMenu();
        }
        else{
            System.out.println("----------------------------------------");
            System.out.println("Kereta Api Gagal Diedit");
            System.out.println("----------------------------------------");
            menuUbah();
        }
    }    

    void menuHapus() {
        System.out.println("\n#DELETE DATA KERETA API#");
        menuTampil();
        String kodeKereta = null;
        Kereta delKereta = null;
        boolean flagIterate = true;
        do {
            System.out.print("Delete Kereta : ");
            kodeKereta = scanner.next();
            if (kodeKereta == "99") {
                flagIterate = false;
            } else {
                kodeKereta = kodeKereta.substring(7);
                delKereta = keretaManager.getByKodeKereta(kodeKereta);
                if (delKereta!=null) {
                    keretaManager.delete(delKereta);
                    flagIterate = false;
                }
            }
        } while (flagIterate);
    }
}