package org.SLL;

import org.CLL.CDLL;
import org.DLL.DoubleLL;
import org.stack.Checker;

public class user {
	
	public void additem(SingleLL l, String item){
		l.insert(item);
	}
	
	public void additemafter(SingleLL l, int number, String item){
		l.insertafter(number, item);
	}
	
	public void additem(DoubleLL l, String item){
		l.insert(item);
	}
	
	public void additemafter(DoubleLL l, int number, String item){
		l.insertAt(number, item);
	}
	
	public void additem(CDLL l, String item){
		l.insert(item);
	}
	
	public void additemafter(CDLL l, int number, String item){
		l.insertAt(number, item);
	}
	
	public static void main(String[] args) {
	user uday = new user();
	user Saurabh = new user();
	//CDLL l1 = new CDLL();
	SingleLL l1 = new SingleLL ();
	uday.additem(l1 , "leche");
	uday.additem(l1 , "pan1");
	uday.additem(l1 , "pan2");
	uday.additem(l1 , "pan3");
	uday.additem(l1 , "pan4");
	uday.additem(l1 , "pan5");
	//Saurabh.additem(l1, "arroz");
	//uday.additemafter(l1, 6, "manzana");
	//l1.iterator();
	l1.removeLast();
	l1.addlast("pan6");
	l1.iterator();
	//l1.printReverse();
	//l1.createCircular(3);
	//l1.checkcirclular();
	
	
	
	}
}
