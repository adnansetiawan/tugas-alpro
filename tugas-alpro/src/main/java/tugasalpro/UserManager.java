package tugasalpro;

import java.io.IOException;
import java.net.URISyntaxException;

public class UserManager {

    public void Save(User user) throws IOException, URISyntaxException
    {
       DbManager dbManager = new DbManager();
       dbManager.write(DbManager.FileName.Users, user);
    }
   
    
}