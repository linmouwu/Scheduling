package cmu.heinz.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 */

@Transactional
public interface PermissionGroupRepository extends CrudRepository<PermissionGroup, Integer> {

    @Query(value = "select * from PERMISSION_GROUP where role = (:param1) limit 1", nativeQuery = true)
    PermissionGroup findByRole(@Param("param1") String role);

}
