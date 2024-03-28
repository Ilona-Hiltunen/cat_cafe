package cat.cat_cafe.web;
import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import cat.cat_cafe.domain.Cat;
import cat.cat_cafe.domain.CatRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class CatController {
    
    @Autowired
    private CatRepository cRepository;

    //Directing users from / and /index to index.html
    @GetMapping(value={"/", "/index"})
    public String showMainPage() {
        return "index";
    }

    //Directing users from /catlist to catlist.html and showing cats from database
    @GetMapping("/catlist")
    public String catList(Model model) {
        model.addAttribute("cats", cRepository.findAll());
        return "catlist";
    }

    //Directing users from /addcat to addcat.html and creating a new cat-object
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/addcat")
    public String addCat(Model model) {
        model.addAttribute("cat", new Cat());
        return "addcat";
    }

    //Generating a method to save a cat to the database and download a picture
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/savecat")
    public String saveCat(@Valid @ModelAttribute("cat") Cat cat, BindingResult bindingResult, Model model, @RequestParam(name="image", required = false) MultipartFile multipartFile) throws IOException {

        if (bindingResult.hasErrors()) {
            model.addAttribute("cat", cat);
            return "/addcat";
        }

    //Had to do this, because I found out multipartfile always getting filled even though user didn't send any file
        if (multipartFile.getSize() > 0) {
        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        cat.setImagePath("/images/" + filename);
        FileUploadUtil.saveFile(filename, multipartFile);
        }

        cRepository.save(cat); 

        return "redirect:/catlist";
    }

    //Doing a second saving method for editing a cat, so if bindingresult has errors, it doesn't direct users to /addcat
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/savecat1")
    public String saveCat1(@Valid @ModelAttribute("cat") Cat cat, BindingResult bindingResult, Model model, @RequestParam(name="image", required = false) MultipartFile multipartFile) throws IOException {

        if (bindingResult.hasErrors()) {
            model.addAttribute("cat", cat);
            return "/editcat";
        }

        if (multipartFile.getSize() > 0) {
        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        cat.setImagePath("/images/" + filename);
        FileUploadUtil.saveFile(filename, multipartFile);
        }

        cRepository.save(cat); 

        return "redirect:/catlist";
    }

    //Generating a method to delete a cat from the database
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/deletecat/{id}")
    public String deleteCat(@PathVariable("id") Long id, Model model) {
        cRepository.deleteById(id);
        return "redirect:/catlist";
    }

    //Generating a method to edit a cat from the database
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/editcat/{id}")
    public String editCat(@PathVariable("id") Long id, Model model) {
        model.addAttribute("cat", cRepository.findById(id));
        return "editcat";
    }
    
    
}
