package tugasalpro.views;

import java.util.List;
import java.util.Scanner;
import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import tugasalpro.utilities.ScreenUtility;

import tugasalpro.managers.KeretaManager;
import tugasalpro.models.Kereta;


public class KeretaPage {
    private final KeretaManager keretaManager;
    Scanner scanner;

    public KeretaPage() {
        keretaManager = new KeretaManager();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        ScreenUtility.ClearScreen();
        System.out.println("\n#KELOLA DATA KERETA API#");
        int pilihan;
        do {
            System.out.println("\n1. Tambah Data Kereta Api");
            System.out.println("2. Lihat Data Kereta Api");
            System.out.println("3. Edit Data Kereta Api");
            System.out.println("4. Delete Data Kereta Api");
            System.out.println("99. Menu Utama\n");
            System.out.print("Pilihan : ");
            pilihan = scanner.nextInt();
            switch(pilihan) {
                case 1:
                    menuTambah();
                    break;
                case 2:
                    showMenuTampil();
                    break;
                case 3:
                    menuUbah();
                    break;
                case 4:
                    menuHapus();
                    break;
                case 99:
                    MenuPage menuPage = new MenuPage();
                    menuPage.showMenuAdmin();
                    break;
            }
        }while(pilihan!=99);
    }
    
    public void menuTambah() {
        ScreenUtility.ClearScreen();
        //needed to prevent scanner nextInt behaviour not consumin nextLine, more info at https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo
        scanner.nextLine();

        System.out.println("#TAMBAH DATA KERETA API#");
        Kereta kereta = new Kereta();
        String kodeKereta = null;
        String namaKereta = null;
        int jmlGerbong = 0;
        int jmlGBisnis = 0;
        int jmlGPremium = 0;
        boolean flagIterate = true;
        
        do{
            System.out.print("Tambah Data Kereta Api: ");
            String dataKereta = scanner.nextLine();
            String[] dataKeretas = dataKereta.split("'");
            if(dataKereta.equals("99")){
                System.out.println("Penambahan kereta dibatalkan");
                flagIterate=false;
            }
            else{
                try{
                    kodeKereta = dataKeretas[0].trim();
                    namaKereta = dataKeretas[1];
    
                    String[] others = dataKeretas[2].split(" ");
                    
                    jmlGerbong = Integer.parseInt(others[1].substring(1, others[1].toCharArray().length));
                    jmlGBisnis = Integer.parseInt(others[2].substring(1, others[2].toCharArray().length));
                    jmlGPremium = Integer.parseInt(others[3].substring(1, others[3].toCharArray().length));
                    
                    if(jmlGerbong == (jmlGBisnis + jmlGPremium)) {
                        if(keretaManager.getIndexByKodeKereta(kodeKereta)!=-1){
                            System.out.println("Kereta dengan kode kereta "+kodeKereta+" sudah ada.");
                            System.out.println("Mohon masukkan kode kereta yang berbeda, atau “99” untuk membatalkan penambahan kereta.");
                        }
                        else{
                            if(keretaManager.getIndexByNamaKereta(namaKereta)!=-1){
                                System.out.println("Kereta dengan nama kereta "+namaKereta+" sudah ada.");
                                System.out.println("Mohon masukkan nama kereta yang berbeda, atau “99” untuk membatalkan penambahan kereta.");
                            }
                            else{
                                kereta.setKodeKereta(kodeKereta);
                                kereta.setNamaKereta(namaKereta);
                                kereta.jmlGerbong(jmlGerbong);
                                kereta.jmlGBisnis(jmlGBisnis);
                                kereta.jmlGPremium(jmlGPremium);
                                
                                keretaManager.add(kereta);
                                System.out.println("Kereta Api Berhasil Ditambahkan");

                                flagIterate = false;
                            }
                        }
                    }
                    else{
                        System.out.println("Jumlah gerbong tidak sesuai");
                        flagIterate = true;
                    }
                }
                catch(Exception ex) {
                    System.out.println("Masukan tidak sesuai format: KodeKAI 'NamaKereta' G6 B4 P2 \n Mohon coba lagi dengan format yang sesuai, atau “99” untuk membatalkan penambahan kereta.");
                    flagIterate = true;
                }
            }
        } while(flagIterate);
    } 

   public void menuTampil() {
        System.out.println("Data Lengkap Kereta Api");
        AsciiTable at = new AsciiTable();
        at.addRule();
        // AT_Row rowTitle =  at.addRow(null, null, null, null, null, "Data Lengkap Kereta Api");
        // rowTitle.setTextAlignment(TextAlignment.CENTER);
        // at.addRule();
        AT_Row row =  at.addRow("No", "Kode Kereta", "Nama Kereta", "Gerbong", "Binis", "Premium");
        row.setTextAlignment(TextAlignment.CENTER);
        at.addRule();

        List<Kereta> listKereta = keretaManager.getAll();

        int no = 1;
        for(Kereta kereta : listKereta) {
            AT_Row contRow =  at.addRow(String.valueOf(no), kereta.getKodeKereta(), kereta.getNamaKereta(), kereta.getJmlGerbong(), kereta.getJmlGBisnis(), kereta.getJmlGPremium());
            at.addRule();
            contRow.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
            contRow.getCells().get(1).getContext().setTextAlignment(TextAlignment.CENTER);
            contRow.getCells().get(3).getContext().setTextAlignment(TextAlignment.CENTER);
            contRow.getCells().get(4).getContext().setTextAlignment(TextAlignment.CENTER);
            contRow.getCells().get(5).getContext().setTextAlignment(TextAlignment.CENTER);
            no++;
        }
        at.setPaddingLeft(1);
        at.setPaddingRight(1);
        CWC_LongestLine cwc = new CWC_LongestLine();
        cwc.add(4, 0).add(20, 0).add(40, 0).add(10, 0).add(10, 0).add(10, 0);
        at.getRenderer().setCWC(cwc);
        System.out.println(at.render());
    }

    public void showMenuTampil() {
        ScreenUtility.ClearScreen();
        System.out.println("#LIHAT DATA KERETA API#");
        menuTampil();
        showMenu();
    }

    public void menuUbah() {
        ScreenUtility.ClearScreen();
        System.out.println("#UBAH DATA KERETA API#");
        menuTampil();
        String kodeKereta = null;
        Kereta kereta = null;
        Kereta newKereta = null;
        boolean flagIterate = true;

        String namaKereta;
        int jmlGerbong, jmlGBisnis, jmlGPremium;
        do {
            System.out.print("Edit Kereta Api : ");
            kodeKereta = scanner.next();

            if(!kodeKereta.equals("99")) {
                    
                    kereta = keretaManager.getByKodeKereta(kodeKereta);
                    if(kereta!=null){
                        scanner.nextLine();
                        newKereta = new Kereta();
                        System.out.print("Kode Kereta : ");
                        kodeKereta = scanner.next();
                        System.out.print("Nama Kereta : ");
                        scanner.nextLine();
                        namaKereta = scanner.nextLine();
                        System.out.print("Jumlah Gerbong : ");
                        jmlGerbong = scanner.nextInt();
                        System.out.print("Jumlah Gerbong Bussiness : ");
                        jmlGBisnis = scanner.nextInt();
                        System.out.print("Jumlah Gerbong Premium: ");
                        jmlGPremium = scanner.nextInt();

                        if(keretaManager.getIndexByKodeKereta(kodeKereta)!=-1){
                            System.out.println("Kereta dengan kode kereta "+kodeKereta+" sudah ada.");
                            System.out.println("Mohon masukkan kode kereta yang berbeda, atau “99” untuk membatalkan pengubahan kereta.");
                        }
                        else{
                            if(keretaManager.getIndexByNamaKereta(namaKereta)!=-1){
                                System.out.println("Kereta dengan nama kereta "+namaKereta+" sudah ada.");
                                System.out.println("Mohon masukkan nama kereta yang berbeda, atau “99” untuk membatalkan pengubahan kereta.");
                            }
                            else{
                                if(jmlGerbong == (jmlGBisnis + jmlGPremium)){
                                    newKereta.setKodeKereta(kodeKereta);
                                    newKereta.setNamaKereta(namaKereta);
                                    newKereta.jmlGerbong(jmlGerbong);
                                    newKereta.jmlGBisnis(jmlGBisnis);
                                    newKereta.jmlGPremium(jmlGPremium);
        
                                    keretaManager.delete(kereta);
                                    keretaManager.add(newKereta);
                                   
                                    System.out.println("Kereta Api Berhasil Diubah");
                                    flagIterate = false;
                                }
                                else{
                                    System.out.println("Jumlah Gerbong Tidak Sesuai");  
                                    flagIterate = true;                     
                                }
                            }
                        }
                    }
                    else{
                        System.out.println("Kereta dengan kode kereta "+kodeKereta+" tidak ada. Mohon masukkan kode kereta yang berbeda, atau “99” untuk membatalkan perubahan kereta");
                        flagIterate = true;
                    }
            }
            else{
                System.out.println("Pengubahan kereta dibatalkan.");
                flagIterate = false;
            }
        } while (flagIterate);

        showMenu();
    }    

    void menuHapus() {
        ScreenUtility.ClearScreen();
        System.out.println("#HAPUS DATA KERETA API#");
        menuTampil();
        String kodeKereta = null;
        Kereta delKereta = null;
        boolean flagIterate = true;
        do {
            System.out.print("Hapus Kereta Api: ");
            kodeKereta = scanner.next();
            if(!kodeKereta.equals("99")) {
               
                    if(keretaManager.getIndexByKodeKereta(kodeKereta)==-1){
                        System.out.println("Kereta dengan kode kereta "+kodeKereta+" tidak ada.");
                        System.out.println("Mohon masukkan kode kereta yang berbeda, atau “99” untuk membatalkan penghapusan kereta.");
                        flagIterate = true;
                    }
                    else{
                        delKereta = keretaManager.getByKodeKereta(kodeKereta);
                        keretaManager.delete(delKereta);
                        System.out.println("Kereta "+kodeKereta+" berhasil dihapus");
                        flagIterate = false;
                    }
            }else{
                System.out.println("Penghapusan dibatalkan.");
                flagIterate=false;
            }
        } while (flagIterate);
    }
}