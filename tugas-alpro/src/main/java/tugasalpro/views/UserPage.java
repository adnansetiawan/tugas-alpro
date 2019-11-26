package tugasalpro.views;

import java.util.Scanner;
import tugasalpro.managers.*;
import tugasalpro.models.*;
import tugasalpro.utilities.ScreenUtility;
import tugasalpro.*;

public class UserPage{
    private UserManager userManager;
    private Scanner scanner;
    private String userNameLama;
    
    public UserPage(){
        userManager = new UserManager();
        scanner = new Scanner(System.in);
    }
    
    private boolean cekUsername(String userName){
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return userName.matches(regex);
    }

    private boolean cekKtp(String ktp){
        String regex = "^[0-9]{16}$";
        return ktp.matches(regex);
    }

    private boolean cekHandphone(String handphone){
        String regex = "^[0-9]{11,12}$";
        return handphone.matches(regex);
    }

    private boolean cekName(String name){
        String regex = "^[\\p{L} .'-]+$";
        return name.matches(regex);
    }

    private String inputKtp(){
        String nomorKtp;
        boolean isKtpValid=false;
        scanner = new Scanner(System.in);
        do{
            System.out.print("Nomor KTP : ");
            nomorKtp = scanner.nextLine();
            //pengecekan apakah KTP duplikat atau tidak
            User userByKtp= null; 
            userByKtp = userManager.getByKtp(nomorKtp);
            if(userByKtp != null){
                System.out.println("Nomor KTP tersebut sudah terdaftar.");
                System.out.println("Mohon masukkan nomor KTP yang berbeda.");
            }else{//akhir pengecekan
                isKtpValid= cekKtp(nomorKtp);
                if(!isKtpValid){
                    System.out.println("Format KTP salah. Nomor KTP hanya boleh angka dan 16 digit.");
                    System.out.println("Mohon masukkan nomor KTP dengan format yang benar.");
                }
            }
        }while(!isKtpValid);
        return nomorKtp;
    }
    
    private String inputNama(){
        String nama;
        boolean namaLengkapIsValid = false;
        scanner = new Scanner(System.in);
        do{
            System.out.print("Nama Lengkap : ");
            nama = scanner.nextLine();
            namaLengkapIsValid = cekName(nama);
            if(!namaLengkapIsValid){
                System.out.println("Format nama salah. Nama hanya boleh mengandung huruf dan spasi.");
                System.out.println("Mohon masukkan nama dengan format yang benar.");
            }
        }while(!namaLengkapIsValid);
        return nama;
    }

    private String inputHandphone(){
        String nomorHp;
        boolean nomorHandphoneIsValid = false;
        scanner= new Scanner(System.in);
        do{
            System.out.print("Nomor Handphone : ");
            nomorHp = scanner.nextLine();
            nomorHandphoneIsValid = cekHandphone(nomorHp);
            if(!nomorHandphoneIsValid){
                System.out.println("Format nomor handphone salah. Nomor handphone hanya boleh mengandung 11-12 digit angka.");
                System.out.println("Mohon masukkan nomor handphone dengan format yang benar.");
            }
        }while(!nomorHandphoneIsValid);
        return nomorHp;
    }
    
    private String inputEmail(){
        String userName;
        boolean emailIsValid = false;
        scanner= new Scanner(System.in);
        do{
            System.out.print("Alamat Email : ");
            userName = scanner.nextLine();
            //Pengecekan email sama atau berbeda
            if (userNameLama.compareTo(userName)!=0){
                //Pengecekan email di JSON
                User userByEmail; 
                userByEmail = userManager.getByEmail(userName);
                if(userByEmail != null){
                    System.out.println("Alamat email tersebut sudah terdaftar.");
                    System.out.println("Mohon masukkan alamat email yang berbeda.");
                }else{
                    emailIsValid = cekUsername(userName);
                    if(!emailIsValid){
                        System.out.println("Format alamat email salah.");
                        System.out.println("Mohon masukkan alamat email dengan format yang benar.");
                    }
                }
            }else{
                emailIsValid = cekUsername(userName);
                if(!emailIsValid){
                    System.out.println("Format alamat email salah.");
                    System.out.println("Mohon masukkan alamat email dengan format yang benar.");
                }
            }
        }while(!emailIsValid);
        return userName;
    }

    private String inputPassword(boolean retype){
        String password;
        boolean passwordIsValid = false;
        scanner= new Scanner(System.in);
        do{
            System.out.print("Password : ");
           
            password = scanner.nextLine();
            if(retype){
                System.out.print("Re-Password : ");
                String rePassword = scanner.nextLine();
                passwordIsValid = password.equals(rePassword);
            }else{
                passwordIsValid = true;
            }
        }while(!passwordIsValid);
        return password;
    }

    public void registrasi(){
        ScreenUtility.ClearScreen();
        userNameLama = "";
        System.out.println("#REGISTER SISTEM#");
        String nomorKtp =  inputKtp();
        String nama = inputNama();
        String nomorHp = inputHandphone();
        String userName = inputEmail();
        String password = inputPassword(true);
        UserInfo userInfo = new UserInfo(nama, nomorKtp, nomorHp);
        userManager.add(new User(userName, password, userInfo));
        LoginPage loginPage =new LoginPage();
        loginPage.showWelcome();
    }

    public void showProfil(User user){
        System.out.println("-- Data Pengguna --");
        System.out.println("No KTP : " +user.getUserInfo().getKtp());
        System.out.println("Nama Lengkap : " +user.getUserInfo().getName());
        System.out.println("Nomor Handphone : " +user.getUserInfo().getHandphone());
        System.out.println("Alamat Email : " +user.getUsername());
        System.out.println("Password : " +user.getPassword());
        userNameLama = user.getUsername();
    }

    public void showUpdatePenggunaPage(){
        boolean flagIterate=true;
        User user = ApplicationSession.getLoggedUser();
        if(user.isAdmin()){
            System.out.println("#KELOLA PROFILE BY ADMIN#");
        }else{
            System.out.println("#KELOLA PROFILE BY PENUMPANG#");
        }
        System.out.println(""); 
        System.out.println("-- Ubah Data Pengguna --");
        String noKtp = null;
        if(user.isAdmin()){
            User userByKtp= null; 
            do{
                noKtp = inputKtp();
                userByKtp = userManager.getByKtp(noKtp);
                if(userByKtp == null){
                    System.out.println("Nomer KTP tersebut tidak ada dalam sistem.");
                    System.out.println("Masukkan nomor KTP yang berbeda, atau 99 untuk membatalkan pengubahan data pengguna.");
                }else{
                    flagIterate=false;
                }
            }while(flagIterate);
            System.out.println(""); 
            showProfil(userByKtp);
        }else{
            noKtp = user.getUserInfo().getKtp();
            System.out.println(""); 
            showProfil(user);
        }
        System.out.println("#UBAH DATA PENGGUNA#");
        System.out.println(""); 
        String nama = inputNama();
        String nomorHp = inputHandphone();
        String userName = inputEmail();
        String password = inputPassword(false);
        UserInfo userInfo = new UserInfo(nama, noKtp, nomorHp);
        User newUser = new User(userName, password, userInfo);
        userManager.update(newUser);
        if(!user.isAdmin()){
            ApplicationSession.setLoggedUser(user);
        }
        System.out.println(""); 
        System.out.println("-- Data Berhasil Diupdate, Berikut Data Terbaru --");
        System.out.println(""); 
        showProfil(newUser);
    }
}