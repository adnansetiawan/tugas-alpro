package tugasalpro.views;

import java.util.List;
import java.util.Scanner;

import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import tugasalpro.ApplicationSession;
import tugasalpro.managers.BookingManager;
import tugasalpro.models.*;
import tugasalpro.utilities.StringUtility;

public class BookingHistoryPage
{
    private BookingManager bookingManager;
    private Scanner scanner;
    public BookingHistoryPage()
    {
        bookingManager = new BookingManager();
        scanner = new Scanner(System.in);
    }
    public void show()
    {
        AsciiTable at = new AsciiTable();
        
        System.out.println("#BOOKING HISTORY#");
        at.addRule();
        AT_Row row =  at.addRow("N0", "KODE BOOKING", "TANGGAL", "JML.PENUMPANG", "KODE JADWAL", "TOTAL", "STATUS");
        row.setTextAlignment(TextAlignment.CENTER);
        at.addRule();
        List<Booking> bookings = bookingManager.getAll(ApplicationSession.getLoggedUser());
       int no=1;
        for(Booking booking : bookings)
        {
            at.addRow(
            String.valueOf(no), booking.getBookingId(), StringUtility.getFormattedDate(booking.getBookingDate()),String.valueOf(booking.getJumlahPenumpang()),
                booking.getKodeJadwal(),StringUtility.getCurrencyFormat(booking.getTotalPembayaran()),(booking.IsBayar()?"paid" : "not paid"));
                at.addRule();
            no++;
        }
        CWC_LongestLine cwc = new CWC_LongestLine();
        cwc.add(4, 0).add(20, 0).add(30, 0).add(10, 0).add(20, 0).add(20, 10).add(20, 10);
        at.getRenderer().setCWC(cwc);
        System.out.println(at.render());
        System.out.println();
        System.out.println("1. Detail");
        System.out.println("99. Keluar");
        int pilihan = scanner.nextInt();
        switch(pilihan)
        {
            case 1:
                BookingPage bookingPage=new BookingPage();
                bookingPage.showDetail();
                break;
            case 99:
                UserMenuPage userMenuPage = new UserMenuPage();
                userMenuPage.ShowMenuPengguna();
                break;
        }
        
    }
}