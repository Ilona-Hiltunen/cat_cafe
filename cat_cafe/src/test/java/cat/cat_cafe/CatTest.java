package cat.cat_cafe;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cat.cat_cafe.domain.Cat;
import cat.cat_cafe.domain.CatRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CatTest {

    @Autowired
    private CatRepository repository;

    @Test
    public void findCat() {
        List<Cat> cats = repository.findByCatname("Button");
        assertThat(cats).hasSize(1);
        assertThat(cats.get(0).getCatname().equalsIgnoreCase("button"));
    }

    @Test
    public void createCat() {
        Cat cat = new Cat("Miuku", LocalDate.of(2020, 10, 7), "Kiva kissa");
        repository.save(cat);
        assertThat(cat.getId()).isNotNull();
        assertThat(cat.getImagepath()).isNotNull();
    }

    @Test
    public void deleteCat() {
        List<Cat> cats = repository.findByCatname("Button");
        Cat cat = cats.get(0);
        repository.delete(cat);
        assertThat(repository.findByCatname("Button")).hasSize(0);
    }

}
