package tugasalpro;
public class KeretaRute {
    private String kodeKeretaRute;
    private String kodeRute;
    private Repository<Kereta> keretaTersedia;


    public KeretaRute() {
    }

    public KeretaRute(String kodeKeretaRute, String kodeRute, Repository<Kereta> keretaTersedia) {
        this.kodeKeretaRute = kodeKeretaRute;
        this.kodeRute = kodeRute;
        this.keretaTersedia = keretaTersedia;
    }

    public String getKodeKeretaRute() {
        return this.kodeKeretaRute;
    }

    public void setKodeKeretaRute(String kodeKeretaRute) {
        this.kodeKeretaRute = kodeKeretaRute;
    }

    public String getKodeRute() {
        return this.kodeRute;
    }

    public void setKodeRute(String kodeRute) {
        this.kodeRute = kodeRute;
    }

    public Repository<Kereta> getKeretaTersedia() {
        return this.keretaTersedia;
    }

    public void setKeretaTersedia(Repository<Kereta> keretaTersedia) {
        this.keretaTersedia = keretaTersedia;
    }

    public KeretaRute kodeKeretaRute(String kodeKeretaRute) {
        this.kodeKeretaRute = kodeKeretaRute;
        return this;
    }

    public KeretaRute kodeRute(String kodeRute) {
        this.kodeRute = kodeRute;
        return this;
    }

    public KeretaRute keretaTersedia(Repository<Kereta> keretaTersedia) {
        this.keretaTersedia = keretaTersedia;
        return this;
    }

}