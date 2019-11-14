package tugasalpro.managers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.google.gson.internal.Streams;

import tugasalpro.models.*;
import tugasalpro.*;

public class JalurRuteManager{
    private final Repository<JalurRute> repository;

    public JalurRuteManager(){
        repository=new Repository<>("JalurRute",JalurRute[].class);
    }

    public void add(JalurRute jalurRute){
        repository.add(jalurRute);
    }

    public List<JalurRute> getAll(){
        return repository.getAll();
    }

    public JalurRute getByKodeJalur(String kodeJalur){
        List<JalurRute> listJalurRute=repository.getAll();
        Optional<JalurRute> selectedJalurRute = listJalurRute.stream().filter(x->x.getKodeJalur().equals(kodeJalur)).findFirst();
        if(selectedJalurRute.isPresent()){
            return selectedJalurRute.get();
        }else{
            return null;
        }
    }

    private int getIndexByKodeJalur(String kodeJalur){
        List<JalurRute> listJalurRute = repository.getAll();
        int index = -1;
        for(int i=0; i<listJalurRute.size(); i++){
            if(listJalurRute.get(i).getKodeJalur().equals(kodeJalur)){
                index = i;
                break;
            }            
        }
        return index;
    }

    public void delete(JalurRute jalurRute){
        try{
            int indexFound = getIndexByKodeJalur(jalurRute.getKodeJalur());
            repository.delete(jalurRute, indexFound);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
           
        }
    }
}