package tugasalpro.managers;

import java.util.List;
import java.util.Optional;

import tugasalpro.Repository;
import tugasalpro.models.Rute;
import tugasalpro.models.WaktuRute;

public class WaktuRuteManager
{
    private Repository<WaktuRute> repository;

    public WaktuRuteManager()
    {
       repository = new Repository<WaktuRute>("WaktuRute", WaktuRute[].class);
    }
    
    public List<WaktuRute> GetAll()
    {
        return repository.getAll();
    }

    public void add(WaktuRute waktuRute)
    {
        repository.add(waktuRute); 
    }

    public void delete(WaktuRute waktuRute)
    {
        List<WaktuRute> listWaktuRute = repository.getAll();
        for(int i = 0; i<listWaktuRute.size(); i++)
        {
            WaktuRute wktrute = listWaktuRute.get(i);
            if(wktrute.getKodeWaktuRute().equals(waktuRute.getKodeWaktuRute()))
            {
                try {
                    listWaktuRute.remove(i);
                    repository.update(listWaktuRute);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                i=0;
            }
        }
    }

    public WaktuRute getByKodeWaktuRute(String kodeWaktuRute)
    {
        List<WaktuRute> textwaktuRute = repository.getAll();
        Optional<WaktuRute> selectedWaktuRute = textwaktuRute.stream().filter(x->x.getKodeWaktuRute().equals(kodeWaktuRute)).findFirst();
        if(selectedWaktuRute.isPresent())
        {
            return selectedWaktuRute.get();
        }
        return null;
    }

    public WaktuRute getByKodeRute(String kodeRute)
    {
        List<WaktuRute> textwaktuRute = repository.getAll();
        Optional<WaktuRute> selectedWaktuRute = textwaktuRute.stream().filter(x->x.getRute().getKodeRute().equals(kodeRute)).findFirst();
        if(selectedWaktuRute.isPresent())
        {
            return selectedWaktuRute.get();
        }
        return null;
    }

    public WaktuRute getByRute(Rute rute)
    {
       
        List<WaktuRute> textwaktuRute = repository.getAll();
        Optional<WaktuRute> selectedWaktuRute = textwaktuRute.stream().filter(x->x.getRute().getKodeRute().equals(rute.getKodeRute())).findFirst();
        if(selectedWaktuRute.isPresent())
        {
            return selectedWaktuRute.get();
        }
        return null;
    }

    public WaktuRute getByLast()
    {
        List<WaktuRute> textwaktuRute = repository.getAll();
        if (textwaktuRute.size()==0) {
            return null;
        } else {
            return textwaktuRute.get(textwaktuRute.size()-1);
        }
    }

    /*
    // this code below was comment due to correcting to the base archttecture

    public WaktuRute getByKodeRuteWaktu(String kodeRute, String kodeWaktu)
    {
        List<WaktuRute> textRute = repository.getAll();
        Optional<WaktuRute> selectedRute = textRute.stream().filter(x->x.getKodeRute().equals(kodeRute) && x.getArrWaktu().equals(kodeWaktu)).findFirst();
        if(selectedRute.isPresent())
        {
            return selectedRute.get();
        }
        return null;
    }

    

    public void tambahRute()
    {
        String hasil = new RepositoryText().bacaFile("NoWaktuRute.json");
        hasil = hasil==""?"0":hasil;
        int noakhir = Integer.parseInt(hasil);
        noakhir = noakhir+1;
        int status = 0;
        textKodeWaktuRute = "WR"+noakhir;
        while (status == 0)
        {
            System.out.print("Kode Rute : ");
            textKodeRute = keyb.nextLine();
            if (textKodeRute.compareTo("99")==0)
            {
                status = 2;
            }
            else
            {
                Rute textRute = new RuteManager().GetByKodeRute(textKodeRute);
                if (textRute != null)
                {
                    WaktuRute textBanding = getByKodeRute(textKodeRute);
                    if (textBanding != null)
                    {
                        System.out.println("Data sudah ada.");
                    }
                    else
                    {
                        status=1;
                    }
                }
                else
                {
                    System.out.println("Kode Rute tidak ditemukan.");
                }
            }
        }
        if (status==1)
        {
            System.out.println();
            System.out.println("Waktu Available Untuk Rute");
            System.out.println("-----------------------------------------------------------------");
            int status2 = 0;
            int i = 1;
            while (status2 == 0)
            {
                System.out.print("Time "+i+" : ");
                textKodeWaktu = keyb.nextLine();
                if (textKodeWaktu.compareTo("99")==0)
                {
                    status2 = 2;
                }
                else
                {
                    Waktu textWaktu = new WaktuManager().GetByKodeWaktu(textKodeWaktu);
                    if (textWaktu != null)
                    {
                        WaktuRute textBanding = getByKodeRuteWaktu(textKodeRute, textKodeWaktu);
                        if (textBanding != null)
                        {
                            System.out.println("Data sudah ada.");
                        }
                        else
                        {
                            WaktuRute waktuRute=new WaktuRute(textKodeWaktuRute,textKodeRute,textKodeWaktu);
                            repository.add(waktuRute);
                            new RepositoryText().tulisFile(String.valueOf(noakhir), "NoWaktuRute.json");
                            i = i+1;
                        }
                    }
                    else
                    {
                        System.out.println("Kode Waktu tidak ditemukan.");
                    }
                }
            }
            System.out.println("-------------------------------------------------------");
            System.out.println("Waktu Untuk Rute Berhasil Ditambahkan");
            System.out.println("-------------------------------------------------------");
        }
    }

    public void hapusRute()
    {
        int status = 0;
        do 
        {
            System.out.print("Hapus Kode Rute : ");
            textKodeRute = keyb.nextLine();
            if (textKodeRute.compareTo("99")==0)
            {
                status=1;
            }
            else
            {
                WaktuRute dataWaktuRute = getByKodeRute(textKodeRute);
                if (dataWaktuRute != null)
                {
                    delete(dataWaktuRute);
                    System.out.println("Kode Rute "+textKodeRute+" berhasil dihapus.");
                    status = 1;
                }
                else
                {
                    System.out.println("Kode Rute "+textKodeRute+" tidak ditemukan.");
                }
            }
        } while (status==0);
    }
    */
}
