package tugasalpro.views;

import java.util.List;
import java.util.Scanner;

import tugasalpro.ApplicationSession;
import tugasalpro.managers.BookingManager;
import tugasalpro.models.*;

public class BookingHistoryPage
{
    BookingManager bookingManager;
    public BookingHistoryPage()
    {
        bookingManager = new BookingManager();
    }
    public void show()
    {
        System.out.println("BOOKING HISTORY");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("No Tanggal\t\t\t\t\tJml.Penumpang\t\tKode Jadwal Total Pembayaran Status");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        List<Booking> bookings = bookingManager.getAll(ApplicationSession.getLoggedUser());
       int no=1;
        for(Booking booking : bookings)
        {
            
            System.out.println(no+"\t"+booking.getBookingDate()+"\t"+booking.getJumlahPenumpang()
            +"\t"+booking.getKodeJadwal()+"\t"+booking.getTotalPembayaran()+"\t"+(booking.IsBayar()?"sudah bayara" : "menunggu pembayaran"));
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
            no++;
        }
        System.out.println();
        System.out.println("99. Keluar");
        Scanner scanner = new Scanner(System.in);
        int pilihan = scanner.nextInt();
        switch(pilihan)
        {
            case 99:
                UserMenuPage userMenuPage = new UserMenuPage();
                userMenuPage.ShowMenuPengguna();
                break;
        }
        
    }
}