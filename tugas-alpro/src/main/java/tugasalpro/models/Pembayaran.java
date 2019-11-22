package tugasalpro.models;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Pembayaran
{
    private String kodePembayaran;
    private String kodeBooking;
    private String kodeKereta;
    private double totalPembayaran;
    private Date tanggalPembayaran;
    private Tiket tiket;
    public Pembayaran()
    {
        
    }
    public void createTiket(String kodeTiket, List<Penumpang> penumpang)
    {
        tiket = new Tiket(kodeTiket, penumpang);
    }
    public Tiket getTiket()
    {
        return this.tiket;
    }
    public void setKodePembayaran(String kodePembayaran) {
        this.kodePembayaran = kodePembayaran;
    }
    public void setTanggalPembayaran(Date tanggalPembayaran) {
        this.tanggalPembayaran = tanggalPembayaran;
    }
    public void setTotalPembayaran(Double totalPembayaran) {
        this.totalPembayaran = totalPembayaran;
    }
    public void setKodeBooking(String kodeBooking) {
        this.kodeBooking = kodeBooking;
    }
    public void setKodeKereta(String kodeKereta)
    {
        this.kodeKereta = kodeKereta;
    }
    public String getKodePembayaran()
    {
        return this.kodePembayaran;
    }
    public String getKodeBooking()
    {
        return this.kodeBooking;
    }
    public String getKodeKereta()
    {
        return this.kodeKereta;
    }
    public Date getTanggalPembayaran()
    {
        return this.tanggalPembayaran;
    }
    public Double getTotalPembayaran()
    {
        return this.totalPembayaran;
    }
    public int getBulanPembayaran()
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.tanggalPembayaran);
        return cal.get(Calendar.MONTH);    
    }
    public int getTahunPembayaran()
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.tanggalPembayaran);
        return cal.get(Calendar.YEAR);    
    }
    
}