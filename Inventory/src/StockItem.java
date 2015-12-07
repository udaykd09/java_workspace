
public class StockItem {
private int ID;
private String Name;
private double price;
private int nos;

public StockItem(int a,String b,double c,int d) {
	this.ID = a;
	this.Name = b;
	this.price = c;
	this.nos = d;
}

@Override
public String toString() {
	return Name + "(" + ID + ")";
}

public double purchase(int d){
	nos = nos - d;
	return d*price;
}

public void restock(int d){
	nos = nos + d;
}

}
