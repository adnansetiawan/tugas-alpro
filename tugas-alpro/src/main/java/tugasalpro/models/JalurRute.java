package tugasalpro.models;

import java.util.ArrayList;
public class JalurRute implements Comparable<JalurRute>{
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

    @Override
    public int compareTo(JalurRute o) {
        // TODO Auto-generated method stub
        if (getDurasi()==o.getDurasi()) {
            return 0;
        } else if (getDurasi()>o.getDurasi()) {
            return 1;
        } else {
            return -1;
        }
    }
        
    public String printJalurStasiun(){
        String jalurStasiun="";
        for(int i=0;i<arrJalurStasiun.size();i++){
            jalurStasiun+="<br>"+arrJalurStasiun.get(i).getStasiunAsal().getKodeStasiun()+"-"+arrJalurStasiun.get(i).getStasiunTujuan().getKodeStasiun()+"<br/>";
        }
        return jalurStasiun;
    }
}