package tugasalpro.models;

import java.util.Date;

import tugasalpro.models.Waktu;

public class Jadwal {
    private String kodeJadwal;
    private Date tanggalJadwal;
    private Waktu waktuBerangkat;
    private Waktu waktuTiba;
    private Kota kotaKeberangkatan;
    private Kota kotaTujuan;
    private Kereta kereta;
    private int kursiKosong;


    public Jadwal() {
    }

    public Jadwal(String kodeJadwal, Date tanggalJadwal, Waktu waktuBerangkat, Waktu waktuTiba, Kota kotaKeberangkatan, Kota kotaTujuan, Kereta kereta, int kursiKosong) {
        this.kodeJadwal = kodeJadwal;
        this.tanggalJadwal = tanggalJadwal;
        this.waktuBerangkat = waktuBerangkat;
        this.waktuTiba = waktuTiba;
        this.kotaKeberangkatan = kotaKeberangkatan;
        this.kotaTujuan = kotaTujuan;
        this.kereta = kereta;
        this.kursiKosong = kursiKosong;
    }

    public String getKodeJadwal() {
        return this.kodeJadwal;
    }

    public void setKodeJadwal(String kodeJadwal) {
        this.kodeJadwal = kodeJadwal;
    }

    public Date getTanggalJadwal() {
        return this.tanggalJadwal;
    }

    public void setTanggalJadwal(Date tanggalJadwal) {
        this.tanggalJadwal = tanggalJadwal;
    }

    public Waktu getWaktuBerangkat() {
        return this.waktuBerangkat;
    }

    public void setWaktuBerangkat(Waktu waktuBerangkat) {
        this.waktuBerangkat = waktuBerangkat;
    }

    public Waktu getWaktuTiba() {
        return this.waktuTiba;
    }

    public void setWaktuTiba(Waktu waktuTiba) {
        this.waktuTiba = waktuTiba;
    }

    public Kota getKotaKeberangkatan() {
        return this.kotaKeberangkatan;
    }

    public void setKotaKeberangkatan(Kota kotaKeberangkatan) {
        this.kotaKeberangkatan = kotaKeberangkatan;
    }

    public Kota getKotaTujuan() {
        return this.kotaTujuan;
    }

    public void setKotaTujuan(Kota kotaTujuan) {
        this.kotaTujuan = kotaTujuan;
    }

    public Kereta getKereta() {
        return this.kereta;
    }

    public void setKereta(Kereta kereta) {
        this.kereta = kereta;
    }

    public int getKursiKosong() {
        return this.kursiKosong;
    }

    public void setKursiKosong(int kursiKosong) {
        this.kursiKosong = kursiKosong;
    }


}