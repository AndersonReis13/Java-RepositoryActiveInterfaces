package application;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entites.Contract;
import model.entites.Installment;
import model.services.ContractService;
import model.services.PaypalService;

import java.time.LocalDate;

public class Project {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);	
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Enter a data for contract: ");
		System.out.print("Number: ");
		int number = sc.nextInt();
		System.out.print("Date: ");
		LocalDate date = LocalDate.parse(sc.next(), dtf);
		System.out.print("Contract value: ");
		double contractValue = sc.nextDouble();
		
		Contract obj = new Contract(number, date, contractValue);
		
		System.out.println("enter the number of installments: ");
		int n = sc.nextInt();
		
		ContractService contract = new ContractService(new PaypalService());
		
		contract.processContract(obj, n);
		
		System.out.println("Installments: ");
		
		for(Installment installments : obj.getIntstallment()) {
			System.out.println(installments);
		}
		
		
	}
}
