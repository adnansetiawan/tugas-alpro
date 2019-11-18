package tugasalpro.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Booking
{
    private String bookingId;
    private Jadwal jadwal;
    private User bookedBy;
    private Date bookingDate;
   
    private List<Penumpang> penumpangs;
    private Double totalPembayaran;
    private String rekeningTujuan;
    private boolean isBayar;
  
    public Booking(String bookingId, Jadwal jadwal)
    {
        penumpangs = new ArrayList<>();
        this.jadwal = jadwal;
        this.bookingId = bookingId;
    }
    public void addPenumpang(Penumpang penumpang)
    {
        penumpangs.add(penumpang);
    }
    public int getJumlahPenumpang()
    {
        return penumpangs.size();
    }
    public List<Penumpang> getAllPenumpang()
    {
        return penumpangs;
    }
    public Jadwal getJadwal()
    {
        return this.jadwal;
    }
    public void setBookingDate(Date bookingDate)
    {
        this.bookingDate = bookingDate;
    }
    public Date getBookingDate()
    {
        return this.bookingDate;
    }
    public void setUser(User bookedBy)
    {
        this.bookedBy = bookedBy;
    }
    public User getBookedBy()
    {
        return this.bookedBy;

    }
    public void setRekeningTujuan(String rekening)
    {
        this.rekeningTujuan = rekening;
    }
    public String getRekeningTujuan()
    {
        return this.rekeningTujuan;

    }
    public void setTotalPembayaran(double total)
    {
        this.totalPembayaran = total;
    }
    public Double getTotalPembayaran()
    {
        return this.totalPembayaran;

    }
    public String getBookingId()
    {
        return this.bookingId;

    }
    public void setBayar(boolean isBayar)
    {
        this.isBayar = isBayar;
    }
    public boolean IsBayar()
    {
        return this.isBayar;

    }
    
}