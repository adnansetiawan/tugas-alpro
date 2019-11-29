package tugasalpro.views;

import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import tugasalpro.utilities.StringUtility;
import tugasalpro.utilities.ScreenUtility;
import tugasalpro.models.Pembayaran;
import tugasalpro.managers.PembayaranManager;

public class PemasukanPage {
    PembayaranManager pembayaranManager;
    Scanner scanner;

    public PemasukanPage() {
        pembayaranManager = new PembayaranManager();
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
                    MenuPage menuPage = new MenuPage();
                    menuPage.showMenuAdmin();
                    break;
            }
        }while(pilihan != 99);
    }

    public void laporanHarian() {
        ScreenUtility.ClearScreen();
        AsciiTable at = new AsciiTable();
        System.out.println("#LAPORAN HARIAN PEMASUKAN");
        System.out.print("Masukkan Tanggal Pencarian : ");
        String tanggal = scanner.next();

        SimpleDateFormat formatTanggal = new SimpleDateFormat("dd-MM-yyyy");
        Date tglInput = null;
        try{
            tglInput = formatTanggal.parse(tanggal);

            System.out.println("Data Pemasukan Harian");
            System.out.println("----------------------------------------");
            at.addRule();
            AT_Row row =  at.addRow("No", "Tanggal", "KAI", "Jumlah Pemasukan");
            row.setTextAlignment(TextAlignment.CENTER);
            at.addRule();

            List<Pembayaran> listPembayaran = pembayaranManager.getAll();
            HashMap<String,Pembayaran> listCetak = new HashMap<>();

            int i = 0;
            double totalPemasukanHarian = 0;
            
            //isi objek pembayaran di tanggal ke hashmap
            for(Pembayaran pembayaran : listPembayaran){
                if(formatTanggal.format(pembayaran.getTanggalPembayaran()).compareTo(formatTanggal.format(tglInput)) == 0){

                    //cek apakah kode kereta udah ada di hash
                    //kalau ada, tambah total pembayaran yg baru ke total pembayaran di kode kereta yg sudah ada 
                    if(listCetak.containsKey(pembayaran.getKodeKereta())){
                        listCetak.get(pembayaran.getKodeKereta()).setTotalPembayaran(listCetak.get(pembayaran.getKodeKereta()).getTotalPembayaran()+pembayaran.getTotalPembayaran());
                    }
                    //kalau belum, tambah object pembayaran utk kode KAI ini ke hash
                    else{
                        listCetak.put(pembayaran.getKodeKereta(), pembayaran);
                    }
                }
            }

            //print isi hash listCetak
            for (Map.Entry<String, Pembayaran> entry : listCetak.entrySet()) {
                    i++;
                    AT_Row contRow = at.addRow(i, formatTanggal.format(entry.getValue().getTanggalPembayaran()), entry.getValue().getKodeKereta(), StringUtility.getCurrencyFormat(entry.getValue().getTotalPembayaran()));
                    at.addRule();
                    contRow.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
                    contRow.getCells().get(1).getContext().setTextAlignment(TextAlignment.CENTER);
                    contRow.getCells().get(2).getContext().setTextAlignment(TextAlignment.CENTER);
                    contRow.getCells().get(3).getContext().setTextAlignment(TextAlignment.JUSTIFIED_RIGHT);
                    
                    totalPemasukanHarian += entry.getValue().getTotalPembayaran();
            }

            CWC_LongestLine cwc = new CWC_LongestLine();
            cwc.add(4, 0).add(20, 0).add(20, 0).add(30, 0);
            at.getRenderer().setCWC(cwc);
            System.out.println(at.render());

            System.out.println("Total Masukan Harian : " + StringUtility.getCurrencyFormat(totalPemasukanHarian));
            System.out.println("----------------------------------------");

            
            
        }catch (Exception e){
            System.out.println("Masukan tidak sesuai format: dd-MM-yyyy");
           
        }
    }

    public void laporanBulanan() {
        ScreenUtility.ClearScreen();
        AsciiTable at = new AsciiTable();
        System.out.println("#LAPORAN BULANAN PEMASUKAN");
        System.out.print("Masukkan Bulan Pencarian : ");
        String tanggal = scanner.next();

        SimpleDateFormat formatTanggal = new SimpleDateFormat("MM-yyyy");
        Date tglInput = null;
        try{
            tglInput = formatTanggal.parse(tanggal);

            System.out.println("Data Pemasukan Bulanan");
            System.out.println("----------------------------------------");
            at.addRule();
            AT_Row row = at.addRow("No", "Tanggal", "Jumlah Pendapatan");
            row.setTextAlignment(TextAlignment.CENTER);
            at.addRule();
    
            List<Pembayaran> listPembayaran = pembayaranManager.getAll();
    
            double totalPemasukanBulanan = 0;
            double totalPemasukanHarian = 0;
    
            SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
            Date tglIterasi = new Date(tglInput.getYear(),tglInput.getMonth(),1);
            YearMonth bulanInput = YearMonth.of(tglInput.getYear(),tglInput.getMonth()+1);
    
            for(int i=1; i<=bulanInput.lengthOfMonth(); i++){
                totalPemasukanHarian = 0;
                tglIterasi.setDate(i);
     
                for(Pembayaran pembayaran : listPembayaran){    
                    if(fmt.format(pembayaran.getTanggalPembayaran()).compareTo(fmt.format(tglIterasi))==0)
                    {
                        totalPemasukanHarian += pembayaran.getTotalPembayaran();
                    }
                }
                AT_Row contRow = at.addRow(i, String.format("%02d", i), StringUtility.getCurrencyFormat(totalPemasukanHarian));
                at.addRule();
                contRow.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
                contRow.getCells().get(1).getContext().setTextAlignment(TextAlignment.CENTER);
                contRow.getCells().get(2).getContext().setTextAlignment(TextAlignment.JUSTIFIED_RIGHT);
    
                totalPemasukanBulanan += totalPemasukanHarian;
            }
            CWC_LongestLine cwc = new CWC_LongestLine();
            cwc.add(4, 0).add(10, 0).add(30, 0);
            at.getRenderer().setCWC(cwc);
            System.out.println(at.render());
    
            System.out.println("Total Masukan Bulanan : " +StringUtility.getCurrencyFormat(totalPemasukanBulanan));
            System.out.println("----------------------------------------");

        }catch (Exception e) {
            System.out.println("Masukan tidak sesuai format: MM-yyyy");
        }
    }

    public void laporanTahunan(){
        ScreenUtility.ClearScreen();
        AsciiTable at = new AsciiTable();
        System.out.println("#LAPORAN TAHUNAN PEMASUKAN");
        System.out.print("Masukkan Tahun Pencarian : ");
        String input = scanner.next();

        try{
            int tahun = Integer.parseInt(input);

            System.out.println("Data Pemasukan Tahunan");
            System.out.println("----------------------------------------");
            at.addRule();
            AT_Row row =  at.addRow("No", "Bulan", "Jumlah Pendapatan");
            row.setTextAlignment(TextAlignment.CENTER);
            at.addRule();

            List<Pembayaran> listPembayaran = pembayaranManager.getAll();
            
            double totalPemasukanTahunan=0;
            double totalPemasukanBulanan;
            
            for(int i=0; i<=11; i++){
                totalPemasukanBulanan = 0;
                for(Pembayaran pembayaran : listPembayaran){
                    if(pembayaran.getBulanPembayaran() == i && pembayaran.getTahunPembayaran() == tahun){
                        totalPemasukanBulanan += pembayaran.getTotalPembayaran();
                    }
                }
                AT_Row contRow = at.addRow(i+1, String.format("%02d", i+1), StringUtility.getCurrencyFormat(totalPemasukanBulanan));
                at.addRule();
                contRow.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
                contRow.getCells().get(1).getContext().setTextAlignment(TextAlignment.CENTER);
                contRow.getCells().get(2).getContext().setTextAlignment(TextAlignment.JUSTIFIED_RIGHT);

                totalPemasukanTahunan += totalPemasukanBulanan;
            }
            CWC_LongestLine cwc = new CWC_LongestLine();
            cwc.add(4, 0).add(10, 0).add(30, 0);
            at.getRenderer().setCWC(cwc);
            System.out.println(at.render());

            System.out.println("Total Masukan Tahunan : "+StringUtility.getCurrencyFormat(totalPemasukanTahunan));
            System.out.println("----------------------------------------");
        }
        catch(Exception e){
            System.out.println("Masukan tidak sesuai format: yyyy");
        }
    }
}