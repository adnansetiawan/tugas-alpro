package tugasalpro.models;

public class Gerbong {
    private String kategori;
    private int nomor;
    public Gerbong() {
    }

    public Gerbong(String kategori, int nomor) {
        this.kategori = kategori;
        this.nomor = nomor;
    }

    public int getNomor() {
        return this.nomor;
    }
    public String getKode() {
        if(kategori.equals("Bisnis"))
        {
            return "B"+this.nomor;
        }
        return "P"+this.nomor;
    }

    
    public String getKategori() {
        return this.kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getNama() {
        if(kategori.equals("Bisnis"))
        {
            return "Bisnis "+this.nomor;
        }
        return "Premium "+this.nomor;
    }    
}