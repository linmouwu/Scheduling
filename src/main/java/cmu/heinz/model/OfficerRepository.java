package cmu.heinz.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Officer repository.
 *
 */

@Transactional
public interface OfficerRepository extends CrudRepository<Officer, Integer> {

    @Query(value = "select * from OFFICER where last_name = (:param1) limit 1", nativeQuery = true)
    Officer findByLastName(@Param("param1") String lastName);

    @Query(value = "select * from OFFICER where uid = (:param1) limit 1", nativeQuery = true)
    Officer findByUID(@Param("param1") String UID);

    @Query(value = "select * from OFFICER where union_id = (:param1)", nativeQuery = true)
    List<Officer> findByUnion(@Param("param1") Integer unionId);

}
