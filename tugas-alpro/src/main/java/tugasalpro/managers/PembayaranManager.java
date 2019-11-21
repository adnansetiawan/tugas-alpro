package tugasalpro.managers;

import java.util.List;

import tugasalpro.Repository;
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
        repository.add(pembayaran);
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