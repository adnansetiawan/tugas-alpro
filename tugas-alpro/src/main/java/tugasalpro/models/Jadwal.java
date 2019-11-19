package tugasalpro.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import tugasalpro.models.Waktu;

public class Jadwal {
    private String kodeJadwal;
    private Date tanggalJadwal;
    private Waktu waktuBerangkat;
    private Waktu waktuTiba;
    private Kota kotaKeberangkatan;
    private Kota kotaTujuan;
    private Kereta kereta;
   
    private List<Kursi> kursi;

    public Jadwal() {
        this.kursi = new ArrayList<Kursi>();
        
    }

    public Jadwal(String kodeJadwal, Date tanggalJadwal, Waktu waktuBerangkat, Waktu waktuTiba, Kota kotaKeberangkatan, Kota kotaTujuan, Kereta kereta) {
        this.kodeJadwal = kodeJadwal;
        this.tanggalJadwal = tanggalJadwal;
        this.waktuBerangkat = waktuBerangkat;
        this.waktuTiba = waktuTiba;
        this.kotaKeberangkatan = kotaKeberangkatan;
        this.kotaTujuan = kotaTujuan;
        this.kereta = kereta;
        this.kursi = new ArrayList<Kursi>();
        generateKursi();
    }

    private void generateAvailableKursiByGerbong(int jmlGerbong, String kategori, String prefix, int row)
    {
        for(int i=1; i<= jmlGerbong; i++) 
        {
            int l=1;
               
            for(int j=1; j<=row; j++)
            {
                for(int k=1; k<= 10; k++)
                {
                    String padded = String.format("%01d" , l);
                    String kodeKursi = prefix + (i) + "-" + padded;
                    Gerbong gerbong = new Gerbong(kategori, i);
                    kursi.add(new Kursi(kodeKursi, gerbong,true));
                    l++;
                }
            }
        }
    }

    public void generateKursi()
    {
        kursi.clear();
        int jmlGBisnis = kereta.getJmlGBisnis();
        generateAvailableKursiByGerbong(jmlGBisnis, "Bisnis", "B", 1);
        int jmlGPremium = kereta.getJmlGPremium();
        generateAvailableKursiByGerbong(jmlGPremium, "Premium", "P", 2);
        
    }

    public String getKodeJadwal() {
        return this.kodeJadwal;
    }

    public void setKodeJadwal(String kodeJadwal) {
        this.kodeJadwal = kodeJadwal;
    }

    public Date getTanggalJadwal() {
        return this.tanggalJadwal;
    }

    public void setTanggalJadwal(Date tanggalJadwal) {
        this.tanggalJadwal = tanggalJadwal;
    }

    public Waktu getWaktuBerangkat() {
        return this.waktuBerangkat;
    }

    public void setWaktuBerangkat(Waktu waktuBerangkat) {
        this.waktuBerangkat = waktuBerangkat;
    }

    public Waktu getWaktuTiba() {
        return this.waktuTiba;
    }

    public void setWaktuTiba(Waktu waktuTiba) {
        this.waktuTiba = waktuTiba;
    }

    public Kota getKotaKeberangkatan() {
        return this.kotaKeberangkatan;
    }

    public void setKotaKeberangkatan(Kota kotaKeberangkatan) {
        this.kotaKeberangkatan = kotaKeberangkatan;
    }

    public Kota getKotaTujuan() {
        return this.kotaTujuan;
    }

    public void setKotaTujuan(Kota kotaTujuan) {
        this.kotaTujuan = kotaTujuan;
    }

    public Kereta getKereta() {
        return this.kereta;
    }

    public void setKereta(Kereta kereta) {
        this.kereta = kereta;
    }

    public int getKursiKosong() {
        long kursiKosongFromList = kursi.stream()
        .filter(c -> c.getIsAvailable())
        .count();
        return (int) kursiKosongFromList;
    }

    public List<Kursi> getKursi()
    {
        return this.kursi;

    }

    public int bookingKursi(String kodeKursi, boolean setIsAvailable)
    {
        int indexFound = -1;
        Kursi selectedKursi = null;
        for(int i=0; i<kursi.size(); i++)
        {
            if(kursi.get(i).getKodeKursi().equals(kodeKursi))
            {
                indexFound  = i;
                selectedKursi = kursi.get(i);
                break;
            }
        }
        if(selectedKursi != null)
        {
            if(!selectedKursi.getIsAvailable())
            {
                return -1;
            }
            selectedKursi.setIsAvailable(setIsAvailable);
            kursi.set(indexFound, selectedKursi);
        }
        return indexFound;
    }
    

}