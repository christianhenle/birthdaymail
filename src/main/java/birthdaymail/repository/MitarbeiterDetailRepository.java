package birthdaymail.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import birthdaymail.domain.MitarbeiterDetail;

public interface MitarbeiterDetailRepository extends CrudRepository<MitarbeiterDetail, Long>,QueryDslPredicateExecutor<MitarbeiterDetail> {

}
