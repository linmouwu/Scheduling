package cmu.heinz.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Union Repositor.
 * @author Mouwu Lin
 * @AndrewID mouwul
 */

@Transactional
public interface UnionRepository extends CrudRepository<Union, String> {
    @Query(value = "select * from UNIONS where Name = (:param1) limit 1", nativeQuery = true)
    Union findByName(@Param("param1") String name);
}
