package tugasalpro.views;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Pattern;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.Window.Hint;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialogBuilder;
import com.googlecode.lanterna.gui2.table.*;


import tugasalpro.models.*;
import tugasalpro.managers.*;

public class StasiunMenuPage extends BasePage { 
    private final StasiunManager stasiunManager;
    Scanner scanner;
      
    public StasiunMenuPage(){
        stasiunManager = new StasiunManager();
        scanner = new Scanner(System.in);
    }
    private void generateListStasiun(WindowBasedTextGUI gui, Panel mainPanel)
    {
         mainPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
         addEmptySpace(mainPanel, 1);
         Table<String> table = new Table<String>("No", "KODE STASIUN", "NAMA STASIUN");
         table.addTo(mainPanel);
         table.setSelectAction(new Runnable() {
             @Override
             public void run() {
                 List<String> data = table.getTableModel().getRow(table.getSelectedRow());
                 Stasiun stasiun = stasiunManager.getByKodeStasiun(data.get(1));
                      String result =   new TextInputDialogBuilder()
                     .setTitle("Pilih")
                     .setDescription("EDIT/DELETE")
                     .setValidationPattern(Pattern.compile("(EDIT|DELETE){1}"), "input tidak valid")
                     .build()
                     .showDialog(gui);
                     switch(result)
                     {
                         case "EDIT":
                             menuUbah(stasiun);
                             break;
                         case "DELETE":
                             stasiunManager.delete(stasiun);
                             MessageDialog.showMessageDialog(gui, "Success", "data stasiun berhasil dihapus", MessageDialogButton.OK);
                             menuTampil();
                             break;
                     }
                 
              
             }
         });
         List<Stasiun> listStasiun = stasiunManager.getAll();
         if(listStasiun.size() > 0)
         {
             int no = 0;
             for (Stasiun stasiun : listStasiun) {
                 no++;
                 table.getTableModel().addRow(String.valueOf(no), stasiun.getKodeStasiun(), stasiun.getNamaStasiun());
             }
             
         }   
        }
    

    public void menuTampil()
    {
        WindowBasedTextGUI gui = new MultiWindowTextGUI(getScreen(), new DefaultWindowManager(),
        new EmptySpace(TextColor.ANSI.BLUE));

        Panel mainPanel = new Panel();
        Panel listPanel = new Panel();
        mainPanel.addComponent(listPanel.withBorder(Borders.singleLine()));
        generateListStasiun(gui,listPanel);
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
        BasicWindow window = new BasicWindow("LIST STASIUN");
        // Create gui and start gui
        window.setComponent(mainPanel);
        window.setHints(Arrays.asList(Hint.CENTERED));
       
        gui.addWindowAndWait(window);

    }
    private void menuTambah() {
        MultiWindowTextGUI gui = new MultiWindowTextGUI(getScreen(), new DefaultWindowManager(),
        new EmptySpace(TextColor.ANSI.BLUE));

        Panel mainPanel = new Panel();

        mainPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
        addEmptySpace(mainPanel, 1);
        mainPanel.addComponent(new Label("[KODE STASIUN] [NAMA STASIUN] :"));
        final TextBox tbStatiun = new TextBox();
        tbStatiun.setPreferredSize(new TerminalSize(35, 1));
        mainPanel.addComponent(tbStatiun);
        addEmptySpace(mainPanel, 1);
        Panel panelButton = new Panel(new GridLayout(2));
       
        Button btnOK = new Button("OK", new Runnable()
        {
        
			@Override
			public void run() {
                String[] stasiunText = tbStatiun.getText().split(" ");
                if(stasiunText.length != 2)
                {
                    MessageDialog.showMessageDialog(gui, "Error", "not valid input", MessageDialogButton.OK);
                    
                }else
                {
                    try
                    {

                        Stasiun stasiun = new Stasiun(UUID.randomUUID().toString(), stasiunText[0],stasiunText[1]);
                        stasiunManager.add(stasiun); 
                        MessageDialog.showMessageDialog(gui, "Success", "stasiun berhasil ditambahkan", MessageDialogButton.OK);
                        menuTampil();
                    }catch(Exception ex)
                    {
                        MessageDialog.showMessageDialog(gui, "Error", "data stasiun gagal ditambahkan", MessageDialogButton.OK);
                       
                    }
                }
				
			}
            
        });
        btnOK.addTo(panelButton);
        Button btnKembali = new Button("TAMPILKAN STASIUN", new Runnable()
        {
        
			@Override
			public void run() {
               menuTampil();
				
			}
            
        });
        btnKembali.addTo(panelButton);
        mainPanel.addComponent(panelButton);
        BasicWindow window = new BasicWindow("TAMBAH STASIUN");
        // Create gui and start gui
        window.setComponent(mainPanel);
        window.setHints(Arrays.asList(Hint.CENTERED));
       
        gui.addWindowAndWait(window);
        
        
    }
   

    private void menuUbah(Stasiun stasiun){
        MultiWindowTextGUI gui = new MultiWindowTextGUI(getScreen(), new DefaultWindowManager(),
        new EmptySpace(TextColor.ANSI.BLUE));

        Panel mainPanel = new Panel(new GridLayout(2));
        mainPanel.addComponent(new Label("Kode Stasiun:"));
        final TextBox tbKodeStasiun = new TextBox(stasiun.getKodeStasiun());
        mainPanel.addComponent(tbKodeStasiun);
        addEmptySpace(mainPanel, 2);
        mainPanel.addComponent(new Label("Nama Stasiun:"));
        final TextBox tbNamaStasiun = new TextBox(stasiun.getNamaStasiun());
        mainPanel.addComponent(tbNamaStasiun);
        addEmptySpace(mainPanel, 2);
       
        Button btnOK = new Button("OK", new Runnable()
        {
        
			@Override
			public void run() {
                try
                {
                    Stasiun updatedStasiun = new Stasiun(stasiun.getId(), tbKodeStasiun.getText(),tbNamaStasiun.getText());
                    stasiunManager.edit(updatedStasiun);
                    MessageDialog.showMessageDialog(gui, "Success", "data stasiun berhasil diupdate", MessageDialogButton.OK);
                    menuTampil();
                }
                catch(Exception ex)
                {
                    MessageDialog.showMessageDialog(gui, "Error", "data stasiun gagal diupdate", MessageDialogButton.OK);
                    
                }
				
			}
            
        });
        btnOK.addTo(mainPanel);
        BasicWindow window = new BasicWindow("EDIT STASIUN");
        // Create gui and start gui
        window.setComponent(mainPanel);
        window.setHints(Arrays.asList(Hint.CENTERED));
       
        gui.addWindowAndWait(window);
        
    }

    
}