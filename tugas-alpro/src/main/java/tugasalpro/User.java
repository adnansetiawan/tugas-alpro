package tugasalpro;
public class User
{
    private String username;
    private String password;
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
    public UserInfo getUserInfo()
    {
        return this.userInfo;

    }
    
}