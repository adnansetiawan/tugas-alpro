package tugasalpro;
public class Stasiun {
    private String kodeStasiun;
    private String namaStasiun;

    public Stasiun(){

    }

    public Stasiun(String kS, String nS){
        kodeStasiun=kS;
        namaStasiun=nS;
    }

    public void setKodeStasiun(String kS){
        kodeStasiun=kS;
    }

    public void setNamaStasiun(String nS){
        namaStasiun=nS;
    }
    
    public String getKodeStasiun(){
        return kodeStasiun;
    }

    public String getNamaStasiun(){
        return namaStasiun;
    }

    public void printStasiun(){
        System.out.println(kodeStasiun+" "+namaStasiun);
    }
}