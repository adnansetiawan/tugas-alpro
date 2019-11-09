
package tugasalpro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.gson.reflect.TypeToken;


public class Repository<T> {
    private String fileName;
    private Class<T[]> className;
    public Repository(String fileName, Class<T[]> className)
    {
        this.fileName = fileName;
        this.className = className;
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
    public void add(T data)
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
        try {
            FileWriter fr = new FileWriter(file, false);
            fr.write(jsonContent);
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
       
        
    }
    public void update(List<T> data)
    {
        File file = getFileFromResources(this.fileName.toString()+".json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type type = new TypeToken<List<T>>() {}.getType();  
        List<T> existingData = getAll();
        existingData = data; 
        String jsonContent = gson.toJson(existingData, type);
        try {
            FileWriter fr = new FileWriter(file, false);
            fr.write(jsonContent);
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
       
        
    }
    public List<T> getAll() 
    {
        File file = getFileFromResources(this.fileName.toString()+".json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        byte[] byteData=null;
        try {
            byteData = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String jsonStr = new String(byteData);
        T[] result = gson.fromJson(jsonStr, this.className);
        return new LinkedList<T>(Arrays.asList(result));
        
    }
}
