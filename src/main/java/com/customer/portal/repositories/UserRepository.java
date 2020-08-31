package com.customer.portal.repositories;

import com.customer.portal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	User findByMobileNumber(String mobileNumber);
}