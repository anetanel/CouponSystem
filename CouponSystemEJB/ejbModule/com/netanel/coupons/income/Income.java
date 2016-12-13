package com.netanel.coupons.income;

import java.io.Serializable;
import java.lang.Double;
import java.lang.Long;
import java.lang.String;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Income
 *
 */
@Entity
@Table(name="INCOME")
public class Income implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="DATE")
	private LocalDate date;
	
	@Column(name="AMOUNT")
	private Double amount;
	
	@Column(name="INCOME_TYPE")
	private IncomeType incomeType;
	
	private static final long serialVersionUID = 1L;

	public Income() {
	}   
	
	public Income(String name, LocalDate date, Double amount, IncomeType incomeType) {
		this.name = name;
		this.date = date;
		this.amount = amount;
		this.incomeType = incomeType;
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public LocalDate getDate() {
		return this.date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}   
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public IncomeType getIncomeType() {
		return incomeType;
	}
	
	public void setIncomeType(IncomeType incomeType) {
		this.incomeType = incomeType;
	}
	
	@Override
	public String toString() {
		return "Income [id=" + id + ", name=" + name + ", date=" + date + ", amount=" + amount + ", incomeType="
				+ incomeType + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((incomeType == null) ? 0 : incomeType.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Income other = (Income) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (incomeType != other.incomeType)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
   
}
