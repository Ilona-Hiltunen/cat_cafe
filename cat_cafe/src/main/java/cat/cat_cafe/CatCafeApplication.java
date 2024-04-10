package cat.cat_cafe;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import cat.cat_cafe.domain.Appuser;
import cat.cat_cafe.domain.AppuserRepository;
import cat.cat_cafe.domain.Booking;
import cat.cat_cafe.domain.BookingRepository;
import cat.cat_cafe.domain.Cat;
import cat.cat_cafe.domain.CatRepository;

@SpringBootApplication
public class CatCafeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatCafeApplication.class, args);
	}

	/* @Bean
	public CommandLineRunner catDemo(AppuserRepository aRepository, BookingRepository bRepository, CatRepository cRepository) {
		return (args) -> {
			aRepository.save(new Appuser("user", "$2a$10$7Ye2569EWEYpdUAkqPYQhOfLRjq8z.CY7yat44Ab9kfn9oGCLFwwm", "USER", "Maija", "Meikäläinen", "maija@gmail.com", "05034342"));
			aRepository.save(new Appuser("user2", "$2a$10$.5B0Dqouq59i1C9MolDWUOGbQGJNUjhTfxzCBUdkNHY7XwUUjSCfK", "USER", "Erkki", "Esimerkki", "erkki@gmail.com", "123456"));
			aRepository.save(new Appuser("admin", "$2a$10$dY9mrHPc8B9khYMbiB2HP.b..qwqCDv6yxpGTIsSu0MPPsRQMBqx6", "ADMIN", "Matti", "Möttönen","matti@gmail.com", "05245235"));

			LocalDate date = LocalDate.of(2021, 8, 18);
			LocalDate date1 = LocalDate.of(2024, 1, 20);
			LocalDate date2 = LocalDate.of(2013, 10, 17);

			cRepository.save(new Cat("Button", date, "Loves to cuddle!", "/images/cat-649164_1280.jpg"));
			cRepository.save(new Cat("Biscuit", date1, "Our newest member. We'll pictures when he's old enough to start working."));
			cRepository.save(new Cat("Lola", date2, "Old lady, who likes to sleep a lot.", "/images/cat-2605502_1280.jpg"));

			LocalDateTime bDate = LocalDateTime.of(2024, 4, 27, 17, 30, 0);
			LocalDateTime bDate1 = LocalDateTime.of(2024, 4, 30, 12, 0, 0);
			LocalDateTime bDate2 = LocalDateTime.of(2024, 5, 8, 14, 45, 0);

			bRepository.save(new Booking(4, bDate, aRepository.findByUsername("user")));
			bRepository.save(new Booking(2, bDate1, aRepository.findByUsername("user")));
			bRepository.save(new Booking(4, bDate2, aRepository.findByUsername("user2")));

		};
	} */
}
