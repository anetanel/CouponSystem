package com.netanel.coupons.ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.netanel.coupons.income.Income;

@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "IncomeQueue"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "IncomeQueue")
public class IncomeConsumerBean implements MessageListener {

	@EJB
	IncomeService incomeService;
	
    public IncomeConsumerBean() {
    }
	
	public void onMessage(Message message) {
			ObjectMessage objMsg = (ObjectMessage) message;
            try {
            	Income income = (Income) objMsg.getObject();
				System.out.println(this.getClass().getName()
						+ " has received a message : " + (Income) income);
				System.out.println(incomeService.storeIncome(income));
			} catch (JMSException e) {
				e.printStackTrace();
			}
    }

}
