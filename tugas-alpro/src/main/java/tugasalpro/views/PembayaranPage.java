package tugasalpro.views;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import de.vandermeer.asciitable.AsciiTable;
import tugasalpro.managers.BookingManager;
import tugasalpro.managers.JadwalManager;
import tugasalpro.managers.PembayaranManager;
import tugasalpro.models.Booking;
import tugasalpro.models.Jadwal;
import tugasalpro.models.Pembayaran;
import tugasalpro.models.Penumpang;
import tugasalpro.models.Tiket;
import tugasalpro.utilities.ScreenUtility;
import tugasalpro.utilities.StringUtility;

public class PembayaranPage
{
    private PembayaranManager pembayaranManager;
    private BookingManager bookingManager;
    private Scanner scanner;
    public PembayaranPage()
    {
        pembayaranManager = new PembayaranManager();
        bookingManager= new BookingManager();
        scanner = new Scanner(System.in);
    }
    private void doPembayaran(Booking booking)
    {
        ScreenUtility.ClearScreen();
        System.out.println("Total Pembayaran    :" + StringUtility.getCurrencyFormat(booking.getTotalPembayaran()));
        System.out.println("No. Rekening        :" + booking.getRekeningTujuan());
        System.out.print("Apakah data pembayaran sudah benar (Y/N) :");
        String pilihan = scanner.nextLine();
        switch(pilihan)
        {
            case "Y":
            case "y":
                Pembayaran pembayaran = new Pembayaran();
                JadwalManager jadwalManager = new JadwalManager();
                Jadwal jadwal = jadwalManager.GetByKodeJadwal(booking.getKodeJadwal());
                pembayaran.setKodePembayaran(StringUtility.getAlphaNumericString(6));
                pembayaran.setKodeKereta(jadwal.getKereta().getKodeKereta());
                pembayaran.setKodeBooking(booking.getBookingId());
                pembayaran.setTanggalPembayaran(new Date());
                pembayaran.setTotalPembayaran(booking.getTotalPembayaran());
                pembayaran.createTiket(StringUtility.getAlphaNumericString(6), booking.getAllPenumpang());
                pembayaranManager.add(pembayaran);
                System.out.println("Pembayaran berhasil!");
                showTiket(pembayaran);
                System.out.println("99. Keluar");
                int pilihanMenu = scanner.nextInt();
                switch(pilihanMenu)
                {
                    case 99:
                        MenuPage menuPage = new MenuPage();
                        menuPage.showMenuAdmin();
                        break;
                }
                
                break;


        }
    }
    private void showTiket(Pembayaran pembayaran)
    {
        Tiket tiket =pembayaran.getTiket();
        System.out.println("#TIKET#");
        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("Kode Tiket   :" + tiket.getKodeTiket());
        List<Penumpang> penumpang = tiket.getPenumpang();
        for(int i=1; i<=penumpang.size();i++)
        {
            at.addRow("Penumpang " + i + " :"  + penumpang.get(i-1).getName());
           
        }
        at.addRule();
        System.out.println(at.render());
    }
    public void showInput()
    {
        Booking bookingByKode =null;
        do
        {
            System.out.println("Masukkan kode booking : ");
            String kodeBooking = scanner.nextLine();
            bookingByKode = bookingManager.getByKode(kodeBooking);
            if(bookingByKode == null)
            {
                System.out.println("kode booking tidak ada");
            }
        }while(bookingByKode == null); 
        doPembayaran(bookingByKode);  
    }
    public void showInputWithBooking(Booking booking)
    {
       
        doPembayaran(booking);
      
    }
    
}