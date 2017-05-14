package cmu.heinz.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Union Repositor.
 */

@Transactional
public interface UnionRepository extends CrudRepository<Union, Integer> {
    @Query(value = "select * from UNIONS where Name = (:param1) limit 1", nativeQuery = true)
    Union findByName(@Param("param1") String name);
}
