package com.axa.condiciones.persistenceLayer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.axa.condiciones.model.entities.UserExa;

public interface UserRepository extends CrudRepository<UserExa, Long> {
	public UserExa findByAp(String ap);
	
	@Query(value = "SELECT u "
			+ "FROM UserExa u "
			+ "WHERE u.ap = :ap "
			+ "AND u.username = :username "
			+ "AND u.address = :address ", nativeQuery = false)
	public UserExa findByApUsernameAddress(
			@Param(value = "ap") String ap, 
			@Param(value = "username") String username, 
			@Param(value = "address") String address);
}
