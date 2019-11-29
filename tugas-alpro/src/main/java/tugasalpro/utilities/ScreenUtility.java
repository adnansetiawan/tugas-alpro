package tugasalpro.utilities;
public class ScreenUtility
{
    public static void ClearScreen()
    {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}