package tugasalpro.managers;

import tugasalpro.*;
import tugasalpro.models.*;

public class LoginManager
{
    private boolean isLogin;
    private UserManager userManager;
    public LoginManager()
    {
        userManager = new UserManager();
    }
    public boolean HasLogin()
    {
        return this.isLogin;
    }
    public void Login(String username, String password)
    {
        if(username.equals("admin") && password.equals("admin"))
        {
            ApplicationSession.setLoggedUser(new User("admin","admin", null));
            this.isLogin = true;
            return;
        }
       User user = userManager.getByUsername(username);
       if(user == null)
       {
            ApplicationSession.setLoggedUser(null);
           this.isLogin = false;
       }else
       {

           if(!user.getPassword().equals(password))
           {
                ApplicationSession.setLoggedUser(null);
                this.isLogin = false ;
           }else
           {
                ApplicationSession.setLoggedUser(user);
                this.isLogin = true ;
           }
           
       }
    }
    public void Logout()
    {
        ApplicationSession.setLoggedUser(null);
        this.isLogin = false;
        
    }
    
}