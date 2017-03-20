package cmu.heinz.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Mouwu Lin
 * @AndrewID mouwul
 */

@Transactional
public interface OfficerRepository extends CrudRepository<Officer, Integer> {

    @Query(value = "select * from OFFICER where last_name = (:param1) limit 1", nativeQuery = true)
    Officer findByLastName(@Param("param1") String lastName);

    @Query(value = "select * from OFFICER where uid = (:param1) limit 1", nativeQuery = true)
    Officer findByUID(@Param("param1") String UID);


}