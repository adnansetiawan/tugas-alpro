package tugasalpro.models;

import java.util.UUID;

public class Kota {
    private String id;
    private String kodeKota;
    private String namaKota;


    public Kota() {
    }

    public Kota(String id, String kodeKota, String namaKota) {
        this.kodeKota = kodeKota;
        this.namaKota = namaKota;
        this.id = id;
        
    }

    public String getKodeKota() {
        return this.kodeKota;
    }

    public void setKodeKota(String kodeKota) {
        this.kodeKota = kodeKota;
    }

    public String getNamaKota() {
        return this.namaKota;
    }

    public void setNamaKota(String namaKota) {
        this.namaKota = namaKota;
    }
    public String getId() {
        return this.id;
    }

    
    

    


}