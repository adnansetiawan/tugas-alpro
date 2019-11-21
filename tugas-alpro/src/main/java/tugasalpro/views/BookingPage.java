package tugasalpro.views;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import tugasalpro.managers.BookingManager;
import tugasalpro.managers.JadwalManager;
import tugasalpro.managers.PembayaranManager;
import tugasalpro.managers.RuteManager;
import tugasalpro.models.*;
import tugasalpro.utilities.StringUtility;

public class BookingPage
{
    private Scanner scanner;
    private BookingManager bookingManager;
    private JadwalManager jadwalManager;
    public BookingPage()
    {
        scanner = new Scanner(System.in);
        bookingManager = new BookingManager();
        jadwalManager = new JadwalManager();
       
    }
    private void showGerbong(int jmlRow, List<Kursi> arrKursi)
    {
            if(arrKursi.size() < 10)
                return;
         
                AsciiTable at = new AsciiTable();
                CWC_LongestLine cwc = new CWC_LongestLine();
                
                
             int offset = 1;
            int limit = 10;
            for(int r=1; r<=jmlRow; r++)
            {
                List<String> nomors = new ArrayList<String>();
               
                 at.addRule();
                
                 int k =offset;
                 for(k=offset; k<=limit; k++)
                 {
                    cwc.add(8,0);
                    char availbelFlag = arrKursi.get(k-1).getIsAvailable() ? 'E' : 'F'; 
                    String nomorKursi = "";
                    
                    
                    nomorKursi +=" "+arrKursi.get(k-1).getKodeKursi()+""+availbelFlag;
               
                    
                    nomors.add(nomorKursi);
                    
                    
                }
                if(nomors.size() > 0)
                {
                    AT_Row row =  at.addRow(nomors);
                    row.setTextAlignment(TextAlignment.CENTER);
                    at.addRule();
                }
               
                offset=k;
                limit = k+9;
                
                
            }
            at.getRenderer().setCWC(cwc);
            System.out.println(at.render());
          
            System.out.println();
          
        
    }
    public void showInput()
    {
        boolean jadwalIsValid = false;
        Jadwal jadwal = null;
        do
        {
        System.out.print("Kode Jadwal :");
        JadwalManager jadwalManager = new JadwalManager();
        String kodeJadwal = scanner.nextLine();
         jadwal = jadwalManager.GetByKodeJadwal(kodeJadwal);
        if(jadwal == null)
        {
            jadwalIsValid = false;
            System.out.println("jadwal tidak valid");
        }else
        {
            jadwalIsValid = true;
        }
        }while(!jadwalIsValid);
       
        System.out.print("Jumlah Penumpang :");
        int jumlahPenumpang = scanner.nextInt();
        System.out.println("------------------------------------------------------------------------------------------");
        Booking booking = new Booking(StringUtility.getAlphaNumericString(6), jadwal.getKodeJadwal());
           
       
        for(int i=1; i<=jumlahPenumpang; i++)
        {
            System.out.print("Penumpang " + i + ": ");
            scanner = new Scanner(System.in);
            String penumpangName = scanner.nextLine();
            booking.addPenumpang(new Penumpang(penumpangName));
        }
        System.out.println();
        int jmlGBisnis = jadwal.getKereta().getJmlGBisnis();
        int jmlGPremium = jadwal.getKereta().getJmlGPremium();
        if(jmlGBisnis > 0)
        {
            List<Kursi> kursiBisnis = jadwal.getKursi().stream().filter(x->x.getGerbong()
            .getKategori().equals("Bisnis"))
            .collect(Collectors.toList());
             for(int i=0; i<jmlGBisnis; i++)
            {
                int nomor =i+1;
                System.out.println("Bisnis "+nomor);
                System.out.println();
                List<Kursi> kursiBisnisByNo = kursiBisnis.stream().
                filter(x->x.getGerbong().getNomor()==nomor)
                .collect(Collectors.toList());
                showGerbong(1, kursiBisnisByNo);
            }
        }
        if(jmlGPremium > 0)
        {
            List<Kursi> kursiPremium = jadwal.getKursi().stream().filter(x->x.getGerbong()
            .getKategori().equals("Premium"))
            .collect(Collectors.toList());
            for(int i=0; i<jmlGPremium; i++)
            {
                int nomor =i+1;
                System.out.println("Premium "+nomor);
                System.out.println();
                List<Kursi> kursiPremiumsByNo = kursiPremium.stream().
                filter(x->x.getGerbong().getNomor()==nomor)
                .collect(Collectors.toList());
                showGerbong(2, kursiPremiumsByNo);
            }
        }
        System.out.println("Pilih Kursi (Dengan Tanda E/Empty) : ");
        int pIndex = 1;
        double totalPembayaran = 0;
        RuteManager ruteManager = new RuteManager();
        Rute rute =   ruteManager.getByKota(jadwal.getKotaKeberangkatan().getKodeKota(),
        jadwal.getKotaTujuan().getKodeKota());
        for(Penumpang p : booking.getAllPenumpang())
        {
            Kursi bookedKursi = null;
            do
            {
            System.out.print("Kursi " +pIndex + " : ");
            String kodeKursi = scanner.nextLine();
            bookedKursi = jadwal.bookingKursi(kodeKursi.toUpperCase(), false);
            
            if(bookedKursi != null)
            {
                p.setKodeKursi(kodeKursi.toUpperCase());
                if(bookedKursi.getGerbong().getKategori().toLowerCase().equals("premium"))
                {
                    totalPembayaran += rute.getHargaPremium();
                }else
                {
                    totalPembayaran += rute.getHargaBisnis();
                }
                pIndex++;
            }else
            {
                System.out.println("nomor kursi tidak tersedia");
            }
        }while(bookedKursi == null);
          
           
           
        }
         jadwalManager = new JadwalManager();
        jadwalManager.edit(jadwal);
        
        
        System.out.println("Total Pembayaran = " +totalPembayaran);
        String rekeningTujuan = "803255671891";
        System.out.println("Rekening Tujuan = " +rekeningTujuan);
        booking.setTotalPembayaran(totalPembayaran);
        booking.setRekeningTujuan(rekeningTujuan);
        bookingManager.add(booking);
        
        System.out.println("1. Pembayaran");
        System.out.println("99. Menu utama");
        int pilihan = scanner.nextInt();
        switch(pilihan)
        {
            case 1:
                PembayaranPage pembayaranPage = new PembayaranPage();
                pembayaranPage.showInputWithBooking(booking);
                break;
            case 99 :
                UserMenuPage userMenuPage = new UserMenuPage();
                userMenuPage.ShowMenuPengguna();
                break;

        }
     
    }
    public void showDetail()
    {
        System.out.println("-----------------");
        System.out.println("#BOOKING DETAIL#");
        System.out.println("-----------------");
        Booking bookingByKode =null;
        do
        {
        System.out.print("Masukkan kode booking : ");
        scanner =new Scanner(System.in);
        String bookingKode = scanner.nextLine();
         bookingByKode = bookingManager.getByKode(bookingKode);
        if(bookingByKode== null)
        {
            System.out.println("kode booking tidak ada");
        }
        }while(bookingByKode==null);
        System.out.println("---------------------------------------------------");
        System.out.println("Kode Jadwal : " + bookingByKode.getKodeJadwal());
        System.out.println("---------------------------------------------------");
        for(int i=1; i<=bookingByKode.getJumlahPenumpang(); i++)
        {
            Penumpang penumpang = bookingByKode.getAllPenumpang().get(i-1);
            System.out.println("Penumpang "+i+" : " + penumpang.getName());
            System.out.println("No. Kursi  : " + penumpang.getKodeKursi());
            System.out.println("---------------------------------------------------");
     
        }
        System.out.println("1. Ganti Kursi");
        System.out.println("2. Cancel");
        System.out.println("99. Keluar");
        int pilihan = scanner.nextInt();
        switch(pilihan)
        {
            case 1:
                gantiKursi(bookingByKode);
                break;
            case 2:
                 deleteBooking(bookingByKode);
                 break;
            case 99:
                UserMenuPage userMenuPage = new UserMenuPage();
                userMenuPage.ShowMenuPengguna();
                break;
        }
       
      
    }
    private void deleteBooking(Booking booking)
    {
        System.out.print("Anda yakin untuk menghapus booking: Y/N : ");
        String pilihan = scanner.next();
        if (pilihan.equals("Y")) 
        {
            bookingManager.delete(booking);
            PembayaranManager pembayaranManager = new PembayaranManager();
            pembayaranManager.delete(booking.getKodeJadwal());
        }
        System.out.println("delete success!!");
        showMenu();
    }
    private void gantiKursi(Booking booking)
    {
        
        Jadwal jadwal = jadwalManager.GetByKodeJadwal(booking.getKodeJadwal());
                
        for(Penumpang p : booking.getAllPenumpang())
        {
            Kursi kursiByKode = null;
            do
            {
                String oldKode = p.getKodeKursi();
                System.out.print("Kursi ["+p.getKodeKursi()+"] ganti ke :");
        
            String kodeKursi = scanner.next();
            kursiByKode = jadwal.bookingKursi(kodeKursi.toUpperCase(), false);
            
            if(kursiByKode != null)
            {
                p.setKodeKursi(kodeKursi.toUpperCase());
                jadwal.bookingKursi(oldKode, true);
                
            }else
            {
                System.out.println("nomor kursi tidak tersedia");
            }
            }while(kursiByKode == null);
        
        }
    bookingManager.update(booking);
    showMenu();
}
    private void showMenu()
    {
        System.out.println("1. Lihat Detail");
        System.out.println("99. Keluar");
        int pilihan = scanner.nextInt();
        switch(pilihan)
        {
            case 1:
                showDetail();
                break;
            case 99:
                UserMenuPage userMenuPage = new UserMenuPage();
                userMenuPage.ShowMenuPengguna();
                break;
        }
    
    }
  
}