package cat.cat_cafe.web;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import cat.cat_cafe.domain.Appuser;
import cat.cat_cafe.domain.AppuserRepository;
import cat.cat_cafe.domain.Booking;
import cat.cat_cafe.domain.BookingRepository;
import jakarta.validation.Valid;

@Controller
public class BookingController {

    @Autowired
    private BookingRepository bRepository;

    @Autowired
    private AppuserRepository aRepository;

    //Directing users from /booktable to booktable.html and creating booking- and appuser-object. Also finding bookings, so user can see his own reservations.
    @GetMapping("/booktable")
    public String bookTable(Model model) {
        Appuser appUser = aRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("appUser", appUser);
        model.addAttribute("booking", new Booking());
        model.addAttribute("bookings", bRepository.findByAppUser(appUser));
        return "/booktable";
    }

    //Generating a method to save a booking to the database with validation
    @PostMapping("/savebooking")
    public String saveBooking(@Valid @ModelAttribute("booking") Booking booking, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("booking", booking);
            model.addAttribute("appUser", booking.getAppUser());
            model.addAttribute("bookings", bRepository.findByAppUser(booking.getAppUser()));
            return "/booktable";
        } else if (booking.getBookingDate().isBefore(LocalDateTime.now())) {
            bindingResult.rejectValue("bookingDate", "err.date", "Booking can't be in the past.");
            model.addAttribute("booking", booking);
            model.addAttribute("appUser", booking.getAppUser());
            model.addAttribute("bookings", bRepository.findByAppUser(booking.getAppUser()));
            return "/booktable";
        } else if (booking.getBookingDate().getHour() < 10 || booking.getBookingDate().getHour() >= 19) {

            if (booking.getBookingDate().getHour() == 19 && booking.getBookingDate().getMinute() == 0) {
            bRepository.save(booking);
            return "redirect:/booktable";
            }

            bindingResult.rejectValue("bookingDate", "err.date1", "Bookings are avaible to 10am-7pm. Please select booking time within these values.");
            model.addAttribute("booking", booking);
            model.addAttribute("appUser", booking.getAppUser());
            model.addAttribute("bookings", bRepository.findByAppUser(booking.getAppUser()));
            return "/booktable";
        } else if (booking.getBookingDate().getDayOfWeek().name().equals("SUNDAY")) {
            bindingResult.rejectValue("bookingDate", "err.date2", "Unfortunately we're closed on sundays.");
            model.addAttribute("booking", booking);
            model.addAttribute("appUser", booking.getAppUser());
            model.addAttribute("bookings", bRepository.findByAppUser(booking.getAppUser()));
            return "/booktable";
        };
        
        bRepository.save(booking);
        return "redirect:/booktable";
    }

    //Anothersaving method for saving booking, when editing it, so it doesn't redirect user to /booktable. Also removed some of the validation, so admin can do editings more freely.
    @PostMapping("/savebooking1")
    public String saveBooking1(@Valid @ModelAttribute("booking") Booking booking, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("booking", booking);
            model.addAttribute("appUser", booking.getAppUser());
            return "/editbooking";
        };
        
        bRepository.save(booking);
        return "redirect:/bookinglist";
    }

    //Directing user from /bookinglist to bookinglist.html
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/bookinglist")
    public String bookingList(Model model) {
        model.addAttribute("bookings", bRepository.findAll());
        return "bookinglist";
    }

    //Generating a method to delete a booking from the database
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/deletebooking/{id}")
    public String deleteBooking(@PathVariable("id") Long id, Model model) {
        bRepository.deleteById(id);
        return "redirect:/bookinglist";
    }

    //Generating a method to edit a booking from the database
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/editbooking/{id}")
    public String editBooking(@PathVariable("id") Long id, Model model) {
        model.addAttribute("booking", bRepository.findById(id));
        return "editbooking";
    }



}
