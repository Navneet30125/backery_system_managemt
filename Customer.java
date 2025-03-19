package com.Bakery_multithreading.program;

public class Customer implements Runnable{
	Bakery bakery;
	String customerName;
	
	public Customer(Bakery bakery, String customerName) {
		super();
		this.bakery = bakery;
		this.customerName = customerName;
	}
	
	@Override
	public void run() {
		   while (!bakery.isProductionFinished()) {
	            bakery.buyGoods(customerName);
	        }
	        System.out.println(customerName + " finished shopping.");
		}
	}
