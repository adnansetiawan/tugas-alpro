package tugasalpro.models;
public class Stasiun {
    private String kodeStasiun;
    private String namaStasiun;
    private String id;
    public Stasiun(){

    }

    public Stasiun(String id, String kS, String nS){
        kodeStasiun=kS;
        namaStasiun=nS;
        this.id = id;
    }

    public void setKodeStasiun(String kS){
        kodeStasiun=kS;
    }

    public void setNamaStasiun(String nS){
        namaStasiun=nS;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getId()
    {
        return this.id;
    }
    public String getKodeStasiun(){
        return kodeStasiun;
    }

    public String getNamaStasiun(){
        return namaStasiun;
    }
}