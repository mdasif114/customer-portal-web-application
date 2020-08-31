package com.customer.portal.repositories;

import com.customer.portal.entities.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<Bank, Long>  {
}
