package tugasalpro.models;

import tugasalpro.Waktu;

public class Jadwal {
    private String kodeJadwal;
    private String tanggalJadwal;
    private Waktu waktuRute;
    private KeretaRute keretaRute;


    public Jadwal() {
    }

    public Jadwal(String kodeJadwal, String tanggalJadwal, Waktu waktuRute, KeretaRute keretaRute) {
        this.kodeJadwal = kodeJadwal;
        this.tanggalJadwal = tanggalJadwal;
        this.waktuRute = waktuRute;
        this.keretaRute = keretaRute;
    }

    public String getKodeJadwal() {
        return this.kodeJadwal;
    }

    public void setKodeJadwal(String kodeJadwal) {
        this.kodeJadwal = kodeJadwal;
    }

    public String getTanggalJadwal() {
        return this.tanggalJadwal;
    }

    public void setTanggalJadwal(String tanggalJadwal) {
        this.tanggalJadwal = tanggalJadwal;
    }

    public Waktu getWaktuRute() {
        return this.waktuRute;
    }

    public void setWaktuRute(Waktu waktuRute) {
        this.waktuRute = waktuRute;
    }

    public KeretaRute getKeretaRute() {
        return this.keretaRute;
    }

    public void setKeretaRute(KeretaRute keretaRute) {
        this.keretaRute = keretaRute;
    }

    public Jadwal kodeJadwal(String kodeJadwal) {
        this.kodeJadwal = kodeJadwal;
        return this;
    }

    public Jadwal tanggalJadwal(String tanggalJadwal) {
        this.tanggalJadwal = tanggalJadwal;
        return this;
    }

    public Jadwal waktuRute(Waktu waktuRute) {
        this.waktuRute = waktuRute;
        return this;
    }

    public Jadwal keretaRute(KeretaRute keretaRute) {
        this.keretaRute = keretaRute;
        return this;
    }
   
}