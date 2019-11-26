package tugasalpro.views;

import java.util.Scanner;
import tugasalpro.ApplicationSession;
import tugasalpro.models.User;

public class MenuPage{
    private Scanner scanner;

    public MenuPage(){
        scanner = new Scanner(System.in);
    }

    public void showMenuPengguna(){
        User user = ApplicationSession.getLoggedUser();
        System.out.println("#MENU PEGGUNA#");
        System.out.println("Selamat datang, " + user.getUsername()+"!");
        System.out.println("1. Booking Tiket");
        System.out.println("2. Kelola Profile");
        System.out.println("3. History Pembelian");
        System.out.println("0. Logout");
        int pilihan = -1;
        do{
            System.out.print("Pilihan :");
            pilihan = scanner.nextInt();
        } while (pilihan < 0 || pilihan > 3);
        switch (pilihan) {
            case 0:
                LoginPage loginPage = new LoginPage();
                loginPage.Logout();
                break;
            case 1:
                BookingPage bookingPage = new BookingPage();
                bookingPage.showInput();
                break;
            case 2:
                UserPage userPage = new UserPage();
                userPage.showUpdatePenggunaPage();
                break;
            case 3:
                BookingHistoryPage bookingHistoryPage = new BookingHistoryPage();
                bookingHistoryPage.show();
                break;
        }
    }

    public void showMenuAdmin(){
        int pilihan = -1;
        do{
            System.out.println("#MENU ADMIN#");
            System.out.println("Selamat datang, Admin!");
            System.out.println("1. Kelola Akun");
            System.out.println("2. Kelola Data Kota");
            System.out.println("3. Generate Waktu");
            System.out.println("4. Kelola Rute");
            System.out.println("5. Kelola Stasiun");
            System.out.println("6. Kelola Jalur Stasiun Pada Rute");
            System.out.println("7. Kelola Data Kereta Api");
            System.out.println("8. Kelola Waktu Pada Rute");
            System.out.println("9. Kelola Kereta Pada Rute");
            System.out.println("10. Generate Jadwal Kereta Api");
            System.out.println("11. Lihat Jadwal Kereta Api");
            System.out.println("12. Lihat Pemasukan");
            System.out.println("0. Logout");
            System.out.print("Pilihan :");
            pilihan = scanner.nextInt();
            switch (pilihan){
                case 0:
                    LoginPage loginPage = new LoginPage();
                    loginPage.Logout();
                    break;
                case 1:
                    UserPage userPage = new UserPage();
                    userPage.showUpdatePenggunaPage();
                    break;
                case 2:
                    KotaPage kotaPage = new KotaPage();
                    kotaPage.showMenu();
                    break;
                case 3:
                    WaktuPage waktuPage = new WaktuPage();
                    waktuPage.generate();
                    break;
                case 4:
                    RutePage rutePage = new RutePage();
                    rutePage.showMenu();
                    break;
                case 5:
                    StasiunPage stasiunPage = new StasiunPage();
                    stasiunPage.showMenu();
                    break;
                case 6:
                    JalurRutePage jalurRutePage = new JalurRutePage();
                    jalurRutePage.showMenu();
                    break;
                case 7:
                    KeretaPage keretaPage = new KeretaPage();
                    keretaPage.showMenu();
                    break;
                case 8:
                    WaktuRutePage waktuRutePage = new WaktuRutePage();
                    waktuRutePage.showMenu();
                    break;
                case 9:
                    KeretaRutePage keretaRutePage = new KeretaRutePage();
                    keretaRutePage.showMenu(); 
                    break;
                case 11:
                    JadwalPage jadwalPage = new JadwalPage();
                    jadwalPage.showMenu();
                    break;
                case 12:
                    PemasukanPage pemasukanPage = new PemasukanPage();
                    pemasukanPage.showMenu();
                    break;
                default:
                    break;
            }
        }while(pilihan != 0); 
    }
}