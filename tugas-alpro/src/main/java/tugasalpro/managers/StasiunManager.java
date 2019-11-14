package tugasalpro.managers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.google.gson.internal.Streams;

import tugasalpro.models.*;
import tugasalpro.*;

public class StasiunManager{
    private final Repository<Stasiun> repository;

    public StasiunManager(){
        repository=new Repository<>("Stasiun",Stasiun[].class);
    }

    public void add(Stasiun stasiun){
        repository.add(stasiun);
    }

    public List<Stasiun> getAll(){
        return repository.getAll();
    }

    public Stasiun getByKodeStasiun(String kodeStasiun){
        List<Stasiun> listStasiun = repository.getAll();
        Optional<Stasiun> selectedStasiun = listStasiun.stream().filter(x->x.getKodeStasiun().equals(kodeStasiun)).findFirst();
        if (selectedStasiun.isPresent()){
            return selectedStasiun.get();
        }else{
            return null;
        }
    }

    private int getIndexByKodeStasiun(String kodeStasiun){
        List<Stasiun> listStasiun = repository.getAll();
        int index = -1;
        for(int i=0; i<listStasiun.size(); i++){
            if(listStasiun.get(i).getKodeStasiun().equals(kodeStasiun)){
                index = i;
                break;
            }            
        }
        return index;
    }

    public void edit(Stasiun stasiun){
        try{
            int indexFound = getIndexByKodeStasiun(stasiun.getKodeStasiun());
            repository.edit(stasiun, indexFound);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
           
        }
    }

    public void delete(Stasiun stasiun){
        try{
            int indexFound = getIndexByKodeStasiun(stasiun.getKodeStasiun());
            repository.delete(stasiun, indexFound);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
           
        }
    }
}