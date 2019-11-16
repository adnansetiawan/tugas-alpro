package tugasalpro.models;
public class UserInfo
{
    private String nama;
    private String ktp;
    private String handphone;
    
    public UserInfo(String nama, String ktp, String handphone)
    {
        this.nama = nama;
        this.ktp = ktp;
        this.handphone = handphone;
    }
    public String getName()
    {
        return this.nama;
    }
    public String getKtp()
    {
        return this.ktp;
    }
    public String getHandphone()
    {
        return this.handphone;
    }
}