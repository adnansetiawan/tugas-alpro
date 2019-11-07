package tugasalpro;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

public class UserManager {

    private Repository<User> repository;
    public UserManager()
    {
        repository = new Repository<User>("Users");
    }
    
    public void Save(User user) throws IOException, URISyntaxException
    {
         repository.save(user);  
    
    }
    public List<User> GetAll() throws IOException, URISyntaxException
    {
        return repository.getAll();
    }
  
   
    
}