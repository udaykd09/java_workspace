package Linkdlist;


public class SList {
	
		Listnode head;
		int size;
			
	public SList(){
		size=0;
		head=null;
	}

	public void insertFront(Object item ){
		head = new Listnode(item, head);
		size++;
	}

	}