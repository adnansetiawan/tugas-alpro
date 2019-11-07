
package tugasalpro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


public class Repository<T> {
    private String fileName;
    public Repository(String fileName)
    {
        this.fileName = fileName;
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
    public void save(T data) throws IOException, URISyntaxException 
    {
        File file = getFileFromResources(this.fileName.toString()+".json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type type = new TypeToken<List<T>>() {}.getType();  
        List<T> existingData = getAll();
        if(existingData == null)
        {
            existingData = new ArrayList<T>();
        }
        existingData.add(data);
        String jsonContent = gson.toJson(existingData, type);
        FileWriter fr = new FileWriter(file, false);
        fr.write(jsonContent);
        fr.close();
        
    }
    public List<T> getAll() throws IOException, URISyntaxException 
    {
        File file = getFileFromResources(this.fileName.toString()+".json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        byte[] byteData = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        String jsonStr = new String(byteData);
        Type type = new TypeToken<List<T>>() {}.getType();  
        List<T> jsonContent =  gson.fromJson(jsonStr, type);
        return jsonContent;
        
    }
}
