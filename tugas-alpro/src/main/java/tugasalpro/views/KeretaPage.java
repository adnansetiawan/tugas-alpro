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
import com.googlecode.lanterna.gui2.table.Table;

import tugasalpro.Gerbong;
import tugasalpro.managers.KeretaManager;
import tugasalpro.models.Kereta;
import tugasalpro.models.Stasiun;



public class KeretaPage extends BasePage {
    private KeretaManager keretaManager;
   
    public KeretaPage(){
        keretaManager = new KeretaManager();
    }

    private void menuTambah() {
        MultiWindowTextGUI gui = new MultiWindowTextGUI(getScreen(), new DefaultWindowManager(),
        new EmptySpace(TextColor.ANSI.BLUE));

        Panel mainPanel = new Panel();

        mainPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
        addEmptySpace(mainPanel, 1);
        mainPanel.addComponent(new Label("[KODE] '[NAMA]' [JML GERBONG] [JML G.BISNIS] [JML G.PREMIUM] :"));
        final TextBox tbKereta = new TextBox();
        tbKereta.setPreferredSize(new TerminalSize(75, 1));
        mainPanel.addComponent(tbKereta);
        addEmptySpace(mainPanel, 1);
        Panel panelButton = new Panel(new GridLayout(2));
       
        Button btnOK = new Button("OK", new Runnable()
        {
        
			@Override
			public void run() {
                String[] dataKeretas = tbKereta.getText().split("'");
                
                try
                {
                    Kereta kereta = new Kereta();
                    kereta.setId(UUID.randomUUID().toString());
                    kereta.setKodeKereta(dataKeretas[0].trim());
                    kereta.setNamaKereta(dataKeretas[1]);
                    String[] others = dataKeretas[2].split(" ");
                    kereta.setJmlGerbong(Integer.parseInt(others[1].substring(1, others[1].toCharArray().length)));
                    kereta.setJmlGBisnis(Integer.parseInt(others[2].substring(1, others[2].toCharArray().length)));
                    kereta.setJmlGPremium(Integer.parseInt(others[3].substring(1, others[3].toCharArray().length)));
                    if(kereta.getJmlGerbong() != kereta.getJmlGBisnis() + kereta.getJmlGPremium())
                    {
                        MessageDialog.showMessageDialog(gui, "Error", "jumlah gerbong tidak valid", MessageDialogButton.OK);
                        return; 
                    }
                    keretaManager.add(kereta);
                    MessageDialog.showMessageDialog(gui, "Success", "stasiun berhasil ditambahkan", MessageDialogButton.OK);
                    menuTampil();
                }catch(Exception ex)
                {
                    MessageDialog.showMessageDialog(gui, "Error", "data stasiun gagal ditambahkan", MessageDialogButton.OK);
                    
                }
                }
			
            
        });
        btnOK.addTo(panelButton);
        Button btnKembali = new Button("TAMPILKAN KERETA", new Runnable()
        {
        
			@Override
			public void run() {
               menuTampil();
				
			}
            
        });
        btnKembali.addTo(panelButton);
        mainPanel.addComponent(panelButton);
        BasicWindow window = new BasicWindow("TAMBAH KERETA");
        // Create gui and start gui
        window.setComponent(mainPanel);
        window.setHints(Arrays.asList(Hint.CENTERED));
        gui.addWindowAndWait(window);
        
        
    }
   
    private void generateListKereta(WindowBasedTextGUI gui, Panel mainPanel)
    {
         mainPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
         addEmptySpace(mainPanel, 1);
         Table<String> table = new Table<String>("No", "KODE KERETA", "NAMA KERETA", "JML GERBONG", "JML BISNIS", "JML PREMIUM");
         table.addTo(mainPanel);
         table.setSelectAction(new Runnable() {
             @Override
             public void run() {
                 List<String> data = table.getTableModel().getRow(table.getSelectedRow());
                 Kereta kereta = keretaManager.getByKodeKereta(data.get(1));
                      String result =   new TextInputDialogBuilder()
                     .setTitle("Pilih")
                     .setDescription("EDIT/DELETE")
                     .setValidationPattern(Pattern.compile("(EDIT|DELETE){1}"), "input tidak valid")
                     .build()
                     .showDialog(gui);
                     switch(result)
                     {
                         case "EDIT":
                             menuUbah(kereta);
                             break;
                         case "DELETE":
                             keretaManager.delete(kereta);
                             MessageDialog.showMessageDialog(gui, "Success", "data kereta berhasil dihapus", MessageDialogButton.OK);
                             menuTampil();
                             break;
                     }
                 
              
             }
         });
         List<Kereta> listKereta = keretaManager.GetAll();
         if(listKereta.size() > 0)
         {
             int no = 0;
             for (Kereta kereta : listKereta) {
                 no++;
                 table.getTableModel().addRow(String.valueOf(no), kereta.getKodeKereta(), kereta.getNamaKereta(),
                 String.valueOf(kereta.getJmlGerbong()), String.valueOf(kereta.getJmlGBisnis()), String.valueOf(kereta.getJmlGPremium()));
             }
             
         }   
        }
    


   public void menuTampil(){
        WindowBasedTextGUI gui = new MultiWindowTextGUI(getScreen(), new DefaultWindowManager(),
        new EmptySpace(TextColor.ANSI.BLUE));


        Panel mainPanel = new Panel();
        Panel listPanel = new Panel();
        mainPanel.addComponent(listPanel.withBorder(Borders.singleLine()));
        generateListKereta(gui,listPanel);
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
        BasicWindow window = new BasicWindow("LIST KERETA");
        // Create gui and start gui
        window.setComponent(mainPanel);
        window.setHints(Arrays.asList(Hint.CENTERED));
    
        gui.addWindowAndWait(window);
    }

   public void menuUbah(Kereta kereta)  {
    MultiWindowTextGUI gui = new MultiWindowTextGUI(getScreen(), new DefaultWindowManager(),
    new EmptySpace(TextColor.ANSI.BLUE));

    Panel mainPanel = new Panel(new GridLayout(2));
    mainPanel.addComponent(new Label("Kode Kereta:"));
    final TextBox tbKode = new TextBox(kereta.getKodeKereta());
    mainPanel.addComponent(tbKode);
    addEmptySpace(mainPanel, 2);
    mainPanel.addComponent(new Label("Nama Stasiun:"));
    final TextBox tbNama = new TextBox(kereta.getNamaKereta());
    mainPanel.addComponent(tbNama);
    addEmptySpace(mainPanel, 2);
    mainPanel.addComponent(new Label("Jml Gerbong:"));
    final TextBox tbJmlGerbong = new TextBox(String.valueOf(kereta.getJmlGerbong()));
    mainPanel.addComponent(tbJmlGerbong);
    addEmptySpace(mainPanel, 2);
    mainPanel.addComponent(new Label("Jml Gerbong Bisnis:"));
    final TextBox tbJmlGerbongBisnis = new TextBox(String.valueOf(kereta.getJmlGBisnis()));
    mainPanel.addComponent(tbJmlGerbongBisnis);
    addEmptySpace(mainPanel, 2);
    mainPanel.addComponent(new Label("Jml Gerbong Premium:"));
    final TextBox tbJmlGerbongPremium = new TextBox(String.valueOf(kereta.getJmlGPremium()));
    mainPanel.addComponent(tbJmlGerbongPremium);
    addEmptySpace(mainPanel, 2);
   
    Button btnOK = new Button("OK", new Runnable()
    {
    
        @Override
        public void run() {
            try
            {
                Kereta updatedKereta = new Kereta(kereta.getId(), tbKode.getText(), tbNama.getText(), 
                Integer.parseInt(tbJmlGerbong.getText()),  Integer.parseInt(tbJmlGerbongBisnis.getText()),  
                Integer.parseInt(tbJmlGerbongPremium.getText()));
                if(kereta.getJmlGerbong() != kereta.getJmlGBisnis() + kereta.getJmlGPremium())
                    {
                        MessageDialog.showMessageDialog(gui, "Error", "jumlah gerbong tidak valid", MessageDialogButton.OK);
                        return; 
                    }
                keretaManager.edit(updatedKereta);
                MessageDialog.showMessageDialog(gui, "Success", "data kereta berhasil diupdate", MessageDialogButton.OK);
                menuTampil();
            }
            catch(Exception ex)
            {
                MessageDialog.showMessageDialog(gui, "Error", "data kereta gagal diupdate", MessageDialogButton.OK);
                
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