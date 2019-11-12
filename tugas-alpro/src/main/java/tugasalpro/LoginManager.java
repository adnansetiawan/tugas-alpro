package tugasalpro;
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
       User user = userManager.GetByUsername(username);
       if(user == null)
       {
           System.out.println("username or password is wrong");
           this.isLogin = false;
       }else
       {

           if(!user.getPassword().equals(password))
           {
                System.out.println("username or password is wrong");
                this.isLogin = false ;
           }else
           {
                System.out.println("login success");
                ApplicationSession.setLoggedUser(user);
                this.isLogin = true ;
           }
           
       }
    }
    public void Logout()
    {
        ApplicationSession.setLoggedUser(null);
        this.isLogin = false;
        LoginPage loginPage = new LoginPage();
        loginPage.showWelcome();
    }
    
}