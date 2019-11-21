package tugasalpro.views;

import java.util.Date;
import java.util.Scanner;

import tugasalpro.managers.BookingManager;
import tugasalpro.managers.JadwalManager;
import tugasalpro.managers.PembayaranManager;
import tugasalpro.models.Booking;
import tugasalpro.models.Jadwal;
import tugasalpro.models.Pembayaran;
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
        System.out.println("no rekening         :" + booking.getRekeningTujuan());
        System.out.println("jumlah pembayaran   :" + booking.getTotalPembayaran());
        System.out.print("anda yakin ingin bayar :");
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
                System.out.println("99. Keluar");
                int pilihanMenu = scanner.nextInt();
                switch(pilihanMenu)
                {
                    case 99:
                        UserMenuPage userMenuPage = new UserMenuPage();
                        userMenuPage.ShowMenuPengguna();
                        break;
                }
                
                break;


        }
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