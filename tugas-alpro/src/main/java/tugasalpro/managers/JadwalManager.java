package tugasalpro.managers;
import java.util.List;
import java.util.Optional;

import tugasalpro.Repository;
import tugasalpro.models.Jadwal;

public class JadwalManager {
    private Repository<Jadwal> repository;
    public JadwalManager()
    {
        repository = new Repository<Jadwal>("Jadwal", Jadwal[].class);
    }
    
    public void add(Jadwal jadwal)
    {
         repository.add(jadwal);  
    
    }
    public List<Jadwal> GetAll()
    {
        return repository.getAll();
    }

    public Jadwal GetByKodeJadwal(String kodeJadwal)
    {
        List<Jadwal> listJadwal = repository.getAll();
        Optional<Jadwal> selectedJadwal = 
            listJadwal.stream().filter(x->x.getKodeJadwal().toLowerCase().equals(kodeJadwal.toLowerCase())).findFirst();
        if(selectedJadwal.isPresent())
            return selectedJadwal.get();
        return null;

    }
    public Jadwal GetByTanggal(String tanggal)
    {
        List<Jadwal> listJadwal = repository.getAll();
        Optional<Jadwal> selectedJadwal = 
            listJadwal.stream().filter(x->x.getTanggalJadwal().equals(tanggal)).findFirst();
        if(selectedJadwal.isPresent())
            return selectedJadwal.get();
        return null;

    }
    public void edit(Jadwal jadwal)
    {
        List<Jadwal> listJadwal = repository.getAll();
        int indexFound = -1;
        for(int i = 0; i< listJadwal.size(); i++)
        {
            Jadwal jdw = listJadwal.get(i);
            
            if(jdw.getKodeJadwal().equals(jadwal.getKodeJadwal()))
            {
                indexFound = i;
                break;
            }
        }
            if (indexFound != -1) {
            try {
                repository.edit(jadwal,indexFound);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        
      
    }

    public void delete(Jadwal jadwal)
    {
        List<Jadwal> listJadwal = repository.getAll();
        int indexFound = -1;
        for(int i = 0; i< listJadwal.size(); i++)
        {
            Jadwal jdw = listJadwal.get(i);
            
            if(jdw.getKodeJadwal().equals(jadwal.getKodeJadwal()))
            {
                indexFound = i;
                break;
            }
        }
            if (indexFound != -1) {
            try {
                listJadwal.remove(indexFound);
                repository.update(listJadwal);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    


}