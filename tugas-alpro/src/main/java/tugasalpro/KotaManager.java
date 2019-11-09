package tugasalpro;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class KotaManager {
     private Repository<Kota> repository;
    public KotaManager()
    {
        repository = new Repository<Kota>("Kota");
    }
    
    public void Save(Kota kota) throws IOException, URISyntaxException
    {
         repository.save(kota);  
    
    }
    public List<Kota> GetAll() throws IOException, URISyntaxException
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