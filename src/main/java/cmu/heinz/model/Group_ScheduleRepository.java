package cmu.heinz.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by hhahann on 4/12/17.
 */
public interface Group_ScheduleRepository extends CrudRepository<Group_Schedule, String> {
    @Query(value = "select SUM(selected_officer) from GROUP_SCHEDULE where union_id = (:param1) AND event_status != 'pending' AND event_status != 'declined' AND start_time <= (:param2) AND end_time >= (:param2) AND shift_Type = (:param3)", nativeQuery = true)
    Integer findByAllDate(@Param("param1") int unionid, @Param("param2") String date, @Param("param3") String shiftType);
//@Query(value = "select COUNT(DISTINCT officer_id) from EVENT_REQUEST where union_id = (:param1) AND event_status != 'pending' AND event_status != 'declined' AND start_time <= (:param2) AND end_time >= (:param3)", nativeQuery = true)
//Integer findByAllDate(@Param("param1") int unionid,@Param("param2") String sd, @Param("param3") String ed);

}
