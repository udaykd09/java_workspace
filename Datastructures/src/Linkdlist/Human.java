package Linkdlist;


public class Human{
	
public void insertitem(Object item, SList l ){
	l.insertFront(item);
}


public static void main(String[] args) {
//	Listnode l1 = new Listnode(1, null);
//	Listnode l2 = new Listnode(2, l1);
//	Listnode l3 = new Listnode(3, l2);
//	Listnode l5 = l2.Addafter(4,l2);
//	System.out.println(l5.item);
	SList l0 = new SList();
	Human uday = new Human();
	Human saurab = new Human();
	uday.insertitem(1, l0);
	saurab.insertitem(2, l0);	
	uday.insertitem(1, l0);
	saurab.insertitem(4, l0);
	System.out.println(l0.size);
	
	
}
}