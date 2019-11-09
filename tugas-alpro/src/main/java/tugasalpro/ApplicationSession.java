package tugasalpro;
public  class ApplicationSession
{
    private static User loggedUser;
    public static void setLoggedUser(User user)
    {
        loggedUser = user;
    }
    public static User getLoggedUser()
    {
        return loggedUser;
    }
}