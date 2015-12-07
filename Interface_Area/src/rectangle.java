
public class rectangle implements Shape{

	double l,w;
	public rectangle(double l1, double w1) {
		// TODO Auto-generated constructor stub
		l=l1;
		w=w1;
	}

	public double area() {
		// TODO Auto-generated method stub
		double A = l*w;
		return A;
	}

	public double perimeter() {
		// TODO Auto-generated method stub
		double P = 2*(l+w);
		return P;
	}

		
	}