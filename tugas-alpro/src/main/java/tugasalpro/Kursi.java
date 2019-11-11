package tugasalpro;
public class Kursi {
    private String kodeKursi;
    private Boolean isAvailable;

    public Kursi() {
    }

    public Kursi(String kodeKursi, int isAvailable) {
        this.kodeKursi = kodeKursi;
        this.isAvailable = isAvailable;
    }

    public String getKodeKursi() {
        return this.kodeKursi;
    }

    public void setKodeKursi(String kodeKursi) {
        this.kodeKursi = kodeKursi;
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

    public Kursi isAvailable(int isAvailable) {
        this.isAvailable = isAvailable;
        return this;
    }

}