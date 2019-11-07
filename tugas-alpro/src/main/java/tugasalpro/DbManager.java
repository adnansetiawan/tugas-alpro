
package tugasalpro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


public class DbManager {
    public static enum FileName {
        Users
    }
    private File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }

    }
    public <T> void write(FileName fileName, T data) throws IOException, URISyntaxException 
    {
        File file = getFileFromResources(fileName.toString()+".json");
        Gson gson = new GsonBuilder().create();
        Type type = new TypeToken<T>() {}.getType();  
        String jsonContent = gson.toJson(data, type);
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);
        br.write(jsonContent);
        br.close();
        fr.close();
       
        
    }
}
