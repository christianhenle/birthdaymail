package birthdaymail.repository;

import org.springframework.data.repository.CrudRepository;

import birthdaymail.domain.Mitarbeiter;

public interface MitarbeiterRepository extends CrudRepository<Mitarbeiter, Long> {

}
