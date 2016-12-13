package com.netanel.coupons.ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;

import com.netanel.coupons.income.Income;

@Remote
public interface IncomeService {
	
	public String storeIncome(Income income);
	
	public List<Income> viewAllIncome();
	
	public List<Income> viewIncomeByCustomer(long customerId);
	
	public List<Income> viewIncomeByCompany(long companyId);

}
