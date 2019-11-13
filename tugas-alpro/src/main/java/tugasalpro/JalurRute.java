package tugasalpro;

import java.util.ArrayList;

public class JalurRute{
    private String kodeJalur;
    private Rute ruteJalur;
    private int durasi;
    private ArrayList<JalurStasiun> arrJalurStasiun;

    public JalurRute(){
        durasi = 0;
        arrJalurStasiun = new ArrayList<JalurStasiun>();
    }

    public JalurRute(String kJ, Rute rJ){
        kodeJalur = kJ;
        ruteJalur = rJ;
        durasi = 0;
        arrJalurStasiun = new ArrayList<JalurStasiun>();
    }

    public void setKodeJalur(String kJ){
        kodeJalur=kJ;
    }

    public void setRuteJalur(Rute rJ){
        ruteJalur=rJ;
    }

    public String getKodeJalur(){
        return kodeJalur;
    }

    public Rute getRuteJalur(){
        return ruteJalur;
    }

    public int getDurasi(){
        return durasi;
    }

    public void addJalurStasiun(JalurStasiun JS){
        arrJalurStasiun.add(JS);
        durasi+=JS.getDurasi();
    }
}