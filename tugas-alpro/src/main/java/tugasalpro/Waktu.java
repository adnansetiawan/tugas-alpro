package tugasalpro;
/**
 *
 * @author Iswahyudi
 */
public class Waktu {
    private String kodeWaktu;
    private String waktu;
    
    public Waktu() {
    }
    
    public Waktu(String kodeWaktu, String waktu) {
        this.kodeWaktu = kodeWaktu;
        this.waktu = waktu;
    }
    
    public String getKodeWaktu() {
        return this.kodeWaktu;
    }
    
    public void setKodeWaktu(String kodeWaktu) {
        this.kodeWaktu = kodeWaktu;
    }
    
    public String getWaktu() {
        return this.waktu;
    }
    
    public void setWaktu(String textWaktu)
    {
        this.waktu = textWaktu;
    }
}
