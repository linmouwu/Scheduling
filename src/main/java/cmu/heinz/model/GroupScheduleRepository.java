package cmu.heinz.model;

        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.data.repository.query.Param;

        import javax.transaction.Transactional;
        import java.util.List;

/**
 * Created by hhahann on 4/12/17.
 */
@Transactional
public interface GroupScheduleRepository extends CrudRepository<GroupSchedule, String> {
    @Query(value = "select SUM(selected_officer) from GROUP_SCHEDULE where union_id = (:param1) AND schedule_status != 'pending' AND schedule_status != 'declined' AND start_time <= (:param2) AND end_time >= (:param2) AND shift_id = (:param3)", nativeQuery = true)
    Integer findByAllDate(@Param("param1") int unionid, @Param("param2") String date, @Param("param3") int shiftId);
    //@Query(value = "select COUNT(DISTINCT officer_id) from EVENT_REQUEST where union_id = (:param1) AND event_status != 'pending' AND event_status != 'declined' AND start_time <= (:param2) AND end_time >= (:param3)", nativeQuery = true)
//Integer findByAllDate(@Param("param1") int unionid,@Param("param2") String sd, @Param("param3") String ed);
    @Query(value = "select * from GROUP_SCHEDULE where union_id = (:param1) order by start_time desc", nativeQuery = true)
    List<GroupSchedule> findByUnion(@Param("param1") int unionId);
    @Query(value = "select * from GROUP_SCHEDULE where id = (:param1)", nativeQuery = true)
    GroupSchedule findById(@Param("param1") int id);

    @Query(value = "select * from GROUP_SCHEDULE where union_id = (:param1) AND schedule_status != 'pending' AND schedule_status != 'declined' AND shift_id = (:param2)", nativeQuery = true)
    List<GroupSchedule> findByAllDateUnionShift(@Param("param1") int unionid, @Param("param2") int shiftId);


}

