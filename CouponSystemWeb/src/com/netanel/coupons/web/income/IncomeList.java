package com.netanel.coupons.web.income;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.netanel.coupons.income.Income;

@XmlRootElement
public class IncomeList {
	private List<Income> incomes;

	public IncomeList() {
	
	}
	public IncomeList(List<Income> incomes) {
		this.incomes = incomes;
	}
	public List<Income> getIncomes() {
		return incomes;
	}
	public void setIncomes(List<Income> incomes) {
		this.incomes = incomes;
	}
	@Override
	public String toString() {
		return "IncomeList [incomes=" + incomes + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((incomes == null) ? 0 : incomes.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IncomeList other = (IncomeList) obj;
		if (incomes == null) {
			if (other.incomes != null)
				return false;
		} else if (!incomes.equals(other.incomes))
			return false;
		return true;
	}
	
	
	
	
}
