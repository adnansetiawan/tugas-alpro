package tugasalpro;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

public class KotaManager {
    private Repository<Kota> repository;
    public KotaManager()
    {
        repository = new Repository<Kota>("Kota", Kota[].class);
    }
    
    public void add(Kota kota)
    {
         repository.add(kota);  
    
    }
    public List<Kota> GetAll()
    {
        return repository.getAll();
    }

    public Kota GetByKodeKota(String kodeKota)
    {
        List<Kota> listKota = repository.getAll();
        Optional<Kota> selectedKota = 
            listKota.stream().filter(x->x.getKodeKota().equals(kodeKota)).findFirst();
        if(selectedKota.isPresent())
            return selectedKota.get();
        return null;

    }
    public Kota GetByNamaKota(String namaKota)
    {
        List<Kota> listKota = repository.getAll();
        Optional<Kota> selectedKota = 
            listKota.stream().filter(x->x.getNamaKota().equals(namaKota)).findFirst();
        if(selectedKota.isPresent())
            return selectedKota.get();
        return null;

    }
    public void edit(Kota kota)
    {
        List<Kota> listKota = repository.getAll();
        int indexFound = -1;
        for(int i = 0; i< listKota.size(); i++)
        {
            Kota kta = listKota.get(i);
            
            if(kta.getKodeKota().equals(kota.getKodeKota()))
            {
                indexFound = i;
                break;
            }
        }
            if (indexFound != -1) {
            try {
                listKota.remove(indexFound);
                listKota.add(kota);
                repository.update(listKota);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        
      
    }

    public void delete(Kota kota)
    {
        List<Kota> listKota = repository.getAll();
        int indexFound = -1;
        for(int i = 0; i< listKota.size(); i++)
        {
            Kota kta = listKota.get(i);
            if(kta.getKodeKota().equals(kota.getKodeKota()))
            {
                indexFound = i;
                break;
            }
        }
        
        if (indexFound != -1) {
            try {
                listKota.remove(indexFound);
                repository.update(listKota);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
      
    }


}