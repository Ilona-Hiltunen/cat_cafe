package cat.cat_cafe.domain;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import java.sql.Date;


public interface BookingRepository extends CrudRepository<Booking, Long>{

    List<Booking> findByBookingDate(Date bookingDate);
    List<Booking> findByAppUser(Appuser appUser);
    
} 
    

