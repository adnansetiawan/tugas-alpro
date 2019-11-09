package tugasalpro;
public class Rute {
    private Kota kotaAsal;
    private Kota kotaTujuan;
    private int hargaBisnis;
    private int hargaPremium;
    private Kereta[] keretaRute;


    public Rute() {
    }

    public Rute(Kota kotaAsal, Kota kotaTujuan, int hargaBisnis, int hargaPremium, Kereta[] keretaRute) {
        this.kotaAsal = kotaAsal;
        this.kotaTujuan = kotaTujuan;
        this.hargaBisnis = hargaBisnis;
        this.hargaPremium = hargaPremium;
        this.keretaRute = keretaRute;
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

    public int getHargaBisnis() {
        return this.hargaBisnis;
    }

    public void setHargaBisnis(int hargaBisnis) {
        this.hargaBisnis = hargaBisnis;
    }

    public int getHargaPremium() {
        return this.hargaPremium;
    }

    public void setHargaPremium(int hargaPremium) {
        this.hargaPremium = hargaPremium;
    }

    public Kereta[] getKeretaRute() {
        return this.keretaRute;
    }

    public void setKeretaRute(Kereta[] keretaRute) {
        this.keretaRute = keretaRute;
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

    public Rute keretaRute(Kereta[] keretaRute) {
        this.keretaRute = keretaRute;
        return this;
    }

  

}