package com.netanel.coupons.ejb;

import java.util.List;
import javax.ejb.Remote;
import com.netanel.coupons.income.Income;

/**
 * Income Service Remote Interface
 *
 */
@Remote
public interface IncomeService {
	
	/**
	 * Persists the Income to the DB.
	 * @param income an {@code Income} object.
	 */
	public void storeIncome(Income income);
	
	/**
	 * Retrieves a list of all the incomes.
	 * @return a {@code List<Income>} of all the incomes in the system.
	 */
	public List<Income> viewAllIncome();
	
	/**
	 * Retrieves incomes by customer ID.
	 * @param customerId a {@code long} customer ID.
	 * @return a {@code List<Income>} of income by the customer. 
	 */
	public List<Income> viewIncomeByCustomer(long customerId);
	
	/**
	 * Retrieves incomes by company ID.
	 * @param companyId a {@code long} company ID.
	 * @return a {@code List<Income>} of income by the company.
	 */
	public List<Income> viewIncomeByCompany(long companyId);

}
