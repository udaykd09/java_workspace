package testingOkay;

public class Account {
	int balance = 1000;

	public synchronized int getbal() {
		System.out.println("get="+Thread.currentThread());
		return balance;
	}

	public int putbal(int x) {
		balance += x;
		System.out.println("putbal="+Thread.currentThread());
		return balance;
	}

	public void transfer(int amt, Account x, Account y) {
		System.out.println("Trnfr=" + Thread.currentThread());
		withdrw(100, x , y);
		//System.out.println("Trnfr2=" + Thread.currentThread());
		deposit(amt, y);
		
	}

	public synchronized void withdrw(int amt, Account x, Account y) {
		System.out.println("Withdrw1 =" + Thread.currentThread());
		//transfer(100, x, y);
		//System.out.println("Withdrw2 =" + Thread.currentThread());
		int i = x.getbal();
		i = -amt;
		x.putbal(i);
		System.out.println("balance aftr Withdrw =" + x.getbal());
		System.out.println("Withdrw2 =" + Thread.currentThread());
	}

	public synchronized void  deposit(int amt, Account y) {
		System.out.println("deposit1="+Thread.currentThread());
		int i = y.getbal();
		i = amt;
		y.putbal(i);
		System.out.println("balance aftr Deposit=" + y.getbal());
		System.out.println("Done");
		System.out.println("deposit2="+Thread.currentThread());
	}

	public static void main(String[] args) {

		Account a = new Account();
		Account b = new Account();
		Moneytranfr a1 = new Moneytranfr(a,b);
		Moneytranfr b1 = new Moneytranfr(b,a);
		a1.start();
		b1.start();
	}
}

class Moneytranfr extends Thread {

	Account aa,bb;
	
	public Moneytranfr(Account aa, Account bb) {
	this.aa = aa;
	this.bb = bb;
	}

	public void run() {
		System.out.println("Run ="+ Thread.currentThread());
	 aa.transfer(100, aa, bb);
	 //bb.transfer(100, bb,aa);
	}
	

}