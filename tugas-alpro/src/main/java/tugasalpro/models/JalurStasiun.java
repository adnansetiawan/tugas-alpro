package tugasalpro;

public class JalurStasiun{
    private Stasiun stasiunAsal;
    private Stasiun stasiunTujuan;
    private int durasi;

    public JalurStasiun(){

    }

    public JalurStasiun(Stasiun sA, Stasiun sT, int d){
        stasiunAsal=sA;
        stasiunTujuan=sT;
        durasi=d;
    }

    public void setStasiunAwal(Stasiun sA){
        stasiunAsal=sA;
    }

    public void setStasiunTujuan(Stasiun sT){
        stasiunTujuan=sT;
    }

    public void setDurasi(int d){
        durasi=d;
    }

    public Stasiun getStasiunAwal(){
        return stasiunAsal;
    }

    public Stasiun getStasiunTujuan(){
        return stasiunTujuan;
    }

    public int getDurasi(){
        return durasi;
    }
}