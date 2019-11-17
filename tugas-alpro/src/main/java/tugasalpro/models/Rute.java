package tugasalpro.models;

import tugasalpro.*;

public class Rute {
    private String id;
    private String kodeRute;
    private Kota kotaAsal;
    private Kota kotaTujuan;
    private double hargaBisnis;
    private double hargaPremium;
    private Repository<KeretaRute> keretaRute;


    public Rute() {
    }

    public Rute(String id, String kodeRute, Kota kotaAsal, Kota kotaTujuan, double hargaBisnis, double hargaPremium, Repository<KeretaRute> keretaRute) {
        this.kodeRute = kodeRute;
        this.kotaAsal = kotaAsal;
        this.kotaTujuan = kotaTujuan;
        this.hargaBisnis = hargaBisnis;
        this.hargaPremium = hargaPremium;
        this.keretaRute = keretaRute;
        this.id = id;
    }
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getKodeRute() {
        return this.kodeRute;
    }

    public void setKodeRute(String kodeRute) {
        this.kodeRute = kodeRute;
    }

    public Kota getKotaAsal() {
        return this.kotaAsal;
    }

    public void setKotaAsal(Kota kotaAsal) {
        this.kotaAsal = kotaAsal;
    }

    public Kota getKotaTujuan() {
        return this.kotaTujuan;
    }

    public void setKotaTujuan(Kota kotaTujuan) {
        this.kotaTujuan = kotaTujuan;
    }

    public double getHargaBisnis() {
        return this.hargaBisnis;
    }

    public void setHargaBisnis(double hargaBisnis) {
        this.hargaBisnis = hargaBisnis;
    }

    public double getHargaPremium() {
        return this.hargaPremium;
    }

    public void setHargaPremium(double hargaPremium) {
        this.hargaPremium = hargaPremium;
    }

    public Repository<KeretaRute> getKeretaRute() {
        return this.keretaRute;
    }

    public void setKeretaRute(Repository<KeretaRute> keretaRute) {
        this.keretaRute = keretaRute;
    }

    public Rute kodeRute(String kodeRute) {
        this.kodeRute = kodeRute;
        return this;
    }

    public Rute kotaAsal(Kota kotaAsal) {
        this.kotaAsal = kotaAsal;
        return this;
    }

    public Rute kotaTujuan(Kota kotaTujuan) {
        this.kotaTujuan = kotaTujuan;
        return this;
    }

    public Rute hargaBisnis(int hargaBisnis) {
        this.hargaBisnis = hargaBisnis;
        return this;
    }

    public Rute hargaPremium(int hargaPremium) {
        this.hargaPremium = hargaPremium;
        return this;
    }

    public Rute keretaRute(Repository<KeretaRute> keretaRute) {
        this.keretaRute = keretaRute;
        return this;
    }

}