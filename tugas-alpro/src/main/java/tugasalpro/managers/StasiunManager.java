package tugasalpro.managers;

import java.util.List;
import java.util.Optional;

import tugasalpro.Repository;
import tugasalpro.models.Stasiun;

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

    public Stasiun getByNamaStasiun(String namaStasiun){
        List<Stasiun> listStasiun = repository.getAll();
        Optional<Stasiun> selectedStasiun = listStasiun.stream().filter(x->x.getNamaStasiun().equals(namaStasiun)).findFirst();
        if (selectedStasiun.isPresent()){
            return selectedStasiun.get();
        }else{
            return null;
        }
    }

    public int getIndexByKodeStasiun(String kodeStasiun){
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
    public int getIndexById(String id){
        List<Stasiun> listStasiun = repository.getAll();
        int index = -1;
        for(int i=0; i<listStasiun.size(); i++){
            if(listStasiun.get(i).getId().equals(id)){
                index = i;
                break;
            }            
        }
        return index;
    }

    public int getIndexByNamaStasiun(String namaStasiun){
        List<Stasiun> listStasiun = repository.getAll();
        int index = -1;
        for(int i=0; i<listStasiun.size(); i++){
            if(listStasiun.get(i).getNamaStasiun().equals(namaStasiun)){
                index = i;
                break;
            }            
        }
        return index;
    }

    public void edit(Stasiun stasiun){
        try{
            int indexFound = getIndexById(stasiun.getId());
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