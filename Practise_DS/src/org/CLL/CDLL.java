package org.CLL;

import org.DLL.DoubleListNode;

public class CDLL {

		private DoubleListNode head;
		private int size;
		
		public CDLL() {
			head = null;
			size = 0;
		}
		
		public void insert(String item){
			if(head==null){
				head = new DoubleListNode(null, null, item);
				head.next = head;
				head.prev = head;
				size++;
			}
			else{
				DoubleListNode temp = head;
				while(temp.next()!=head){
					temp = temp.next();
				}
				temp.setNext( new DoubleListNode(temp, head, item));
				size++;
			}
			}
		
		public void insertAt(int n, String item){
			if(head==null){
				System.out.println("List is empty");
			}
			else{
				DoubleListNode temp1 = head;
				DoubleListNode temp2 = head;
				int x=0;
				while(temp1.next()!=head){
					if(x>=size-n){
						for(int i=0;i<n;i++){
							temp2 = temp2.next();
						}
					}
					temp1 = temp1.next();
				}
				temp2.setNext( new DoubleListNode(temp2, temp2.next(), item)); 
				size++;
			}
		}
		
		public void iterator(){
			if(head==null){
				System.out.println("List is empty");
			}
			else{
				DoubleListNode temp = head;
				System.out.println(size);
				int number = 0;
				while ( number <= size) {
					number++;
					System.out.println(number + ". " + temp.toString());
					temp = temp.next();
				}
			}
		}
	}