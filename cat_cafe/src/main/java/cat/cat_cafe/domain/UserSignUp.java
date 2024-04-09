package cat.cat_cafe.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

//Not defining this class with entity, because this is only used when signing up. It isn't meant to be in the database.
public class UserSignUp {

//Generating an username with validation
@NotNull(message = "{username.not.empty}")
@Size(min = 2, max = 20, message = "{username.size}")
private String username;

//Generating a password with validation
@NotNull(message = "{password.not.empty}")
private String password;

//Generating a role with validation and setting a default role
@NotNull(message = "{role.not.empty}")
private String role = "USER";

//Generating firstname with validation
@NotNull(message = "{firstname.not.empty}")
@Size(min = 3, max = 20, message = "{firstname.size}")
private String firstname;

//Generating last name with validation
@NotNull(message = "{lastname.not.empty}")
@Size(min = 3, max = 30, message = "{lastname.size}")
private String lastname;

//Generating an email with validation
@NotNull(message = "{email.not.empty}")
@Size(min = 5, max = 40, message = "{email.size}")
private String email;

//Generating a phone number with validation
@NotNull(message = "{pNumber.not.empty}")
@Size(min=5, max = 20, message = "{pNumber.size}")
private String pNumber;

//Generating an empty constructor and one with values
public UserSignUp() {
}

public UserSignUp(
        @NotNull(message = "{username.not.empty}") @Size(min = 2, max = 20, message = "{username.size}") String username,
        @NotNull(message = "{password.not.empty}") String password, @NotNull(message = "{role.not.empty}") String role,
        @NotNull(message = "{firstname.not.empty}") @Size(min = 3, max = 20, message = "{firstname.size}") String firstname,
        @NotNull(message = "{lastname.not.empty}") @Size(min = 3, max = 30, message = "{lastname.size}") String lastname,
        @NotNull(message = "{email.not.empty}") @Size(min = 5, max = 40, message = "{email.size}") String email,
        @NotNull(message = "{pNumber.not.empty}") @Size(min = 5, max = 20, message = "{pNumber.size}") String pNumber) {
    this.username = username;
    this.password = password;
    this.role = role;
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.pNumber = pNumber;
}

//Generating getters and setters
public String getUsername() {
    return username;
}

public void setUsername(String username) {
    this.username = username;
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}

public String getRole() {
    return role;
}

public void setRole(String role) {
    this.role = role;
}

public String getFirstname() {
    return firstname;
}

public void setFirstname(String firstname) {
    this.firstname = firstname;
}

public String getLastname() {
    return lastname;
}

public void setLastname(String lastname) {
    this.lastname = lastname;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getpNumber() {
    return pNumber;
}

public void setpNumber(String pNumber) {
    this.pNumber = pNumber;
}

//Generating toString
@Override
public String toString() {
    return "UserSignUp [username=" + username + ", password=" + password + ", role=" + role + ", firstname=" + firstname
            + ", lastname=" + lastname + ", email=" + email + ", pNumber=" + pNumber + "]";
}

}
