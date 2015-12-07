package myCoolMath1;

import MyCoolMath.IDrivable;
import MyCoolMath.Vehicle;
import MyCoolMath.mazda;
import MyCoolMath.mazda2;

public class Driver{
	
public void drive(IDrivable v) {
		v.drive();
	}

	public static void main(String args[]) {
		
		Driver d = new Driver();
		//Abstract method -- Vehicle v = new Vehicle();
		Vehicle v1 = new mazda();
		Vehicle v2 = new mazda2();
		
		//v.drive();
		d.drive(v1);
		d.drive(v2);
	}

}
