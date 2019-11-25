package tugasalpro.managers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import tugasalpro.*;
import tugasalpro.models.*;

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
    private int findById(String id)
    {
        List<Kota> listKota = repository.getAll();
        int indexFound = -1;
        for(int i = 0; i< listKota.size(); i++)
        {
            Kota kta = listKota.get(i);
            
            if(kta.getId().equals(id))
            {
                indexFound = i;
                break;
            }
        }
        return indexFound;
    }
    public void edit(Kota kota)
    {
        int indexFound = findById(kota.getId());   
        if (indexFound != -1) {
        try {
            
                repository.edit(kota, indexFound);
        }
        catch(Exception e){
                e.printStackTrace();
            }
        }
        
      
    }

    public void delete(Kota kota)
    {
        List<Kota> listKota = repository.getAll();
        for(int i = 0; i<listKota.size(); i++)
        {
            Kota textKota = listKota.get(i);
            if (textKota.getKodeKota().equals(kota.getKodeKota()))
            {
                try {
                    listKota.remove(i);
                    repository.update(listKota);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                i=0;
            }
        }

        /* Non aktifkan script lama
        int indexFound = findById(kota.getId());
        
        if (indexFound != -1) {
            try {
                repository.delete(kota, indexFound);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        */
      
    }


}