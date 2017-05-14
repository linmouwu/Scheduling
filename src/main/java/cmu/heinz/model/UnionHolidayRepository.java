package cmu.heinz.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Holiday repository.
 */

@Transactional
public interface UnionHolidayRepository extends CrudRepository<UnionHoliday, Integer> {
    @Query(value = "select * from Union_Holiday where unions_id = (:param1)", nativeQuery = true)
    List<UnionHoliday> findByUnionID(@Param("param1") int unionid);
}
