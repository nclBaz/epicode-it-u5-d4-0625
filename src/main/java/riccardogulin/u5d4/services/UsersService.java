package riccardogulin.u5d4.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riccardogulin.u5d4.entities.User;
import riccardogulin.u5d4.exceptions.NotFoundException;
import riccardogulin.u5d4.exceptions.ValidationException;
import riccardogulin.u5d4.repositories.UsersRepository;

import java.util.List;

@Service // Anche questa è una specializzazione di @Component
@Slf4j
public class UsersService {
	private final UsersRepository usersRepository;

	@Autowired
	public UsersService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	public List<User> findAll() {
		return this.usersRepository.findAll();
	}

	public void saveNewUser(User newUser) {

		// 1. Controllo che la mail non sia già in uso (TODO:)
		// 2. Altri controlli di validazione dei dati (es. password > 8 caratteri)
		if (newUser.getPassword().length() < 8) throw new ValidationException("Password troppo corta");
		// 3. Aggiunta ulteriori campi
		// 4. Salvo finalmente l'utente usando la repository
		this.usersRepository.save(newUser);
		// 5. Log di avvenuto salvataggio
		// System.out.println("L'utente " + newUser.getSurname() + " è stato salvato!");
		log.info("L'utente " + newUser.getSurname() + " è stato salvato!");
		// Log con Logback grazie a annotazione @Slf4j (alternativa più PRO ai normali system.out)
	}

	public User findById(long userId) {
//		Optional<User> foundOrNot = this.usersRepository.findById(userId);
//		if (foundOrNot.isPresent()) return foundOrNot.get();
//		else throw new NotFoundException(userId);
		return this.usersRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId)); // Alternativa più compatta al codice di sopra
	}

	public void findByIdAndDelete(long userId) {
		User found = this.findById(userId);

		this.usersRepository.delete(found);

		log.info("L'utente " + found.getId() + " è stato eliminato correttamente");

	}

	public void findByIdAndUpdate(long userId, User updatedUser) {
		// 0. Faccio tutti i controlli del caso

		// 1. Cerco l'utente tramite id
		User found = this.findById(userId);

		// 2. Aggiorno i campi di tale utente con quelli provenienti da updatedUser
		found.setName(updatedUser.getName());
		found.setSurname(updatedUser.getSurname());
		found.setEmail(updatedUser.getEmail());
		found.setPassword(updatedUser.getPassword());

		// 3. Ri-salvo l'utente modificato
		this.usersRepository.save(found);

		// 4. Log
		log.info("L'utente " + found.getId() + " è stato aggiornato");
	}
}
