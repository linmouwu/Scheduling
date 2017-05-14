package cmu.heinz.model;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 */

@Transactional
public interface ShiftTypeRepository extends CrudRepository<ShiftType, Integer> {

    @Query(value = "select * from shift_type where union_id = (:param1) and activated = true", nativeQuery = true)
    List<ShiftType> getShiftTypeByUnionId(@Param("param1") Integer unionId);

    @Query(value = "select * from shift_type where Shift_name = (:param1) and activated = true", nativeQuery = true)
    ShiftType findByName(@Param("param1") String type);

    @Modifying
    @Query(value = "delete from shift_type where id = (:param1)", nativeQuery = true)
    void removeShiftType(@Param("param1") Integer id);


}
