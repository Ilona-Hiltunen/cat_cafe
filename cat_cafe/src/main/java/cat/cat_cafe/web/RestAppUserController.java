package cat.cat_cafe.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import cat.cat_cafe.domain.Appuser;
import cat.cat_cafe.domain.AppuserRepository;
import cat.cat_cafe.domain.UserSignUp;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class RestAppUserController {

    @Autowired
    private AppuserRepository repository;

    @GetMapping("/users")
    public Iterable<Appuser> getUsers() {
        return repository.findAll();
    }

    @GetMapping("/user/{id}")
    public Optional<Appuser> getUser(@PathVariable("id") Long id) {
        return repository.findById(id);
    }

    @PostMapping("/newuser")
    Appuser newUser (@RequestBody UserSignUp  newUser) {
        
        String password = newUser.getPassword();
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        String hashpass = bc.encode(password);

        Appuser user = new Appuser(newUser.getUsername(), hashpass, "USER", newUser.getFirstname(), newUser.getLastname(), newUser.getEmail(), newUser.getpNumber());

        return repository.save(user);
    }
    
    @PutMapping("/user/{id}")
    Appuser editUser (@PathVariable("id") Long id, @RequestBody Appuser editUser) {
        editUser.setAppUserId(id);
        return repository.save(editUser);
    }

    @DeleteMapping("/deleteuser/{id}")
    public void deleteUser (@PathVariable("id") Long id) {
        repository.deleteById(id);
    }
    

}
