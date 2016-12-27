package com.netanel.coupons.ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.netanel.coupons.income.Income;

/**
 * Income Consumer Message Driven Bean.
 * Listens to the "IncomeQueue" queue, and passes the {@code Income} object to the Income Service for persisting. 
 * @see IncomeService 
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "IncomeQueue"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "IncomeQueue")
public class IncomeConsumerBean implements MessageListener {

	@EJB
	private IncomeService incomeService;
	
    public IncomeConsumerBean() {
    }
	
    
	public void onMessage(Message message) {
			ObjectMessage objMsg = (ObjectMessage) message;
            try {
            	Income income = (Income) objMsg.getObject();
				incomeService.storeIncome(income);
			} catch (JMSException e) {
				e.printStackTrace();
			}
    }

}
