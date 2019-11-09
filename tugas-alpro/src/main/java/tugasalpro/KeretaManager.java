package tugasalpro;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class KeretaManager {
    private Repository<Kereta> repository;
    public KeretaManager()
    {
        repository = new Repository<Kereta>("Kereta");
    }
    
    public void Save(Kereta kereta) throws IOException, URISyntaxException
    {
         repository.save(kereta);  
    
    }
    public List<Kereta> GetAll() throws IOException, URISyntaxException
    {
        return repository.getAll();
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