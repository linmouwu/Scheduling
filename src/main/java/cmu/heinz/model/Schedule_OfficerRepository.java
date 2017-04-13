package cmu.heinz.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.*;
/**
 * Created by hhahann on 4/12/17.
 */
public interface Schedule_OfficerRepository extends CrudRepository<Schedule_Officer,String> {
    @Query(value = "select * from SCHEDULE_OFFICER where officer_id = (:param1)", nativeQuery = true)
    List<Schedule_Officer> findByOfficer(@Param("param1") int officer_id);
}
