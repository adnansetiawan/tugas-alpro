package tugasalpro.utilities;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import tugasalpro.models.Waktu;

public class TimeUnitUtility {
 
 public String convertToHHMMSS(long miliSeconds)
 {
  int hrs = (int) TimeUnit.MILLISECONDS.toHours(miliSeconds) % 24;
  int min = (int) TimeUnit.MILLISECONDS.toMinutes(miliSeconds) % 60;
  int sec = (int) TimeUnit.MILLISECONDS.toSeconds(miliSeconds) % 60;
  return String.format("%02d.%02d.%02d", hrs, min, sec);
 }

 public String convertToHHMM(long miliSeconds)
 {
  int hrs = (int) TimeUnit.MILLISECONDS.toHours(miliSeconds) % 24;
  int min = (int) TimeUnit.MILLISECONDS.toMinutes(miliSeconds) % 60;
  int sec = (int) TimeUnit.MILLISECONDS.toSeconds(miliSeconds) % 60;
  return String.format("%02d.%02d", hrs, min, sec);
 }

 public String convToHHMM(long miliSeconds) {
    if (TimeUnit.MILLISECONDS.toDays(miliSeconds)>0) {
      return String.format("+%02d %02d.%02d",TimeUnit.MILLISECONDS.toDays(miliSeconds),TimeUnit.MILLISECONDS.toHours(miliSeconds),
            TimeUnit.MILLISECONDS.toMinutes(miliSeconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(miliSeconds)));
    } else  {
      return String.format("%02d.%02d", TimeUnit.MILLISECONDS.toHours(miliSeconds),
            TimeUnit.MILLISECONDS.toMinutes(miliSeconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(miliSeconds)));
    }
    
 }

 public long HHMMtoMilis(String jam) {
    final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs
    String hour = jam.substring(0,2);
    String minute = jam.substring(3,5);
    return Integer.valueOf(hour)*60*ONE_MINUTE_IN_MILLIS+Integer.valueOf(minute)*ONE_MINUTE_IN_MILLIS;
 }

 public Date addOneDay(Date date) {
    Calendar c = Calendar.getInstance(); 
    c.setTime(date); 
    c.add(Calendar.DATE, 1);
    date = c.getTime();
    return date;
 }

 public int getDaysFromMinutes(int minute) {
   return minute/(24*60);
 }

 public Date addDays(Date date, int days)
 {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.DATE, days); //minus number would decrement the days
    return cal.getTime();
 }

 public int waktuToHH(Waktu waktu) {
   String jam = waktu.getWaktu();
   String hour = jam.substring(0,2);
   return Integer.valueOf(hour);
 }

 public int waktuToMM(Waktu waktu) {
   String jam = waktu.getWaktu();
   String minute = jam.substring(3,5);
   return Integer.valueOf(minute);
 }

}