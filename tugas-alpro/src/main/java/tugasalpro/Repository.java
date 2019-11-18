
package tugasalpro;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
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
  
    
    public boolean add(T data)
    {
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
          
            File file = getFile(this.fileName);

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
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type type = new TypeToken<List<T>>() {}.getType();  
        List<T> existingData = getAll();
        existingData = data; 
        String jsonContent = gson.toJson(existingData, type);
        try {
            File file = getFile(this.fileName);
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
    private File getFile(String filename)
    {
        Path databasePath = FileSystems.getDefault().getPath("database").toAbsolutePath();;
        File file = new File(databasePath.toString(), this.fileName+".json");
        if(!file.exists())
        {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return file;
        

    }
    public List<T> getAll() 
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        byte[] byteData=null;
        try {
            File f = getFile(this.fileName.toString()+".json");
          
            byteData = Files.readAllBytes(f.toPath());
            
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
