package tugasalpro.views;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import java.text.SimpleDateFormat;import java.text.spi.DateFormatProvider;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import tugasalpro.*;
import tugasalpro.models.*;
import tugasalpro.managers.*;

public class PemasukanMenuPage{
    Scanner scanner;

    public PemasukanMenuPage(){
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
        }catch (ParseException e){
            e.printStackTrace();
        }
        System.out.println("Data Pemasukan Harian");
        System.out.println("----------------------------------------");
        System.out.println("No \t Tanggal \t KAI \t Jumlah Pemasukan");
        /*List<PemasukanKeretaHarian> listPemasukanKeretaHarian = pemasukanKeretaHarianManager.getAll();
        int i=0;
        int totalPemasukanHarian=0;
        for(PemasukanKeretaHarian pemasukanKeretaHarian : listPemasukanKeretaHarian){
            if(pemasukanKeretaHarian.getTanggal().equals(tgl)){
                i++;
                System.out.println(i+" \t "+pemasukanKeretaHarian.getTanggal()+" \t "+pemasukanKeretaHarian.getKereta().getKodeKereta()+" \t "+pemasukanKeretaHarian.getJumlahPemasukan());
                totalPemasukanHarian+=pemasukanKeretaHarian.getJumlahPemasukan();
            }
        }
        System.out.println("Total Masukan Harian : "+totalPemasukanHarian);*/
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
        }catch (ParseException e){
            e.printStackTrace();
        }
        System.out.println("Data Pemasukan Bulanan");
        System.out.println("----------------------------------------");
        System.out.println("No \t Tanggal \t Jumlah Pemasukan");
        /*List<PemasukanKeretaHarian> listPemasukanKeretaHarian = pemasukanKeretaHarianManager.getAll();
        int i=0;
        int totalPemasukanBulanan=0;
        for(pemasukanKeretaHarian pemasukanKeretaHarian : listPemasukanKeretaHarian){
            if(pemasukanKeretaHarian.getTanggal().MONTH.equals(tgl.MONTH)&&pemasukanKeretaHarian.getTanggal().YEAR.equals(tgl.YEAR))
                i++;
                System.out.println(i+" \t "+pemasukanKeretaHarian.getTanggal()+" \t ");
                int totalPemasukanHarian=0;
                for(pemasukanKeretaHarian pemasukanKeretaHarian : listPemasukanKeretaHarian){
                    totalPemasukanHarian+=pemasukanKeretaHarian.getJumlahPemasukan();
                }
                System.out.println(totalPemasukanHarian);
                totalPemasukanBulanan+=totalPemasukanHarian;
            }
        }
        System.out.println("Total Masukan Bulanan : "+totalPemasukanBulanan);*/
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
        }catch (ParseException e){
            e.printStackTrace();
        }
        System.out.println("Data Pemasukan Tahunan");
        System.out.println("----------------------------------------");
        System.out.println("No \t Bulan \t Jumlah Pemasukan");
        /*List<PemasukanKeretaHarian> listPemasukanKeretaHarian = pemasukanKeretaHarianManager.getAll();
        int i=0;
        int totalPemasukanTahunan=0;
        for(pemasukanKeretaHarian pemasukanKeretaHarian : listPemasukanKeretaHarian){
            if(pemasukanKeretaHarian.getTanggal().YEAR.equals(tgl.YEAR)){
                i++;
                System.out.println(i+" \t "+pemasukanKeretaHarian.getTanggal().MONTH+"+" \t ");
                int totalPemasukanBulanan=0;
                for(pemasukanKeretaHarian pemasukanKeretaHarian : listPemasukanKeretaHarian){
                    int totalPemasukanHarian=0;
                    for(pemasukanKeretaHarian pemasukanKeretaHarian : listPemasukanKeretaHarian){
                        totalPemasukanHarian+=pemasukanKeretaHarian.getJumlahPemasukan();
                    }
                    totalPemasukanBulanan+=pemasukanKeretaHarian.getJumlahPemasukan();
                }
                totalPemasukanBulanan+=pemasukanKeretaHarian.totalPemasukanHarian;
            }
            System.out.println(totalPemasukanBulanan);
            totalPemasukanTahunan+=totalPemasukanBulanan;
        }
        System.out.println("Total Masukan Tahunan : "+totalPemasukanTahunan);*/
        System.out.println("----------------------------------------");
    }
}