package tugasalpro.views;

import java.util.Arrays;
import java.util.Scanner;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.Window.Hint;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;

import tugasalpro.managers.*;
import tugasalpro.models.*;
import tugasalpro.views.buttonevents.OnKembaliToMenuUtamaClicked;
import tugasalpro.*;
public class ProfilePenggunaPage extends BasePage
{
      
    public ProfilePenggunaPage() {
     }
   
   
   
    public void Registrasi()
    {
        showForm(true, null);
       
    }
    private void showForm(boolean isRegistration, User user)
    {
        String defaultKtp = user == null? "" : user.getUserInfo().getKtp();
        String defaultName = user == null?"" : user.getUserInfo().getName();
        String defaultPhone = user == null?"" : user.getUserInfo().getHandphone();
        String defaultEmail = user == null?"" : user.getUsername();
        String defaultPassword = user == null?"" : user.getPassword();
      
        WindowBasedTextGUI gui = new MultiWindowTextGUI(getScreen(), new DefaultWindowManager(),
        new EmptySpace(TextColor.ANSI.BLUE));
 
         Panel panel = new Panel();
         panel.setLayoutManager(new GridLayout(2));
         addEmptySpace(panel, 2);
         panel.addComponent(new Label("No. Ktp      :"));
         final TextBox ktpTextBox = new TextBox(defaultKtp);
         ktpTextBox.setReadOnly(!isRegistration);
         ktpTextBox.setPreferredSize(new TerminalSize(28,1));
         ktpTextBox.addTo(panel);
         addEmptySpace(panel, 2);
         panel.addComponent(new Label("Nama Lengkap :"));
         final TextBox namaLengkapTextBox = new TextBox(defaultName);
         namaLengkapTextBox.setPreferredSize(new TerminalSize(28,1));
         namaLengkapTextBox.addTo(panel);
         addEmptySpace(panel, 2);
         panel.addComponent(new Label("No. Handphone:"));
         final TextBox handphoneTextBox = new TextBox(defaultPhone);
         handphoneTextBox.setPreferredSize(new TerminalSize(28,1));
         handphoneTextBox.addTo(panel);
         addEmptySpace(panel, 2);
         panel.addComponent(new Label("Email        :"));
         final TextBox emailTextBox = new TextBox(defaultEmail);
         emailTextBox.setPreferredSize(new TerminalSize(28,1));
         emailTextBox.addTo(panel);
         addEmptySpace(panel, 2);
         panel.addComponent(new Label("Password     :"));
         final TextBox passwordTextBox = new TextBox(defaultPassword);
         passwordTextBox.setMask('*');
         passwordTextBox.setPreferredSize(new TerminalSize(28,1));
         passwordTextBox.addTo(panel);

         final TextBox repasswordTextBox = new TextBox(defaultPassword);
         if(isRegistration)
         {
            addEmptySpace(panel, 2);
            panel.addComponent(new Label("Re-Password   :"));
            repasswordTextBox.setMask('*');
            repasswordTextBox.setPreferredSize(new TerminalSize(28,1));
            repasswordTextBox.addTo(panel);
         }   
         addEmptySpace(panel, 2);
         addEmptySpace(panel, 2);
         Button btnBack =  new Button("Kembali",new OnKembaliToDataPenggunaClicked(user));
         btnBack.addTo(panel);
         Button btnOk =  new Button("Update", new OnUpdateDataPenggunaClicked(isRegistration, gui,
         ktpTextBox, namaLengkapTextBox, handphoneTextBox, emailTextBox, passwordTextBox, repasswordTextBox));
         btnOk.addTo(panel);
         String title = "";
         if(isRegistration)
         {
            title = "REGISTER";
         }else
         {
            title = "UPDATE DATA";
         }
         BasicWindow window = new BasicWindow(title);
         // Create gui and start gui
         window.setComponent(panel);
         window.setHints(Arrays.asList(Hint.CENTERED));
        
         gui.addWindowAndWait(window);

    }
    public void editDataPengguna(User user)
    {
       showForm(false, user);
        
    }
    public void enterNoKTP()
    {
        
        WindowBasedTextGUI gui = new MultiWindowTextGUI(getScreen(), new DefaultWindowManager(),
        new EmptySpace(TextColor.ANSI.BLUE));
 
        Panel panel = new Panel();
 
         panel.setLayoutManager(new GridLayout(2));
         addEmptySpace(panel, 2);
         panel.addComponent(new Label("No. Ktp      :"));
         final TextBox ktpTextBox = new TextBox();
         ktpTextBox.setPreferredSize(new TerminalSize(28,1));
         ktpTextBox.addTo(panel);
         addEmptySpace(panel, 2);
         Button btnCari =  new Button("Cari", new OnCariByKtpClicked(gui, ktpTextBox));
         btnCari.addTo(panel);
         Button btnBack =  new Button("Kembali",new OnKembaliToMenuUtamaClicked(true));
         btnBack.addTo(panel);
        

         BasicWindow window = new BasicWindow("CARI PENGGUNA");
         // Create gui and start gui
         window.setComponent(panel);
         window.setHints(Arrays.asList(Hint.CENTERED));
        
         gui.addWindowAndWait(window);


    }
    public void showProfilePenggunaByUser(User user)
    {
        
        User loggedUser = ApplicationSession.getLoggedUser();
        if(!loggedUser.isAdmin())
        {
            user = loggedUser;
        }
        MultiWindowTextGUI gui = new MultiWindowTextGUI(getScreen(), new DefaultWindowManager(),
        new EmptySpace(TextColor.ANSI.BLUE));
 
        Panel panel = new Panel();
 
         panel.setLayoutManager(new GridLayout(2));
         addEmptySpace(panel, 2);
         panel.addComponent(new Label("No. Ktp      :"));
         panel.addComponent(new Label(user.getUserInfo().getKtp()));
         addEmptySpace(panel, 2);
         panel.addComponent(new Label("Nama Lengkap :"));
         panel.addComponent(new Label(user.getUserInfo().getName()));
         addEmptySpace(panel, 2);
         panel.addComponent(new Label("No. Handphone:"));
         panel.addComponent(new Label(user.getUserInfo().getHandphone()));
         addEmptySpace(panel, 2);
         panel.addComponent(new Label("Email        :"));
         panel.addComponent(new Label(user.getUsername()));
         addEmptySpace(panel, 2);
         panel.addComponent(new Label("Password     :"));
         String passwordMask = user.getPassword().replaceAll("(?s).", "*");
         panel.addComponent(new Label(passwordMask));
         

         addEmptySpace(panel, 2);
         addEmptySpace(panel, 2);
         if(loggedUser.isAdmin())
         {
            Button btnBack =  new Button("Kembali",new OnKembaliToCariClicked());
            btnBack.addTo(panel);
         }else
         {
            Button btnBack =  new Button("Kembali",new OnKembaliToMenuUtamaClicked(false));
            btnBack.addTo(panel);
         }
         Button btnOk =  new Button("Update", new OnPrepareProfileUpdateClicked(user));
         btnOk.addTo(panel);
        
         String titleLabel = "";
         if(loggedUser.isAdmin())
         {
            titleLabel = "KELOLA PROFILE BY ADMIN";
       
         }else
         {
            titleLabel = "KELOLA PROFILE BY PENUMPANG";
         }
         BasicWindow window = new BasicWindow(titleLabel);
         // Create gui and start gui
         window.setComponent(panel);
         window.setHints(Arrays.asList(Hint.CENTERED));
        
         gui.addWindowAndWait(window);

    }
}
class OnPrepareProfileUpdateClicked implements Runnable {
    
    private User userData;
    public OnPrepareProfileUpdateClicked(User userData) {
        this.userData = userData;
    }

    @Override
    public void run() {
       ProfilePenggunaPage profilePenggunaPage = new ProfilePenggunaPage();
       profilePenggunaPage.editDataPengguna(this.userData);
    }
}
class OnKembaliToDataPenggunaClicked implements Runnable {
    
    private User userData;
   
    public OnKembaliToDataPenggunaClicked(User userData) {
        this.userData= userData;
    }

    @Override
    public void run() {
       ProfilePenggunaPage profilePenggunaPage = new ProfilePenggunaPage();
       profilePenggunaPage.showProfilePenggunaByUser(userData);
    }
}
class OnUpdateDataPenggunaClicked implements Runnable {
    
    private TextBox ktpTextBox;
    private TextBox emailTextBox;
    private TextBox passwordTextBox;
    private TextBox repasswordTextBox;
    private TextBox namaTextBox;
    private TextBox handphoneTextBox;
    private WindowBasedTextGUI window;
    private boolean isNew;
    public OnUpdateDataPenggunaClicked(boolean isNew, WindowBasedTextGUI window, TextBox ktpTextBox,
    TextBox namaTextBox, TextBox handphoneTextBox,TextBox emailTextBox, TextBox passwordTextBox, TextBox repasswordTextBox) {
        this.window = window;
        this.ktpTextBox = ktpTextBox;
        this.emailTextBox = emailTextBox;
        this.passwordTextBox = passwordTextBox;
        this.namaTextBox = namaTextBox;
        this.handphoneTextBox= handphoneTextBox;
        this.isNew = isNew;
        this.repasswordTextBox = repasswordTextBox;
    }

    @Override
    public void run() {
        if(isNew)
        {
            if(!checkKtp(ktpTextBox.getText()))
            {
                MessageDialog.showMessageDialog(this.window, "Error", "Ktp hanya boleng mengandung angka dan 16 digit", MessageDialogButton.OK);
                return;
            }else
            {
                UserManager userManager = new UserManager();
                User user = userManager.getByKtp(ktpTextBox.getText());
                if(user != null)
                {
                    MessageDialog.showMessageDialog(this.window, "Error", "user dengan no.ktp: " + ktpTextBox.getText() + " sudah ada", MessageDialogButton.OK);
                    return;
                }
            }
        }
        
      if(!checkName(namaTextBox.getText()))
      {
        MessageDialog.showMessageDialog(this.window, "Error", "nama tidak boleh kosong and hanya mengandung huruf dan spasi", MessageDialogButton.OK);
        return;
      }
      if(!checkHandphone(handphoneTextBox.getText()))
      {
        MessageDialog.showMessageDialog(this.window, "Error", "handphone hanya boleh angka dan 12 digit", MessageDialogButton.OK);
        return;
      }
      if(!checkUsername(emailTextBox.getText()))
      {
        MessageDialog.showMessageDialog(this.window, "Error", "format email salah", MessageDialogButton.OK);
        return;
      }
      if(!checkUsername(emailTextBox.getText()))
      {
        MessageDialog.showMessageDialog(this.window, "Error", "format email salah", MessageDialogButton.OK);
        return;
      }
      if(isNew)
      {
        if(!checkPassword(passwordTextBox.getText(), repasswordTextBox.getText()))
        {
            
            MessageDialog.showMessageDialog(this.window, "Error", "password tidak sesuai", MessageDialogButton.OK);
            return;
        }
      }
      User user = new User(emailTextBox.getText(), passwordTextBox.getText(), 
      new UserInfo(namaTextBox.getText(),ktpTextBox.getText(),  handphoneTextBox.getText()));
      if(!user.isAdmin())
      {
          ApplicationSession.setLoggedUser(user);
      }
      UserManager userManager = new UserManager();
      if(isNew)
      {
            userManager.add(user);
            MessageDialog.showMessageDialog(this.window, "Success", "registrasi berhasil", MessageDialogButton.OK);
     
      }else
      {
            userManager.update(user);
            MessageDialog.showMessageDialog(this.window, "Success", "update data berhasil", MessageDialogButton.OK);
     
      }
      ProfilePenggunaPage profilePenggunaPage = new ProfilePenggunaPage();
      profilePenggunaPage.showProfilePenggunaByUser(user);
       
      
    }
    private boolean checkUsername(String userName) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return userName.matches(regex);
    }

    

    private boolean checkHandphone(String handphone) {
        String regex = "^[0-9]{11,12}$";
        return handphone.matches(regex);
    }

    private boolean checkName(String name) {
        String regex = "^[\\p{L} .'-]+$";
        return name.matches(regex);
    }
    private boolean checkKtp(String ktp) {
        String regex = "^[0-9]{16}$";
        return ktp.matches(regex);
    }
    private boolean checkPassword(String password, String repassword) {
       return password.equals(repassword);
    }

}
class OnKembaliToCariClicked implements Runnable {
    public OnKembaliToCariClicked() {
    }

    @Override
    public void run() {
            ProfilePenggunaPage profilePenggunaPage = new ProfilePenggunaPage();
            profilePenggunaPage.enterNoKTP();
        
    }
}
class OnCariByKtpClicked implements Runnable {
    
    private TextBox ktpTextBox;
    private UserManager userManager;
    private WindowBasedTextGUI window;
    public OnCariByKtpClicked(WindowBasedTextGUI window, TextBox ktpTextBox) {
        this.ktpTextBox = ktpTextBox;
        this.userManager = new UserManager();
    }

    @Override
    public void run() {
        ProfilePenggunaPage profilePenggunaPage = new ProfilePenggunaPage();
        User user = userManager.getByKtp(this.ktpTextBox.getText());
        if(user != null)
        {
            profilePenggunaPage.showProfilePenggunaByUser(user);
        }else
        {
            MessageDialog.showMessageDialog(this.window, "Error", "no ktp tidak vl", MessageDialogButton.OK);
    
        }
         
        
    }
    
}