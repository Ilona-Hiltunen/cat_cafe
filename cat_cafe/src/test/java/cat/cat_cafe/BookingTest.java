package cat.cat_cafe;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import cat.cat_cafe.domain.AppuserRepository;
import cat.cat_cafe.domain.Booking;
import cat.cat_cafe.domain.BookingRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookingTest {

    @Autowired
    private BookingRepository repository;

    @Autowired
    private AppuserRepository aRepository;

    @Test
    public void findBooking() {
        Optional<Booking> booking = repository.findById((long) 1);
        assertThat(booking).isNotNull();
    }

    @Test
    public void createBooking() {
        Booking booking = new Booking(2, LocalDateTime.of(2024, 5, 20, 16, 0, 0), aRepository.findByUsername("user2"));
        repository.save(booking);
        assertThat(booking.getId()).isNotNull();
        assertThat(booking.getGuestCount()).isEqualTo(2);
    }

    @Test
    public void deleteBooking() {
        List<Booking> bookings = repository.findByAppUser(aRepository.findByUsername("user2"));
        repository.deleteAll(bookings);
        assertThat(repository.findByAppUser(aRepository.findByUsername("user2"))).hasSize(0);
    }

}
