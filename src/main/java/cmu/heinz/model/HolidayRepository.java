package cmu.heinz.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hhahann on 4/30/17.
 */
@Transactional
public interface HolidayRepository extends CrudRepository<Holiday, Integer> {

}
