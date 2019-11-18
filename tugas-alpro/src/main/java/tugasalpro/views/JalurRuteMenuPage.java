package tugasalpro.views;


import java.util.List;
import java.util.Scanner;

import tugasalpro.models.*;
import tugasalpro.managers.*;

public class JalurRuteMenuPage{
    private  JalurRuteManager jalurRuteManager;
    private  RuteManager ruteManager;
    private  StasiunManager stasiunManager;
    Scanner scanner;
    
    public JalurRuteMenuPage(){
        jalurRuteManager=new JalurRuteManager();
        stasiunManager=new StasiunManager();
        scanner=new Scanner(System.in);
    }

    public void showMenu(){
        System.out.println("\n#KELOLA STASIUN BERDASARKAN RUTE#");
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
        System.out.println("\n#TAMBAH JALUR STASIUN PADA RUTE#");
        JalurRute jalurRute = new JalurRute();
        RuteManager ruteManager = new RuteManager();
        String kodeJalur;
        boolean reInput = false;
        do{
            System.out.print("Kode Jalur : ");
            kodeJalur=scanner.next();
            Rute rute = ruteManager.GetByKodeRute(kodeJalur);
            if(rute == null){
                System.out.println("Kode Jalur Tidak Tersedia");
                reInput = false;
            }else
            {
                reInput = true;
            }
        }while(!reInput);
        jalurRute.setKodeJalur(kodeJalur);
        String kodeRute=null;
        /*do{
            System.out.print("Kode Rute : ");
            kodeRute=scanner.next();
            if(ruteManager.GetIndexByKodeRute(kodeRute)==-1){
                System.out.println("Kode Rute Tidak Tersedia");
            }
        }while(ruteManager.GetIndexByKodeRute(kodeRute)==-1);*/
        jalurRute.setRuteJalur(ruteManager.GetByKodeRute(kodeRute));
        System.out.println("Stasiun Awal Sampai Stasiun Akhir\n----------------------------------------");
        JalurStasiun jalurStasiun = new JalurStasiun();
        String namaStasiunAsal, namaStasiunTujuan;
        int i = 1, durasiJalurStasiun;
        do{
            System.out.print("Jalur "+i+" : ");
            namaStasiunAsal = scanner.next();
            if (!namaStasiunAsal.equals("99")){
                namaStasiunTujuan=scanner.next();
                durasiJalurStasiun=scanner.nextInt();
                while(stasiunManager.getIndexByNamaStasiun(namaStasiunAsal)==-1||stasiunManager.getIndexByNamaStasiun(namaStasiunTujuan)==-1){
                    if(stasiunManager.getIndexByNamaStasiun(namaStasiunAsal)==-1){
                        System.out.println("Stasiun Asal Tidak Tersedia");
                    }
                    if(stasiunManager.getIndexByNamaStasiun(namaStasiunTujuan)==-1){
                        System.out.println("Stasiun Tujuan Tidak Tersedia");
                    }
                    System.out.print("Jalur "+i+" : ");
                    namaStasiunAsal=scanner.next();
                    namaStasiunTujuan=scanner.next();
                    durasiJalurStasiun=scanner.nextInt();
                }
                jalurStasiun.setStasiunAsal(stasiunManager.getByNamaStasiun(namaStasiunAsal));
                jalurStasiun.setStasiunTujuan(stasiunManager.getByNamaStasiun(namaStasiunTujuan));
                jalurStasiun.setDurasi(durasiJalurStasiun);
                jalurRute.addJalurStasiun(jalurStasiun);
                i++;
            }
        }while (!namaStasiunAsal.equals("99"));
        jalurRuteManager.add(jalurRute);
        System.out.println("----------------------------------------");
        System.out.println("Jalur Stasiun yang dilewati berdasarkan Rute Berhasil Ditambahkan");
        System.out.println("----------------------------------------");
        showMenu();
    }

    public void menuTampil(){
        System.out.println("\n#LIHAT JALUR STASIUN BERDASARKAN RUTE#");
        System.out.println("-------------------------------------------------------");
        System.out.println("No \t Kode Jalur \t Kode Rute \t Jalur yang Dilewati \t Durasi");
        List<JalurRute> listJalurRute = jalurRuteManager.getAll();
        int i = 0;
        /*for (JalurRute jalurRute : listJalurRute) {
            ArrayList<JalurStasiun> jalurStasiun = jalurRute.getArrJalurStasiun();
            i++;
            System.out.println(i+" \t "+jalurRute.getKodeJalur()+" \t \t "+jalurRute.getRuteJalur().getKodeRute()+" \t "+jalurStasiun.get(0).getStasiunAsal().getKodeStasiun()+"-"+jalurStasiun.get(0).getStasiunTujuan().getKodeStasiun()+" \t "+jalurRute.getDurasi());
            int j = 0;
            for (JalurStasiun listJalurStasiun : jalurStasiun) {
                if (j!=0) {
                    System.out.println("\t \t \t\t \t\t "+listJalurStasiun.getStasiunAsal().getKodeStasiun()+listJalurStasiun.getStasiunTujuan().getKodeStasiun());
                }
                j++;
            }
        }*/
        System.out.println("-------------------------------------------------------");
        showMenu();
    }

    public void menuHapus(){
        String kodeJalur = null;
        JalurRute delJalur = null;
        boolean flagIterate = true;
        System.out.println("\n#HAPUS JALUR STASIUN BERDASARKAN RUTE#");
        System.out.println("-------------------------------------------------------");
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