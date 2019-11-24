package tugasalpro.managers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.google.gson.internal.Streams;

import tugasalpro.models.*;
import tugasalpro.*;

public class UserManager {

    private Repository<User> repository;
    public UserManager()
    {
        repository = new Repository<User>("Users", User[].class);
    }
    
    public void add(User user)
    {
         repository.add(user);  
    
    }
    public List<User> getAll()
    {
        return repository.getAll();
    }
    public User getByUsername(String username)
    {
        List<User> users = repository.getAll();
        Optional<User> selectedUser = 
            users.stream().filter(x->x.getUsername().equals(username)).findFirst();
        if(selectedUser.isPresent())
            return selectedUser.get();
        return null;

    }
    public User getByKtp(String ktp)
    {
        int indexFound = getIndexByKtp(ktp);
        if(indexFound == -1)
            return null;
        return repository.getAll().get(indexFound);

    }
    private int getIndexByKtp(String ktp)
    {
        List<User> users = repository.getAll();
        int index = -1;
        for(int i=0; i<users.size(); i++)
        {
            
            if(users.get(i).getUserInfo().getKtp().equals(ktp))
            {
                index = i;
                break;
            }
            
        }
        return index;

    }

    public User getByEmail(String email)
    {
        List<User> users = repository.getAll();
        if (users.size()>0)
        {
            Optional<User> selectedUser = 
            users.stream().filter(x->x.getUsername().equals(email)).findFirst();
            if(selectedUser.isPresent())
                return selectedUser.get();
            return null;
        }
        else
        {
            return null;
        }
    }

    public void update(User user)
    {
        
        try
        {
            int indexFound = getIndexByKtp(user.getUserInfo().getKtp());
            repository.edit(user, indexFound);
           
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
           
        }
        
      
    }
    public void delete(User user)
    {
        
        try
        {
            int indexFound = getIndexByKtp(user.getUserInfo().getKtp());
            repository.delete(user, indexFound);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
           
        }
        
      
    }
  
   
    
}