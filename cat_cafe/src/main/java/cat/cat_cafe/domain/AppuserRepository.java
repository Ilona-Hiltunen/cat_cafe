package cat.cat_cafe.domain;
import org.springframework.data.repository.CrudRepository;

public interface AppuserRepository extends CrudRepository<Appuser, Long> {

    Appuser findByUsername(String username);
    Appuser findByEmail(String email);
    Appuser findBypNumber(String pNumber);
    
} 
    

