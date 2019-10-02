package com.axa.condiciones.persistenceLayer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.axa.condiciones.model.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
	public User findByAp(String ap);
	
	@Query(value = "SELECT u "
			+ "FROM User u "
			+ "WHERE u.ap = :ap "
			+ "AND u.username = :username "
			+ "AND u.address = :address ", nativeQuery = false)
	public User findByApUsernameAddress(
			@Param(value = "ap") String ap, 
			@Param(value = "username") String username, 
			@Param(value = "address") String address);
}
