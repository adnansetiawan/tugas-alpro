package tugasalpro.models;

import java.util.List;

public class Tiket
{
    private String kodeTiket;
    private List<Penumpang> penumpang;
    public Tiket(String kode, List<Penumpang> penumpang)
    {
         this.kodeTiket= kode;
         this.penumpang=penumpang;
    }
    public String getKodeTiket()
    {
        return this.kodeTiket;

    }
    public List<Penumpang> getPenumpang()
    {
        return this.penumpang;
    }
    
}