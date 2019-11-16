package tugasalpro.views.buttonevents;

import tugasalpro.views.UserMenuPage;

public class OnKembaliToMenuUtamaClicked implements Runnable {
    private boolean isAdmin;
    public OnKembaliToMenuUtamaClicked(boolean isAdmin) {
        this.isAdmin= isAdmin;
    }

    @Override
    public void run() {
        UserMenuPage userMenuPage = new UserMenuPage();
        if(this.isAdmin)
        {
             userMenuPage.ShowMenuAdmin();
        }else
        {
             userMenuPage.ShowMenuPengguna();
        }
     
      
    }
}