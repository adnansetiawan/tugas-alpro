package tugasalpro.managers;

import java.util.List;
import java.util.Optional;

import tugasalpro.models.KeretaRute;
import tugasalpro.Repository;

public class KeretaRuteManager {
    private Repository<KeretaRute> repository;
    public KeretaRuteManager()
    {
       repository = new Repository<KeretaRute>("KeretaRute", KeretaRute[].class);

    }
    
    public void add(KeretaRute keretaRute)
    {
         repository.add(keretaRute);  
    
    }

    public List<KeretaRute> GetAll()
    {
        return repository.getAll();
    }

    public KeretaRute getByKodeKeretaRute(String kodeKeretaRute)
    {
        List<KeretaRute> listKeretaRute = repository.getAll();
        Optional<KeretaRute> selectedKeretaRute = listKeretaRute .stream().filter(x->x.getKodeKeretaRute().equals(kodeKeretaRute)).findFirst();
        if (selectedKeretaRute.isPresent())
        {
            return selectedKeretaRute.get();
        } else {
            return null;
        }
        
    }


     public void edit(KeretaRute keretaRute)
    {
        List<KeretaRute> listKeretaRute = repository.getAll();
        int indexFound = -1;
        for(int i = 0; i< listKeretaRute.size(); i++)
        {
            KeretaRute kta = listKeretaRute.get(i);
            
            if(kta.getKodeKeretaRute().equals(keretaRute.getKodeKeretaRute()))
            {
                indexFound = i;
                break;
            }
        }
            if (indexFound != -1) {
            try {
                listKeretaRute.remove(indexFound);
                listKeretaRute.add(keretaRute);
                repository.update(listKeretaRute);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        
      
    }

    public void delete(KeretaRute keretaRute)
    {
        List<KeretaRute> listKeretaRute = repository.getAll();
        int indexFound = -1;
        for(int i = 0; i< listKeretaRute.size(); i++)
        {
            KeretaRute kta = listKeretaRute.get(i);
            
            if(kta.getKodeKeretaRute().equals(keretaRute.getKodeKeretaRute()))
            {
                indexFound = i;
                break;
            }
        }
            if (indexFound != -1) {
            try {
                listKeretaRute.remove(indexFound);
                repository.update(listKeretaRute);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
      
    }

}