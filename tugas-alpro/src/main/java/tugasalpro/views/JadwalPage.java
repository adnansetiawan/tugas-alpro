package tugasalpro.views;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import tugasalpro.managers.JadwalManager;
import tugasalpro.managers.KeretaManager;
import tugasalpro.managers.KeretaRuteManager;
import tugasalpro.managers.KotaManager;
import tugasalpro.managers.WaktuManager;
import tugasalpro.models.Jadwal;
import tugasalpro.models.Kereta;
import tugasalpro.models.KeretaRute;

public class JadwalPage {
    private KeretaManager keretaManager;
    private KotaManager kotaManager;
    private KeretaRuteManager keretaRuteManager;
    private WaktuManager waktuManager;
    private JadwalManager jadwalManager;
    Scanner scanner;

    public JadwalPage() {
        keretaManager = new KeretaManager();
        keretaRuteManager = new KeretaRuteManager();
        jadwalManager = new JadwalManager();
        waktuManager = new WaktuManager();
        kotaManager = new KotaManager();

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
        String pilihan;
        int lastIndex = -1;
        System.out.print("Anda Yakin untuk generate Jadwal Kereta: Y/N : ");
        pilihan = scanner.next();
        if (pilihan.equals("Y")) {
            // generate Jadwal

            // get today
            Date dateobj = new Date();

            // get last generated index;
            lastIndex = jadwalManager.GetAll().size();
            Jadwal jadwal = new Jadwal();
            List<KeretaRute> listKeretaRute = keretaRuteManager.GetAll();
            ArrayList<Kereta> listKereta = null;
            for (int i = 0; i < listKeretaRute.size(); i++) {
                KeretaRute keretaRute = listKeretaRute.get(i);
                listKereta = keretaRute.getKeretaTersedia();
                for (int j = 0; j < listKereta.size(); j++) {
                    lastIndex++;
                    jadwal.setKodeJadwal("JW" + lastIndex);
                    jadwal.setKereta(listKereta.get(j));
                    jadwal.setKotaKeberangkatan(keretaRute.getRuteKereta().getKotaAsal());
                    jadwal.setKotaTujuan(keretaRute.getRuteKereta().getKotaTujuan());
                    jadwal.setKursiKosong(
                            listKereta.get(j).getJmlGBisnis() * 10 + listKereta.get(j).getJmlGPremium() * 20);
                    jadwal.setTanggalJadwal(dateobj);
                    jadwal.setWaktuBerangkat(waktuManager.GetByKodeWaktu("TM1"));
                    jadwal.setWaktuTiba(waktuManager.GetByKodeWaktu("TM1"));
                    jadwalManager.add(jadwal);
                }

            }

        }

    }

    public void menuTampil() {
        System.out.println("-------------------------------------------------------");
        System.out.println(
                "No \t Kode Jadwal \t Tanggal \t Waktu Keberangkatan \t Keberangkatan \t Tujuan \t Waktu Tiba \t KAI \t Status");
        List<Jadwal> listJadwal = jadwalManager.GetAll();

        int i = 0;
        for (Jadwal jadwal : listJadwal) {
            i++;
            System.out.print(i + " \t " + jadwal.getKodeJadwal() + " \t " + jadwal.getTanggalJadwal() + "\t\t"
                    + jadwal.getWaktuBerangkat().getWaktu() + "\t" + jadwal.getKotaKeberangkatan().getNamaKota() + "\t"
                    + jadwal.getKotaTujuan().getNamaKota() + "\t" + jadwal.getWaktuTiba().getWaktu() + "\t"
                    + jadwal.getKereta().getKodeKereta());
            if (jadwal.getKursiKosong() > 0) {
                System.out.println("Tersisa " + jadwal.getKursiKosong() + " Kursi");
            } else {
                System.out.println("Full");
            }

        }
        System.out.println("-------------------------------------------------------");
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
            tanggal = new SimpleDateFormat("dd-MM-yyyy").parse(scanner.next());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("-------------------------------------------------------");
        System.out.println(
                "No \t Kode Jadwal \t Tanggal \t Waktu Keberangkatan \t Keberangkatan \t Tujuan \t Waktu Tiba \t KAI \t Status");
        List<Jadwal> listJadwal = jadwalManager.GetAll();

        int i = 0;
        for (Jadwal jadwal : listJadwal) {
            if (jadwal.getKotaKeberangkatan().equals(kotaManager.GetByNamaKota(kotaKebrangkatan)) 
                && jadwal.getKotaTujuan().equals(kotaManager.GetByNamaKota(kotaTujuan))
                && jadwal.getTanggalJadwal().equals(tanggal)) {

                System.out.print(i + " \t " + jadwal.getKodeJadwal() + " \t " + jadwal.getTanggalJadwal() + "\t\t"
                    + jadwal.getWaktuBerangkat().getWaktu() + "\t" + jadwal.getKotaKeberangkatan().getNamaKota() + "\t"
                    + jadwal.getKotaTujuan().getNamaKota() + "\t" + jadwal.getWaktuTiba().getWaktu() + "\t"
                    + jadwal.getKereta().getKodeKereta());
                if (jadwal.getKursiKosong() > 0) {
                    System.out.println("Tersisa " + jadwal.getKursiKosong() + " Kursi");
                } else {
                    System.out.println("Full");
                }

            }
        }
        System.out.println("-------------------------------------------------------");


    }

    

    
}