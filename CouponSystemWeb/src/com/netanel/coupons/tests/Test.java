package com.netanel.coupons.tests;


import java.time.LocalDate;
import com.netanel.coupons.income.Income;
import com.netanel.coupons.income.IncomeType;

public class Test {
	public static void main(String[] args) { 
		Income income = new Income("blah", LocalDate.now(), 10.0, IncomeType.COMPANY_NEW_COUPON);
		System.out.println(income);
		
		
		
	}
	
}
