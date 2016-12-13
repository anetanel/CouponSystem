package com.netanel.coupons.ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.netanel.coupons.income.Income;

@Stateless
public class IncomeServiceBean implements IncomeService {

	@PersistenceContext(unitName="PersistenceUnit")
	protected EntityManager entityManager;
	
    public IncomeServiceBean() {
    	System.out.println("**********************" + this.getClass().getName() + " started...");
    }
    

	@Override
	public String storeIncome(Income income) {
		entityManager.persist(income);
		return "Storing Income: " + income;		
	}


	@Override
	public List<Income> viewAllIncome() {
		Query query;
		query = entityManager.createQuery("SELECT i FROM Income AS i");
		List<Income> incomes = query.getResultList();
		return incomes;
	}


	@Override
	public List<Income> viewIncomeByCustomer(long customerId) {
		Query query;
		query = entityManager.createQuery("SELECT i FROM Income AS i");
		List<Income> incomes = query.getResultList();
		return incomes;
	}


	@Override
	public List<Income> viewIncomeByCompany(long companyId) {
		// TODO Auto-generated method stub
		return null;
	}
}
