package tugasalpro.managers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import tugasalpro.models.Kereta;
import tugasalpro.Repository;

public class KeretaManager {
    private Repository<Kereta> repository;
    public KeretaManager()
    {
       repository = new Repository<Kereta>("Kereta", Kereta[].class);

    }
    
    public void Save(Kereta kereta)
    {
        repository.add(kereta);  
    
    }

    public List<Kereta> GetAll()
    {
        return repository.getAll();
    }

    public Kereta getByKodeKereta(String kodeKereta)
    {
        List<Kereta> keretas = repository.getAll();
        Optional<Kereta> selectedKereta = keretas.stream().filter(x->x.getKodeKereta().equals(kodeKereta)).findFirst();
        if(selectedKereta.isPresent())
        {
            return selectedKereta.get();
        }
        return null;
    }

    public void add(Kereta kereta)
    {
        repository.add(kereta); 
    }
    public int getIndexById(String id){
        List<Kereta> listKereta = repository.getAll();
        int index = -1;
        for(int i=0; i<listKereta.size(); i++){
            if(listKereta.get(i).getId().equals(id)){
                index = i;
                break;
            }            
        }
        return index;
    }
    public void edit(Kereta kereta)
    {
        int indexFound = getIndexById(kereta.getId());
        if(indexFound != -1)
        {
            repository.edit(kereta, indexFound);
        }
    }

    public void delete(Kereta kereta)
    {
        int indexFound = getIndexById(kereta.getId());
        if(indexFound != -1)
        {
            repository.delete(kereta, indexFound);
        }
    }

}