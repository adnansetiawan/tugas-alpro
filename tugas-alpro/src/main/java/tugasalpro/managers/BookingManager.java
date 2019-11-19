package tugasalpro.managers;

import tugasalpro.ApplicationSession;
import tugasalpro.Repository;
import tugasalpro.models.Booking;
import tugasalpro.models.User;

import java.util.*;
import java.util.stream.Collectors;
public class BookingManager
{
    private Repository<Booking> repository;
    public BookingManager()
    {
        repository = new Repository<>("Booking", Booking[].class);
         
    }
    public void add(Booking booking)
    {
        booking.setUser(ApplicationSession.getLoggedUser());
        booking.setBookingDate(new Date());
        repository.add(booking);
    }
    private int findById(String kode)
    {
        List<Booking> listBooking = repository.getAll();
        int indexFound = -1;
        for(int i = 0; i< listBooking.size(); i++)
        {
            Booking booking = listBooking.get(i);
            
            if(booking.getBookingId().equals(kode))
            {
                indexFound = i;
                break;
            }
        }
        return indexFound;
    }
    public void update(Booking booking)
    {
        int indexFound = findById(booking.getBookingId());   
        if (indexFound != -1) {
        try {
            
            repository.edit(booking, indexFound);
        }
        catch(Exception e){
                e.printStackTrace();
            }
        }
        
    }
    public void delete(Booking booking)
    {
        int indexFound = findById(booking.getBookingId());   
        if (indexFound != -1) {
        try {
            
            repository.delete(booking, indexFound);
        }
        catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public List<Booking> getAll(User user)
    {
        List<Booking> bookings= repository.getAll();
        bookings = bookings.stream().filter(x->x.getBookedBy().getUserInfo().getKtp().equals(user.getUserInfo().getKtp()))
        .collect(Collectors.toList());
        return bookings;

    }
    public Booking getByKode(String kode)
    {
        List<Booking> bookings= repository.getAll();
        Optional<Booking> booking = bookings.stream().filter(x->x.getBookingId().equals(kode)).findFirst();
        if(booking == null)
            return null;
        return booking.get();

    }
}