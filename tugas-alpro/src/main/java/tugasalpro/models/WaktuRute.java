package tugasalpro.models;

import java.util.ArrayList;

public class WaktuRute
{
    private String kodeWaktuRute;
    private Rute rute;
    private ArrayList<Waktu> arrWaktu;


    public WaktuRute() {
    }

    public WaktuRute(String kodeWaktuRute, Rute rute, ArrayList<Waktu> arrWaktu) {
        this.kodeWaktuRute = kodeWaktuRute;
        this.rute = rute;
        this.arrWaktu = arrWaktu;
    }

    public String getKodeWaktuRute() {
        return this.kodeWaktuRute;
    }

    public void setKodeWaktuRute(String kodeWaktuRute) {
        this.kodeWaktuRute = kodeWaktuRute;
    }

    public Rute getRute() {
        return this.rute;
    }

    public void setRute(Rute rute) {
        this.rute = rute;
    }

    public ArrayList<Waktu> getArrWaktu() {
        return this.arrWaktu;
    }

    public void setArrWaktu(ArrayList<Waktu> arrWaktu) {
        this.arrWaktu = arrWaktu;
    }

}