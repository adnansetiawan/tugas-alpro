package tugasalpro.views;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

public class BasePage
{
    public BasePage()
    {

    }
    protected  void addEmptySpace(Panel panel, int number)
    {
        for(int i=0;i<number;i++)
        {
            panel.addComponent(new EmptySpace());

        }
    }
    protected Terminal getTerminal()
    {
       return PageComponent.getTerminal();
    }
    protected Screen getScreen()
    {
       return PageComponent.getScreen();
    }
}