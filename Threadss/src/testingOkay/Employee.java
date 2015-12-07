package testingOkay;

public class Employee {
	
	 void Updatesalary(int x){
		x += 0.1*x;
		System.out.println("Salary" + Thread.currentThread() + x);
//		bonus(x);
	}
	
	void bonus(int y){
		y += 0.1*y;
		System.out.println("Salary+ bonus" + Thread.currentThread() + y);
	}
	
	public static void main(String args[]){
		final Employee a = new Employee();
		Runnable b = new Runnable(){ 				//Just like Cars, made a reference of the interface and implements method
			@Override
			synchronized public void run() {
				a.Updatesalary(1000);
				a.bonus(100);
			}
		};
		Thread a1 = new Thread(b);
		a1.start();
		Thread a2 = new Thread(b);
		a2.start();
		Thread a3 = new Thread(b);
		a3.start();

}
}