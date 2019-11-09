package tugasalpro;
public class Kereta {
    private String kodeKereta;
    private String namaKereta;
    private int jmlGerbong;
    private int jmlGBisnis;
    private int jmlGPremium;
    private Gerbong[] arrGBisnis;
    private Gerbong[] arrGPremium;

    public Kereta() {
    }

    public Kereta(String kodeKereta, String namaKereta, int jmlGerbong, int jmlGBisnis, int jmlGPremium, Gerbong[] arrGBisnis, Gerbong[] arrGPremium) {
        this.kodeKereta = kodeKereta;
        this.namaKereta = namaKereta;
        this.jmlGerbong = jmlGerbong;
        this.jmlGBisnis = jmlGBisnis;
        this.jmlGPremium = jmlGPremium;
        this.arrGBisnis = arrGBisnis;
        this.arrGPremium = arrGPremium;
    }

    public String getKodeKereta() {
        return this.kodeKereta;
    }

    public void setKodeKereta(String kodeKereta) {
        this.kodeKereta = kodeKereta;
    }

    public String getNamaKereta() {
        return this.namaKereta;
    }

    public void setNamaKereta(String namaKereta) {
        this.namaKereta = namaKereta;
    }

    public int getJmlGerbong() {
        return this.jmlGerbong;
    }

    public void setJmlGerbong(int jmlGerbong) {
        this.jmlGerbong = jmlGerbong;
    }

    public int getJmlGBisnis() {
        return this.jmlGBisnis;
    }

    public void setJmlGBisnis(int jmlGBisnis) {
        this.jmlGBisnis = jmlGBisnis;
    }

    public int getJmlGPremium() {
        return this.jmlGPremium;
    }

    public void setJmlGPremium(int jmlGPremium) {
        this.jmlGPremium = jmlGPremium;
    }

    public Gerbong[] getArrGBisnis() {
        return this.arrGBisnis;
    }

    public void setArrGBisnis(Gerbong[] arrGBisnis) {
        this.arrGBisnis = arrGBisnis;
    }

    public Gerbong[] getArrGPremium() {
        return this.arrGPremium;
    }

    public void setArrGPremium(Gerbong[] arrGPremium) {
        this.arrGPremium = arrGPremium;
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

    public Kereta arrGBisnis(Gerbong[] arrGBisnis) {
        this.arrGBisnis = arrGBisnis;
        return this;
    }

    public Kereta arrGPremium(Gerbong[] arrGPremium) {
        this.arrGPremium = arrGPremium;
        return this;
    }
}