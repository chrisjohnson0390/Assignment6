package com.meritamerica.assignment6.repository;

import com.meritamerica.assignment6.misc.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {
	AccountHolder findById(long id);

	
 	
	

}