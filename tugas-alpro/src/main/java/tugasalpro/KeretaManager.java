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


    public void add() {

    }

    public void view() {

    }

    public void edit() {

    }

    public void del() {

    }

}