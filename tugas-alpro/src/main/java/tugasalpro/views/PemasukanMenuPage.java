package tugasalpro.views;

import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import tugasalpro.models.Pembayaran;
import tugasalpro.managers.PemasukanManager;
import tugasalpro.managers.PembayaranManager;

public class PemasukanMenuPage {
    PemasukanManager pemasukanManager;
    PembayaranManager pembayaranManager;
    Scanner scanner;

    public PemasukanMenuPage() {
        pemasukanManager=new PemasukanManager();
        scanner=new Scanner(System.in);
    }

    public void showMenu() {
        int pilihan;
        System.out.println("#LAPORAN PEMASUKAN#\n");
        do{
            System.out.println("1. Laporan Harian");
            System.out.println("2. Laporan Bulanan");
            System.out.println("3. Laporan Tahunan");
            System.out.println("99. Menu Utama");
            System.out.print("Pilihan : ");
            pilihan=scanner.nextInt();
            switch(pilihan) {
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

    public void laporanHarian() {
        AsciiTable at = new AsciiTable();
        System.out.println("#LAPORAN HARIAN PEMASUKAN");
        System.out.print("Masukkan Tanggal Pencarian : ");
        String tanggal = scanner.next();

        SimpleDateFormat formatTanggal=new SimpleDateFormat("dd-mm-yyyy");
        Date tglInput=null;
        try{
            tglInput = formatTanggal.parse(tanggal);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Data Pemasukan Harian");
        System.out.println("----------------------------------------");
        at.addRule();
        AT_Row row =  at.addRow("No", "Tanggal", "KAI", "Jumlah Pemasukan");
        row.setTextAlignment(TextAlignment.CENTER);
        at.addRule();

        List<Pembayaran> listPembayaran = pembayaranManager.getAll();

        int i=0;
        double totalPemasukanHarian=0;

        for(Pembayaran pembayaran : listPembayaran){
            if(pembayaran.getTanggalPembayaran().equals(tglInput)){
                i++;
                at.addRow(i, pembayaran.getTanggalPembayaran(), pembayaran.getKodeKereta(), pembayaran.getTotalPembayaran());
                at.addRule();
                totalPemasukanHarian += pembayaran.getTotalPembayaran();
            }
            CWC_LongestLine cwc = new CWC_LongestLine();
            cwc.add(4, 0).add(20, 0).add(30, 0).add(30, 0);
            at.getRenderer().setCWC(cwc);
            System.out.println(at.render());
        }

        System.out.println("Total Masukan Harian : " + totalPemasukanHarian);
        System.out.println("----------------------------------------");
    }

    public void laporanBulanan() {
        AsciiTable at = new AsciiTable();
        System.out.println("#LAPORAN BULANAN PEMASUKAN");
        System.out.print("Masukkan Bulan Pencarian : ");
        String tanggal=scanner.next();

        SimpleDateFormat formatTanggal=new SimpleDateFormat("mm-yyyy");
        Date tglInput=null;
        try{
            tglInput = formatTanggal.parse(tanggal);
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("Data Pemasukan Bulanan");
        System.out.println("----------------------------------------");
        at.addRule();
        AT_Row row = at.addRow("No", "Tanggal", "Jumlah Pendapatan");
        row.setTextAlignment(TextAlignment.CENTER);
        at.addRule();

        List<Pembayaran> listPembayaran = pembayaranManager.getAll();

        double totalPemasukanBulanan=0;
        double totalPemasukanHarian=0;

        Date tglIterasi = new Date(tglInput.getYear(), tglInput.getMonth(),1);

        YearMonth bulanInput = YearMonth.of(tglInput.getYear(),tglInput.getMonth());

        for(int i=1; i<=bulanInput.lengthOfMonth(); i++){
            totalPemasukanHarian = 0;

            tglIterasi.setDate(i);

            for(Pembayaran pembayaran : listPembayaran){    
                if(tglIterasi == pembayaran.getTanggalPembayaran()) {
                    totalPemasukanHarian += pembayaran.getTotalPembayaran();
                }
            }
            at.addRow(i, tglIterasi.toString(), totalPemasukanHarian);
            at.addRule();

            totalPemasukanBulanan += totalPemasukanHarian;
        }
        CWC_LongestLine cwc = new CWC_LongestLine();
        cwc.add(4, 0).add(20, 0).add(50, 0);
        at.getRenderer().setCWC(cwc);
        System.out.println(at.render());

        System.out.println("Total Masukan Bulanan : "+totalPemasukanBulanan);
        System.out.println("----------------------------------------");
    }

    public void laporanTahunan(){
        AsciiTable at = new AsciiTable();
        System.out.println("#LAPORAN TAHUNAN PEMASUKAN");
        System.out.print("Masukkan Tahun Pencarian : ");
        String tanggal = scanner.next();
        SimpleDateFormat formatTanggal = new SimpleDateFormat("yyyy");
        Date tgl = null;
        try{
            tgl = formatTanggal.parse(tanggal);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Data Pemasukan Tahunan");
        System.out.println("----------------------------------------");
        at.addRule();
        AT_Row row =  at.addRow("No", "Bulan", "Jumlah Pendapatan");
        row.setTextAlignment(TextAlignment.CENTER);
        at.addRule();

        List<Pembayaran> listPembayaran = pembayaranManager.getAll();
        
        double totalPemasukanTahunan=0;
        double totalPemasukanBulanan;
        
        for(int i=1; i<=12; i++){
            totalPemasukanBulanan = 0;
            for(Pembayaran pembayaran : listPembayaran){
                if(pembayaran.getBulanPembayaran() == i){
                    totalPemasukanBulanan += pembayaran.getTotalPembayaran();
                }
            }
            at.addRow(i, String.format("%02d", i), totalPemasukanBulanan);
            at.addRule();

            totalPemasukanTahunan += totalPemasukanBulanan;
        }
        CWC_LongestLine cwc = new CWC_LongestLine();
        cwc.add(4, 0).add(20, 0).add(50, 0);
        at.getRenderer().setCWC(cwc);
        System.out.println(at.render());

        System.out.println("Total Masukan Tahunan : "+totalPemasukanTahunan);
        System.out.println("----------------------------------------");
    }
}