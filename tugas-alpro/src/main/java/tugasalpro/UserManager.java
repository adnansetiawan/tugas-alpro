package tugasalpro;

import java.util.List;
import java.util.Optional;


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
    public User GetByUsername(String username)
    {
        List<User> users = repository.getAll();
        Optional<User> selectedUser = 
            users.stream().filter(x->x.getUsername().equals(username)).findFirst();
        if(selectedUser.isPresent())
            return selectedUser.get();
        return null;

    }
    public User GetByKtp(String ktp)
    {
        List<User> users = repository.getAll();
        Optional<User> selectedUser = 
            users.stream().filter(x->x.getUserInfo().geKtp().equals(ktp)).findFirst();
        if(selectedUser.isPresent())
            return selectedUser.get();
        return null;

    }
  
   
    
}