package tugasalpro.models;


public class Kota {
    private String kodeKota;
    private String namaKota;


    public Kota() {
    }

    public Kota(String kodeKota, String namaKota) {
        this.kodeKota = kodeKota;
        this.namaKota = namaKota;
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

    public Kota kodeKota(String kodeKota) {
        this.kodeKota = kodeKota;
        return this;
    }

    public Kota namaKota(String namaKota) {
        this.namaKota = namaKota;
        return this;
    }

    


}