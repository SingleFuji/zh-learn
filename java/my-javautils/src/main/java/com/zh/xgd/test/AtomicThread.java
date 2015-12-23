package com.zh.xgd.test;

public class AtomicThread implements Runnable{

	public void run() {
		// TODO Auto-generated method stub
		CycleAtomicInteger count = new CycleAtomicInteger(20);
		for(int i=0;i<50;i++)
		{
			System.out.println(Thread.currentThread().getName()+i+"_COUNT_"+count.next());
		}
	}

}
