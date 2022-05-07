package com.example.workflow;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Named
public class ReserveSeatOnTrain implements JavaDelegate {

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		
		String money = "0.0";
		String ticketType = "Coach"; // default ticket type
		
		money = (String) delegateExecution.getVariable("money");
		double moneyDouble = Double.parseDouble(money);
		
		if (moneyDouble >= 10000) {
			ticketType = "First Class";
		} else if (moneyDouble >= 5000) {
			ticketType = "Business Class";
		}
		
		delegateExecution.setVariable("ticketType", ticketType);
		
	}

}
