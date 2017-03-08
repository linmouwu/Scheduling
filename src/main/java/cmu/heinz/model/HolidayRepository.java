package cmu.heinz.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mouwu Lin
 * @AndrewID mouwul
 */

@Transactional
public interface HolidayRepository extends CrudRepository<Holiday, String> {
}
