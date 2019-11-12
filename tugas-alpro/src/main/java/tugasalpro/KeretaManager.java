package tugasalpro;

import java.util.List;
import java.util.Optional;

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

    public void edit(Kereta kereta)
    {
        List<Kereta> listKereta = repository.getAll();
        int indexFound = -1;
        for(int i = 0; i<listKereta.size(); i++)
        {
            Kereta krt = listKereta.get(i);
            if(krt.getKodeKereta().equals(kereta.getKodeKereta()))
            {
                indexFound = i;
                break;
            }
        }
        if (indexFound != -1){
            try {
                listKereta.remove(indexFound);
                listKereta.add(kereta);
                repository.update(listKereta);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void delete(Kereta kereta)
    {
        List<Kereta> listKereta = repository.getAll();
        int indexFound = -1;
        for(int i = 0; i<listKereta.size(); i++)
        {
            Kereta krt = listKereta.get(i);
            if(krt.getKodeKereta().equals(kereta.getKodeKereta()))
            {
                indexFound = i;
                break;
            }
        }
        if (indexFound != -1) {
            try {
                listKereta.remove(indexFound);
                repository.update(listKereta);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}