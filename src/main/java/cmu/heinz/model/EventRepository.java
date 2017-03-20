package cmu.heinz.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by hhahann on 3/19/17.
 */
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
}