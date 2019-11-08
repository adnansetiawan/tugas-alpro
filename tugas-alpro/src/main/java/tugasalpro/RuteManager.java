package tugasalpro;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class RuteManager {
     private Repository<Rute> repository;
    public RuteManager()
    {
        repository = new Repository<Rute>("Rute");
    }
    
    public void Save(Rute rute) throws IOException, URISyntaxException
    {
         repository.save(rute);  
    
    }
    public List<Rute> GetAll() throws IOException, URISyntaxException
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