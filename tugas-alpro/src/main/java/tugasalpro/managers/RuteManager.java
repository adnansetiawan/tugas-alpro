package tugasalpro.managers;

import java.util.List;
import java.util.Optional;

import tugasalpro.Repository;
import tugasalpro.models.Rute;

public class RuteManager {
    private static Repository<Rute> repository;

    public RuteManager() {
        repository = new Repository<Rute>("Rute", Rute[].class);
    }

    public void add(Rute rute) {
        repository.add(rute);

    }

    public List<Rute> GetAll() {
        return repository.getAll();
    }

    public Rute GetByKodeRute(String kodeRute) {
        List<Rute> listRute = repository.getAll();
        if (listRute.size()>0)
        {
            Optional<Rute> selectedRute = 
            listRute.stream().filter(x->x.getKodeRute().equals(kodeRute)).findFirst();
            if(selectedRute.isPresent())
                return selectedRute.get();
            return null;
        }
        else
        {
            return null;
        }

    }
    public Rute GetById(String id) {
        List<Rute> listRute = repository.getAll();
        if (listRute.size()>0)
        {
            Optional<Rute> selectedRute = 
            listRute.stream().filter(x->x.getId().equals(id)).findFirst();
            if(selectedRute.isPresent())
                return selectedRute.get();
            return null;
        }
        else
        {
            return null;
        }

    }
    public int getIndexById(String id){
        List<Rute> listRute = repository.getAll();
        int index = -1;
        for(int i=0; i<listRute.size(); i++){
            if(listRute.get(i).getId().equals(id)){
                index = i;
                break;
            }            
        }
        return index;
    }
    public Rute GetByNamaKotaAsal(String namaKota)
    {
        List<Rute> listRute = repository.getAll();
        Optional<Rute> selectedRute = 
            listRute.stream().filter(x->x.getKotaAsal().getNamaKota().equals(namaKota)).findFirst();
        if(selectedRute.isPresent())
            return selectedRute.get();
        return null;

    }

    public Rute GetByNamaKotaTujuan(String namaKota)
    {
        List<Rute> listRute = repository.getAll();
        Optional<Rute> selectedRute = 
            listRute.stream().filter(x->x.getKotaTujuan().getNamaKota().equals(namaKota)).findFirst();
        if(selectedRute.isPresent())
            return selectedRute.get();
        return null;

    }

    public void edit(Rute rute)
    {
        int indexFound = getIndexById(rute.getId());
        if(indexFound != -1)
        {
            repository.edit(rute, indexFound);
        }  
        
      
    }

    public void delete(Rute rute)
    {
        List<Rute> listRute = repository.getAll();
        int indexFound = -1;
        for(int i = 0; i< listRute.size(); i++)
        {
            Rute kta = listRute.get(i);
            
            if(kta.getKodeRute().equals(rute.getKodeRute()))
            {
                indexFound = i;
                break;
            }
        }
            if (indexFound != -1) {
            try {
                listRute.remove(indexFound);
                repository.update(listRute);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        
      
    }

}