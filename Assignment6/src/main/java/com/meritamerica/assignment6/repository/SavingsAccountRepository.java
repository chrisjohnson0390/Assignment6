package com.meritamerica.assignment6.repository;

import com.meritamerica.assignment6.accounts.*;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long>{
	List<SavingsAccount> findByAccountHolder(long id);

}