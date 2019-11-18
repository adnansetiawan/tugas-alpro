package tugasalpro.managers;

import tugasalpro.ApplicationSession;
import tugasalpro.Repository;
import tugasalpro.models.Booking;
import java.util.*;
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
}