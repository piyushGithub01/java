package com.java.concurrency.example;

public class OddEvenPrinting {
	/*
	 * running two thread simultaneously  - one prints odd number, other prints even number using wait notify all 
	 */

	public static void main(String[] args) {

		OddEvenResource resource = new OddEvenResource();
		Thread t1 = new Thread(new OddPrinterThread(resource));
		Thread t2 = new Thread(new EvenPrinterThread(resource));
		t1.start();
		t2.start();
	}

}

class OddPrinterThread implements Runnable{

	private OddEvenResource resource;
	public OddPrinterThread(OddEvenResource resource){
		this.resource = resource;
	}
	@Override
	public void run() {
		synchronized(resource){
			while(true){
				if(this.resource.getCounter() % 2 == 1){
					try {
						resource.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					System.out.println("Odd  : "+resource.incrementAndGet());
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					resource.notifyAll();
				}
			}
		}
	}
	
}


class EvenPrinterThread implements Runnable{

	private OddEvenResource resource;
	public EvenPrinterThread(OddEvenResource resource){
		this.resource = resource;
	}
	@Override
	public void run() {
		synchronized(resource){
			while(true){
				if(this.resource.getCounter() == 0 || this.resource.getCounter() % 2 == 0){
					try {
						resource.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					System.out.println("Even : "+resource.incrementAndGet());
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					resource.notifyAll();
				}
			}
		}
	}
	
}

class OddEvenResource{
	private int counter = 0;
	public int getCounter(){
		return counter;
	}
	public int incrementAndGet(){
		return ++counter;
	}
}
