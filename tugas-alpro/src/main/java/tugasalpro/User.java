package tugasalpro;
public class User
{
    private String username;
    private String password;
    private boolean isAdmin;
    private UserInfo userInfo;
    public User(String username, String password, UserInfo userInfo)
    {
        this.username = username;
        this.password = password;
        this.userInfo = userInfo;
    }
    public String getUsername()
    {
        return this.username;

    }
    public String getPassword()
    {
        return this.password;

    }
    public UserInfo getUserInfo()
    {
        if(isAdmin())
        {
            return new UserInfo("admin", "", "");
        }
        return this.userInfo;

    }
    public boolean isAdmin()
    {
        return username.equals("admin") && password.equals("admin");
    }
    
}