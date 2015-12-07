package org.DLL;

public class DoubleLL {

	private DoubleListNode head;
	private int size;
	
	public DoubleLL() {
		head = null;
		size = 0;
	}
	
	public void insert(String item){
		if(head==null){
			head = new DoubleListNode(null, null, item);
			size++;
		}
		else{
			DoubleListNode temp = head;
			while(temp.next()!=null){
				temp = temp.next();
			}
			temp.setNext( new DoubleListNode(temp, null, item));
			size++;
		}
		}
	
	public void insertAt(int n, String item){
		if(head==null){
			System.out.println("List is empty");
		}
		else{
			DoubleListNode temp = head;
			for (int i = 1; i < n; i++) {
				temp = temp.next();
			}
			temp.setNext( new DoubleListNode(temp, temp.next(), item)); 
			size++;
		}
	}
	
	public void iterator(){
		if(head==null){
			System.out.println("List is empty");
		}
		else{
			DoubleListNode temp = head;
			int number = 0;
			while (temp != null) {
				number++;
				System.out.println(number + ". " + temp.toString());
				temp = temp.next();
			}
		}
	}
}
