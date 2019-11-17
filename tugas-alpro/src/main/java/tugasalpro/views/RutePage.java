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
import com.googlecode.lanterna.gui2.dialogs.*;
import com.googlecode.lanterna.gui2.table.*;

import tugasalpro.managers.*;
import tugasalpro.models.Rute;


public class RutePage extends BasePage {
    private RuteManager ruteManager;
    private KotaManager kotaManager;
    Scanner scanner;

    public RutePage() {

        ruteManager = new RuteManager();
        kotaManager = new KotaManager();
        scanner = new Scanner(System.in);

    }
    private void generateListRute(WindowBasedTextGUI gui, Panel mainPanel)
   {
        mainPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
        addEmptySpace(mainPanel, 1);
        Table<String> table = new Table<String>("No", "KEBERANGKATAN", "TUJUAN", "KODE RUTE", "TARIF BISNIS", "TARIF PREMIUM");
        table.addTo(mainPanel);
        table.setSelectAction(new Runnable() {
            @Override
            public void run() {
                List<String> data = table.getTableModel().getRow(table.getSelectedRow());
                Rute rute = ruteManager.GetByKodeRute(data.get(3));
                     String result =   new TextInputDialogBuilder()
                    .setTitle("Pilih")
                    .setDescription("EDIT/DELETE")
                    .setValidationPattern(Pattern.compile("(EDIT|DELETE){1}"), "input tidak valid")
                    .build()
                    .showDialog(gui);
                    switch(result)
                    {
                        case "EDIT":
                            menuUbah(rute);
                            break;
                        case "DELETE":
                            ruteManager.delete(rute);
                            MessageDialog.showMessageDialog(gui, "Success", "data rute berhasil dihapus", MessageDialogButton.OK);
                            menuTampil();
                            break;
                    }
                
             
            }
        });
        List<Rute> listRute = ruteManager.GetAll();
        if(listRute.size() > 0)
        {
            int no = 0;
            for (Rute rute : listRute) {
                no++;
                table.getTableModel().addRow(String.valueOf(no), 
                rute.getKotaAsal().getNamaKota(), rute.getKotaTujuan().getNamaKota(), rute.getKodeRute(), 
                String.valueOf(rute.getHargaBisnis()), String.valueOf(rute.getHargaPremium()));
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
        generateListRute(gui,listPanel);
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
        BasicWindow window = new BasicWindow("LIST RUTE");
        // Create gui and start gui
        window.setComponent(mainPanel);
        window.setHints(Arrays.asList(Hint.CENTERED));
       
        gui.addWindowAndWait(window);

       
    }

   public void menuTambah() {
        MultiWindowTextGUI gui = new MultiWindowTextGUI(getScreen(), new DefaultWindowManager(),
        new EmptySpace(TextColor.ANSI.BLUE));

        Panel mainPanel = new Panel();

        mainPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
        addEmptySpace(mainPanel, 1);
        mainPanel.addComponent(new Label("[KOTA ASAL] [KOTA TUJUAN] [HARGA BISNIS [HARGA PREMIUM] :"));
        final TextBox tbRute = new TextBox();
        tbRute.setPreferredSize(new TerminalSize(50, 1));
        mainPanel.addComponent(tbRute);
        addEmptySpace(mainPanel, 1);
        Panel panelButton = new Panel(new GridLayout(2));
       
        Button btnOK = new Button("OK", new Runnable()
        {
        
			@Override
			public void run() {
                String[] ruteText = tbRute.getText().split(" ");
                if(ruteText.length != 4)
                {
                    MessageDialog.showMessageDialog(gui, "Error", "not valid input", MessageDialogButton.OK);
                    
                }else
                {
                    try
                    {
                        Rute rute = new Rute();
                        rute.setId(UUID.randomUUID().toString());
                        rute.setKotaAsal(kotaManager.GetByNamaKota(ruteText[0]));
                        rute.setKotaTujuan(kotaManager.GetByNamaKota(ruteText[1]));
                        rute.setHargaBisnis(Double.parseDouble(ruteText[2]));
                        rute.setHargaPremium(Double.parseDouble(ruteText[3]));
                        rute.setKodeRute(rute.getKotaAsal().getKodeKota()+"-"+rute.getKotaTujuan().getKodeKota());
                        rute.setKeretaRute(null);
                        ruteManager.add(rute); 
                        MessageDialog.showMessageDialog(gui, "Success", "rute berhasil ditambahkan", MessageDialogButton.OK);
                        menuTampil();
                    }catch(Exception ex)
                    {
                        MessageDialog.showMessageDialog(gui, "Error", "data rute gagal ditambahkan", MessageDialogButton.OK);
                       
                    }
                }
				
			}
            
        });
        btnOK.addTo(panelButton);
        Button btnKembali = new Button("TAMPILKAN RUTE", new Runnable()
        {
        
			@Override
			public void run() {
               menuTampil();
				
			}
            
        });
        btnKembali.addTo(panelButton);
        mainPanel.addComponent(panelButton);
        BasicWindow window = new BasicWindow("TAMBAH RUTE");
        // Create gui and start gui
        window.setComponent(mainPanel);
        window.setHints(Arrays.asList(Hint.CENTERED));
       
        gui.addWindowAndWait(window);
        
        
    }

 

   public void menuUbah(Rute rute) {
        MultiWindowTextGUI gui = new MultiWindowTextGUI(getScreen(), new DefaultWindowManager(),
        new EmptySpace(TextColor.ANSI.BLUE));

        Panel mainPanel = new Panel(new GridLayout(2));
        mainPanel.addComponent(new Label("Kota Asal:"));
        final TextBox tbKotaAsal = new TextBox(rute.getKotaAsal().getNamaKota());
        mainPanel.addComponent(tbKotaAsal);
        addEmptySpace(mainPanel, 2);
        mainPanel.addComponent(new Label("Kota Tujuan:"));
        final TextBox tbKotaTujuan = new TextBox(rute.getKotaTujuan().getNamaKota());
        mainPanel.addComponent(tbKotaTujuan);
        addEmptySpace(mainPanel, 2);
        mainPanel.addComponent(new Label("Harga Bisnis:"));
        final TextBox tbHargaBisnis = new TextBox(String.valueOf(rute.getHargaBisnis()));
        mainPanel.addComponent(tbHargaBisnis);
        addEmptySpace(mainPanel, 2);
        mainPanel.addComponent(new Label("Harga Premium:"));
        final TextBox tbHargaPremium = new TextBox(String.valueOf(rute.getHargaPremium()));
        mainPanel.addComponent(tbHargaPremium);
        addEmptySpace(mainPanel, 2);
      
        Button btnOK = new Button("OK", new Runnable()
        {
        
			@Override
			public void run() {
                try
                {
                    Rute updatedRute = new Rute();
                    updatedRute.setId(rute.getId());
                    updatedRute.setKotaAsal(kotaManager.GetByNamaKota(tbKotaAsal.getText()));
                    updatedRute.setKotaTujuan(kotaManager.GetByNamaKota(tbKotaTujuan.getText()));
                    updatedRute.setHargaBisnis(Double.parseDouble(tbHargaBisnis.getText()));
                    updatedRute.setHargaPremium(Double.parseDouble(tbHargaPremium.getText()));
                    updatedRute.setKodeRute(updatedRute.getKotaAsal().getKodeKota()+"-"+updatedRute.getKotaTujuan().getKodeKota());
                    updatedRute.setKeretaRute(null);
                    ruteManager.edit(updatedRute);
                  
                    MessageDialog.showMessageDialog(gui, "Success", "data rute berhasil diupdate", MessageDialogButton.OK);
                    menuTampil();
                }
                catch(Exception ex)
                {
                    MessageDialog.showMessageDialog(gui, "Error", "data rute gagal diupdate", MessageDialogButton.OK);
                    
                }
				
			}
            
        });
        btnOK.addTo(mainPanel);
        BasicWindow window = new BasicWindow("EDIT RUTE");
        // Create gui and start gui
        window.setComponent(mainPanel);
        window.setHints(Arrays.asList(Hint.CENTERED));
       
        gui.addWindowAndWait(window);
    
    }

   
}