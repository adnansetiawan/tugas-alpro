package tugasalpro.models;
public class Kursi {
    private String kodeKursi;
    private Gerbong gerbong;
    private Boolean isAvailable;

    public Kursi() {
    }

    public Kursi(String kodeKursi, Gerbong gerbong, Boolean isAvailable) {
        this.kodeKursi = kodeKursi;
        this.gerbong =gerbong;
        this.isAvailable = isAvailable;
    }

    public String getKodeKursi() {
        return this.kodeKursi;
    }

    public void setKodeKursi(String kodeKursi) {
        this.kodeKursi = kodeKursi;
    }
    public Gerbong getGerbong() {
        return this.gerbong;
    }

    public void setGerbong(Gerbong gerbong) {
        this.gerbong = gerbong;
    }
    
    public Boolean getIsAvailable() {
        return this.isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Kursi kodeKursi(String kodeKursi) {
        this.kodeKursi = kodeKursi;
        return this;
    }

    public Kursi isAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
        return this;
    }
   

}