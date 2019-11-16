package tugasalpro.models;


import java.util.ArrayList;

public class KeretaRute {
    private String kodeKeretaRute;
    private Rute ruteKereta;
    private ArrayList<Kereta> keretaTersedia;


    public KeretaRute() {
        this.keretaTersedia = new ArrayList<Kereta>();
    }

    public KeretaRute(String kodeKeretaRute, Rute ruteKereta, ArrayList<Kereta> keretaTersedia) {
        this.kodeKeretaRute = kodeKeretaRute;
        this.ruteKereta = ruteKereta;
        this.keretaTersedia = keretaTersedia;
    }

    public String getKodeKeretaRute() {
        return this.kodeKeretaRute;
    }

    public void setKodeKeretaRute(String kodeKeretaRute) {
        this.kodeKeretaRute = kodeKeretaRute;
    }

    public Rute getRuteKereta() {
        return this.ruteKereta;
    }

    public void setRuteKereta(Rute ruteKereta) {
        this.ruteKereta = ruteKereta;
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

    public KeretaRute keretaTersedia(ArrayList<Kereta> keretaTersedia) {
        this.keretaTersedia = keretaTersedia;
        return this;
    }

}