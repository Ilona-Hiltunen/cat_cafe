package cat.cat_cafe.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.GenerationType;

@Entity
@Table(name="users")
public class Appuser {

// Generating an id with validation
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", nullable = false, unique = true)
@NotNull(message = "{id.not.null}")
private long userid;

//Generating an username with validation
@Column(name = "username", nullable = false, unique = true)
@NotNull(message = "{username.not.empty}")
@Size(min = 2, max = 20, message = "{username.size}")
private String username;

//Generating a password with validation
@Column(name = "password", nullable = false)
@NotNull(message = "{password.not.empty}")
private String passwordHash;

//Generating a role with validation
@Column(name = "role", nullable = false)
@NotNull(message = "{role.not.empty}")
private String role;

//Generating firstname with validation
@Column(name = "firstname", nullable = false)
@NotNull(message = "{firstname.not.empty}")
@Size(min = 3, max = 20, message = "{firstname.size}")
private String firstname;

//Generating last name with validation
@Column(name = "lastname", nullable = false)
@NotNull(message = "{lastname.not.empty}")
@Size(min = 3, max = 30, message = "{lastname.size}")
private String lastname;

//Generating an email with validation
@Column(name = "email", unique = true)
@NotNull(message = "{email.not.empty}")
@Size(min = 5, max = 40, message = "{email.size}")
private String email;

//Generating a phone number with validation
@Column(name = "phone", unique = true)
@NotNull(message = "{pNumber.not.empty}")
@Size(min=5, max = 20, message = "{pNumber.size}")
private String pNumber;

//Joining this column to an Booking-column, so we can connect the user with his bookings
@OneToMany(cascade = CascadeType.ALL, mappedBy = "appUser")
@JsonIgnore
private List<Booking> bookings;

//Generating a empty constructor and one with values
public Appuser() {
}

public Appuser(
        @NotNull(message = "{username.not.empty}") @Size(min = 2, max = 20, message = "{username.size}") String username,
        @NotNull(message = "{password.not.empty}") String passwordHash,
        @NotNull(message = "{role.not.empty}") String role,
        @NotNull(message = "{firstname.not.empty}") @Size(min = 3, max = 20, message = "{firstname.size}") String firstname,
        @NotNull(message = "{lastname.not.empty}") @Size(min = 3, max = 30, message = "{lastname.size}") String lastname,
        @NotNull(message = "{email.not.empty}") @Size(min = 5, max = 40, message = "{email.size}") String email,
        @NotNull(message = "{pNumber.not.empty}") @Size(min = 5, max = 20, message = "{pNumber.size}") String pNumber) {
    this.username = username;
    this.passwordHash = passwordHash;
    this.role = role;
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.pNumber = pNumber;
}

//Generating getters and setters

public long getUserid() {
    return userid;
}

public void setUserid(long userid) {
    this.userid = userid;
}

public String getUsername() {
    return username;
}

public void setUsername(String username) {
    this.username = username;
}

public String getPasswordHash() {
    return passwordHash;
}

public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
}

public String getRole() {
    return role;
}

public void setRole(String role) {
    this.role = role;
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

public List<Booking> getBookings() {
    return bookings;
}

public void setBookings(List<Booking> bookings) {
    this.bookings = bookings;
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

//Generating toString

@Override
public String toString() {
    return "Appuser [appUserId=" + userid + ", username=" + username + ", passwordHash=" + passwordHash + ", role="
            + role + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", pNumber=" + pNumber
            + "]";
}


    
}
