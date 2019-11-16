package tugasalpro.views;

import java.util.Arrays;
import java.util.Scanner;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.Window.Hint;

import tugasalpro.managers.*;
import tugasalpro.models.*;
import tugasalpro.views.buttonevents.OnKembaliToMenuUtamaClicked;
import tugasalpro.*;
public class ProfilePenggunaPage extends BasePage
{
    private UserManager userManager;
    private Scanner scanner;
      
    public ProfilePenggunaPage() {
        userManager = new UserManager();
        scanner = new Scanner(System.in);
    }
    private boolean checkUsername(String userName) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return userName.matches(regex);
    }

    

    private boolean checkHandphone(String handphone) {
        String regex = "^[0-9]{11,12}$";
        return handphone.matches(regex);
    }

    private boolean checkName(String name) {
        String regex = "^[\\p{L} .'-]+$";
        return name.matches(regex);
    }

    private String InputKtp()
    {
        String nomorKtp;
        boolean ktpIsValid = false;
        scanner = new Scanner(System.in);
      
        do
        {
            System.out.print("Nomor KTP :");
            nomorKtp = scanner.nextLine();
            ktpIsValid = true;
            if(!ktpIsValid)
            {
                System.out.println("format ktp salah, hanya boleh angka dan 16 digit");
           
            }
        }while(!ktpIsValid);
        return nomorKtp;
    }
    private String InputNama()
    {
        String nama;
        boolean namaLengkapIsValid = false;
        scanner = new Scanner(System.in);
        do
        {
            System.out.print("Nama Lengkap :");
            nama = scanner.nextLine();
            namaLengkapIsValid = checkName(nama);
            if(!namaLengkapIsValid)
            {
                System.out.println("nama hanya boleh mengandung huruf dan spasi");
           
            }
        }while(!namaLengkapIsValid);
        return nama;
    }
    private String InputHandphone()
    {
        String nomorHp;
        boolean nomorHandphoneIsValid = false;
        scanner= new Scanner(System.in);
        do
        {
            System.out.print("Nomor Handphone :");
            nomorHp = scanner.nextLine();
            nomorHandphoneIsValid = checkHandphone(nomorHp);
            if(!nomorHandphoneIsValid)
            {
                System.out.println("format handphone salah, hanya boleh angka, min : 11 dan max: 12 digit");
           
            }
        }while(!nomorHandphoneIsValid);
        return nomorHp;
    }
    private String InputEmail()
    {
        String userName;
        boolean emailIsValid = false;
        scanner= new Scanner(System.in);
        do
        {
            System.out.print("Email :");
            userName = scanner.nextLine();
            emailIsValid = checkUsername(userName);
            if(!emailIsValid)
            {
                System.out.println("format email salah, silahkan coba lagi");
           
            }
        }while(!emailIsValid);
        return userName;
    }
    private String InputPassword(boolean retype)
    {
        String password;
        boolean passwordIsValid = false;
        scanner= new Scanner(System.in);
        do
        {
            System.out.print("Password :");
           
            password = scanner.nextLine();
            if(retype)
            {
                System.out.print("Re-Password :");
                String rePassword = scanner.nextLine();
                passwordIsValid = password.equals(rePassword);
            }else
            {
                passwordIsValid = true;
            }
            
        }while(!passwordIsValid);
        return password;
    }
    public void Registrasi()
    {
        System.out.println("#REGISTER SISTEM#");
       String nomorKtp =  InputKtp();
       String nama = InputNama();
       String nomorHp = InputHandphone();
       String userName = InputEmail();
       String password = InputPassword(true);
       UserInfo userInfo = new UserInfo(nama, nomorKtp, nomorHp);
       userManager.add(new User(userName, password, userInfo));
       LoginPage loginPage =new LoginPage();
       loginPage.showWelcome();
       
    }
    public void editDataPengguna(User user)
    {
       
        MultiWindowTextGUI gui = new MultiWindowTextGUI(getScreen(), new DefaultWindowManager(),
        new EmptySpace(TextColor.ANSI.BLUE));
 
        Panel panel = new Panel();
 
         panel.setLayoutManager(new GridLayout(2));
         addEmptySpace(panel, 2);
         panel.addComponent(new Label("No. Ktp      :"));
         final TextBox ktpTextBox = new TextBox(user.getUserInfo().getKtp());
         ktpTextBox.setPreferredSize(new TerminalSize(28,1));
         ktpTextBox.addTo(panel);
         addEmptySpace(panel, 2);
         panel.addComponent(new Label("Nama Lengkap :"));
         final TextBox namaLengkapTextBox = new TextBox(user.getUserInfo().getName());
         namaLengkapTextBox.setPreferredSize(new TerminalSize(28,1));
         namaLengkapTextBox.addTo(panel);
         addEmptySpace(panel, 2);
         panel.addComponent(new Label("No. Handphone:"));
         final TextBox handphoneTextBox = new TextBox(user.getUserInfo().getHandphone());
         handphoneTextBox.setPreferredSize(new TerminalSize(28,1));
         handphoneTextBox.addTo(panel);
         addEmptySpace(panel, 2);
         panel.addComponent(new Label("Email        :"));
         final TextBox emailTextBox = new TextBox(user.getUsername());
         emailTextBox.setPreferredSize(new TerminalSize(28,1));
         emailTextBox.addTo(panel);
         addEmptySpace(panel, 2);
         panel.addComponent(new Label("Password     :"));
         final TextBox passwordTextBox = new TextBox(user.getPassword());
         passwordTextBox.setMask('*');
         passwordTextBox.setPreferredSize(new TerminalSize(28,1));
         passwordTextBox.addTo(panel);

         addEmptySpace(panel, 2);
         addEmptySpace(panel, 2);
         Button btnBack =  new Button("Kembali",new OnKembaliToDataPenggunaClicked(user));
         btnBack.addTo(panel);
         Button btnOk =  new Button("Update", new OnPrepareProfileUpdateClicked(user));
         btnOk.addTo(panel);
         
         BasicWindow window = new BasicWindow("UPDATE DATA");
         // Create gui and start gui
         window.setComponent(panel);
         window.setHints(Arrays.asList(Hint.CENTERED));
        
         gui.addWindowAndWait(window);

    }
    public void enterNoKTP()
    {
        
        MultiWindowTextGUI gui = new MultiWindowTextGUI(getScreen(), new DefaultWindowManager(),
        new EmptySpace(TextColor.ANSI.BLUE));
 
        Panel panel = new Panel();
 
         panel.setLayoutManager(new GridLayout(2));
         addEmptySpace(panel, 2);
         panel.addComponent(new Label("No. Ktp      :"));
         final TextBox ktpTextBox = new TextBox();
         ktpTextBox.setPreferredSize(new TerminalSize(28,1));
         ktpTextBox.addTo(panel);
         addEmptySpace(panel, 2);
         Button btnCari =  new Button("Cari", new OnCariByKtpClicked(ktpTextBox));
         btnCari.addTo(panel);
         Button btnBack =  new Button("Kembali",new OnKembaliToMenuUtamaClicked(true));
         btnBack.addTo(panel);
        
         BasicWindow window = new BasicWindow("CARI PENGGUNA");
         // Create gui and start gui
         window.setComponent(panel);
         window.setHints(Arrays.asList(Hint.CENTERED));
        
         gui.addWindowAndWait(window);


    }
    public void showProfilePenggunaByUser(User user)
    {
        
        User loggedUser = ApplicationSession.getLoggedUser();
        if(!loggedUser.isAdmin())
        {
            user = loggedUser;
        }
        MultiWindowTextGUI gui = new MultiWindowTextGUI(getScreen(), new DefaultWindowManager(),
        new EmptySpace(TextColor.ANSI.BLUE));
 
        Panel panel = new Panel();
 
         panel.setLayoutManager(new GridLayout(2));
         addEmptySpace(panel, 2);
         panel.addComponent(new Label("No. Ktp      :"));
         panel.addComponent(new Label(user.getUserInfo().getKtp()));
         addEmptySpace(panel, 2);
         panel.addComponent(new Label("Nama Lengkap :"));
         panel.addComponent(new Label(user.getUserInfo().getName()));
         addEmptySpace(panel, 2);
         panel.addComponent(new Label("No. Handphone:"));
         panel.addComponent(new Label(user.getUserInfo().getHandphone()));
         addEmptySpace(panel, 2);
         panel.addComponent(new Label("Email        :"));
         panel.addComponent(new Label(user.getUsername()));
         addEmptySpace(panel, 2);
         panel.addComponent(new Label("Password     :"));
         String passwordMask = user.getPassword().replaceAll("(?s).", "*");
         panel.addComponent(new Label(passwordMask));
         

         addEmptySpace(panel, 2);
         addEmptySpace(panel, 2);
         if(loggedUser.isAdmin())
         {
            Button btnBack =  new Button("Kembali",new OnKembaliToCariClicked());
            btnBack.addTo(panel);
         }else
         {
            Button btnBack =  new Button("Kembali",new OnKembaliToMenuUtamaClicked(false));
            btnBack.addTo(panel);
         }
         Button btnOk =  new Button("Update", new OnPrepareProfileUpdateClicked(user));
         btnOk.addTo(panel);
        
         String titleLabel = "";
         if(loggedUser.isAdmin())
         {
            titleLabel = "KELOLA PROFILE BY ADMIN";
       
         }else
         {
            titleLabel = "KELOLA PROFILE BY PENUMPANG";
         }
         BasicWindow window = new BasicWindow(titleLabel);
         // Create gui and start gui
         window.setComponent(panel);
         window.setHints(Arrays.asList(Hint.CENTERED));
        
         gui.addWindowAndWait(window);


        /*System.out.println(""); 
        System.out.println("-- Ubah Data Pengguna --");
        String noKtp = null;
        if(user.isAdmin())
        {
            boolean isUserByKtpFound=false;
            User userByKtp= null; 
            do
            {
                noKtp = InputKtp();
                userByKtp = userManager.getByKtp(noKtp);
                isUserByKtpFound = userByKtp != null;
                if(userByKtp == null)
                {
                    System.out.println("Tidak Ada Nomor KTP dalam sistem, silahkan coba lagi");
                }
             }while(!isUserByKtpFound);
             System.out.println(""); 
             ShowProfile(userByKtp);

        }else
        {
            noKtp = user.getUserInfo().getKtp();
            System.out.println(""); 
            ShowProfile(user);
        }
        System.out.println("#UBAH DATA PENGGUNA#");
        System.out.println(""); 
        String nama = InputNama();
        String nomorHp = InputHandphone();
        String userName = InputEmail();
        String password = InputPassword(false);
        UserInfo userInfo = new UserInfo(nama, noKtp, nomorHp);
        User newUser = new User(userName, password, userInfo);
        userManager.update(newUser);
        if(!user.isAdmin())
        {
            ApplicationSession.setLoggedUser(user);
        }
        System.out.println(""); 
        System.out.println("-- Data Berhasil Diupdate, Berikut Data Terbaru --");
        System.out.println(""); 
        ShowProfile(newUser);*/
       
    }
}
class OnPrepareProfileUpdateClicked implements Runnable {
    
    private User userData;
    public OnPrepareProfileUpdateClicked(User userData) {
        this.userData = userData;
    }

    @Override
    public void run() {
       ProfilePenggunaPage profilePenggunaPage = new ProfilePenggunaPage();
       profilePenggunaPage.editDataPengguna(this.userData);
    }
}
class OnKembaliToDataPenggunaClicked implements Runnable {
    
    private User userData;
   
    public OnKembaliToDataPenggunaClicked(User userData) {
        this.userData= userData;
    }

    @Override
    public void run() {
       ProfilePenggunaPage profilePenggunaPage = new ProfilePenggunaPage();
       profilePenggunaPage.showProfilePenggunaByUser(userData);
    }
}
class OnKembaliToCariClicked implements Runnable {
    public OnKembaliToCariClicked() {
    }

    @Override
    public void run() {
            ProfilePenggunaPage profilePenggunaPage = new ProfilePenggunaPage();
            profilePenggunaPage.enterNoKTP();
        
    }
}
class OnCariByKtpClicked implements Runnable {
    
    private TextBox ktpTextBox;
    private UserManager userManager;
    public OnCariByKtpClicked(TextBox ktpTextBox) {
        this.ktpTextBox = ktpTextBox;
        this.userManager = new UserManager();
    }

    @Override
    public void run() {
        boolean ktpIsValid = checkKtp(this.ktpTextBox.getText());
        if(!ktpIsValid)
        {
            System.out.println("format ktp salah, hanya boleh angka dan 16 digit");
       
        }else
        {
            ProfilePenggunaPage profilePenggunaPage = new ProfilePenggunaPage();
            User user = userManager.getByKtp(this.ktpTextBox.getText());
            profilePenggunaPage.showProfilePenggunaByUser(user);
        }
    }
    private boolean checkKtp(String ktp) {
        String regex = "^[0-9]{16}$";
        return ktp.matches(regex);
    }
}