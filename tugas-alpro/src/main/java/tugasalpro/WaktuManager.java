package tugasalpro;

import java.util.List;
import java.util.Optional;
/**
 *
 * @author Iswahyudi
 */
import java.util.Scanner;

public class WaktuManager {
    private Repository<Waktu> repository;
    Scanner input;
    private int i, jam, menit;
    private String kode, textJam;
    
    public WaktuManager()
    {
        repository = new Repository<Waktu>("Waktu", Waktu[].class);
    }

    public List<Waktu> GetAll()
    {
        return repository.getAll();
    }
    
    public void Generate()
    {
        input = new Scanner(System.in);
        Waktu textWaktu = new Waktu();
        //repository = new Repository<Waktu>("Waktu", Waktu[].class);
        System.out.print("Apakah anda yakin untuk generate waktu (Y/N)? ");
        char yaTidak = input.next().charAt(0) ;
        if (yaTidak=='Y')
        {
            List<Waktu> existingWaktu = repository.getAll();
            if (existingWaktu.size()>0)
            {
                System.out.println("Data waktu sudah digenerate.");
            }
            else
            {
                for (i=1; i<=95;i++)
                {
                    kode = "TM"+i;
                    if (i==1)
                    {
                        textJam = "00.00";
                    }
                    else
                    {
                        if (i%4 ==0)
                        {
                            jam = jam+1;
                            menit = 0;
                        }
                        else
                        {
                            menit = menit+15;
                        }
                        if (jam < 10)
                        {
                            textJam = "0"+jam+".";
                        }
                        else
                        {
                            textJam = jam+".";
                        }
                        if (menit==0)
                        {
                            textJam = textJam+"00";
                        }
                        else
                        {
                            textJam = textJam+menit;
                        }
                    }
                    textWaktu.setKodeWaktu(kode);
                    textWaktu.setWaktu(textJam); 
                    repository.add(textWaktu);
                }
                System.out.println("Generate Waktu Berhasil!");
            } 
        }
    }

    public Waktu GetByKodeWaktu(String kodeWaktu)
    {
        List<Waktu> listWaktu = repository.getAll();
        if (listWaktu.size()>0)
        {
            Optional<Waktu> selectedWaktu = 
            listWaktu.stream().filter(x->x.getKodeWaktu().equals(kodeWaktu)).findFirst();
            if(selectedWaktu.isPresent())
                return selectedWaktu.get();
            return null;
        }
        else
        {
            return null;
        }

    }
    
}
