/* (a) Write Two different outputs from executing the program Increment

	Thread A value = 1
	Thread B value = 2
	Thread B value = 4
	Thread A value = 3

	Thread B value = 2
	Thread B value = 3
	Thread A value = 1
	Thread A value = 4

   (b) Fix the code so that it only produces one result.
   - added synchronized to incrementAndWrite() method
*/

public class Increment implements Runnable{
	private int value = 0;
	// add synchronized to make question B correct
	private synchronized void incrementAndPrint(){
	  value++;
	  System.out.println("Thread " + Thread.currentThread().getName()
					+ " value = " + value);
	}

	public void run(){
		for (int i = 0; i < 2; i++)
		{
			//Thread.sleep(500); // 0.5 seconds
			incrementAndPrint();
		}
	}

	public static void main(String[] args){
		Increment inc = new Increment();
		new Thread(inc, "A").start();
		new Thread(inc, "B").start();
	}
}
