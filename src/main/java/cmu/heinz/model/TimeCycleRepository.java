package cmu.heinz.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Mouwu Lin
 * @AndrewID mouwul
 */
public interface TimeCycleRepository extends CrudRepository<TimeCycle, Integer> {

    @Query(value = "update TIME_CYCLE set activated=false where id = (:param1)", nativeQuery = true)
    void activateById(@Param("param1") Integer id);

    @Query(value = "update TIME_CYCLE set activated=true where id = (:param1)", nativeQuery = true)
    void deactivateById(@Param("param1") Integer id);

    @Query(value = "select * from TIME_CYCLE where activated = true order by end_date DESC limit 1", nativeQuery = true)
    TimeCycle findActivate();

}
