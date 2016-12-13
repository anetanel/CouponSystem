package com.netanel.coupons.tests;

import java.time.LocalDate;

import com.netanel.coupons.income.Income;
import com.netanel.coupons.income.IncomeType;

public class TestIncome {
	public static void main(String[] args) {
		
		Income income = new Income();
		income.setName("testincome1");
		income.setAmount(199.95);
		income.setDate(LocalDate.now());
		income.setIncomeType(IncomeType.CUSTOMER_PURCHASE);
		
		System.out.println(income);
		System.out.println(income.getIncomeType().getDescription());
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
//		EntityManager em = emf.createEntityManager();
//		
//		//em.getTransaction().begin();
//		em.persist(income);
//		//em.getTransaction().commit();
//		em.close();
		
	}
}
