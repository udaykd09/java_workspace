
public class Test {
public static void main(String[] args) {
	StockItem deo = new StockItem(1, "deo", 102.3 , 5);
	StockItem choc = new StockItem(1, "deo", 1.5 , 100);
	StockItem bread = new StockItem(1, "deo", 10 , 10);
	CustomerTransaction uday = new CustomerTransaction();
	uday.purchase(deo, 1);
	uday.purchase(choc, 3); 
	//double total = uday.purchase(bread, 1);
	System.out.println("total=" + CustomerTransaction.total);
	System.out.println("change is" + uday.pay(200));

}
}
