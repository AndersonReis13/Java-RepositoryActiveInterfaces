package model.services;

import java.time.LocalDate;

import model.entites.Contract;
import model.entites.Installment;

public class ContractService {
	private OnlinePaymentService onlinePaymentService;

	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}
	
	public void processContract(Contract contract, int months) {
       
		double basicQuota = contract.getTotalvalue() / months;
		
		for(int i=1;i<=months;i++) {
    	  LocalDate dueDate = contract.getDate().plusMonths(i);
    	  
    	  double interest = onlinePaymentService.interest(basicQuota, i);
    	  double paymentFee = onlinePaymentService.paymentFee(basicQuota + interest);
    	  double quota = basicQuota + interest + paymentFee;
    	  
    	  contract.getIntstallment().add(new Installment(dueDate, quota));
    	  
		}
	}
	
}
