package tugasalpro.managers;

import java.util.List;

import tugasalpro.Repository;
import tugasalpro.models.Booking;
import tugasalpro.models.Pembayaran;

public class PembayaranManager
{
    private Repository<Pembayaran> repository;
    public PembayaranManager()
    {
        repository = new Repository<Pembayaran>("Pembayaran", Pembayaran[].class);
    }
    public void add(Pembayaran pembayaran)
    {
        BookingManager bookingManager = new BookingManager();
        Booking booking = bookingManager.getByKode(pembayaran.getKodeBooking());
        repository.add(pembayaran);
        booking.setBayar(true);
        bookingManager.update(booking);
        
    }
    public List<Pembayaran> getAll()
    {
       return repository.getAll();
    }

    public void delete(String kodeBooking)
    {
        List<Pembayaran> pembayarans = getAll();
        for(int i=0; i<pembayarans.size(); i++)
        {
            if(pembayarans.get(i).getKodeBooking().equals(kodeBooking))
            {
                repository.delete(pembayarans.get(i), i);
                break;
            }
        }
    }

}