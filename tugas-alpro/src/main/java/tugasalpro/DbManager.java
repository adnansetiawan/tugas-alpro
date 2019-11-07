
package tugasalpro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;


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
    public void write(FileName fileName, String data) throws IOException, URISyntaxException 
    {
        File file = getFileFromResources(fileName.toString()+".json");
       
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);
        br.write(data);
        br.close();
        fr.close();
       
        
    }
}
