package com.netanel.coupons.web.services;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.netanel.coupons.facades.ClientType;
import com.netanel.coupons.income.Income;
import com.netanel.coupons.jbeans.CouponType;
import com.netanel.coupons.web.business.BusinessDelegate;

@Path("general")
public class GeneralService {
	@Context
	private HttpServletRequest request;
	
	private BusinessDelegate bd = new BusinessDelegate();

	public GeneralService() {
		
	}
	
	@GET
	@Path("clienttype")
	@Produces(MediaType.TEXT_PLAIN)
	public String getClientType() {
		ClientType clientType = (ClientType) request.getSession(false).getAttribute("CLIENT_TYPE");
		return clientType.toString().toLowerCase();
		//return "{\"clientType\": \""+ clientType.toString() + "\"}";
	}

	@GET
	@Path("getCouponTypes")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<String> getCouponTypes() {
		Set<String> s = new HashSet<>();
		for (CouponType c : CouponType.values()) {
			s.add(c.name());
		}
		return s;
	}
	 
	
	@POST
	@Path("income")
	public void income(@QueryParam("name") String name) {
		
		Income income = new Income();
		income.setName(name);
		income.setAmount(199.95);
		income.setDate(LocalDate.now());
		
		bd.storeIncome(income);
	
	}
	
	@POST
	@Path("testQueue")
	public void testQueue() {
		bd.testQueue();
	}
	
	
	
	
	
}
