package tugasalpro.utilities;
public class ScreenUtility
{
    public static void ClearScreen()
    {
        System.out.print("\033[H\033[2J");
    }
}