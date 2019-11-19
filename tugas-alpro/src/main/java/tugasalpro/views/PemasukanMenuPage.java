package tugasalpro.views;


import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import tugasalpro.models.*;
import tugasalpro.managers.*;

public class PemasukanMenuPage{
    PemasukanManager pemasukanManager;
    Scanner scanner;

    public PemasukanMenuPage(){
        pemasukanManager=new PemasukanManager();
        scanner=new Scanner(System.in);
    }

    public void showMenu(){
        int pilihan;
        System.out.println("#LAPORAN PEMASUKAN#\n");
        do{
            System.out.println("1.  Laporan Harian");
            System.out.println("2.  Laporan Bulanan");
            System.out.println("3.  Laporan Tahunan");
            System.out.println("99. Menu Utama");
            System.out.print("Pilihan : ");
            pilihan=scanner.nextInt();
            switch(pilihan){
                case 1:
                    laporanHarian();
                    break;
                case 2:
                    laporanBulanan();
                    break;
                case 3:
                    laporanTahunan();
                    break;
                case 99:
                    UserMenuPage menu=new UserMenuPage();
                    menu.ShowMenuAdmin();
                    break;
            }
        }while(pilihan!=99);
    }

    public void laporanHarian(){
        System.out.println("#LAPORAN HARIAN PEMASUKAN");
        System.out.print("Masukkan Tanggal Pencarian : ");
        String tanggal=scanner.next();
        SimpleDateFormat formatTanggal=new SimpleDateFormat("dd-mm-yyyy");
        Date tgl=null;
        try{
            tgl = formatTanggal.parse(tanggal);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Data Pemasukan Harian");
        System.out.println("----------------------------------------");
        System.out.println("No \t Tanggal \t KAI \t Jumlah Pemasukan");
        List<Pemasukan> listPemasukan = pemasukanManager.getAll();
        int i=0;
        int totalPemasukanHarian=0;
        for(Pemasukan pemasukan : listPemasukan){
            if(pemasukan.getTanggal().equals(tgl)){
                i++;
                System.out.println(i+" \t "+pemasukan.getTanggal()+" \t "+pemasukan.getKereta().getKodeKereta()+" \t "+pemasukan.getPemasukan());
                totalPemasukanHarian+=pemasukan.getPemasukan();
            }
        }
        System.out.println("Total Masukan Harian : "+totalPemasukanHarian);
        System.out.println("----------------------------------------");
    }

    public void laporanBulanan(){
        System.out.println("#LAPORAN BULANAN PEMASUKAN");
        System.out.print("Masukkan Bulan Pencarian : ");
        String tanggal=scanner.next();
        SimpleDateFormat formatTanggal=new SimpleDateFormat("mm-yyyy");
        Date tgl=null;
        try{
            tgl = formatTanggal.parse(tanggal);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Data Pemasukan Bulanan");
        System.out.println("----------------------------------------");
        System.out.println("No \t Tanggal \t Jumlah Pemasukan");
        List<Pemasukan> listPemasukan = pemasukanManager.getAll();
        int i=0;
        int totalPemasukanBulanan=0;
        Calendar cal1=Calendar.getInstance();
        Calendar cal2=Calendar.getInstance();
        cal2.setTime(tgl);
        for(Pemasukan pemasukan : listPemasukan){
            cal1.setTime(pemasukan.getTanggal());
            if(cal1.get(Calendar.MONTH)==cal2.get(Calendar.MONTH)&&cal1.get(Calendar.YEAR)==cal2.get(Calendar.YEAR)){
                i++;
                System.out.println(i+" \t "+pemasukan.getTanggal()+" \t ");
                int totalPemasukanHarian=0;
                for(Pemasukan pemasukan1 : listPemasukan){
                    totalPemasukanHarian+=pemasukan1.getPemasukan();
                }
                System.out.println(totalPemasukanHarian);
                totalPemasukanBulanan+=totalPemasukanHarian;
            }
        }
        System.out.println("Total Masukan Bulanan : "+totalPemasukanBulanan);
        System.out.println("----------------------------------------");
    }

    public void laporanTahunan(){
        System.out.println("#LAPORAN TAHUNAN PEMASUKAN");
        System.out.print("Masukkan Tahun Pencarian : ");
        String tanggal=scanner.next();
        SimpleDateFormat formatTanggal=new SimpleDateFormat("yyyy");
        Date tgl=null;
        try{
            tgl = formatTanggal.parse(tanggal);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Data Pemasukan Tahunan");
        System.out.println("----------------------------------------");
        System.out.println("No \t Bulan \t Jumlah Pemasukan");
        List<Pemasukan> listPemasukan = pemasukanManager.getAll();
        int i=0;
        int totalPemasukanTahunan=0;
        System.out.println("Total Masukan Tahunan : "+totalPemasukanTahunan);
        System.out.println("----------------------------------------");
    }
}