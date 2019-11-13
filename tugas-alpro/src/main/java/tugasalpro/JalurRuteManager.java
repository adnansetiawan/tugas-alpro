package tugasalpro;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

public class JalurRuteManager{
    private Repository<JalurRute> repository;

    public JalurRuteManager(){
        repository=new Repository<JalurRute>("listJalurRute",JalurRute[].class);
    }

    public void add(JalurRute jalurRute){
        repository.add(jalurRute);
    }

    public List<JalurRute> getAll(){
        return repository.getAll();
    }

    public JalurRute getByKodeJalurRute(String kodeJalurRute)
    {
        List<JalurRute> listJalurRute=repository.getAll();
        Optional<JalurRute> selectedJalurRute = listJalurRute.stream().filter(x->x.getKodeJalur().equals(kodeJalurRute)).findFirst();
        if(selectedJalurRute.isPresent()){
            return selectedJalurRute.get();
        }else{
            return null;
        }
    }

    public void delete(JalurRute jalurRute){
        List<JalurRute> listJalurRute=repository.getAll();
        int indexFound=-1;
        for(int i=0;i<listJalurRute.size();i++){
            JalurRute jr=listJalurRute.get(i);
            if(jr.getKodeJalur().equals(jalurRute.getKodeJalur())){
                indexFound=i;
                break;
            }
        }
        if (indexFound!=-1){
            try{
                listJalurRute.remove(indexFound);
                repository.update(listJalurRute);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}