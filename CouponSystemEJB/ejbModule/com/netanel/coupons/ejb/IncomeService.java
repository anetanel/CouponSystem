package com.netanel.coupons.ejb;

import javax.ejb.Remote;

import com.netanel.coupons.income.Income;

@Remote
public interface IncomeService {
	
	public String doSomething();

	public String storeIncome(Income income);

}
