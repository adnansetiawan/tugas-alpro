package tugasalpro;

import java.util.List;
import java.util.Scanner;
import java.lang.String;

public class StasiunMenu{
    private StasiunManager stasiunManager;
    Scanner scanner;
      
    public StasiunMenu(){
        stasiunManager = new StasiunManager();
        scanner = new Scanner(System.in);
    }

    public void showStasiunMainMenu(){
        User user = ApplicationSession.getLoggedUser();
        System.out.println("#KELOLA DATA STASIUN#");
        showStasiunMenu();
    }

    public void showStasiunMenu(){
        System.out.println("\n1.  Tambah Data Stasiun");
        System.out.println("2.  Lihat Data Stasiun");
        System.out.println("3.  Edit Data Stasiun");
        System.out.println("4.  Delete Data Stasiun");
        System.out.println("99. Menu Utama\n");
        System.out.print("Pilihan : ");
        int pilihan = scanner.nextInt();
        switch(pilihan){
            case 1:
                showTambahDataStasiun();
                break;
            case 2:
                //Lihat Data Stasiun
                break;
            case 3:
                //Edit Data Stasiun
                break;
            case 4:
                //Delete Data Stasiun
                break;
            case 99:
                MenuManager menu = new MenuManager();
                menu.ShowMenuAdmin();
                break;
        }
    }

    public void showTambahDataStasiun(){
        System.out.println("#TAMBAH DATA STASIUN#");
        System.out.print("Tambah Data Stasiun : ");
        String masukan = scanner.nextLine();
        System.out.println("----------------------------------------");
        /*String input[] = foo.split(" ",2);
        if (input[1]!=null){
            Stasiun stasiun = new Stasiun(input[0],input[1]);
            System.out.println("Stasiun Berhasil Ditambahkan");
        }else{
            System.out.println("Stasiun Gagal Ditambahkan");
        }
        System.out.println("----------------------------------------");
        showStasiunMenu();*/
    }
    
    public void showDataStasiun(){
        System.out.println("Data Lengkap Stasiun");
        System.out.println("----------------------------------------");
        List<Stasiun> listStasiun = stasiunManager.getAll();
        for(int i=0;i<stasiunManager.getAll().size();i++){
            //System.out.println("%-5d%-25d%-50d",i+1,listStasiun[i].getKodeStasiun(),listStasiun[i].getNamaStasiun());
        }
        System.out.println("\n----------------------------------------");
    }

    public void showLihatDataStasiun(){
        System.out.println("#LIHAT DATA STASIUN#");
        showDataStasiun();
        showStasiunMenu();
    }

    public void showEditDataStasiun(){
        System.out.println("#EDIT DATA STASIUN#");
        showDataStasiun();
        System.out.print("Edit Stasiun : ");
        String masukan = scanner.nextLine();
        /*String input[] = foo.split("_",2);
        if (input[0].equals("EDIT")){
            Stasiun stasiun = stasiunManager.getByKodeStasiun(input[1]);
            if(stasiun!=null){
                System.out.print("Kode Stasiun : ");
                String kK = scanner.nextLine();
                System.out.print("Nama Stasiun : ");
                String nK = scanner.nextLine();
                Stasiun stn = new Stasiun(kK,nK);
                //stasiunManager.edit(input[1],stn);
                System.out.println("Stasiun Berhasil Diedit");
            }else{
                System.out.println("Kode Stasiun Tidak Ditemukan, Stasiun Gagal Diedit");
            }
        }else{
            System.out.println("Format Salah, Stasiun Gagal Diedit");
        }
        showStasiunMenu();*/
    }

    public void showDeleteDataStasiun(){
        showDataStasiun();
        System.out.print("Delete Stasiun : ");
        String masukan = scanner.nextLine();
        /*String input[] = foo.split("_",2);
        if (input[0].equals("DELETE")){
            Stasiun stasiun = stasiunManager.getByKodeStasiun(input[1]);
            if(stasiun!=null){
                stasiunManager.delete(input[1]);
                System.out.println("Stasiun Berhasil Dihapus");
            }else{
                System.out.println("Kode Stasiun Tidak Ditemukan, Stasiun Gagal Dihapus");
            }
        }else{
            System.out.println("Format Salah, Stasiun Gagal Dihapus");
        }*/

    }
}