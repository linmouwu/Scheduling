package cmu.heinz.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.*;
/**
 */
@Transactional
public interface ScheduleOfficerRepository extends CrudRepository<ScheduleOfficer,String> {
    @Query(value = "select * from SCHEDULE_OFFICER where officer_id = (:param1)", nativeQuery = true)
    List<ScheduleOfficer> findByOfficer(@Param("param1") int officer_id);
    @Query(value = "select * from SCHEDULE_OFFICER where schedule_id = (:param1)", nativeQuery = true)
    List<ScheduleOfficer> findByScheduleId(@Param("param1") int schedule_id);
    @Query(value = "delete from SCHEDULE_OFFICER where officer_id = (:param1) AND schedule_id = (:param2)", nativeQuery = true)
    void deleteScheduleOfficer(@Param("param2") int schedule_id, @Param("param1") int officer_id);
}
