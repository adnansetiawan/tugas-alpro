package tugasalpro.views;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.terminal.*;

public class PageComponent
{
    private static Terminal terminal;
    private static Screen screen;
    public static void Init()
    {
        try {
            terminal = new DefaultTerminalFactory().createTerminalEmulator();
            terminal.setBackgroundColor(TextColor.ANSI.WHITE);

            screen = new TerminalScreen(terminal);
            screen.startScreen();
        } catch (Exception ex) {

        }

    }
    public static Terminal getTerminal()
    {
        return terminal;
    }
    public static Screen getScreen()
    {
        return screen;
    }
   
}