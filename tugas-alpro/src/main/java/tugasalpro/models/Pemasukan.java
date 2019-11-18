package tugasalpro.models;
import java.util.ArrayList;
import java.util.Date;

public class Pemasukan{
    private Date tanggal;
    private Kereta kereta;
    private int pemasukan;

    public Pemasukan(){
        
    }

    public Date getTanggal(){
        return tanggal;
    }
    public Kereta getKereta(){
        return kereta;
    }
    public int getPemasukan(){
        return pemasukan;
    }

    public void setTanggal(Date t){
        tanggal=t;
    }
    public void setKereta(Kereta k){
        kereta=k;
    }
    public void setPemasukan(int p){
        pemasukan=p;
    }
}