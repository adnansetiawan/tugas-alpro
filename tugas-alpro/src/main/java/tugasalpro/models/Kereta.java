package tugasalpro.models;

public class Kereta {
    private String id;
    private String kodeKereta;
    private String namaKereta;
    private int jmlGerbong;
    private int jmlGBisnis;
    private int jmlGPremium;
   
    public Kereta() {
    }

    public Kereta(String id, String kodeKereta, String namaKereta, int jmlGerbong, int jmlGBisnis, int jmlGPremium) {
        this.id = id;
        this.kodeKereta = kodeKereta;
        this.namaKereta = namaKereta;
        this.jmlGerbong = jmlGerbong;
        this.jmlGBisnis = jmlGBisnis;
        this.jmlGPremium = jmlGPremium;
    }

    public String getId() {
        return this.id;
    }

    public String getKodeKereta() {
        return this.kodeKereta;
    }

    public String getNamaKereta() {
        return this.namaKereta;
    }

    public int getJmlGerbong() {
        return this.jmlGerbong;
    }
    
    public int getJmlGBisnis() {
        return this.jmlGBisnis;
    }

    public int getJmlGPremium() {
        return this.jmlGPremium;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setKodeKereta(String kodeKereta) {
        this.kodeKereta = kodeKereta;
    }

    public void setNamaKereta(String namaKereta) {
        this.namaKereta = namaKereta;
    }

    public void setJmlGerbong(int jmlGerbong) {
        this.jmlGerbong = jmlGerbong;
    }

    public void setJmlGBisnis(int jmlGBisnis) {
        this.jmlGBisnis = jmlGBisnis;
    }

    public void setJmlGPremium(int jmlGPremium) {
        this.jmlGPremium = jmlGPremium;
    }

    public Kereta kodeKereta(String kodeKereta) {
        this.kodeKereta = kodeKereta;
        return this;
    }

    public Kereta namaKereta(String namaKereta) {
        this.namaKereta = namaKereta;
        return this;
    }

    public Kereta jmlGerbong(int jmlGerbong) {
        this.jmlGerbong = jmlGerbong;
        return this;
    }

    public Kereta jmlGBisnis(int jmlGBisnis) {
        this.jmlGBisnis = jmlGBisnis;
        return this;
    }

    public Kereta jmlGPremium(int jmlGPremium) {
        this.jmlGPremium = jmlGPremium;
        return this;
    }
}