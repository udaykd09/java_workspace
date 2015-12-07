

import java.util.Scanner;

/*Create your own Java Fraction Class, your class must support the following:
Two Constructors
Addition for Fraction
Subtraction for Fraction
Create a FractionDriver Class to test your Fraction Class
Both class must be in a package called MyCoolMath*/


public class FractionDriver extends Thread {
	
	public void run() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws InterruptedException {

		Scanner s = new Scanner(System.in);
		System.out.println("Enter 1st fraction numerator : ");
		int w = s.nextInt();
		System.out.println("Enter 1st fraction Denominator : ");
		int x = s.nextInt();
		System.out.println("Enter 2nd fraction numerator : ");
		int y = s.nextInt();
		System.out.println("Enter 2nd fraction Denominator : ");
		int z = s.nextInt();
		Fraction a = new Fraction(w,x);
		Fraction b = new Fraction(y,z);
		System.out.println("First Fraction is : " + a.getNumerator()  + "/" + a.getDenominator());
		System.out.println("Second Fraction is : " + b.getNumerator() + "/" + b.getDenominator());
		Fraction c = a.addFractions(a,b);
		System.out.println("Addition of Fraction is : " + c.getNumerator() + "/" + c.getDenominator());
		Fraction d = b.subFractions(a,b);
		System.out.println("Subtraction of Fraction is : " + d.getNumerator() + "/" + d.getDenominator());
		s.close();
		
	}


}
