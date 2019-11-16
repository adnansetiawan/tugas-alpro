package tugasalpro.models;

import java.util.ArrayList;

public class WaktuRute
{
    private String kodeWaktuRute, kodeRute, arrWaktu;
    
    public String getKodeWaktuRute()
    {
        return kodeWaktuRute;
    }
    public void setKodeWaktuRute(String kodeWaktuRute)
    {
        this.kodeWaktuRute = kodeWaktuRute;
    }

    public String getKodeRute()
    {
        return kodeRute;
    }
    public void setKodeRute(String kodeRute)
    {
        this.kodeRute = kodeRute;
    }

    public String getArrWaktu()
    {
        return arrWaktu;
    }
    public void setArrWaktu(String arrWaktu)
    {
        this.arrWaktu = arrWaktu;
    }

    public WaktuRute()
    {
    }

    public WaktuRute(String kodeWaktuRute, String kodeRute, String arrWaktu)
    {
        this.kodeWaktuRute = kodeWaktuRute;
        this.kodeRute = kodeRute;
        this.arrWaktu = arrWaktu;
    }
}