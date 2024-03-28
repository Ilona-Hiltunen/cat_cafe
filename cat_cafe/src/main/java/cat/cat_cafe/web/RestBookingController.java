package cat.cat_cafe.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import cat.cat_cafe.domain.AppuserRepository;
import cat.cat_cafe.domain.Booking;
import cat.cat_cafe.domain.BookingRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class RestBookingController {

    @Autowired
    private BookingRepository bRepository;

    @Autowired
    private AppuserRepository aRepository;

    @GetMapping("/bookings")
    public Iterable<Booking> getBookings() {
        return bRepository.findAll();
    }

    @GetMapping("/booking/{id}")
    public Optional<Booking> getBooking(@PathVariable("id") Long id) {
        return bRepository.findById(id);
    }

    @PostMapping("/newbooking")
    public Booking booking (@RequestBody Booking newBooking) {
        return bRepository.save(newBooking);
    }
    
    @PutMapping("/booking/{id}")
    public Booking changeBooking(@PathVariable Long id, @RequestBody Booking editBooking) {
        editBooking.setId(id);
        return bRepository.save(editBooking);
    }

    @DeleteMapping("/removebooking/{id}")
    public void removeBooking(@PathVariable Long id) {
        bRepository.deleteById(id);
    }

}
