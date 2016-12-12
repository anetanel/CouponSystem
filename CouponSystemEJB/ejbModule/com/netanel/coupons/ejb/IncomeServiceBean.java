package com.netanel.coupons.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.netanel.coupons.income.Income;

@Stateless
public class IncomeServiceBean implements IncomeService {

	@PersistenceContext(unitName="PersistenceUnit")
	protected EntityManager entityManager;
	
    public IncomeServiceBean() {
    	System.out.println("**********************" + this.getClass().getName() + " started...");
    }
    
    @Override
    public String doSomething() {
    	return "*******************************" + this.getClass().getName() + " is Doing something..";
    }

	@Override
	public String storeIncome(Income income) {
		
		//entityManager.getTransaction().begin();
		
		entityManager.persist(income);
		//entityManager.getTransaction().commit();
		//entityManager.close();
		
		return "Storing Income: " + income + " em: " + entityManager;		
	}
}
