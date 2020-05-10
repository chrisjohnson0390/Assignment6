package com.meritamerica.assignment6.misc;

import com.meritamerica.assignment6.accounts.*;
import com.meritamerica.assignment6.exceptions.*;
import com.meritamerica.assignment6.repository.*;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javassist.NotFoundException;

@RestController
public class MeritBankController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AccountHolderRepository accountHolderRepository;

	@Autowired
	private AccountHolderContactDetailsRepository accountHolderContactDetailsRepository;

	@Autowired
	private CheckingAccountRepository checkingAccountRepository;

	@Autowired
	private SavingsAccountRepository savingsAccountRepository;

	@Autowired
	private CDAccountRepository cdAccountRepository;

	@PostMapping("/AccountHolders")
	@ResponseStatus(HttpStatus.CREATED)
	public AccountHolder addAccountHolder(@RequestBody @Valid AccountHolder accountHolder) {
		accountHolderRepository.save(accountHolder);
		return accountHolder;
	}

	@GetMapping(value = "/AccountHolders")
	@ResponseStatus(HttpStatus.OK)
	public List<AccountHolder> getAccountHolders() {
		return accountHolderRepository.findAll();
	}

	@GetMapping(value = "/AccountHolders/{id}")
	public AccountHolder getAccountHolderById(@PathVariable(name = "id") long id) throws NoResourceFoundException {
		AccountHolder accountHolder = accountHolderRepository.findById(id);
		return accountHolder;
	}
	
	@PostMapping("/AccountHolders/{id}/CheckingAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public CheckingAccount addCheckingAccount(@PathVariable(name = "id") long id, @RequestBody @Valid CheckingAccount checkingAccount) throws ExceedsCombinedBalanceLimitException, NotFoundException, NegativeAmountException {
		AccountHolder accountHolder = accountHolderRepository.findById(id);
		accountHolder.addCheckingAccount(checkingAccount);
		checkingAccountRepository.save(checkingAccount);
		return checkingAccount;
	}
	
	@GetMapping(value = "/AccountHolders/{id}/CheckingAccounts")
	@ResponseStatus(HttpStatus.OK)
	public List<CheckingAccount> getCheckingAccounts(@PathVariable(name = "id") long id) throws NoResourceFoundException {
		AccountHolder accountHolder = accountHolderRepository.findById(id);
		List<CheckingAccount> checkingAccount = checkingAccountRepository.findByAccountHolder(accountHolder.getId());
		return checkingAccount;
	}

	@PostMapping("/AccountHolders/{id}/SavingsAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public SavingsAccount addSavingsAccount(@PathVariable(name = "id") long id, @RequestBody @Valid SavingsAccount savingsAccount) throws ExceedsCombinedBalanceLimitException, NotFoundException, NegativeAmountException {
		AccountHolder accountHolder = accountHolderRepository.findById(id);
		accountHolder.addSavingsAccount(savingsAccount);
		savingsAccountRepository.save(savingsAccount);
		return savingsAccount;
	}

	@GetMapping(value = "/AccountHolders/{id}/SavingsAccounts")
	@ResponseStatus(HttpStatus.OK)
	public List<SavingsAccount> getSavingsAccounts(@PathVariable(name = "id") long id) throws NoResourceFoundException {
		AccountHolder accountHolder = accountHolderRepository.findById(id);
		List<SavingsAccount> savingsAccount = savingsAccountRepository.findByAccountHolder(accountHolder.getId());
		return savingsAccount;
	}


	@GetMapping(value = "/AccountHolders/{id}/CDAccounts")
	@ResponseStatus(HttpStatus.OK)
	public List<CDAccount> getCDAccounts(@PathVariable(name = "id") long id) throws NoResourceFoundException {
		AccountHolder accountHolder = accountHolderRepository.findById(id);
		List<CDAccount> CDAccount = cdAccountRepository.findByAccountHolder(accountHolder.getId());
		return CDAccount;
	}

	@PostMapping("/AccountHolders/{id}/CDAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public CDAccount addCdAccount(@PathVariable(name = "id") long id, @RequestBody @Valid CDAccount CDAccount) throws ExceedsCombinedBalanceLimitException, NotFoundException, NegativeAmountException {
		AccountHolder accountHolder = accountHolderRepository.findById(id);
		accountHolder.addCDAccount(CDAccount);
		cdAccountRepository.save(CDAccount);
		return CDAccount;
	}
	
	@CrossOrigin
	@PostMapping("CDOfferings")
	@ResponseStatus(HttpStatus.CREATED)
	public CDOffering createCDOffering(@RequestBody CDOffering CDOffering) {
		MeritBank.addCDOffering(CDOffering);
		return CDOffering;
	}
	
	@CrossOrigin
	@GetMapping("CDOfferings")
	public List<CDOffering> getCDOfferings() throws NotFoundException {
		List<CDOffering> CDOffering = MeritBank.getCDOfferings();
		return CDOffering;
	}
}