package DLinkdlist;

public class Dlist {
protected DListNode head;
protected int size;

protected DListNode newNode(Object item, DListNode prev, DListNode next){
	return new DListNode(item, prev, next);
}

public Dlist() {
 this.head = new DListNode(Integer.MIN_VALUE, null, null);
 this.head.next = this.head;
 this.head.prev = this.head;
 this.size = 0;
}

public void insertfront(Object item){
	this.head.next = newNode(item, this.head, this.head.next);
	if(this.size==0){
        this.head.prev = this.head.next;
    }else{
        this.head.next.next.prev = this.head.next;
    }
	size++;
}

public void insertback(Object item){
	this.head.prev = newNode(item, this.head.prev, this.head);
	 if(this.size == 0){
	        this.head.next = this.head.prev;
	    }else{
	        this.head.prev.prev.next = this.head.prev;
	    }
	size++;
}



}
