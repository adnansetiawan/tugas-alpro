package tugasalpro.managers;

import java.util.List;
import java.util.Optional;

import tugasalpro.Repository;
import tugasalpro.models.Rute;

public class RuteManager {
    private Repository<Rute> repository;
    public RuteManager()
    {
        repository = new Repository<Rute>("Rute", Rute[].class);
    }
    
    public void add(Rute rute) 
    {
         repository.add(rute);  
    
    }
    public List<Rute> GetAll()
    {
        return repository.getAll();
    }

    public Rute GetByKodeRute(String kodeRute)
    {
        List<Rute> listRute = repository.getAll();
        Optional<Rute> selectedRute = 
            listRute.stream().filter(x->x.getKodeRute().equals(kodeRute)).findFirst();
        if(selectedRute.isPresent())
            return selectedRute.get();
        return null;

    }
    public Rute GetByNamaKotaAsal(String namaKota)
    {
        List<Rute> listRute = repository.getAll();
        Optional<Rute> selectedRute = 
            listRute.stream().filter(x->x.getKotaAsal().getNamaKota().equals(namaKota)).findFirst();
        if(selectedRute.isPresent())
            return selectedRute.get();
        return null;

    }

    public Rute GetByNamaKotaTujuan(String namaKota)
    {
        List<Rute> listRute = repository.getAll();
        Optional<Rute> selectedRute = 
            listRute.stream().filter(x->x.getKotaTujuan().getNamaKota().equals(namaKota)).findFirst();
        if(selectedRute.isPresent())
            return selectedRute.get();
        return null;

    }

    public void edit(Rute rute)
    {
        List<Rute> listRute = repository.getAll();
        int indexFound = -1;
        for(int i = 0; i< listRute.size(); i++)
        {
            Rute kta = listRute.get(i);
            
            if(kta.getKodeRute().equals(rute.getKodeRute()))
            {
                indexFound = i;
                break;
            }
        }
            if (indexFound != -1) {
            try {
                listRute.remove(indexFound);
                listRute.add(rute);
                repository.update(listRute);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        
      
    }

    public void delete(Rute rute)
    {
        List<Rute> listRute = repository.getAll();
        int indexFound = -1;
        for(int i = 0; i< listRute.size(); i++)
        {
            Rute kta = listRute.get(i);
            
            if(kta.getKodeRute().equals(rute.getKodeRute()))
            {
                indexFound = i;
                break;
            }
        }
            if (indexFound != -1) {
            try {
                listRute.remove(indexFound);
                repository.update(listRute);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        
      
    }

}