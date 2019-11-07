package tugasalpro;
public class LoginManager
{
    private boolean isLogin;
    public boolean HasLogin()
    {
        return this.isLogin;
    }
    public void Login(String username, String password)
    {
       this.isLogin = true;
    }
    public void Logout()
    {
        this.isLogin = false;
    }
    
}