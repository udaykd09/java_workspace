package org.DLL;

public class DoubleListNode {

	public DoubleListNode next;
	public DoubleListNode prev;
	private String Item;
	
	public DoubleListNode(DoubleListNode prev, DoubleListNode next, String Item){
		this.next = next;
		this.prev = prev;
		this.Item = Item;
	}
	
	@Override
	public String toString() {
		return "[Item=" + Item + "]";
	}

	public DoubleListNode next(){
		return this.next;
	}
	
	public DoubleListNode prev(){
		return this.prev;
	}
	
	public void setPrev(DoubleListNode prev) {
		this.prev = prev;
	}
	
	public void setNext(DoubleListNode next) {
		this.next = next;
	}
}
