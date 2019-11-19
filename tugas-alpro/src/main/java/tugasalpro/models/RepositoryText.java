package tugasalpro.models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
//import java.net.URL;
import java.io.File;
 
public class RepositoryText {
    // Method tulis file

	public RepositoryText()
    {
    }
    
    public void tulisFile(String teks, String namaFile) {
        File namaTxt = getFileFromResources(namaFile);
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new FileWriter(namaTxt, false)));
            out.print(teks);
            out.close();
        } catch (IOException e) {
            System.out.println("Gagal menulis ke file " + namaFile);
            e.printStackTrace();
        }
    }
 
    // Method baca file
    public String bacaFile(String namaFile) {
        File namaTxt = getFileFromResources(namaFile);
        BufferedReader br = null;
        String stringHasil = "";
 
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(namaTxt));
            while ((sCurrentLine = br.readLine()) != null) {
                stringHasil = stringHasil + sCurrentLine;
            }
 
        } catch (IOException e) {
            System.out.println("Gagal membaca file " + namaFile);
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return stringHasil;
    }

    public File getFileFromResources(String namaFile)
    {
        String direktory = System.getProperty("user.dir")+"/tugas-alpro/tugas-alpro/database/";
        File file = new File(direktory, namaFile);
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

    /*public File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!, please add the file first");
        } else {
            return new File(resource.getFile());
        }
    }*/

}
