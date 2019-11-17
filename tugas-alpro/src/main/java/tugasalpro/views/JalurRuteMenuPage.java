package tugasalpro.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.Window.Hint;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialogBuilder;
import com.googlecode.lanterna.gui2.table.*;

import tugasalpro.*;
import tugasalpro.models.*;
import tugasalpro.managers.*;

public class JalurRuteMenuPage extends BasePage {
    private  JalurRuteManager jalurRuteManager;
    private  RuteManager ruteManager;
    private  StasiunManager stasiunManager;
    Scanner scanner;
    
    public JalurRuteMenuPage(){
        jalurRuteManager=new JalurRuteManager();
        stasiunManager=new StasiunManager();
        scanner=new Scanner(System.in);
    }
    private void generateListJalurRute(WindowBasedTextGUI gui, Panel mainPanel)
    {
         mainPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
         addEmptySpace(mainPanel, 1);
         Table<String> table = new Table<String>("No", "KODE JALUR", "KODE RUTE", "JALUR YG DILEWATI", "WAKTU TEMPUH");
         table.addTo(mainPanel);
         table.setSelectAction(new Runnable() {
             @Override
             public void run() {
                 List<String> data = table.getTableModel().getRow(table.getSelectedRow());
                 JalurRute jalurRute = jalurRuteManager.getByKodeJalur(data.get(1));
                      String result =   new TextInputDialogBuilder()
                     .setTitle("Pilih")
                     .setDescription("EDIT/DELETE")
                     .setValidationPattern(Pattern.compile("(EDIT|DELETE){1}"), "input tidak valid")
                     .build()
                     .showDialog(gui);
                     switch(result)
                     {
                         case "EDIT":
                            // menuUbah(stasiun);
                             break;
                         case "DELETE":
                             jalurRuteManager.delete(jalurRute);
                             MessageDialog.showMessageDialog(gui, "Success", "data jalur rute berhasil dihapus", MessageDialogButton.OK);
                             menuTampil();
                             break;
                     }
                 
              
             }
         });
         List<JalurRute> listJalurRute = jalurRuteManager.getAll();
         if(listJalurRute.size() > 0)
         {
             int no = 0;
             for (JalurRute jalurRute : listJalurRute) {
                 no++;
                 //table.getTableModel().addRow(String.valueOf(no), stasiun.getKodeStasiun(), stasiun.getNamaStasiun());
             }
             
         }   
        }
    
   
    public void menuTambah(){
        System.out.println("\n#TAMBAH JALUR STASIUN PADA RUTE#");
        JalurRute jalurRute = new JalurRute();
        String kodeJalur;
        do{
            System.out.print("Kode Jalur : ");
            kodeJalur=scanner.next();
            if(jalurRuteManager.getIndexByKodeJalur(kodeJalur)!=-1){
                System.out.println("Kode Jalur Tidak Tersedia");
            }
        }while(jalurRuteManager.getIndexByKodeJalur(kodeJalur)!=-1);
        jalurRute.setKodeJalur(kodeJalur);
        String kodeRute=null;
        /*do{
            System.out.print("Kode Rute : ");
            kodeRute=scanner.next();
            if(ruteManager.GetIndexByKodeRute(kodeRute)==-1){
                System.out.println("Kode Rute Tidak Tersedia");
            }
        }while(ruteManager.GetIndexByKodeRute(kodeRute)==-1);*/
        jalurRute.setRuteJalur(ruteManager.GetByKodeRute(kodeRute));
        System.out.println("Stasiun Awal Sampai Stasiun Akhir\n----------------------------------------");
        JalurStasiun jalurStasiun = new JalurStasiun();
        String namaStasiunAsal, namaStasiunTujuan;
        int i = 1, durasiJalurStasiun;
        do{
            System.out.print("Jalur "+i+" : ");
            namaStasiunAsal = scanner.next();
            if (!namaStasiunAsal.equals("99")){
                namaStasiunTujuan=scanner.next();
                durasiJalurStasiun=scanner.nextInt();
                while(stasiunManager.getIndexByNamaStasiun(namaStasiunAsal)==-1||stasiunManager.getIndexByNamaStasiun(namaStasiunTujuan)==-1){
                    if(stasiunManager.getIndexByNamaStasiun(namaStasiunAsal)==-1){
                        System.out.println("Stasiun Asal Tidak Tersedia");
                    }
                    if(stasiunManager.getIndexByNamaStasiun(namaStasiunTujuan)==-1){
                        System.out.println("Stasiun Tujuan Tidak Tersedia");
                    }
                    System.out.print("Jalur "+i+" : ");
                    namaStasiunAsal=scanner.next();
                    namaStasiunTujuan=scanner.next();
                    durasiJalurStasiun=scanner.nextInt();
                }
                jalurStasiun.setStasiunAsal(stasiunManager.getByNamaStasiun(namaStasiunAsal));
                jalurStasiun.setStasiunTujuan(stasiunManager.getByNamaStasiun(namaStasiunTujuan));
                jalurStasiun.setDurasi(durasiJalurStasiun);
                jalurRute.addJalurStasiun(jalurStasiun);
                i++;
            }
        }while (!namaStasiunAsal.equals("99"));
        jalurRuteManager.add(jalurRute);
        System.out.println("----------------------------------------");
        System.out.println("Jalur Stasiun yang dilewati berdasarkan Rute Berhasil Ditambahkan");
        System.out.println("----------------------------------------");
        //showMenu();
    }

    public void menuTampil()
    {
        WindowBasedTextGUI gui = new MultiWindowTextGUI(getScreen(), new DefaultWindowManager(),
        new EmptySpace(TextColor.ANSI.BLUE));


        Panel mainPanel = new Panel();
        Panel listPanel = new Panel();
        mainPanel.addComponent(listPanel.withBorder(Borders.singleLine()));
        generateListJalurRute(gui,listPanel);
        addEmptySpace(mainPanel, 1);
        Panel panel = new Panel(new GridLayout(2));
       
        Button btnTambah = new Button("Tambah", new Runnable()
        {
        
			@Override
			public void run() {
                menuTambah();
          		
			}
            
        });
        Button btnKembali = new Button("Kembali", new Runnable()
        {
        
			@Override
			public void run() {
                UserMenuPage userMenuPage = new UserMenuPage();
                userMenuPage.ShowMenuAdmin();
          		
			}
            
        });
        btnTambah.addTo(panel);
        btnKembali.addTo(panel);
        mainPanel.addComponent(panel);
        BasicWindow window = new BasicWindow("LIST JALUR STASIUN");
        // Create gui and start gui
        window.setComponent(mainPanel);
        window.setHints(Arrays.asList(Hint.CENTERED));
       
        gui.addWindowAndWait(window);
    }

    
}