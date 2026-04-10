package riccardogulin.u5d4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import riccardogulin.u5d4.entities.User;

import java.util.List;
import java.util.Optional;

@Repository // Specializzazione di @Component dedicata al mondo dei DB
public interface UsersRepository extends JpaRepository<User, Long> {
	// Quando estendo JpaRepository, ottengo automaticamente tutti i metodi base già pronti all'uso (.save(), .findById(), .....)
	// Eventualmente poi potrò anche aggiungere qua ulteriori metodi custom
	// <User, Long> nelle parentesi angolari devo indicare <Entità, Tipo dell'id di quell'Entità>

	// ******************************************** DERIVED QUERIES **************************************************
	List<User> findBySurname(String surname); // SELECT * FROM users WHERE surname = :surname

	List<User> findByNameAndSurname(String name, String surname); // SELECT * FROM users WHERE name = :name AND surname = :surname;

	List<User> findByNameStartingWithIgnoreCase(String partialName); // SELECT * FROM users WHERE name ILIKE CONCAT(:partialName, '%')

	List<User> findByNameIn(List<String> names);

	List<User> findByEmailIsNotNull();

	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);

	// Link alla documentazione -> https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

	// ************************************************ JPQL QUERIES ********************************************
	@Query("SELECT u FROM User u WHERE u.name = :name")
	List<User> filterByName(String name);

	// ************************************************ NATIVE QUERIES ********************************************
	@Query(nativeQuery = true, value = "SELECT * FROM users WHERE name = :name")
	List<User> filterByNameSQL(String name);


}
