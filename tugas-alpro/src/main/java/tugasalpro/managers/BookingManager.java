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
    public List<Booking> getAll(User user)
    {
        List<Booking> bookings= repository.getAll();
        bookings = bookings.stream().filter(x->x.getBookedBy().getUserInfo().getKtp().equals(user.getUserInfo().getKtp()))
        .collect(Collectors.toList());
        return bookings;

    }
}