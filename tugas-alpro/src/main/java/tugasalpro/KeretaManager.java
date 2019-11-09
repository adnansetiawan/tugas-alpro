package tugasalpro;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

public class KeretaManager {
    private Repository<Kereta> repository;
    public KeretaManager()
    {
        repository = new Repository<Kereta>("Keretas", Kereta[].class);
    }
    
    public void Save(Kereta kereta) throws IOException, URISyntaxException
    {
         repository.save(kereta);  
    
    }
    public List<Kereta> GetAll() throws IOException, URISyntaxException
    {
        return repository.getAll();
    }


    public Kereta getByKodeKereta(String kodeKereta) throws IOException, URISyntaxException
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