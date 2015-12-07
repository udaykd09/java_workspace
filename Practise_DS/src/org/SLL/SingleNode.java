package org.SLL;

public class SingleNode {
	
	public String item;
	private SingleNode node;
	
	public SingleNode(String item, SingleNode node) {
		this.node=node;
		this.item=item;
	}
	
	public SingleNode next(){		
		return node;
	}

	public void setnext(SingleNode l){		
		this.node = l;
	}
	
	@Override
	public String toString() {
		return "SingleNode [item=" + item+"]";
	}
	
}
