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
import com.googlecode.lanterna.gui2.table.Table;

import tugasalpro.managers.*;
import tugasalpro.models.*;

public class KotaPage extends BasePage {
    private KotaManager kotaManager;
    Scanner scanner;

    public KotaPage() {

        kotaManager = new KotaManager();
        scanner = new Scanner(System.in);

    }

    public void showMenu() {

        MultiWindowTextGUI gui = new MultiWindowTextGUI(getScreen(), new DefaultWindowManager(),
        new EmptySpace(TextColor.ANSI.BLUE));

        Panel mainPanel = new Panel();

        mainPanel.setLayoutManager(new GridLayout(1));
        addEmptySpace(mainPanel, 1);
        Panel panel = new Panel(new LinearLayout(Direction.VERTICAL));
        panel.addComponent(new Label("1. Tambah Kota"));
        panel.addComponent(new Label("2. Tampilkan Kota"));
        panel.addComponent(new Label("99. Keluar"));
        addEmptySpace(panel, 1);
        mainPanel.addComponent(panel);
        Panel panelInput = new Panel(new GridLayout(3));
        
        panelInput.addComponent(new Label("Pilih    :"));
        panelInput.addComponent(AnimatedLabel.createClassicSpinningLine());
        final TextBox pilihanText = new TextBox().setValidationPattern(Pattern.compile("[0-9]*"));
        pilihanText.addTo(panelInput);
        mainPanel.addComponent(panelInput);
        addEmptySpace(mainPanel, 1);
        Button btnOK = new Button("OK", new Runnable()
        {
        
			@Override
			public void run() {
                int input = Integer.parseInt(pilihanText.getText());
                switch(input)
                {
                    case 1 : 
                        menuTambah();
                        break;
                    case 2:
                        menuTampil();
                        break;
                    case 99:
                        UserMenuPage userMenuPage = new UserMenuPage();
                        userMenuPage.ShowMenuAdmin();
                        break;    
                    
                }
				
			}
            
        });
        btnOK.addTo(mainPanel);
        BasicWindow window = new BasicWindow("MENU KOTA");
        // Create gui and start gui
        window.setComponent(mainPanel);
        window.setHints(Arrays.asList(Hint.CENTERED));
       
        gui.addWindowAndWait(window);
    }

    private void menuTambah() {
       
        MultiWindowTextGUI gui = new MultiWindowTextGUI(getScreen(), new DefaultWindowManager(),
        new EmptySpace(TextColor.ANSI.BLUE));

        Panel mainPanel = new Panel();

        mainPanel.setLayoutManager(new GridLayout(2));
        addEmptySpace(mainPanel, 2);
        mainPanel.addComponent(new Label("[KODE] [NAMA]  :"));
        final TextBox tbKota = new TextBox();
        mainPanel.addComponent(tbKota);
        addEmptySpace(mainPanel, 2);
        Button btnOK = new Button("OK", new Runnable()
        {
        
			@Override
			public void run() {
                String[] kotaText = tbKota.getText().split(" ");
                if(kotaText.length != 2)
                {
                    MessageDialog.showMessageDialog(gui, "Error", "not valid input", MessageDialogButton.OK);
                    
                }else
                {
                    Kota kota = new Kota(UUID.randomUUID().toString(),kotaText[0],kotaText[1]);
                    kotaManager.add(kota);   
                    MessageDialog.showMessageDialog(gui, "Success", "kota berhasil ditambahkan", MessageDialogButton.OK);
                    menuTampil();
                }
				
			}
            
        });
        btnOK.addTo(mainPanel);
        Button btnKembali = new Button("MENU KOTA", new Runnable()
        {
        
			@Override
			public void run() {
               showMenu();
				
			}
            
        });
        btnKembali.addTo(mainPanel);
        
        BasicWindow window = new BasicWindow("TAMBAH KOTA");
        // Create gui and start gui
        window.setComponent(mainPanel);
        window.setHints(Arrays.asList(Hint.CENTERED));
       
        gui.addWindowAndWait(window);
        
    }
   private void generateListKota(Panel mainPanel)
   {
        mainPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
        addEmptySpace(mainPanel, 1);
        Table<String> table = new Table<String>("No", "KODE KOTA", "NAMA KOTA");
        table.setSelectAction(new Runnable() {
            @Override
            public void run() {
                List<String> data = table.getTableModel().getRow(table.getSelectedRow());
                Kota kota = kotaManager.GetByKodeKota(data.get(1));
                menuUbah(kota);
            }
        });
        List<Kota> listKota = kotaManager.GetAll();
        if(listKota.size() == 0)
            return;
        int no = 0;
        for (Kota kota : listKota) {
            no++;
            table.getTableModel().addRow(String.valueOf(no), kota.getKodeKota(), kota.getNamaKota());
        }
        table.addTo(mainPanel);
   }
   private void menuTampil()  {

        MultiWindowTextGUI gui = new MultiWindowTextGUI(getScreen(), new DefaultWindowManager(),
        new EmptySpace(TextColor.ANSI.BLUE));

        Panel mainPanel = new Panel();
        generateListKota(mainPanel);
        //addEmptySpace(mainPanel, 1);
        Button btnOK = new Button("OK", new Runnable()
        {
        
			@Override
			public void run() {
                showMenu();
          		
			}
            
        });
        btnOK.addTo(mainPanel);
        BasicWindow window = new BasicWindow("LIST KOTA");
        // Create gui and start gui
        window.setComponent(mainPanel);
        window.setHints(Arrays.asList(Hint.CENTERED));
       
        gui.addWindowAndWait(window);

    }

   private void menuUbah(Kota kota) {
        
        MultiWindowTextGUI gui = new MultiWindowTextGUI(getScreen(), new DefaultWindowManager(),
        new EmptySpace(TextColor.ANSI.BLUE));

        Panel mainPanel = new Panel(new GridLayout(2));
        mainPanel.addComponent(new Label("Kode Kota:"));
        final TextBox tbKode = new TextBox(kota.getKodeKota());
        mainPanel.addComponent(tbKode);
        addEmptySpace(mainPanel, 2);
        mainPanel.addComponent(new Label("Nama Kota:"));
        final TextBox tbNama = new TextBox(kota.getNamaKota());
        mainPanel.addComponent(tbNama);
        addEmptySpace(mainPanel, 2);
      
        Button btnOK = new Button("OK", new Runnable()
        {
        
			@Override
			public void run() {
                 Kota updatedKota = new Kota(kota.getId(), tbKode.getText(), tbNama.getText());
                    kotaManager.edit(updatedKota);   
                    MessageDialog.showMessageDialog(gui, "Success", "kota berhasil ditambahkan", MessageDialogButton.OK);
                    menuTampil();
                
				
			}
            
        });
        btnOK.addTo(mainPanel);
        BasicWindow window = new BasicWindow("EDIT KOTA");
        // Create gui and start gui
        window.setComponent(mainPanel);
        window.setHints(Arrays.asList(Hint.CENTERED));
       
        gui.addWindowAndWait(window);
    }

   private void menuHapus() {
        menuTampil();
        String kodeKota = null;
        Kota delKota = null;
        boolean flagIterate = true;
        do {
            System.out.print("Hapus Kota : ");
            kodeKota = scanner.next();
            if (kodeKota=="99") {
                flagIterate = false;
            } else {
                kodeKota = kodeKota.substring(7);
                delKota = kotaManager.GetByKodeKota(kodeKota);
                if (delKota!=null) {
                    kotaManager.delete(delKota);
                    flagIterate = false;
                }
            }
        } while (flagIterate);
    }
}