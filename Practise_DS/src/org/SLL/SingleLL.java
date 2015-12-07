package org.SLL;

import javax.swing.plaf.SliderUI;

public class SingleLL {

	public SingleNode head;
	private int size;

	public SingleLL() {
		head = null;
		size = 0;
	}

	public void insert(String item) {
		if (head == null) {
			head = new SingleNode(item, null);
			// System.out.println(head.toString());
		} else {
			SingleNode temp = head;
			while (temp.next() != null) {
				temp = temp.next();
			}
			temp.setnext(new SingleNode(item, null));
		}
		size++;
	}

	public void iterator() {
		SingleNode temp = head;
		int number = 0;
		while (temp != null) {
			number++;
			System.out.println(number + ". " + temp.toString());
			temp = temp.next();
		}
		//System.out.println(number + ". " + temp);
	}
	
	public void printReverse(){
		ReverseIterator(head);
	}
	
	public void ReverseIterator(SingleNode c) {
		if( c.next() != null){
		ReverseIterator(c.next());
		}
		System.out.println(c.toString());
	}
	
	public void removeLast(){
		SingleNode temp = head;
		while (temp.next().next() != null) {
			temp = temp.next();
		}
		temp.setnext(null);
		size--;
	}
	
	public void addlast(String Item){
		this.insertafter(size, Item);
	}
	
	public void insertafter(int number, String item) {
		SingleNode temp = head;
		for (int i = 1; i < number; i++) {
			temp = temp.next();
		}
		temp.setnext(new SingleNode(item, temp.next()));
		size++;
		
	}
	
	public SingleNode getnode(int number){
		SingleNode temp1 = head;
		SingleNode temp2 = head;
		int x = 0;
		while (temp1.next() != null) {
			if (x >= size - number) {
				temp2 = temp2.next();
			}
			temp1 = temp1.next();
			x++;
		}
		return temp2;
	}

	public void createCircular(int number) {
		SingleNode temp1 = head;
		SingleNode temp2 = getnode(number);
		
		while (temp1.next() != null) {
			temp1 = temp1.next();
		}
		temp1.setnext(temp2);
		System.out.println("Size =" + size + ", " + temp2.toString()
				+ temp1.next().toString());
	}

	public void checkcirclular() {
		SingleNode temp1 = head;
		SingleNode temp2 = head;

		while (temp1.next() != null) {
			temp2 = temp2.next();
			temp1 = temp1.next().next();
			if(temp1==temp2){
				System.out.println("It is circular");
				return;
			}
		}
	}
}
