package cat.cat_cafe.domain;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import java.time.LocalDateTime;


public interface BookingRepository extends CrudRepository<Booking, Long>{

    List<Booking> findByBookingDate(LocalDateTime localDateTime);
    List<Booking> findByAppUser(Appuser appUser);
    
} 
    

