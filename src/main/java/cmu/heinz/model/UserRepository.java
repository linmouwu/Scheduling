package cmu.heinz.model;

import cmu.heinz.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Mouwu Lin
 * @AndrewID mouwul
 */

@Transactional
public interface UserRepository extends CrudRepository<User, String> {

}