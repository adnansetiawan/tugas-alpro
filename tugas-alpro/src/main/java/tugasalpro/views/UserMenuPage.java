package tugasalpro.views;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.Window.Hint;
import com.googlecode.lanterna.*;

import tugasalpro.*;
import tugasalpro.models.*;

public class UserMenuPage {
    private Scanner scanner;

    public UserMenuPage() {
        scanner = new Scanner(System.in);

    }

    public void ShowMenuPengguna() {
        User user = ApplicationSession.getLoggedUser();
        System.out.println("#MENU PEGGUNA#");
        System.out.println("Welcome," + user.getUsername());
        System.out.println("1. Booking Tiket");
        System.out.println("2. Kelola Profile");
        System.out.println("3. History Pembelian");
        System.out.println("0. Logout");
        int pilihan = -1;
        do {
            System.out.print("Pilihan :");
            pilihan = scanner.nextInt();
        } while (pilihan < 0 || pilihan > 3);
        switch (pilihan) {
            case 0:
            LoginPage loginPage = new LoginPage();
            loginPage.Logout();
            break;
        case 2:
            ProfilePenggunaPage profilePenggunaPage = new ProfilePenggunaPage();
            profilePenggunaPage.ShowUpdatePenggunaPage();
            break;
        }

    }
    private void addEmptySpace(Panel panel, int number)
    {
        for(int i=0;i<number;i++)
        {
            panel.addComponent(new EmptySpace());

        }
    }
    public void ShowMenuAdmin()
    {
        
        int pilihan = -1;
        // Create panel to hold components
        MultiWindowTextGUI gui = new MultiWindowTextGUI(PageComponent.getScreen(), new DefaultWindowManager(),
        new EmptySpace(TextColor.ANSI.BLUE));

        Panel panel = new Panel();

        panel.setLayoutManager(new GridLayout(1));
        addEmptySpace(panel, 1);
        panel.addComponent(new Label("1. Kelola Account"));
        panel.addComponent(new Label("2. Kelola Data Kota"));
        panel.addComponent(new Label("3. Generate Waktu"));
        panel.addComponent(new Label("4. Kelola Rute"));
       
        addEmptySpace(panel, 1);

        Panel panel2 = new Panel();
        panel2.setLayoutManager(new GridLayout(3));

        panel2.addComponent(new Label("Pilih    :"));
        panel2.addComponent(AnimatedLabel.createClassicSpinningLine());

        final TextBox pilihanText = new TextBox().setValidationPattern(Pattern.compile("[0-9]*"));
        pilihanText.addTo(panel2);
        addEmptySpace(panel2, 5);

        new Button("OK").addTo(panel2);
        panel.addComponent(panel2);

        // Create window to hold the panel
        BasicWindow window = new BasicWindow("WELCOME ADMIN");
        // Create gui and start gui
        window.setComponent(panel);
        window.setHints(Arrays.asList(Hint.CENTERED));
       
        gui.addWindowAndWait(window);
        /*do
        {
            System.out.println("#MENU ADMIN#");
            System.out.println("Welcome, Admin");
            System.out.println("1. Kelola Akun");
            System.out.println("2. Kelola Data Kota");
            System.out.println("3. Generate Waktu");
            System.out.println("4. Kelola Rute");
            System.out.println("5. Kelola Stasiun");
            System.out.println("6. Kelola Jalur Stasiun Pada Rute");
            System.out.println("7. Kelola Waktu Pada Rute");
            System.out.println("8. Kelola Kereta Pada Rute");
            System.out.println("9. Generate Jadwal Kereta Api");
            System.out.println("10. Lihat Pemasukan");
            System.out.println("11. Lihat Jadwal Kereta Api");
            System.out.println("12. Kelola Kereta");
            System.out.println("0. Logout");
            System.out.print("Pilihan :");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 0:
                
                    LoginPage loginPage = new LoginPage();
                    loginPage.Logout();
                    break;
                case 1:
                    ProfilePenggunaPage profilePenggunaPage = new ProfilePenggunaPage();
                    profilePenggunaPage.ShowUpdatePenggunaPage();
                    break;
                case 2:
                    KotaPage kotaPage = new KotaPage();
                    kotaPage.showMenu();
                    break;
                case 3:
                    
                    break;
                case 4:
                    RutePage rutePage = new RutePage();
                    rutePage.showMenu();
                    break;
                case 5:
                    
                    break;
                case 6:
                    
                    break;
                case 7:
                    
                    break;
                case 8:
                    KeretaRutePage keretaRutePage = new KeretaRutePage();
                    keretaRutePage.showMenu();
                    break;
                 case 12:
                    KeretaPage keretaPage = new KeretaPage();
                    keretaPage.showMenu();
                    break;
            
                default:
                    break;
            }

        }while(pilihan != 0);*/
      
    }

}