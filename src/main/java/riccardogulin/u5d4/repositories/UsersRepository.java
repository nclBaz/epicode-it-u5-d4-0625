package riccardogulin.u5d4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardogulin.u5d4.entities.User;

@Repository // Specializzazione di @Component dedicata al mondo dei DB
public interface UsersRepository extends JpaRepository<User, Long> {
	// Quando estendo JpaRepository, ottengo automaticamente tutti i metodi base già pronti all'uso (.save(), .findById(), .....)
	// Eventualmente poi potrò anche aggiungere qua ulteriori metodi custom
	// <User, Long> nelle parentesi angolari devo indicare <Entità, Tipo dell'id di quell'Entità>
}
