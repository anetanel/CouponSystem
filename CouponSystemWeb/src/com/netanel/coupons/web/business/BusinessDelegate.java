package com.netanel.coupons.web.business;

import java.util.Hashtable;
import java.util.List;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.netanel.coupons.ejb.IncomeService;
import com.netanel.coupons.ejb.IncomeServiceBean;
import com.netanel.coupons.income.Income;

public class BusinessDelegate {
	private static final String CONNECTION_FACTORY = "ConnectionFactory";
	private static final String JMS_QUEUE_INCOME_QUEUE = "jms/queue/IncomeQueue";
	private InitialContext ctx;
	private QueueConnectionFactory qconFactory;
	private QueueConnection qcon;
	private QueueSession qsession;
	private QueueSender qsender;
	private Queue queue;
	private IncomeService incomeService;

	public BusinessDelegate() {
		init();
	}

	public synchronized void storeIncome(Income income) {
		try {
			qcon = qconFactory.createQueueConnection();
			qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			queue = (Queue) ctx.lookup(JMS_QUEUE_INCOME_QUEUE);
			qsender = qsession.createSender(queue);
			ObjectMessage objMsg = qsession.createObjectMessage(income);
			qsender.send(objMsg);
		} catch (JMSException | NamingException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public synchronized List<Income> viewAllIncome() {
		List<Income> list = incomeService.viewAllIncome();
		return list;
	}
	
	public synchronized List<Income> viewIncomeByCustomer(long customerId) {
		List<Income> list = incomeService.viewIncomeByCustomer(customerId);
		return list;
	}
	
	public synchronized List<Income> viewIncomeByCompany(long companyId) {
		List<Income> list = incomeService.viewIncomeByCompany(companyId);
		return list;
	}


	private void init() {
		try {
			final String appName = "CouponSystemEAR";
			final String moduleName = "CouponSystemEJB";
			final String distinctName = "";
			final String beanName = IncomeServiceBean.class.getSimpleName();
			final String viewClassName = IncomeService.class.getName();

			ctx = getInitialContext();
			incomeService = (IncomeService) ctx.lookup(
					"ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
			qconFactory = (QueueConnectionFactory) ctx.lookup(CONNECTION_FACTORY);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private void close() {
		try {
			qsender.close();
			qsession.close();
			qcon.close();
			//ctx.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	private InitialContext getInitialContext() {
		final Hashtable<String, String> jndiProperties = new Hashtable<>();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		try {
			return new InitialContext(jndiProperties);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
