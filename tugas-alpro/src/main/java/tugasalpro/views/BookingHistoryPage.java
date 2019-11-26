package tugasalpro.views;

import java.util.List;
import java.util.Scanner;

import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.asciitable.CWC_LongestWord;
import de.vandermeer.asciitable.CWC_LongestWordMin;
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
        at.addRule();
    
        AT_Row rowTitle =  at.addRow( null, null,null,null, null, null,"#BOOKING HISTORY#");
        rowTitle.setTextAlignment(TextAlignment.CENTER);
          at.addRule();
        AT_Row row =  at.addRow("N0", "KODE BOOKING", "TANGGAL", "JML.PENUMPANG", "KODE JADWAL", "TOTAL", "STATUS");
        row.setTextAlignment(TextAlignment.CENTER);
        at.addRule();
        List<Booking> bookings = bookingManager.getAll(ApplicationSession.getLoggedUser());
       int no=1;
        for(Booking booking : bookings)
        {
            AT_Row contRow =  at.addRow(
            String.valueOf(no), booking.getBookingId(), StringUtility.getFormattedDate(booking.getBookingDate()),String.valueOf(booking.getJumlahPenumpang()),
                booking.getKodeJadwal(),StringUtility.getCurrencyFormat(booking.getTotalPembayaran()),(booking.IsBayar()?"sudah bayar" : "belum bayar"));
                at.addRule();
            contRow.getCells().get(3).getContext().setTextAlignment(TextAlignment.JUSTIFIED_RIGHT);
            contRow.getCells().get(5).getContext().setTextAlignment(TextAlignment.JUSTIFIED_RIGHT);

            no++;
        }
        at.setPaddingLeft(1);
        at.setPaddingRight(1);
        CWC_LongestLine cwc = new CWC_LongestLine();
        cwc.add(3, 0).add(15, 0).add(15, 0).add(10, 0).add(10, 0).add(20, 0).add(15, 0);
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
                MenuPage userMenuPage = new MenuPage();
                userMenuPage.showMenuPengguna();
                break;
        }
        
    }
}