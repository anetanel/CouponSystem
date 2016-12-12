package com.netanel.coupons.web.business;

import java.time.LocalDate;
import java.util.Hashtable;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.netanel.coupons.ejb.IncomeService;
import com.netanel.coupons.ejb.IncomeServiceBean;
import com.netanel.coupons.income.Income;

public class BusinessDelegate {
	//private IncomeService incomeService;
	private InitialContext ctx;
	private QueueConnectionFactory qconFactory;
	private QueueConnection qcon;
	private QueueSession qsession;
	private QueueSender qsender;
	private Queue queue;
	private TextMessage txtMsg;

	public BusinessDelegate() {
		init();
	}
	
	public void storeIncome(Income income) {
		System.out.println("In BD");
		//System.out.println(incomeService.storeIncome(income));		
	}
	
	public void testQueue() {
		try {
			//txtMsg = qsession.createTextMessage("blaaah");
			//qsender.send(txtMsg);
			Income income = new Income();
			income.setName("My name");
			income.setAmount(199.95);
			income.setDate(LocalDate.now());
			
			ObjectMessage objMsg = qsession.createObjectMessage(income);
			qsender.send(objMsg);
			qsender.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	private void init() {
		try {
			ctx = getInitialContext();
			qconFactory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
			qcon = qconFactory.createQueueConnection();	
			qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			queue = (Queue) ctx.lookup("jms/queue/IncomeQueue");
			qsender = qsession.createSender(queue);
		} catch (NamingException | JMSException e) {
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
	
//    private static IncomeService lookupIncomeService() throws NamingException {
//        final Hashtable<String, String> jndiProperties = new Hashtable<>();
//        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
//        final Context context = new InitialContext(jndiProperties);
//        // The app name is the application name of the deployed EJBs. This is typically the ear name
//        // without the .ear suffix. However, the application name could be overridden in the application.xml of the
//        // EJB deployment on the server.
//        // Since we haven't deployed the application as a .ear, the app name for us will be an empty string
//        final String appName = "CouponSystemEAR";
//        // This is the module name of the deployed EJBs on the server. This is typically the jar name of the
//        // EJB deployment, without the .jar suffix, but can be overridden via the ejb-jar.xml
//        // In this example, we have deployed the EJBs in a jboss-as-ejb-remote-app.jar, so the module name is
//        // jboss-as-ejb-remote-app
//        final String moduleName = "CouponSystemEJB";
//        // AS7 allows each deployment to have an (optional) distinct name. We haven't specified a distinct name for
//        // our EJB deployment, so this is an empty string
//        final String distinctName = "";
//        // The EJB name which by default is the simple class name of the bean implementation class
//        final String beanName = IncomeServiceBean.class.getSimpleName();
//        // the remote view fully qualified class name
//        final String viewClassName = IncomeService.class.getName();
//        // let's do the lookup
//        return (IncomeService) context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
//    }
	
}
