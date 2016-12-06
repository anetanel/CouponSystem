package com.netanel.coupons.ejb;

import javax.ejb.Stateless;

import com.netanel.coupons.income.Income;

@Stateless
public class IncomeServiceBean implements IncomeService {

    public IncomeServiceBean() {
    	System.out.println("**********************" + this.getClass().getName() + " started...");
    }
    
    @Override
    public String doSomething() {
    	return "*******************************" + this.getClass().getName() + " is Doing something..";
    }

	@Override
	public String storeIncome(Income income) {
		return "Storing Income:/n" + income;		
	}
}
