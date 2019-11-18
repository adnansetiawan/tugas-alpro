package tugasalpro.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tugasalpro.models.*;

public class BookingPage
{
    private Scanner scanner;
    public BookingPage()
    {
        scanner = new Scanner(System.in);
    }
    public void showInput()
    {
        System.out.print("Kode Jadwal :");
        String kodeJadwal = scanner.nextLine();
        System.out.print("Jumlah Penumpang :");
        int jumlahPenumpang = scanner.nextInt();
        System.out.println("-------------------------------------------");
           
        List<Penumpang> penumpangs = new ArrayList<Penumpang>();
        for(int i=1; i<=jumlahPenumpang; i++)
        {
            System.out.print("Penumpang " + i + ": ");
            scanner = new Scanner(System.in);
            String penumpangName = scanner.nextLine();
            penumpangs.add(new Penumpang(penumpangName));
        }
        System.out.println("--------------------------------------------------------------------------------------------");
        for(int i=1; i<=2; i++)
        {
            System.out.println("Gerbong Bussiness " + i + ": ");
            System.out.println("--------------------------------------------------------------------------------------------");
      
                for(int k=1; k<=10; k++)
                {
                    char namaKursi = k > 5 ? 'F' : 'E'; 
                    String nomorKursi = "";
                    if(k == 1)
                    {
                        nomorKursi += "|";
                    }
                    nomorKursi +=" B"+i+"-"+k+" "+namaKursi + " |";
                    
                    System.out.print(nomorKursi);
                    
                }
                System.out.println();
            System.out.println("--------------------------------------------------------------------------------------------");
      
            System.out.println();
          
        }
        for(int i=1; i<=2; i++)
        {
            System.out.println("Gerbong Premium " + i + ": ");
            System.out.println("--------------------------------------------------------------------------------------------");
      
                for(int k=1; k<=10; k++)
                {
                    char namaKursi = k > 5 ? 'F' : 'E'; 
                    String nomorKursi = "";
                    if(k == 1)
                    {
                        nomorKursi += "|";
                    }
                    nomorKursi +=" P"+i+"-"+k+" "+namaKursi + " |";
                    
                    System.out.print(nomorKursi);
                    
                }
                System.out.println();
                System.out.println("--------------------------------------------------------------------------------------------");
                for(int k=1; k<=10; k++)
                {
                    char namaKursi = k > 5 ? 'F' : 'E'; 
                    String nomorKursi = "";
                    if(k == 1)
                    {
                        nomorKursi += "|";
                    }
                    nomorKursi +=" P"+i+"-"+k+" "+namaKursi + " |";
                    
                    System.out.print(nomorKursi);
                    
                }
                System.out.println();
            System.out.println("--------------------------------------------------------------------------------------------");
      
            System.out.println();
          
        }
    }
}