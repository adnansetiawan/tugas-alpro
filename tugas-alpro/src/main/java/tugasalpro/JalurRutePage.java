package tugasalpro;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

public class JalurRutePage {
    private JalurRuteManager jalurRuteManager;
    Scanner scanner;
    
    public JalurRutePage(){
        jalurRuteManager=new JalurRuteManager();
        scanner=new Scanner(System.in);
    }

    public void showJalurRutePage(){
        System.out.println("#KELOLA STASIUN BERDASARKAN RUTE#");
        showMenu();
    }

    public void showMenu(){
        int pilihan=0;
        do{
            System.out.println("\n1.  Tambah Jalur Stasiun pada Rute");
            System.out.println("2.  Lihat Jalur Stasiun pada Rute");
            System.out.println("3.  Delete Jalur Stasiun pada Rute");
            System.out.println("99. Menu Utama\n");
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
                    MenuManager menu = new MenuManager();
                    menu.ShowMenuAdmin();
                    break;
            }
        }while(pilihan!=99);
    }

    public void menuTambah(){
        System.out.println("#TAMBAH JALUR STASIUN PADA RUTE#");
        JalurRute jalurRute = new JalurRute();
        List<JalurRute> listJalurRute = jalurRuteManager.getAll();
        int id = listJalurRute.size()+1;
        String kodeJalur;
        if (id < 10){
            kodeJalur = "JL0"+id;
        }else{
            kodeJalur = "JL"+id;
        }
        jalurRute.setKodeJalur(kodeJalur);
        System.out.print("Kode Rute : ");
        Rute rute = new Rute();
        rute.setKodeRute(scanner.next());
        jalurRute.setRuteJalur(rute);
        System.out.println("Stasiun Awal Sampai Stasiun Akhir\n----------------------------------------");
        JalurStasiun jalurStasiun = new JalurStasiun();
        Stasiun stasiunAwal = new Stasiun();
        Stasiun stasiunTujuan = new Stasiun();
        String masukan;
        do{
            System.out.print("Jalur %d : ");
            masukan = scanner.next();
            if (masukan!="99"){
                stasiunAwal.setNamaStasiun(masukan);
                jalurStasiun.setStasiunAwal(stasiunAwal);
                stasiunTujuan.setNamaStasiun(scanner.next());
                jalurStasiun.setStasiunTujuan(stasiunTujuan);
                jalurStasiun.setDurasi(scanner.nextInt());
            }
            jalurRute.addJalurStasiun(jalurStasiun);
        }while (masukan!="99");
        jalurRuteManager.add(jalurRute);
        System.out.println("----------------------------------------");
        System.out.println("Jalur Stasiun yang dilewati berdasarkan Rute Berhasil Ditambahkan");
        System.out.println("----------------------------------------");
        showMenu();
    }

    public void menuTampil(){
        /*
        System.out.println("#LIHAT STASIUN BERDASARKAN RUTE#");
        System.out.print("Kode Rute : ");
        List<JalurRute> listJalurRute = jalurRuteManager.getAll();
        int i = 0;
        jalurRute.setRuteJalur(scanner.next());
        */
    }

    public void menuHapus(){

    }
}