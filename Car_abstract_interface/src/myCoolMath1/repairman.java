package myCoolMath1;

import MyCoolMath.IRepairable;
import MyCoolMath.Vehicle;
import MyCoolMath.mazda;
import MyCoolMath.mazda2;

public class repairman {

	public void repair(IRepairable v) {
		v.repair();
	}

	public static void main(String args[]) {

		Vehicle v = new mazda();

		repairman rr = new repairman();
		rr.repair(v);
		// // r1.repair();
		// // r2.drive();

	}
}