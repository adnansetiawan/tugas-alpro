package tugasalpro;

import java.util.List;
import java.util.Scanner;

import tugasalpro.views.UserMenuPage;

public class JalurRutePage {
    private final JalurRuteManager jalurRuteManager;
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
        int pilihan;
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
                    UserMenuPage menu = new UserMenuPage();
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
            if (!masukan.equals("99")){
                stasiunAwal.setNamaStasiun(masukan);
                jalurStasiun.setStasiunAwal(stasiunAwal);
                stasiunTujuan.setNamaStasiun(scanner.next());
                jalurStasiun.setStasiunTujuan(stasiunTujuan);
                jalurStasiun.setDurasi(scanner.nextInt());
            }
            jalurRute.addJalurStasiun(jalurStasiun);
        }while (!masukan.equals("99"));
        jalurRuteManager.add(jalurRute);
        System.out.println("----------------------------------------");
        System.out.println("Jalur Stasiun yang dilewati berdasarkan Rute Berhasil Ditambahkan");
        System.out.println("----------------------------------------");
        showMenu();
    }

    public void menuTampil(){
        System.out.println("#LIHAT STASIUN BERDASARKAN RUTE#");
        System.out.println("-------------------------------------------------------");
        System.out.println("No \t Kode Jalur \t Kode Rute \t Jalur yang Dilewati \t Durasi");
        List<JalurRute> listJalurRute = jalurRuteManager.getAll();
        int i = 0,j;
        for (JalurRute jalurRute : listJalurRute) {
            i++;
            System.out.print(i+" \t "+jalurRute.getKodeJalur()+" \t "+jalurRute.getRuteJalur().getKodeRute()+" \t ");
            jalurRute.printJalurStasiun();
        }
        System.out.println("-------------------------------------------------------");
        showMenu();
    }

    public void menuHapus(){
        String kodeJalur = null;
        JalurRute delJalur = null;
        boolean flagIterate = true;
        do {
            System.out.print("Hapus Jalur : ");
            kodeJalur = scanner.next();
            if (kodeJalur.equals("99")) {
                flagIterate = false;
            } else {
                delJalur = jalurRuteManager.getByKodeJalur(kodeJalur);
                if (delJalur!=null) {
                    jalurRuteManager.delete(delJalur);
                    flagIterate = false;
                }
            }
        } while (flagIterate);
        showMenu();
    }
}