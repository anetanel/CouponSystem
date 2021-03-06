package com.netanel.coupons.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.netanel.coupons.income.Income;

/**
 * Income Service Stateless Bean.
 * Used to persist client's income and retrieve income information.
 *
 */
@Stateless
public class IncomeServiceBean implements IncomeService {

	@PersistenceContext(unitName="PersistenceUnit")
	private EntityManager entityManager;
	
    public IncomeServiceBean() {}
    

	@Override
	public void storeIncome(Income income) {
		entityManager.persist(income);
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
		query = entityManager.createQuery("SELECT i FROM Income i WHERE i.clientType LIKE 'CUSTOMER' AND i.clientId=" + customerId);
		List<Income> incomes = query.getResultList();
		return incomes;
	}


	@Override
	public List<Income> viewIncomeByCompany(long companyId) {
		Query query;
		query = entityManager.createQuery("SELECT i FROM Income i WHERE i.clientType LIKE 'COMPANY' AND i.clientId=" + companyId);
		List<Income> incomes = query.getResultList();
		return incomes;
	}
}
