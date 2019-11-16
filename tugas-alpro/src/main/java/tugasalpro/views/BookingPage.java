package tugasalpro.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Pattern;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.Window.Hint;
import com.googlecode.lanterna.gui2.table.Table;

import tugasalpro.Repository;
import tugasalpro.managers.BookingManager;
import tugasalpro.models.*;
import tugasalpro.views.buttonevents.OnKembaliToMenuUtamaClicked;

public class BookingPage extends BasePage
{
    public BookingPage()
    {
    }
    public void showInfoPembayaran()
    {
        WindowBasedTextGUI gui = new MultiWindowTextGUI(getScreen(), new DefaultWindowManager(),
        new EmptySpace(TextColor.ANSI.BLUE));
        BasicWindow window = new BasicWindow("INFO PEMBAYARAN");

        Panel mainPanel = new Panel();
        mainPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        mainPanel.setLayoutManager(new GridLayout(1));
        addEmptySpace(mainPanel, 1);
        mainPanel.addComponent(new Label("Total Pembayaran    : 400000"));
        mainPanel.addComponent(new Label("Rekening Tujuan     : 803255671891"));
       
        addEmptySpace(mainPanel, 1);
        Button btnPembayaran = new Button("Pembayaran", new Runnable(){
        
            @Override
            public void run() {
                
            }
        });
        btnPembayaran.addTo(mainPanel);
        Button btnMenuUtama = new Button("Menu Utama", new OnKembaliToMenuUtamaClicked(false));
        btnMenuUtama.addTo(mainPanel);

        window.setHints(Arrays.asList(Hint.CENTERED));
        window.setComponent(mainPanel);
	    gui.addWindowAndWait(window);
   
    }
    public void showInputJadwal()
    {
        WindowBasedTextGUI gui = new MultiWindowTextGUI(getScreen(), new DefaultWindowManager(),
        new EmptySpace(TextColor.ANSI.BLUE));
        BasicWindow window = new BasicWindow("BOOKING TIKET");

        Panel mainPanel = new Panel();
        mainPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        mainPanel.setLayoutManager(new GridLayout(2));
        addEmptySpace(mainPanel, 2);
        mainPanel.addComponent(new Label("Kode Jadwal     :"));
        final TextBox kodeJadwalTextBox = new TextBox();
        kodeJadwalTextBox.setPreferredSize(new TerminalSize(15,1));
        kodeJadwalTextBox.addTo(mainPanel);
        addEmptySpace(mainPanel, 2);
        mainPanel.addComponent(new Label("Jml Penumpang   :"));
        final TextBox jmlPenumpangTextBox = new TextBox();
        jmlPenumpangTextBox.setValidationPattern(Pattern.compile("[0-9]*"));
        jmlPenumpangTextBox.setPreferredSize(new TerminalSize(5,1));
        jmlPenumpangTextBox.addTo(mainPanel);
    
        addEmptySpace(mainPanel, 2);
        Button btnOk = new Button("OK", new Runnable(){
        
            @Override
            public void run() {
                showInputPenumpang(kodeJadwalTextBox.getText(), Integer.parseInt(jmlPenumpangTextBox.getText()));
                
            }
        });
        btnOk.addTo(mainPanel);
        window.setHints(Arrays.asList(Hint.CENTERED));
        window.setComponent(mainPanel);
	    gui.addWindowAndWait(window);
    }
    public void showInputPenumpang(String kodeJadwal, int jumlahPenumpang)
    {
        WindowBasedTextGUI gui = new MultiWindowTextGUI(getScreen(), new DefaultWindowManager(),
        new EmptySpace(TextColor.ANSI.BLUE));
        BasicWindow window = new BasicWindow("BOOKING TIKET - ISI DATA PENUMPANG");

        Panel mainPanel = new Panel();
        mainPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        mainPanel.setLayoutManager(new GridLayout(2));
        addEmptySpace(mainPanel, 2);
        for(int i=1; i<= jumlahPenumpang;i++)
        {
            mainPanel.addComponent(new Label("Penumpang "+i+"   :"));
            final TextBox kodeJadwalTextBox = new TextBox();
            kodeJadwalTextBox.setPreferredSize(new TerminalSize(15,1));
            kodeJadwalTextBox.addTo(mainPanel);
            addEmptySpace(mainPanel, 2);
        }
       
        Button btnOk = new Button("OK", new Runnable(){
        
            @Override
            public void run() {
              Booking booking = new Booking(UUID.randomUUID().toString(),kodeJadwal);
               for(Component component : mainPanel.getChildren())
                {
                    if(component.getClass().equals(TextBox.class))
                    {
                        TextBox tb = (TextBox) component;
                        booking.addPenumpang(new Penumpang(tb.getText()));
                    }
                }
                showKursi(booking);
                
            }
        });
        btnOk.addTo(mainPanel);
        window.setHints(Arrays.asList(Hint.CENTERED));
        window.setComponent(mainPanel);
	    gui.addWindowAndWait(window);
    }
    public void showKursi(Booking booking)
    {
        
    WindowBasedTextGUI gui = new MultiWindowTextGUI(getScreen(), new DefaultWindowManager(),
    new EmptySpace(TextColor.ANSI.BLUE));
    BasicWindow window = new BasicWindow("BOOKING TIKET - PILIH KURSI");

    Panel mainPanel = new Panel();
    LinearLayout linearLayout = new LinearLayout(Direction.VERTICAL);
    linearLayout.setSpacing(0); 
    mainPanel.setLayoutManager(linearLayout);
   
    int pi = 1;
    Panel penumpangPanel = new Panel(new GridLayout(2));
    mainPanel.addComponent(penumpangPanel.withBorder(Borders.singleLine("Penumpang")));
    for(Penumpang p : booking.getAllPenumpang())
    {
        penumpangPanel.addComponent(new Label("Kursi Penumpang "+pi+" :"));  
        final TextBox kursiTextBox = new TextBox();
        kursiTextBox.setPreferredSize(new TerminalSize(15,1));
        kursiTextBox.addTo(penumpangPanel);
        addEmptySpace(penumpangPanel, 2);
        pi++;
    }
    Button btnOk = new Button("OK", new Runnable(){
        @Override
        public void run() {
            int i=0;
            for(Component component : penumpangPanel.getChildren())
            {
                if(component.getClass().equals(TextBox.class))
                {
                    TextBox tb = (TextBox) component;
                    booking.getAllPenumpang().get(i).setKodeKursi(tb.getText());
                    i++;    
                }
                
            }
            booking.setRekeningTujuan("803255671891");
            booking.setTotalPembayaran(400000);
            BookingManager bookingManager= new BookingManager();
            bookingManager.add(booking);
            showInfoPembayaran();
            
        }
    });
    addEmptySpace(penumpangPanel, 1);
    penumpangPanel.addComponent(btnOk);


    Panel panelKursi = new Panel();
    mainPanel.addComponent(panelKursi);   
    generateKursiDisplay(panelKursi, "BISNIS", "B", 1, 1);
    generateKursiDisplay(panelKursi, "PREMIUM", "P", 2, 2);
    
    
    window.setHints(Arrays.asList(Hint.CENTERED));
    window.setComponent(mainPanel);
	gui.addWindowAndWait(window);
       
    }
    private void generateKursiDisplay(Panel mainPanel, String namaGerbong, String gerbongPrefix,
     int jmlGerbong, int row)
    {
        int jmlKursi = 10 * row;
        for(int b=1;b<=jmlGerbong;b++)
        {
             GridLayout gridLayout = new GridLayout(10);
            gridLayout.setHorizontalSpacing(0);
            gridLayout.setVerticalSpacing(0);
            Panel bisnisPanel = new Panel(gridLayout);
            mainPanel.addComponent(bisnisPanel.withBorder(Borders.singleLine(namaGerbong+(b+1))));
            for(int i=0; i<jmlKursi; i++)
            {
                    Panel itemPanel = new Panel();
                    String padded = String.format("%02d" , i+1);
                    TextColor color = i > 5 ? TextColor.ANSI.RED : TextColor.ANSI.GREEN;
                    Label lbKursi = new Label(gerbongPrefix+b+"-"+padded);
                    itemPanel.addComponent(lbKursi.setBackgroundColor(color));
                    bisnisPanel.addComponent(itemPanel.withBorder(Borders.singleLine()));
            }
        }
        
    }
}
