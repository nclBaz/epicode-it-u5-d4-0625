package riccardogulin.u5d4.runners;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import riccardogulin.u5d4.entities.User;
import riccardogulin.u5d4.services.BlogsService;
import riccardogulin.u5d4.services.UsersService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
@Slf4j
public class Runner implements CommandLineRunner {

//
//	private final UsersRepository usersRepository; // ❌ MAI USARE DIRETTAMENTE LE REPOSITORY, SI PASSA SEMPRE DAI RISPETTIVI SERVICE
//
//	@Autowired
//	public Runner(UsersRepository usersRepository) {
//		this.usersRepository = usersRepository;
//	}

	private final UsersService usersService;
	private final BlogsService blogsService;

	@Autowired
	public Runner(UsersService usersService, BlogsService blogsService) {
		this.usersService = usersService;
		this.blogsService = blogsService;
	}

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(Locale.ITALIAN);
		User user = new User(faker.lordOfTheRings().character(), faker.name().lastName(), faker.internet().emailAddress(), faker.internet().password());
//
		this.usersService.saveNewUser(user);
//
//		this.usersService.findAll().forEach(System.out::println);
//
//		User fromDB = this.usersService.findById(1);
//		System.out.println(fromDB);
//
//		try {
//			this.usersService.findByIdAndDelete(2);
//		} catch (NotFoundException ex) {
//			log.error(ex.getMessage());
//		}
//
//		// this.usersService.findByIdAndUpdate(1, new User("Aldo", "Baglio", "aldo@gmail.com", "12345678"));
//		this.blogsService.saveNewBlog(1, "Spring", "Non è così male come credevo");

		this.usersService.filterByNames(new ArrayList<>(List.of("Aldo", "Frodo Baggins", "Gollum"))).forEach(System.out::println);

	}
}
