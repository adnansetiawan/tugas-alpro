package tugasalpro;

import java.util.Scanner;

public class ProfilePenggunaPage
{
    private UserManager userManager;
    private Scanner scanner;
      
    public ProfilePenggunaPage() {
        userManager = new UserManager();
        scanner = new Scanner(System.in);
    }
    private boolean CheckUsername(String userName) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return userName.matches(regex);
    }

    private boolean CheckKtp(String ktp) {
        String regex = "^[0-9]{16}$";
        return ktp.matches(regex);
    }

    private boolean CheckHandphone(String handphone) {
        String regex = "^[0-9]{11,12}$";
        return handphone.matches(regex);
    }

    private boolean CheckName(String name) {
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
            ktpIsValid = CheckKtp(nomorKtp);
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
            namaLengkapIsValid = CheckName(nama);
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
            nomorHandphoneIsValid = CheckHandphone(nomorHp);
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
            emailIsValid = CheckUsername(userName);
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
       userManager.Add(new User(userName, password, userInfo));
       LoginPage loginPage =new LoginPage();
       loginPage.showWelcome();
       
    }
    public void ShowProfile(User user)
    {
        System.out.println("-- Data Pengguna --");
        System.out.println("No KTP :" +user.getUserInfo().geKtp());
        System.out.println("Nama Lengkap :" +user.getUserInfo().geName());
        System.out.println("Nomor Handphone :" +user.getUserInfo().geHandphone());
        System.out.println("Email :" +user.getUsername());
        System.out.println("Password :" +user.getPassword());
      
    }
    public void ShowUpdatePenggunaPage()
    {
        User user = ApplicationSession.getLoggedUser();
           
        if(user.isAdmin())
        {
            System.out.println("#KELOLA PROFILE BY ADMIN#");
      
        }else
        {
             System.out.println("#KELOLA PROFILE BY PENUMPANG#");
        }
        System.out.println("-- Ubah Data Pengguna --");
        String noKtp = null;
        if(user.isAdmin())
        {
            boolean isUserByKtpFound=false;
            User userByKtp= null; 
            do
            {
                noKtp = InputKtp();
                userByKtp = userManager.GetByKtp(noKtp);
                isUserByKtpFound = userByKtp != null;
                if(userByKtp == null)
                {
                    System.out.println("Tidak Ada Nomor KTP dalam sistem, silahkan coba lagi");
                }
             }while(!isUserByKtpFound);
             ShowProfile(userByKtp);

        }else
        {
            noKtp = user.getUserInfo().geKtp();
            ShowProfile(user);
        }
        System.out.println("#UBAH DATA PENGGUNA#");
        String nama = InputNama();
        String nomorHp = InputHandphone();
        String userName = InputEmail();
        String password = InputPassword(false);
        UserInfo userInfo = new UserInfo(nama, noKtp, nomorHp);
        User newUser = new User(userName, password, userInfo);
        userManager.Update(newUser);
        System.out.println("-- Data Berhasil Diupdate, Berikut Data Terbaru --");
        ShowProfile(newUser);
       
    }
}