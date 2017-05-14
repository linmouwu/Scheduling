package cmu.heinz.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 */
@Transactional
public interface HolidayRepository extends CrudRepository<Holiday, Integer> {

}
