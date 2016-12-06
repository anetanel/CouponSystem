package com.netanel.coupons.web.business;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.netanel.coupons.ejb.IncomeService;
import com.netanel.coupons.ejb.IncomeServiceBean;
import com.netanel.coupons.income.Income;

public class BusinessDelegate {
	IncomeService incomeService = null;

	public BusinessDelegate() {
		try {
			incomeService = lookupIncomeService();
		} catch (NamingException e) {
			e.printStackTrace();
		}		
	}
	
	public void storeIncome(Income income) {
		System.out.println("In BD");
		System.out.println(incomeService.storeIncome(income));		
	}
	
    private static IncomeService lookupIncomeService() throws NamingException {
        final Hashtable<String, String> jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final Context context = new InitialContext(jndiProperties);
        // The app name is the application name of the deployed EJBs. This is typically the ear name
        // without the .ear suffix. However, the application name could be overridden in the application.xml of the
        // EJB deployment on the server.
        // Since we haven't deployed the application as a .ear, the app name for us will be an empty string
        final String appName = "CouponSystemEAR";
        // This is the module name of the deployed EJBs on the server. This is typically the jar name of the
        // EJB deployment, without the .jar suffix, but can be overridden via the ejb-jar.xml
        // In this example, we have deployed the EJBs in a jboss-as-ejb-remote-app.jar, so the module name is
        // jboss-as-ejb-remote-app
        final String moduleName = "CouponSystemEJB";
        // AS7 allows each deployment to have an (optional) distinct name. We haven't specified a distinct name for
        // our EJB deployment, so this is an empty string
        final String distinctName = "";
        // The EJB name which by default is the simple class name of the bean implementation class
        final String beanName = IncomeServiceBean.class.getSimpleName();
        // the remote view fully qualified class name
        final String viewClassName = IncomeService.class.getName();
        // let's do the lookup
        return (IncomeService) context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
    }
	
}
