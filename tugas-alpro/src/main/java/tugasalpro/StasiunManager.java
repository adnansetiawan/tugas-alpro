package tugasalpro;

import java.util.List;
import java.util.Optional;

public class StasiunManager{
    private Repository<Stasiun> repository;

    public StasiunManager(){
        repository=new Repository<Stasiun>("listStasiun".Stasiun[].class)
    }

    public void add(Stasiun stasiun){
        repository.add(stasiun);
    }

    public List<Stasiun> getAll(){
        return repository.getAll();
    }

    public Stasiun getByKodeStasiun(String kodeStasiun){
        List<Stasiun> listStasiun = repository.getAll();
        Optional<Stasiun> selectedStasiun = listStasiun.stream().filter(x->x.getKodeStasiun().equals(kodeStasiun).findFirst());
        if (selectedStasiun.isPresent()){
            return selectedStasiun.get();
        }else{
            return null;
        }
    }

    public void edit(Stasiun stasiun){
        List<Stasiun> listStasiun = repository.getAll();
        int indexFound=-1;
        do{
            Stasiun stn = listStasiun.get(i);
            if(stn.getKodeStasiun().equals(stasiun.getKodeStasiun())){
                indexFound=i;
            }else{
                i++;
            }
        }while(indexFound==-1);
        try{
            listStasiun.remove(indexFound);
            listStasiun.add(stasiun);
            repository.update(listStasiun);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void delete(Stasiun stasiun){
        List<Stasiun> listStasiun = repository.getAll();
        int indexFound=-1;
        do{
            Stasiun stn = listStasiun.get(i);
            if(stn.getKodeStasiun().equals(stasiun.getKodeStasiun())){
                indexFound=i;
            }else{
                i++;
            }
        }while(indexFound==-1);
        try{
            listStasiun.remove(indexFound);
            repository.update(listStasiun);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}