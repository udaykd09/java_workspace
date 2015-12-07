
public class circle implements Shape{

	double r;
	public circle(double a) {
		r = a;// TODO Auto-generated constructor stub
	}
	
	public double area() {
		double A = Math.PI*r*r; 
		return A;
	}

	
	public double perimeter() {
		double P = 2*Math.PI*r;
		return P;
	}
}