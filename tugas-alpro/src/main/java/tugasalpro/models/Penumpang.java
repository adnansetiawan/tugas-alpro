package tugasalpro.models;

public class Penumpang
{
    private String name;
    private String KodeKursi;
    public Penumpang(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return this.name;
    }
    public void setKodeKursi(String kodeKursi)
    {
        this.KodeKursi = kodeKursi;
    }
    public String getKodeKursi()
    {
        return this.KodeKursi;
    }
    

}