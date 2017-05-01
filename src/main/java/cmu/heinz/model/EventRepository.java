package cmu.heinz.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Event Reponsitory interface.
 * Created by Nan Xia on 3/19/17.
 */
import java.util.Date;
public interface EventRepository extends CrudRepository<Event, String> {

    @Query(value = "select * from EVENT_REQUEST where uid = (:param1) AND event_status = 'pending'", nativeQuery = true)
    List<Event> findByPendingUID(@Param("param1") String uid);

    @Query(value = "select * from EVENT_REQUEST where union_id = (:param1) AND event_status = 'pending'", nativeQuery = true)
    List<Event> findByPendingUnionID(@Param("param1") String unionid);

    @Query(value = "select * from EVENT_REQUEST where uid = (:param1) AND (event_status = 'approved' OR event_status = 'declined')", nativeQuery = true)
    List<Event> findByPreviousUID(@Param("param1") String uid);

    @Query(value = "select * from EVENT_REQUEST where union_id = (:param1) AND (event_status = 'approved' OR event_status = 'declined')", nativeQuery = true)
    List<Event> findByPreviousUnionID(@Param("param1") String uid);

    @Query(value = "select * from EVENT_REQUEST where id = (:param1)", nativeQuery = true)
    Event findByID(@Param("param1") int id);

    @Query(value = "select * from EVENT_REQUEST where union_id = (:param1) AND event_status != 'pending' AND event_status != 'declined'", nativeQuery = true)
    List<Event> findByAllUnionID(@Param("param1") int unionid);

    @Query(value = "select * from EVENT_REQUEST where union_id = (:param1) AND shift_type = (:param2) AND event_status != 'pending' AND event_status != 'declined'", nativeQuery = true)
    List<Event> findByAllUnionIDAndShift(@Param("param1") int union_id,@Param("param2") int shift_id);
    @Query(value = "select * from EVENT_REQUEST where officer_id = (:param1) AND event_status != 'pending' AND event_status != 'declined'", nativeQuery = true)
    List<Event> findByOfficerID(@Param("param1") int officerid);
    @Query(value = "select COUNT(DISTINCT officer_id) from EVENT_REQUEST where union_id = (:param1) AND event_status != 'pending' AND event_status != 'declined' AND start_time <= (:param2) AND end_time >= (:param2) AND shift_Type = (:param3)", nativeQuery = true)
    Integer findByAllDate(@Param("param1") int unionid,@Param("param2") String date, @Param("param3") String shiftType);
//@Query(value = "select COUNT(DISTINCT officer_id) from EVENT_REQUEST where union_id = (:param1) AND event_status != 'pending' AND event_status != 'declined' AND start_time <= (:param2) AND end_time >= (:param3)", nativeQuery = true)
//Integer findByAllDate(@Param("param1") int unionid,@Param("param2") String sd, @Param("param3") String ed);

}