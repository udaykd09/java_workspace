package MyCoolMath;

abstract public class Vehicle implements IDrivable, IRepairable {

	public void drive() {
		System.out.println("Vehicle driving");
	}

	public void repair() {
		System.out.println("Vehicle reparing");
	}

}
