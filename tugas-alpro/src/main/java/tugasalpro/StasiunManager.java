package tugasalpro;
import java.util.ArrayList;

public class StasiunManager{
    private Stasiun[] arrStasiun;

    public StasiunManager(){
        ArrayList<Stasiun> arrStasiun = new ArrayList<Stasiun>();
    }

    public Stasiun[] getArrStasiun(){
        return arrStasiun;
    }

    public void add(Stasiun s){
        arrStasiun.add(s);
    }

    public void view(){
        int i;
        for (i=0;i<arrStasiun.size();i++){
            arrStasiun[i].printStasiun();
        }
    }

    public int searchIdx(String kS){
        int i=0;
        boolean found=false;
        while (!found){
            if (arrStasiun[i].getKodeStasiun().==kS){
                found=true;
            }else{
                i++;
            }
        }
        return i;
    }

    public void edit(int idx, String kS, String nS){
        arrStasiun[idx].setKodeStasiun(kS);
        arrStasiun[idx].setNamaStasiun(nS);
    }

    public void del(){
        arrStasiun.remove(arrStasiun.size()-1);
    }
}