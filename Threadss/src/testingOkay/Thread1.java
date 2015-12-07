package testingOkay;

public class Thread1 {

	public static void main(String[] args) {
Threadb a = new Threadb();
Thread1 o = new Thread1();
//a.setPriority(8);
a.start();
synchronized (a) {
	
	try {
		a.wait();
		System.out.println("Total :"+ a.total);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
}

class Threadb extends Thread{
	int total=0;
	
	public void run(){
		for(int i=0; i<100; i++){
			total += i;
			//notifyAll();
		}
	}

}
