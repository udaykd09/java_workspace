package DLinkdlist;

public class Human {

	public void insertitem(DList1 d, Object i){
	d.insertFront(i);
	//d.insertBack(i);
	}
	
	public static void main(String[] args) {
		Human Uday = new Human();
		Human Saurab = new Human();
		
		DList1 grocery = new DList1();	
		DList1 medical = new DList1();
		System.out.println(grocery.size);
		Uday.insertitem(grocery, 1);
		Saurab.insertitem(medical, 2);
		System.out.println(medical.size);
		Saurab.insertitem(medical, 3);
		System.out.println(medical.size);
		System.out.println(grocery.size);
	}
}
