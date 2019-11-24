package tugasalpro.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Waktu implements Comparable<Waktu> {
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

    public void setWaktu(String textWaktu) {
        this.waktu = textWaktu;
    }

    @Override
    public int compareTo(Waktu o) {
        
        String waktu1 = getWaktu().replace('.', ':');
        String waktu2 = o.getWaktu().replace('.', ':');

        DateFormat sdf = new SimpleDateFormat("hh:mm");
        Date dateWaktu1 = null;
        Date dateWaktu2 = null;
        try {
            dateWaktu1 = sdf.parse(waktu1);
            dateWaktu2 = sdf.parse(waktu2);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (dateWaktu1 == null || dateWaktu2  == null) {
            return 0;
        } else {
            return dateWaktu1.compareTo(dateWaktu2);
        }
        
    }
}
