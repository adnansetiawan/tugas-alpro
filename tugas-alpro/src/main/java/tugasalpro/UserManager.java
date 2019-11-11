package tugasalpro;

import java.util.List;
import java.util.Optional;


public class UserManager {

    private Repository<User> repository;
    public UserManager()
    {
        repository = new Repository<User>("Users", User[].class);
    }
    
    public void Add(User user)
    {
         repository.add(user);  
    
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
    public void Update(User user)
    {
        List<User> users = repository.getAll();
        int indexFound = -1;
        for(int i = 0; i< users.size(); i++)
        {
            User usr = users.get(i);
            if(usr.isAdmin())
                continue;
            if(usr.getUserInfo().geKtp().equals(user.getUserInfo().geKtp()))
            {
                indexFound = i;
                break;
            }
        }
            if(indexFound == -1)
            return;
            try
            {
                users.remove(indexFound);
                users.add(user);
                repository.update(users);
                ApplicationSession.setLoggedUser(user);
           }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        
      
    }
  
   
    
}