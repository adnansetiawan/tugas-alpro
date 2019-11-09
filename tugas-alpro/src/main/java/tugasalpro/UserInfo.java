package tugasalpro;
class UserInfo
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
    public String geName()
    {
        return this.nama;
    }
    public String geKtp()
    {
        return this.ktp;
    }
    public String geHandphone()
    {
        return this.handphone;
    }
}