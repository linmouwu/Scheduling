package cmu.heinz.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Holiday repository.
 * @author Mouwu Lin
 * @AndrewID mouwul
 */

@Transactional
public interface HolidayRepository extends CrudRepository<Union_Holiday, String> {
    @Query(value = "select * from Union_Holiday where unions_id = (:param1)", nativeQuery = true)
    List<Union_Holiday> findByUnionID(@Param("param1") int unionid);
}
