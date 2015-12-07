package DLinkdlist;

public class DListNode {
protected DListNode prev;
protected DListNode next;
public Object item;
protected Dlist lp;

public DListNode(Object i, DListNode p, DListNode n) {
this.item = i;
this.prev = p;
this.next = n;
//this.lp = lp;
}

}
