package tugasalpro;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

public class KeretaPage{
    private KeretaManager keretaManager;
    private Gerbong gerbong;
    Scanner scanner;

    public KeretaPage(){
        keretaManager = new KeretaManager();
        gerbong = new Gerbong();
        scanner = new Scanner(System.in);
    }

    void showMenu() throws IOException, URISyntaxException{
        int pilihan = 0;
        do {
            System.out.println("Menu Kereta Api");
            System.out.println("1. Tambah Kereta Api");
            System.out.println("2. Ubah Kereta Api");
            System.out.println("3. Tampilkan Kereta Api");
            System.out.println("4. Hapus Kereta Api");
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

    void menuTambah() throws IOException, URISyntaxException {
        Kereta kereta = new Kereta();
        String kodeKereta, namaKereta;
        int jmlGerbong, jmlGBisnis, jmlGPremium;

        System.out.print("Tambah Kereta Api: ");
        kodeKereta = scanner.next();
        namaKereta = scanner.next();
        jmlGerbong = scanner.nextInt();
        jmlGBisnis = scanner.nextInt();
        jmlGPremium = scanner.nextInt();

        if(jmlGerbong==(jmlGBisnis+jmlGPremium)){
            kereta.setKodeKereta(kodeKereta);
            kereta.setNamaKereta(namaKereta);
            kereta.jmlGerbong(jmlGerbong);
            kereta.jmlGBisnis(jmlGBisnis);
            kereta.jmlGPremium(jmlGPremium);

            keretaManager.add(kereta);
            System.out.println("Kereta Api Berhasil Ditambahkan");
        }
        else{
            System.out.println("Kereta Api Gagal Ditambahkan");
            menuTambah();
        }
    } 

    void menuTampil() throws IOException, URISyntaxException {
        System.out.println("-------------------------------------------------------");
        System.out.println("No \t Kode Kereta \t Nama Kereta \t Gerbong \t Business \t Premium");
        List<Kereta> listKereta = keretaManager.GetAll();

        int i = 0;
        for(Kereta kereta : listKereta){
            i++;
            System.out.println(i+" \t "+kereta.getKodeKereta()+" \t "+kereta.getNamaKereta()+"\t\t"+kereta.getJmlGerbong()+"\t"+kereta.getJmlGBisnis()+"\t"+kereta.getJmlGPremium());
        }
        System.out.println("-------------------------------------------------------");
    }

    void menuUbah() throws IOException, URISyntaxException {
        menuTampil();
        String kodeKereta = null;
        Kereta kereta = null;
        boolean flagIterate = true;

        String namaKereta;
        int jmlGerbong, jmlGBisnis, jmlGPremium;
        do {
            System.out.print("Edit Rute : ");
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
            System.out.println("Kereta Api Berhasil Ditambahkan");
        }
        else{
            System.out.println("Kereta Api Gagal Ditambahkan");
            menuTambah();
        }
    }    

    void menuHapus() throws IOException, URISyntaxException {
        menuTampil();
        String kodeKereta = null;
        Kereta delKereta = null;
        boolean flagIterate = true;
        do {
            System.out.print("Hapus Kereta : ");
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