package tugasalpro.views;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import tugasalpro.managers.JadwalManager;
import tugasalpro.managers.JalurRuteManager;
import tugasalpro.managers.KeretaRuteManager;
import tugasalpro.managers.KotaManager;
import tugasalpro.managers.RuteManager;
import tugasalpro.managers.WaktuManager;
import tugasalpro.managers.WaktuRuteManager;
import tugasalpro.models.Jadwal;
import tugasalpro.models.JalurRute;
import tugasalpro.models.Kereta;
import tugasalpro.models.Kota;
import tugasalpro.models.Rute;
import tugasalpro.models.Waktu;
import tugasalpro.utilities.StringUtility;
import tugasalpro.utilities.TimeUnitUtility;

public class JadwalPage {
    private KotaManager kotaManager;
    private KeretaRuteManager keretaRuteManager;
    private WaktuManager waktuManager;
    private RuteManager ruteManager;
    private JadwalManager jadwalManager;
    private JalurRuteManager jalurRuteManager;
    private WaktuRuteManager waktuRuteManager;
    private TimeUnitUtility timeUtility;
    Scanner scanner;

    public JadwalPage() {
        keretaRuteManager = new KeretaRuteManager();
        jadwalManager = new JadwalManager();
        waktuManager = new WaktuManager();
        kotaManager = new KotaManager();
        ruteManager = new RuteManager();
        jalurRuteManager = new JalurRuteManager();
        waktuRuteManager = new WaktuRuteManager();
        timeUtility = new TimeUnitUtility();


        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int pilihan = 0;
        do {
            System.out.println("Menu Kereta Api");
            System.out.println("1. Generate Jadwal Kereta Api");
            System.out.println("2. Tampilkan Kereta Api");
            System.out.println("99. Keluar");
            System.out.print("Pilihan : ");
            pilihan = scanner.nextInt();
            if (pilihan == 1) {
                menuGenerate();
            } else if (pilihan == 2) {
                menuTampil();
            }

        } while (pilihan != 99);
    }

    public void menuGenerate() {
        final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs
        String pilihan;
        List<Rute> listRute = ruteManager.GetAll();
        List<JalurRute> listJalurRute = jalurRuteManager.getAll();
        List<Jadwal> listJadwal = jadwalManager.GetAll();
        int durasiTerlama = 0;
        String waktuTiba = null;
        int maxHari = 0;

        Collections.reverse(listJalurRute); // sorting berdasarkan durasi descending
        Collections.reverse(listRute); // sorting berdasarkan tanggal descending
        Collections.reverse(listJadwal);

        int lastIndex = -1;
        System.out.print("Anda Yakin untuk generate Jadwal Kereta: Y/N : ");
        pilihan = scanner.next();
        if (pilihan.equals("Y")) {
            // generate Jadwal
            Jadwal jadwal = new Jadwal();
            Date dateobj = new Date();
            if (listJadwal.size()>0) {
                dateobj = listJadwal.get(0).getTanggalJadwal();
            }
            
            long curTimeInMs = 0;

            
            
            // jadwal terlama
            durasiTerlama = listJalurRute.get(0).getDurasi();
            maxHari = timeUtility.getDaysFromMinutes(durasiTerlama*2)+1;
            System.out.println("deb max hari : "+maxHari);
            for (int h=0; h<maxHari; h++) {
                dateobj = timeUtility.addOneDay(dateobj);
                // cek maksimum hari berdasarkan durasi terlama
            
            // untuk setiap jalur rute diambil kereta dan waktuRute
                for (int i=0; i<listJalurRute.size(); i++) {
                    // ambil kereta pada rute
                    
                    ArrayList<Kereta> listKereta = null;
                    try {
                        listKereta = keretaRuteManager.getByKodeRute(listJalurRute.get(i).getRuteJalur().getKodeRute()).getKeretaTersedia();
                    } catch (Exception e) {
                        //TODO: handle exception
                        System.out.println("Terjadi Kesalahan");
                    }
                    // ambil waktu pada rute
                    ArrayList<Waktu> listWaktu = null;
                    try {
                        listWaktu = waktuRuteManager.getByKodeRute(listJalurRute.get(i).getRuteJalur().getKodeRute()).getArrWaktu();
                    } catch (Exception e) {
                        //TODO: handle exception
                        System.out.println("Terjadi Kesalahan");
                    }

                    // jika salah satu kosong, jangan lakukan generate
                    if (listKereta!=null && listWaktu!=null) {
                        if (listKereta.size()>0 && listWaktu.size()>0) {  
                        Collections.sort(listWaktu); // sorting waktu dari yang tercepat/awal
                        int maxIterate = 0;
                        if (listKereta.size()>listWaktu.size()) { // pemilihan perulangan maksimum
                            maxIterate = listWaktu.size();
                        } else {
                            maxIterate = listKereta.size();
                        }

                        for (int j = 0; j < maxIterate; j++) {
                            if (isKeretaAvailableToRun(listJalurRute.get(i).getDurasi()*2, listKereta.get(j), listWaktu.get(j), dateobj)) {
                                lastIndex = jadwalManager.GetAll().size();
                                lastIndex++;
                                curTimeInMs = timeUtility.HHMMtoMilis(listWaktu.get(j).getWaktu());
                                waktuTiba = timeUtility.convertToHHMM(curTimeInMs + (listJalurRute.get(i).getDurasi() * ONE_MINUTE_IN_MILLIS));
                                String formatted = String.format("%05d", lastIndex);
                                jadwal.setKodeJadwal("JW" + formatted);
                                jadwal.setKereta(listKereta.get(j));
                                jadwal.setKotaKeberangkatan(listJalurRute.get(i).getRuteJalur().getKotaAsal());
                                jadwal.setKotaTujuan(listJalurRute.get(i).getRuteJalur().getKotaTujuan());
                                jadwal.setTanggalJadwal(dateobj);
                                jadwal.setWaktuBerangkat(listWaktu.get(j));
                                jadwal.setWaktuTiba(waktuTiba);
                                jadwal.generateKursi();
                                jadwalManager.add(jadwal);
                            }
                        }
                    }
                }
                    


            }
        }

            


    }




            

        

    }

    public void menuTampil() {
        AsciiTable at = new AsciiTable();
        at.addRule();
        AT_Row rowHeader = at.addRow("NO", "KODE JADWAL", "TANGGAL", "WAKTU BERANGKAT", "KOTA ASAL", "KOTA TUJUAN", "WAKTU TIBA", "KERETA", "STATUS");
        rowHeader.setTextAlignment(TextAlignment.CENTER);
        at.addRule();
        List<Jadwal> listJadwal = jadwalManager.GetAll();

        int i = 0;
        for (Jadwal jadwal : listJadwal) {
            i++;
            String status =  "";
            if (jadwal.getKursiKosong() > 0) {
                status = "Tersisa " + jadwal.getKursiKosong() + " Kursi";
            } else {
                status = "Full";
            }
            AT_Row row = at.addRow(i, jadwal.getKodeJadwal(),StringUtility.getFormattedDate(jadwal.getTanggalJadwal()), jadwal.getWaktuBerangkat().getWaktu(),
            jadwal.getKotaKeberangkatan().getNamaKota(), jadwal.getKotaTujuan().getNamaKota(),jadwal.getWaktuTiba(),
            jadwal.getKereta().getKodeKereta(), status);
            row.setTextAlignment(TextAlignment.CENTER);
            at.addRule();

        }
        at.setPaddingLeft(1);
        at.setPaddingRight(1);
        CWC_LongestLine cwc = new CWC_LongestLine();
        cwc.add(3, 0).add(15, 0).add(15, 0).add(5, 0).add(15, 0).add(15, 0).add(5, 0).add(15, 0).add(20, 0);
        at.getRenderer().setCWC(cwc);
        System.out.println(at.render());
        
    }

    public void menuSearch() {
        String kotaKebrangkatan;
        String kotaTujuan;
        Date tanggal = null;
        System.out.println("Cari Jadwal Kereta Api");
        System.out.print("Keberangkatan : ");
        kotaKebrangkatan = scanner.next();
        System.out.print("Tujuan : ");
        kotaTujuan = scanner.next();
        System.out.print("Tanggal : ");
        try {
            String tglStr = scanner.next();
            tanggal = new SimpleDateFormat("dd-MM-yyyy").parse(tglStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        AsciiTable at = new AsciiTable();
        at.addRule();
        AT_Row rowHeader = at.addRow("NO", "KODE JADWAL", "TANGGAL", "WAKTU BERANGKAT", "KOTA ASAL", "KOTA TUJUAN", "WAKTU TIBA", "KERETA", "STATUS");
        rowHeader.setTextAlignment(TextAlignment.CENTER);
        at.addRule();
        List<Jadwal> listJadwal = jadwalManager.GetAll();

        int i = 0;
        for (Jadwal jadwal : listJadwal) {
            Kota kotaFrom = kotaManager.GetByNamaKota(kotaKebrangkatan);
            Kota kotaTo= kotaManager.GetByNamaKota(kotaTujuan);
            if (jadwal.getKotaKeberangkatan().getKodeKota().equals(kotaFrom.getKodeKota()) 
                && jadwal.getKotaTujuan().getKodeKota().equals(kotaTo.getKodeKota()) 
                && jadwal.getTanggalJadwal().equals(tanggal)) {

                    i++;
                    String status =  "";
                    if (jadwal.getKursiKosong() > 0) {
                        status = "Tersisa " + jadwal.getKursiKosong() + " Kursi";
                    } else {
                        status = "Full";
                    }
                    AT_Row row = at.addRow(i, jadwal.getKodeJadwal(),StringUtility.getFormattedDate(jadwal.getTanggalJadwal()), jadwal.getWaktuBerangkat().getWaktu(),
                    jadwal.getKotaKeberangkatan().getNamaKota(), jadwal.getKotaTujuan().getNamaKota(),jadwal.getWaktuTiba(),
                    jadwal.getKereta().getKodeKereta(), status);
                    row.setTextAlignment(TextAlignment.CENTER);
                    at.addRule();
                    break;

            }
        }
        at.setPaddingLeft(1);
        at.setPaddingRight(1);
        CWC_LongestLine cwc = new CWC_LongestLine();
        cwc.add(3, 0).add(15, 0).add(15, 0).add(5, 0).add(15, 0).add(15, 0).add(5, 0).add(15, 0).add(20, 0);
        at.getRenderer().setCWC(cwc);
        System.out.println(at.render());
      

    }

    boolean isKeretaAvailableToRun(int durasi, Kereta kereta, Waktu waktuBerangkat, Date tanggalBerangkat) {
        boolean defaultResult  = true;
        int maxHari  = timeUtility.getDaysFromMinutes(durasi)+1;
        Date tanggalHariIni = new Date();
        Date tanggalCek;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        String strTanggalCek; 


        while (maxHari>-1) {
            // ambil kereta dengan jadwal = tanggal hari ini dikurangi max hari
            tanggalCek = timeUtility.addDays(tanggalHariIni, -1*maxHari);
            strTanggalCek = dateFormat.format(tanggalCek);
            List<Jadwal> listJadwal = jadwalManager.getByTanggalAndKereta(kereta, strTanggalCek);
            if (listJadwal.size()>0) {
                for (int i=0; i<listJadwal.size(); i++) {
                    Jadwal jdw = listJadwal.get(i);
                    Calendar calJadwal = Calendar.getInstance();
                    calJadwal.setTime(jdw.getTanggalJadwal());
                    calJadwal.set(calJadwal.get(Calendar.YEAR), calJadwal.get(Calendar.MONTH), 
                        calJadwal.get(Calendar.DAY_OF_MONTH), timeUtility.waktuToHH(jdw.getWaktuBerangkat()), 
                        timeUtility.waktuToMM(jdw.getWaktuBerangkat()));
                    Calendar calBerangkat = Calendar.getInstance();
                    calBerangkat.setTime(tanggalBerangkat);
                    calBerangkat.set(calBerangkat.get(Calendar.YEAR),calBerangkat.get(Calendar.MONTH),
                        calBerangkat.get(Calendar.DAY_OF_MONTH),timeUtility.waktuToHH(waktuBerangkat),
                        timeUtility.waktuToMM(waktuBerangkat));
                    long selisihWaktu = Math.abs(calBerangkat.getTimeInMillis()-calJadwal.getTimeInMillis());
                    //System.out.println("waktu berangkat : "+calBerangkat);
                    //System.out.println("waktu jadwal : "+calJadwal);
                    //System.out.println("cek selisih : "+selisihWaktu);
                    //System.out.println("cek durasi : "+(durasi*2*60000));
                    if (selisihWaktu<durasi*2*60000) {
                        defaultResult = false;
                    }
                    
                }
            }


            maxHari--;
        }



        return defaultResult;
    }

    

    
}