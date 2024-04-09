package cat.cat_cafe.domain;


import java.time.LocalDate;
import java.time.Period;

import org.springframework.format.annotation.DateTimeFormat;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

//Defining this class as with Entity, so it represents a table stored in a database
@Entity
public class Cat {

//Generating an id with validation
@Id
@NotNull(message = "{id.not.null}")
@Column(name="id", nullable=false, updatable=false)
@GeneratedValue(strategy = GenerationType.AUTO)
private long id;

//Setting a cat name with validation
@NotEmpty(message="{catname.not.empty}")
@Size(min=2, max=100, message="{catname.size}")
private String catname;

//Setting a cat's birthdate with validation. I also had to set specific formatting, so that Google Chrome shows date input on the website as it's supposed to.
@NotNull(message="{date.not.null}")
@DateTimeFormat(pattern = "yyyy-MM-dd")
private LocalDate birthdate;

//Setting a cat description with validation
@Size(max=500, message="{description.size}")
private String description;

//Generating String with path to cat's image and setting a default picture
private String imagepath = "/images/paw-print-220232_1280.jpg";

//Generating a empty constructor and one with values

public Cat() {
}

public Cat(
        @NotEmpty(message = "{catname.not.empty}") @Size(min = 2, max = 100, message = "{catname.size}") String catname,
        @NotNull(message = "{date.not.null}") LocalDate birthdate,
        @Size(max = 500, message = "{description.size}") String description) {
    this.catname = catname;
    this.birthdate = birthdate;
    this.description = description;
}

public Cat(
        @NotEmpty(message = "{catname.not.empty}") @Size(min = 2, max = 100, message = "{catname.size}") String catname,
        @NotNull(message = "{date.not.null}") LocalDate birthdate,
        @Size(max = 500, message = "{description.size}") String description, String imagepath) {
    this.catname = catname;
    this.birthdate = birthdate;
    this.description = description;
    this.imagepath = imagepath;
}

//Generating getters and setters

public long getId() {
    return id;
}

public void setId(long id) {
    this.id = id;
}

public String getCatname() {
    return catname;
}

public void setCatname(String catname) {
    this.catname = catname;
}

public LocalDate getBirthdate() {
    return birthdate;
}

public void setBirthdate(LocalDate birthdate) {
    this.birthdate = birthdate;
}

public String getDescription() {
    return description;
}

public void setDescription(String description) {
    this.description = description;
}

public String getImagepath() {
    return imagepath;
}

public void setImagepath(String imagePath) {
    this.imagepath = imagePath;
}

//Generating method to return String of cat's age

public String getAge() {
    return ageToString(birthdate);
}

public String ageToString(LocalDate birthdate) {
    

    if (birthdate !=null) {
    String years = String.valueOf(Period.between(birthdate, LocalDate.now()).getYears());
    String months = String.valueOf(Period.between(birthdate, LocalDate.now()).getMonths());
    
    return years + " years and " + months + " months";
    }

    return "0";
}


@Override
public String toString() {
    return "Cat [id=" + id + ", catname=" + catname + ", birthdate=" + birthdate + ", description=" + description
            + ", imagepath=" + imagepath + "]";
}
    
}
