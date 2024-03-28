package cat.cat_cafe.web;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import cat.cat_cafe.domain.Cat;
import cat.cat_cafe.domain.CatRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class RestCatController {

    @Autowired
    private CatRepository repository;

    //Rest-method to show all the cats
    @GetMapping("/cats")
    public Iterable<Cat> getCats() {
        return repository.findAll();
    }

    //Rest-method to show spesific cat
    @GetMapping("/cat/{id}")
    public Optional<Cat> getCat(@PathVariable("id") Long id) {
        return repository.findById(id);
    }

    //Rest-method to add a new cat
    @PostMapping("/newcat")
    Cat newCat (@RequestBody Cat newCat) {

        return repository.save(newCat);
    }
    
    //Rest-method to edit cat
    @PutMapping("/cat/{id}")
    Cat changeCat (@PathVariable Long id, @RequestBody Cat editCat) {

        return repository.save(editCat);
    }
    
    //Rest-method to delete cat
    @DeleteMapping("/removecat/{id}")
    public void removeCat(@PathVariable Long id) {
        repository.deleteById(id);
    }
    
}
