package tugasalpro;


import java.util.ArrayList;

public class KeretaRute {
    private String kodeKeretaRute;
    private String kodeRute;
    private ArrayList<Kereta> keretaTersedia;


    public KeretaRute() {
        this.keretaTersedia = new ArrayList<Kereta>();
    }

    public KeretaRute(String kodeKeretaRute, String kodeRute, ArrayList<Kereta> keretaTersedia) {
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

    public ArrayList<Kereta> getKeretaTersedia() {
        return this.keretaTersedia;
    }

    public void setKeretaTersedia(ArrayList<Kereta> keretaTersedia) {
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

    public KeretaRute keretaTersedia(ArrayList<Kereta> keretaTersedia) {
        this.keretaTersedia = keretaTersedia;
        return this;
    }

}