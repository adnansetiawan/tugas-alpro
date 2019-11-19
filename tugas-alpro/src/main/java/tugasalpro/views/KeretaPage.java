package tugasalpro.views;

import java.util.List;
import java.util.Scanner;

import tugasalpro.managers.KeretaManager;
import tugasalpro.models.Kereta;


public class KeretaPage {
    private final KeretaManager keretaManager;
    Scanner scanner;

    public KeretaPage() {
        keretaManager = new KeretaManager();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        System.out.println("\n#KELOLA DATA KERETA API#");
        int pilihan;
        do {
            System.out.println("\n1. Tambah Data Kereta Api");
            System.out.println("2. Lihat Data Kereta Api");
            System.out.println("3. Edit Data Kereta Api");
            System.out.println("4. Delete Data Kereta Api");
            System.out.println("99. Menu Utama\n");
            System.out.print("Pilihan : ");
            pilihan = scanner.nextInt();
            switch(pilihan) {
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
    
    public void menuTambah() {

        //needed to prevent scanner nextInt behaviour not consumin nextLine, more info at https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo
        scanner.nextLine();

        System.out.println("\n#TAMBAH DATA KERETA API#");
        Kereta kereta = new Kereta();
        String kodeKereta = null;
        String namaKereta = null;
        int jmlGerbong = 0;
        int jmlGBisnis = 0;
        int jmlGPremium = 0;
        boolean inputNotValid = false;
        
        do{
            System.out.print("Tambah Data Kereta Api: ");
            // String dataKereta = "KODE121 'Kucing Kereta' G6 B2 P3";
            String dataKereta = scanner.nextLine();
            String[] dataKeretas = dataKereta.split("'");
            try{
                kodeKereta = dataKeretas[0].trim();
                // System.out.println();
                // System.out.println("|"+kodeKereta+"|");
               
                namaKereta = dataKeretas[1];
                // System.out.println(namaKereta);

                String[] others = dataKeretas[2].split(" ");
                
                jmlGerbong = Integer.parseInt(others[1].substring(1, others[1].toCharArray().length));
                jmlGBisnis = Integer.parseInt(others[2].substring(1, others[2].toCharArray().length));
                jmlGPremium = Integer.parseInt(others[3].substring(1, others[3].toCharArray().length));
                
                // System.out.println(jmlGerbong+" - "+jmlGBisnis+" - "+jmlGPremium);
                
                inputNotValid = true;
            }
            catch(Exception ex) {
                System.out.println("Format Salah");
                inputNotValid = false;
            }
        } while(!inputNotValid);
        
        if(jmlGerbong == (jmlGBisnis + jmlGPremium)) {
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

   public void menuTampil() {
        System.out.println("Data Lengkap Kereta Api");
        System.out.println("----------------------------------------");
        System.out.println("No \t Kode Kereta \t Nama Kereta \t     Gerbong  Bisnis   Premium");
        List<Kereta> listKereta = keretaManager.GetAll();

        int i = 0;
        for(Kereta kereta : listKereta) {
            i++;
            System.out.println(i+" \t "+kereta.getKodeKereta()+" \t "+kereta.getNamaKereta()+"\t\t"+kereta.getJmlGerbong()+"\t"+kereta.getJmlGBisnis()+"\t"+kereta.getJmlGPremium());
        }
        System.out.println("\n----------------------------------------");
    }

    public void showMenuTampil() {
        System.out.println("\n#LIHAT DATA KERETA API#");
        menuTampil();
        showMenu();
    }

    public void menuUbah() {
        System.out.println("\n#EDIT DATA KERETA API#");
        menuTampil();
        String kodeKereta = null;
        Kereta kereta = null;
        Kereta newKereta = null;
        String perintah;
        boolean flagIterate = true;

        String namaKereta;
        int jmlGerbong, jmlGBisnis, jmlGPremium;
        do {
            System.out.print("Edit Kereta Api : ");
            kodeKereta = scanner.next();

            if(!kodeKereta.equals("99")) {
                perintah = kodeKereta.substring(0,Math.min(kodeKereta.length(),5));
                while(!perintah.equals("EDIT_")){
                    System.out.print("Format Salah\nEdit Kereta Api : ");
                    kodeKereta = scanner.next();
                    perintah = kodeKereta.substring(0,Math.min(kodeKereta.length(),5));
                }
                if(perintah.equals("EDIT_")) {
                    kodeKereta = kodeKereta.substring(5);
                    kereta = keretaManager.getByKodeKereta(kodeKereta);
                    if(kereta != null){

                        scanner.nextLine();

                        newKereta = new Kereta();
                        System.out.print("Kode Kereta : ");
                        kodeKereta = scanner.next();
                        System.out.print("Nama Kereta : ");
                        scanner.nextLine();
                        namaKereta = scanner.nextLine();
                        System.out.print("Jumlah Gerbong : ");
                        jmlGerbong = scanner.nextInt();
                        System.out.print("Jumlah Gerbong Bussiness : ");
                        jmlGBisnis = scanner.nextInt();
                        System.out.print("Jumlah Gerbong Premium: ");
                        jmlGPremium = scanner.nextInt();

                        if(jmlGerbong == (jmlGBisnis + jmlGPremium)){
                            newKereta.setKodeKereta(kodeKereta);
                            newKereta.setNamaKereta(namaKereta);
                            newKereta.jmlGerbong(jmlGerbong);
                            newKereta.jmlGBisnis(jmlGBisnis);
                            newKereta.jmlGPremium(jmlGPremium);

                            
                            keretaManager.delete(kereta);

                            keretaManager.add(newKereta);

                            System.out.println("----------------------------------------");
                            System.out.println("Kereta Api Berhasil Diedit");
                            System.out.println("----------------------------------------");
                            flagIterate = false;
                        }
                        else{
                            System.out.println("----------------------------------------");
                            System.out.println("Kereta Api Gagal Diedit");
                            System.out.println("----------------------------------------");
                            
                        }
                        
                    }else{
                        System.out.println("Kereta Tidak Tersedia");
                    }
                }
            }else{
                flagIterate = false;
            }
        } while (flagIterate);

        showMenu();
        // kereta = new Kereta();
        // System.out.print("Kode Kereta : ");
        // kodeKereta = scanner.next();
        // System.out.print("Nama Kereta : ");
        // namaKereta = scanner.next();
        // System.out.print("Jumlah Gerbong : ");
        // jmlGerbong = scanner.nextInt();
        // System.out.print("Jumlah Gerbong Bussiness : ");
        // jmlGBisnis = scanner.nextInt();
        // System.out.print("Jumlah Gerbong Premium: ");
        // jmlGPremium = scanner.nextInt();

        // if(jmlGerbong == (jmlGBisnis + jmlGPremium)){
        //     kereta.setKodeKereta(kodeKereta);
        //     kereta.setNamaKereta(namaKereta);
        //     kereta.jmlGerbong(jmlGerbong);
        //     kereta.jmlGBisnis(jmlGBisnis);
        //     kereta.jmlGPremium(jmlGPremium);

        //     keretaManager.add(kereta);
        //     System.out.println("----------------------------------------");
        //     System.out.println("Kereta Api Berhasil Diedit");
        //     System.out.println("----------------------------------------");
        //     showMenu();
        // }
        // else{
        //     System.out.println("----------------------------------------");
        //     System.out.println("Kereta Api Gagal Diedit");
        //     System.out.println("----------------------------------------");
        //     menuUbah();
        // }
    }    

    void menuHapus() {
        System.out.println("\n#DELETE DATA KERETA API#");
        menuTampil();
        String kodeKereta = null;
        Kereta delKereta = null;
        String perintah;
        boolean flagIterate = true;
        do {
            System.out.print("Delete Kereta Api: ");
            kodeKereta = scanner.next();

            if(!kodeKereta.equals("99")) {
                perintah = kodeKereta.substring(0,Math.min(kodeKereta.length(),7));
                while(!perintah.equals("DELETE_")){
                    System.out.print("Format Salah\nDelete Kereta Api : ");
                    kodeKereta = scanner.next();
                    perintah = kodeKereta.substring(0,Math.min(kodeKereta.length(),7));
                }
                if(perintah.equals("DELETE_")) {
                    kodeKereta = kodeKereta.substring(7);
                    delKereta = keretaManager.getByKodeKereta(kodeKereta);
                    if(delKereta != null){
                        keretaManager.delete(delKereta);
                        flagIterate = false;
                    }else{
                        System.out.println("Kereta Api Tidak Tersedia");
                    }
                }
            }else{
                flagIterate=false;
            }
        } while (flagIterate);
    }
}