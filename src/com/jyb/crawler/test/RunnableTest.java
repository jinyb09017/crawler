package com.jyb.crawler.test;

public class RunnableTest implements Runnable {
	int k=0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RunnableTest().test();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(k<100)
		{
			System.out.println("¼ÌÐøÔËÐÐ"+k);
			k=k+1;
		}
		
	}
	public void test()
	{
		for(int i=0;i<2;i++)
		new Thread(this).start();
	}
	
	

}
