package tugasalpro;


import java.util.List;


public class UserManager {

    private Repository<User> repository;
    public UserManager()
    {
        repository = new Repository<User>("Users", User[].class);
    }
    
    public void Save(User user)
    {
         repository.save(user);  
    
    }
    public List<User> GetAll()
    {
        return repository.getAll();
    }
  
   
    
}