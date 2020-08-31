package com.customer.portal.repositories;

import com.customer.portal.entities.Identity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentityRepository extends  JpaRepository<Identity, Long>  {
}
