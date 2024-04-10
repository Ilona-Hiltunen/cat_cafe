package cat.cat_cafe;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cat.cat_cafe.domain.Appuser;
import cat.cat_cafe.domain.AppuserRepository;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppUserTest {

    @Autowired
    private AppuserRepository repository;

    @Test
    @Order(1)
    public void findByAppUser() {
        Appuser appuser = repository.findByUsername("user");
        assertThat(appuser.getUsername().equalsIgnoreCase("user"));
    }

    @Test
    @Order(2)
    public void createAppUser() {
        Appuser appuser = new Appuser("user1", "$2a$10$mfyLs3laQX00O0oyW//jHuB5hHv0GvKwDYBZpnVYHxHK.NfsfI2XC", "USER", "testi", "ukko", "testi@gmail.com", "352442");
        assertThat(appuser.getUserid()).isNotNull();
    }

    @Test
    @Order(3)
    public void deleteAppUser() {
        Appuser appuser = repository.findByUsername("user");
        repository.delete(appuser);
        assertThat(repository.findByUsername("user1")).isNull();
    }

}