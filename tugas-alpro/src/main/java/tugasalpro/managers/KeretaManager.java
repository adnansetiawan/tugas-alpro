package tugasalpro.managers;

import java.util.List;
import java.util.Optional;

import tugasalpro.models.Kereta;
import tugasalpro.*;

public class KeretaManager {
    private Repository<Kereta> repository;

    public KeretaManager() {
       repository = new Repository<Kereta>("Kereta", Kereta[].class);
    }

    public void add(Kereta kereta) {
        repository.add(kereta); 
    }

    public List<Kereta> GetAll() {
        return repository.getAll();
    }

    public Kereta getByKodeKereta(String kodeKereta)
    {
        List<Kereta> keretas = repository.getAll();
        Optional<Kereta> selectedKereta = keretas.stream().filter(x->x.getKodeKereta().equals(kodeKereta)).findFirst();
        if(selectedKereta.isPresent()) {
            return selectedKereta.get();
        }
        else{
            return null;
        }
    }

    public int getIndexById(String id){
        List<Kereta> listKereta = repository.getAll();
        int index = -1;
        for(int i=0; i<listKereta.size(); i++) {
            if(listKereta.get(i).getId().equals(id)) {
                index = i;
                break;
            }            
        }
        return index;
    }

    public int getIndexByKodeKereta(String kodeKereta){
        List<Kereta> listKereta = repository.getAll();
        int index = -1;
        for(int i=0; i<listKereta.size(); i++) {
            if(listKereta.get(i).getKodeKereta().equals(kodeKereta)){
                index = i;
                break;
            }            
        }
        return index;
    }

    public void edit(Kereta kereta) {
        try{
            int indexFound = getIndexById(kereta.getId());
            repository.edit(kereta, indexFound);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(Kereta kereta) {
        try{
            int indexFound = getIndexByKodeKereta(kereta.getKodeKereta());
            repository.delete(kereta, indexFound);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}