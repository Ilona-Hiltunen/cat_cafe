package cat.cat_cafe.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import cat.cat_cafe.domain.Appuser;
import cat.cat_cafe.domain.AppuserRepository;
import cat.cat_cafe.domain.UserSignUp;
import jakarta.validation.Valid;

@Controller
public class AppUserController {

    @Autowired
    private AppuserRepository repository;

    //Directing users from /login to log in page
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //Directing users from /signup and generating new appuser-object
    @GetMapping("/signup")
    public String addCustomer(Model model) {
        model.addAttribute("signupUser", new UserSignUp());
        return "signup";
    }

    //Generating a method to save user with validation and checking that username, email and phone number are unique.
    @PostMapping("/savecustomer")
    public String saveCustomer(@Valid @ModelAttribute("signupUser") UserSignUp signupUser, BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            String password = signupUser.getPassword();
            BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
            String hashpass = bc.encode(password);

            Appuser user = new Appuser(signupUser.getUsername(), hashpass, "USER", signupUser.getFirstname(), signupUser.getLastname(), signupUser.getEmail(), signupUser.getpNumber());

            if (repository.findByUsername(signupUser.getUsername()) == null) {
                if(repository.findByEmail(signupUser.getEmail()) == null) {
                    if(repository.findBypNumber(signupUser.getpNumber()) == null) {
                        repository.save(user);
                    } else {
                        bindingResult.rejectValue("pNumber", "err.number", "Phone number is already in use.");
                        return "signup";
                    }
                } else {
                    bindingResult.rejectValue("email", "err.email", "Email is already in use.");
                    return "signup";
                }
            } else {
                bindingResult.rejectValue("username", "err.username", "Username already exists.");
                return "signup";
            }
        } else {
            return "signup";
        }

        return "redirect:/login";
    }
}
