package cat.cat_cafe.domain;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

//Defining this class as with Entity, so it represents a table stored in a database
@Entity
@Table(name="bookings")
public class Booking {

//Generating an id with validation
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@NotNull(message="{id.not.null}")
@Column(name="id", nullable=false, updatable=false)
private long id;

//Setting the number of guests with validation
@NotNull(message = "{guestCount.not.null}")
@Min(value=1, message="{guestCount.min}")
@Max(value=6, message="{guestCount.max}")
@Column(name="guests", nullable = false)
private int guestCount;

//Setting the time and date of a booking with validation. I also had to set specific formatting, so that Google Chrome shows datetime-local input on the website as it's supposed to.
@NotNull(message="{date.not.null}")
@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
@Column(name = "bookingdate", nullable = false)
private LocalDateTime bookingDate;

//Joining this column to an Appuser-column, so we can connect the reservation with the user who made it
@ManyToOne
@JoinColumn(name="userid")
@NotNull(message = "{appUser.not.null}")
private Appuser appUser;


//Generating empty constructor and one with values

public Booking() {
}

public Booking(
        @NotNull(message = "{guestCount.not.null}") @Min(value = 1, message = "{guestCount.min}") @Max(value = 6, message = "{guestCount.max}") int guestCount,
        @NotNull(message = "{date.not.null}") LocalDateTime bookingDate, @NotNull(message = "{appUser.not.null}") Appuser appUser) {
    this.guestCount = guestCount;
    this.bookingDate = bookingDate;
    this.appUser = appUser;
}

public Booking(@NotNull(message = "{id.not.null}") Long id,
        @NotNull(message = "{guestCount.not.null}") @Min(value = 1, message = "{guestCount.min}") @Max(value = 6, message = "{guestCount.max}") int guestCount,
        @NotNull(message = "{date.not.null}") LocalDateTime bookingDate) {
    this.id = id;
    this.guestCount = guestCount;
    this.bookingDate = bookingDate;
}


//Generating getters and setters

public long getId() {
    return id;
}

public void setId(long id) {
    this.id = id;
}

public int getGuestCount() {
    return guestCount;
}

public void setGuestCount(int guestCount) {
    this.guestCount = guestCount;
}

public LocalDateTime getBookingDate() {
    return bookingDate;
}

public void setBookingDate(LocalDateTime bookingDate) {
    this.bookingDate = bookingDate;
}

public Appuser getAppUser() {
    return appUser;
}

public void setAppUser(Appuser appUser) {
    this.appUser = appUser;
}

//Generating method to get a String of the time, so it shows with 2 digits in browser

public String getTime() {
    return timeOfBookingToString(bookingDate);
}

public String timeOfBookingToString(LocalDateTime bookingdate) {

    NumberFormat formatter = new DecimalFormat("00");
    String hours = formatter.format(bookingdate.getHour());
    String minutes = formatter.format(bookingdate.getMinute());

    return hours + ":" + minutes;
}

//Generating a method to have booking's ending time

public String getEndingtime() {
    return timeOfEndingToString(bookingDate);
}

public String timeOfEndingToString(LocalDateTime bookingdate) {

    NumberFormat formatter = new DecimalFormat("00");
    int hour = bookingdate.getHour() + 1;
    String hours = formatter.format(hour);
    String minutes = formatter.format(bookingdate.getMinute());

    return hours + ":" + minutes;
}


//Generating toString. Ensuring that our app doesn't crash, if user attribute is not defined.

@Override
public String toString() {

    if(this.appUser != null) {
    return "Booking [id=" + id + ", guestCount=" + guestCount + ", bookingDate=" + bookingDate + ", user=" + appUser + "]";
    } else {
    return "Booking [id=" + id + ", guestCount=" + guestCount + ", bookingDate=" + bookingDate + "]";
    }
}

}
