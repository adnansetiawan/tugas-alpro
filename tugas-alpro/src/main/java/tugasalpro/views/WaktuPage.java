package tugasalpro.views;

import tugasalpro.models.*;
import tugasalpro.managers.*;

public class WaktuPage
{
    public WaktuPage()
    {
        new WaktuManager().Generate();
    }
}