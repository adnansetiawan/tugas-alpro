package tugasalpro.models;

import java.util.List;

public class Tiket
{
    private String kode;
    private List<Penumpang> penumpang;
    public Tiket(String kode, List<Penumpang> penumpang)
    {
         this.kode= kode;
         this.penumpang=penumpang;
    }
    public String getKode()
    {
        return this.kode;

    }
    public List<Penumpang> getPenumpang()
    {
        return this.penumpang;
    }
    
}