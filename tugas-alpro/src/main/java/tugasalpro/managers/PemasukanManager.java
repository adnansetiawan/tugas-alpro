package tugasalpro.managers;

import java.util.Date;
import java.util.List;

import tugasalpro.*;
import tugasalpro.models.*;

public class PemasukanManager{
    private Repository<Pemasukan> repository;

    public PemasukanManager(){
        repository=new Repository<>("Pemasukan",Pemasukan[].class);
    }

    public void add(Pemasukan pemasukan){
        repository.add(pemasukan);
    }

    public List<Pemasukan> getAll(){
        return repository.getAll();
    }

    public Pemasukan getByTanggalKereta(Date tanggal, Kereta kereta){
        List<Pemasukan> listPemasukan = repository.getAll();
        Pemasukan pemasukan = new Pemasukan();
        int jml=0;
        for(int i=0;i<listPemasukan.size();i++){
            if(listPemasukan.get(i).getTanggal().equals(tanggal)&&listPemasukan.get(i).getKereta().equals(kereta)){
                jml+=listPemasukan.get(i).getPemasukan();
            }
        }
        pemasukan.setTanggal(tanggal);
        pemasukan.setKereta(kereta);
        pemasukan.setPemasukan(jml);
        return pemasukan;
    }

    public int getIndexByTanggalKereta(Date tanggal, Kereta kereta){
        List<Pemasukan> listPemasukan = repository.getAll();
        int index = -1;
        for(int i=0; i<listPemasukan.size(); i++){
            if(listPemasukan.get(i).getTanggal().equals(tanggal)&&listPemasukan.get(i).getKereta().equals(kereta)){
                index = i;
                break;
            }            
        }
        return index;
    }

    public void edit(Date tanggal, Kereta kereta, int jmlPemasukan){
        try{
            int indexFound = getIndexByTanggalKereta(tanggal,kereta);
            Pemasukan pemasukan = getByTanggalKereta(tanggal,kereta);
            pemasukan.setTanggal(tanggal);
            pemasukan.setKereta(kereta);
            pemasukan.setPemasukan(pemasukan.getPemasukan()+jmlPemasukan);
            repository.edit(pemasukan, indexFound);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}