
package tugasalpro;

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

    public Repository()
    {
    }

    public Repository(String fileName, Class<T[]> className)
    {
        this.fileName = fileName;
        this.className = className;
    }
    public File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!, please add the file first");
        } else {
            return new File(resource.getFile());
        }

    }
    public boolean add(T data)
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
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("failed to update");
            return false;
          
        }
       
        
    }
    public boolean update(List<T> data)
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
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("failed to update");
            return false;
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
        if(result == null)
            return new LinkedList<T>();
        return new LinkedList<T>(Arrays.asList(result));
        
    }
    public boolean delete(T data,int index) 
    {
        if(index < 0)
            return false;
        List<T> lists =getAll();
        if(lists.size()== 0)
            return false;
        lists.remove(index);
        update(lists);
        return true;
    }
    public boolean edit(T data,int index) 
    {
        if(index < 0)
            return false;
        List<T> lists =getAll();
        if(lists.size()== 0)
            return false;
        lists.set(index, data);    
        update(lists);
        return true;
    }
}
