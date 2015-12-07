package Linkdlist;
public class Listnode {

		private Object item;
		private Listnode node;

		public Listnode(Object item, Listnode node) {
		this.node= node;
		this.item=item;
		}
			
		public Listnode Addafter(Object item, Listnode l){
			node = new Listnode(item, node);
			Listnode lx = new Listnode(item,l);
			return lx;
		}
		}