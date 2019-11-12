package tugasalpro;
public class Gerbong {
    private Kursi[] arrKursi;
    private String kategori;
   
    public Gerbong() {
    }

    public Gerbong(Kursi[] arrKursi, String kategori) {
        this.arrKursi = arrKursi;
        this.kategori = kategori;
    }

    public Kursi[] getArrKursi() {
        return this.arrKursi;
    }

    public void setArrKursi(Kursi[] arrKursi) {
        this.arrKursi = arrKursi;
    }

    public String getKategori() {
        return this.kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public Gerbong arrKursi(Kursi[] arrKursi) {
        this.arrKursi = arrKursi;
        return this;
    }

    public Gerbong kategori(String kategori) {
        this.kategori = kategori;
        return this;
    }
}