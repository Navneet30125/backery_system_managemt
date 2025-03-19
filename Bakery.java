package com.Bakery_multithreading.program;

public class Bakery {
	int goodsAvailable = 0;
	final int MAX_GOODS_PER_DAY = 100;
	int goodsProducedToday = 0;

	public synchronized void bakeGoods() {
		try {
			while (goodsAvailable > 0) {
				wait();
			}
			if (goodsProducedToday < MAX_GOODS_PER_DAY) {
				int goodsToBake = Math.min(101, MAX_GOODS_PER_DAY - goodsAvailable);
				goodsAvailable += goodsToBake;
				goodsProducedToday += goodsToBake;
				System.out.println("Baker baked " + goodsToBake + " goods. Total available: " + goodsAvailable);
				notifyAll();
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public synchronized void buyGoods(String customerName) {
		try {
			while (goodsAvailable == 0 && goodsProducedToday < MAX_GOODS_PER_DAY) {
				wait();
			}
			if (goodsAvailable > 0) {
				goodsAvailable--;
				System.out.println(customerName + " bought a good. Goods left: " + goodsAvailable);
				if (goodsAvailable == 0) {
					notify(); // Notify baker if bakery is empty
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	public boolean isProductionFinished() {
		return goodsProducedToday >= MAX_GOODS_PER_DAY && goodsAvailable==0;
	}
}
