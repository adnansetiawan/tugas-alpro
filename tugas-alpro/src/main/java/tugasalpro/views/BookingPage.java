package tugasalpro.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import tugasalpro.managers.BookingManager;
import tugasalpro.models.*;

public class BookingPage
{
    private Scanner scanner;
    private BookingManager bookingManager;
    public BookingPage()
    {
        scanner = new Scanner(System.in);
        bookingManager = new BookingManager();
    }
    private void showGerbong(String nama, String prefix, int jmlGerbong, int jmlRow)
    {
        for(int i=1; i<=jmlGerbong; i++)
        {
            System.out.println("Gerbong "+nama+"" + i + ": ");
            System.out.println("------------------------------------------------------------------------------------------");
            int no = 1;
              
            for(int r=0; r<jmlRow; r++)
            {
                if(r > 0)
                {
                    System.out.println("------------------------------------------------------------------------------------------");
          
                }
                 for(int k=1; k<=10; k++)
                {
                    char namaKursi = k > 5 ? 'F' : 'E'; 
                    String nomorKursi = "";
                    if(k == 1)
                    {
                        nomorKursi += "|";
                    }
                    if(no < 10)
                    {
                        nomorKursi +=prefix+i+"- "+no+" "+namaKursi + " |";
                       
                    }else
                    {
                        nomorKursi +=prefix+i+"-"+no+" "+namaKursi + " |";
                    }
                    no++;
                    System.out.print(nomorKursi);
                    
                }
                System.out.println();
            }
           
            System.out.println("------------------------------------------------------------------------------------------");
          
            System.out.println();
          
        }
    }
    public void showInput()
    {
        System.out.print("Kode Jadwal :");
        String kodeJadwal = scanner.nextLine();
        System.out.print("Jumlah Penumpang :");
        int jumlahPenumpang = scanner.nextInt();
        System.out.println("------------------------------------------------------------------------------------------");
        Booking booking = new Booking(UUID.randomUUID().toString(), kodeJadwal);
           
       
        for(int i=1; i<=jumlahPenumpang; i++)
        {
            System.out.print("Penumpang " + i + ": ");
            scanner = new Scanner(System.in);
            String penumpangName = scanner.nextLine();
            booking.addPenumpang(new Penumpang(penumpangName));
        }
        System.out.println();
        showGerbong("Bisnis", "B", 2, 1);
        showGerbong("Premium", "P", 3, 2);
        System.out.println("Pilih Kursi (Dengan Tanda E/Empty) : ");
        int pIndex = 1;
        for(Penumpang p : booking.getAllPenumpang())
        {
            System.out.print("Kursi " +pIndex + " : ");
            p.setKodeKursi(scanner.nextLine());
            pIndex++;
           
        }
        double totalPembayaran = 400000;
        System.out.println("Total Pembayaran = " +totalPembayaran);
        String rekeningTujuan = "803255671891";
        System.out.println("Rekening Tujuan = " +rekeningTujuan);
        booking.setTotalPembayaran(totalPembayaran);
        booking.setRekeningTujuan(rekeningTujuan);
        bookingManager.add(booking);
        System.out.println("1. Pembayaran");
        System.out.println("2. Menu utama");
        int pilihan = scanner.nextInt();
        switch(pilihan)
        {
            case 2 :
                UserMenuPage userMenuPage = new UserMenuPage();
                userMenuPage.ShowMenuPengguna();
                break;

        }
     
    }
}