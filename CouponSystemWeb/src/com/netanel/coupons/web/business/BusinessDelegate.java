package com.netanel.coupons.web.business;

import java.util.Hashtable;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

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

	public BusinessDelegate() {
		init();
	}
	
	public synchronized void storeIncome(Income income) {
		try {
			ObjectMessage objMsg = qsession.createObjectMessage(income);
			qsender.send(objMsg);
		} catch (JMSException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
		
//	public void testQueue() {
//		try {
//
//			Income income = new Income();
//			income.setName("My name");
//			income.setAmount(199.95);
//			income.setDate(LocalDate.now());
//			
//			ObjectMessage objMsg = qsession.createObjectMessage(income);
//			qsender.send(objMsg);
//			close();
//		} catch (JMSException e) {
//			e.printStackTrace();
//		}
//	}
	
	private void init() {
		try {
			ctx = getInitialContext();
			qconFactory = (QueueConnectionFactory) ctx.lookup(CONNECTION_FACTORY);
			qcon = qconFactory.createQueueConnection();	
			qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			queue = (Queue) ctx.lookup(JMS_QUEUE_INCOME_QUEUE);
			qsender = qsession.createSender(queue);
		} catch (NamingException | JMSException e) {
			e.printStackTrace();
		}
	}
	
	private void close() {
		try {
			qsender.close();
			qsession.close();
			qcon.close();
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
