package tugasalpro.managers;

import java.util.List;
import java.util.Optional;
import tugasalpro.Repository;
import tugasalpro.models.Waktu;



public class WaktuManager {
    private Repository<Waktu> repository;
    private int i, jam, menit;
    private String kode, textJam;
    
    public WaktuManager()
    {
        repository = new Repository<Waktu>("Waktu", Waktu[].class);
    }

    public List<Waktu> GetAll()
    {
        return repository.getAll();
    }
    
    public void Generate()
    {
        // Var waktu
        Waktu textWaktu = new Waktu();
        for (i=1; i<=95;i++)
        {
            kode = "TM"+i;
            if (i==1)
            {
                textJam = "00.00";
            }
            else
            {
                if (i%4 ==0)
                {
                    jam = jam+1;
                    menit = 0;
                }
                else
                {
                    menit = menit+15;
                }
                if (jam < 10)
                {
                    textJam = "0"+jam+".";
                }
                else
                {
                    textJam = jam+".";
                }
                if (menit==0)
                {
                    textJam = textJam+"00";
                }
                else
                {
                    textJam = textJam+menit;
                }
            }
            textWaktu.setKodeWaktu(kode);
            textWaktu.setWaktu(textJam); 
            repository.add(textWaktu);
        }    
    }

    public Waktu GetByKodeWaktu(String kodeWaktu)
    {
        List<Waktu> listWaktu = repository.getAll();
        if (listWaktu.size()>0)
        {
            Optional<Waktu> selectedWaktu = 
            listWaktu.stream().filter(x->x.getKodeWaktu().equals(kodeWaktu)).findFirst();
            if(selectedWaktu.isPresent())
                return selectedWaktu.get();
            return null;
        }
        else
        {
            return null;
        }

    }
    
}
