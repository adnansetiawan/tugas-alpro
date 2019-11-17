package tugasalpro.views;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.Window.Hint;
import com.googlecode.lanterna.gui2.dialogs.*;
import com.googlecode.lanterna.gui2.table.Table;

import tugasalpro.Waktu;
import tugasalpro.WaktuManager;

public class WaktuPage extends BasePage
{
    WaktuManager waktuManager;
    public WaktuPage()
    {
        waktuManager = new WaktuManager();
    }
    public void showWaktu()
    {
        WindowBasedTextGUI gui = new MultiWindowTextGUI(getScreen(), new DefaultWindowManager(),
        new EmptySpace(TextColor.ANSI.BLUE));

        Panel mainPanel = new Panel(new GridLayout(1));
        
        Panel listPanel = new Panel();
        mainPanel.addComponent(listPanel.withBorder(Borders.singleLine()));
        generateListWaktu(gui,listPanel);
        addEmptySpace(mainPanel, 1);
        Panel panel = new Panel(new GridLayout(2));
       
        Button btnTambah = new Button("Generate", new Runnable()
        {
        
			@Override
			public void run() {
                showGenerate();
          		
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
        BasicWindow window = new BasicWindow("LIST WAKTU");
        // Create gui and start gui
        window.setComponent(mainPanel);
        window.setHints(Arrays.asList(Hint.CENTERED));
       
        gui.addWindowAndWait(window);

    }
    private void generateListWaktu(WindowBasedTextGUI gui, Panel mainPanel)
    {
         mainPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
         addEmptySpace(mainPanel, 1);
         Table<String> table = new Table<String>("No", "KODE WAKTU", "WAKTU");
         table.addTo(mainPanel);
         List<Waktu> listWaktu = waktuManager.GetAll();
         if(listWaktu.size() > 0)
         {
            
            int no = 0;
            for (Waktu waktu : listWaktu) {
                no++;
                table.getTableModel().addRow(String.valueOf(no), waktu.getKodeWaktu(), waktu.getWaktu());
            }
            
        }
    }
    private void showGenerate()
    {
        WindowBasedTextGUI gui = new MultiWindowTextGUI(getScreen(), new DefaultWindowManager(),
        new EmptySpace(TextColor.ANSI.BLUE));

        Panel mainPanel = new Panel();
        addEmptySpace(mainPanel, 1);
        mainPanel.addComponent(new Label("Apakah anda yakin untuk generate waktu?"));
       
        Panel panel = new Panel(new GridLayout(2));
        addEmptySpace(mainPanel, 2);
        Button btnTambah = new Button("OK", new Runnable()
        {
        
            @Override
            public void run() {
                List<Waktu> existingWaktu = waktuManager.GetAll();
                if(existingWaktu.size() > 0)
                {
                    MessageDialog.showMessageDialog(gui, "Success", "Waktu sudah pernah digenerate!", MessageDialogButton.OK);

                }else
                {
                    waktuManager.Generate();
                    MessageDialog.showMessageDialog(gui, "Success", "Generate Waktu Berhasil!", MessageDialogButton.OK);
                    showWaktu();
                }
    
            }
            
        });
        Button btnKembali = new Button("TIDAK", new Runnable()
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
        BasicWindow window = new BasicWindow("LIST WAKTU");
        // Create gui and start gui
        window.setComponent(mainPanel);
        window.setHints(Arrays.asList(Hint.CENTERED));
    
        gui.addWindowAndWait(window);
    }   

}