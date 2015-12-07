
public class CustomerTransaction {

static double total=0;

public double purchase(StockItem x, int d){
	total += x.purchase(d);
	return total;
}
public double pay(double amt){
	return total-amt;
}

}
