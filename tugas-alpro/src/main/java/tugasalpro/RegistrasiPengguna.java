package tugasalpro;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class RegistrasiPengguna {
    private UserManager userManager;

    public RegistrasiPengguna() {
        userManager = new UserManager();
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

    public void Registrasi() throws IOException, URISyntaxException
    {
        System.out.println("#REGISTER SISTEM#");

        String userName;
        String nomorKtp;
        String nomorHp;
        String nama;
        String password;
        Scanner scanner = new Scanner(System.in);
        boolean ktpIsValid = false;
        boolean namaLengkapIsValid = false;
        boolean nomorHandphoneIsValid = false;
        boolean emailIsValid = false;
        boolean passwordIsValid = false;

        do
        {
            System.out.print("Nomor KTP :");
            nomorKtp = scanner.nextLine();
            ktpIsValid = CheckKtp(nomorKtp);
        }while(!ktpIsValid);
        do
        {
            System.out.print("Nama Lengkap :");
            nama = scanner.nextLine();
            namaLengkapIsValid = CheckName(nama);
        }while(!namaLengkapIsValid);
        do
        {
            System.out.print("Nomor Handphone :");
            nomorHp = scanner.nextLine();
            nomorHandphoneIsValid = CheckHandphone(nomorHp);
            
        }while(!nomorHandphoneIsValid);
        do
        {
            System.out.print("Email :");
            userName = scanner.nextLine();
            emailIsValid = CheckUsername(userName);
            
        }while(!emailIsValid);
        do
        {
            System.out.print("Password :");
           
            password = scanner.nextLine();
            System.out.print("Re-Password :");
            String rePassword = scanner.nextLine();
            passwordIsValid = password.equals(rePassword);
            
        }while(!passwordIsValid);
       if(emailIsValid && namaLengkapIsValid && nomorHandphoneIsValid
       && ktpIsValid && passwordIsValid)
       {
           UserInfo userInfo = new UserInfo(nama, nomorKtp, nomorHp);
           userManager.Save(new User(userName, password, userInfo));
       }
       
    }
}