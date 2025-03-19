package com.Bakery_multithreading.program;

public class BakeryExample {

	public static void main(String[] args) {
    Bakery bakery = new Bakery();
    
    // Create and start the baker thread
    Thread bakerThread = new Thread(new Baker(bakery));
    bakerThread.start();
    
    // Create and start customer threads
    int numberOfCustomers = 5;
    Thread[] customerThreads = new Thread[numberOfCustomers];
    for (int i = 0; i < numberOfCustomers; i++) {
        customerThreads[i] = new Thread(new Customer(bakery, "Customer-" + (i + 1)));
        customerThreads[i].start();
    }
    // Wait for all threads to complete
    try {
    	bakerThread.join();
        for (Thread customerThread : customerThreads) {
            customerThread.join();
        }
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
    System.out.println("Bakery simulation finished.");
}
}
