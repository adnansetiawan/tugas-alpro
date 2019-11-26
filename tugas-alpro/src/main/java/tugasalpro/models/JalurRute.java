package tugasalpro.models;

import java.util.ArrayList;
import java.util.Formatter;
public class JalurRute{
    private String kodeJalur;
    private Rute ruteJalur;
    private int durasi;
    private boolean activeStatus;
    private ArrayList<JalurStasiun> arrJalurStasiun;

    public JalurRute(){
        durasi = 0;
        activeStatus = true;
        arrJalurStasiun = new ArrayList<>();
    }

    public JalurRute(String kJ, Rute rJ){
        kodeJalur = kJ;
        ruteJalur = rJ;
        durasi = 0;
        activeStatus = true;
        arrJalurStasiun = new ArrayList<>();
    }

    public void setKodeJalur(String kJ){
        kodeJalur=kJ;
    }

    public void setRuteJalur(Rute rJ){
        ruteJalur=rJ;
    }
    
    public void setActiveStatus (boolean aS){
        activeStatus=aS;
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
    
    public boolean getActiveStatus(){
        return activeStatus;
    }
    
    public ArrayList<JalurStasiun> getArrJalurStasiun(){
        return arrJalurStasiun;
    }

    public void addJalurStasiun(JalurStasiun JS){
        arrJalurStasiun.add(JS);
        durasi+=JS.getDurasi();
    }

    public String printJalurStasiun(){
        String jalurStasiun="";
        for(int i=0;i<arrJalurStasiun.size();i++){
            if(i!=0){
                jalurStasiun+=" | ";
            }
            jalurStasiun+=arrJalurStasiun.get(i).getStasiunAsal().getKodeStasiun()+"-"+arrJalurStasiun.get(i).getStasiunTujuan().getKodeStasiun();
        }
        return jalurStasiun;
    }
}