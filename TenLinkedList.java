package Lab01;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public  class TenLinkedList<E>  implements List<E>{
	
	
		/**
		 * Instance variables: 
		 * - first refers to the first node in the list. 
		 * - last refers to the last node in the list
		 * - currentNode has been declared in case we need to manipulate a node in any of the following methods. 
		 * - length refers to the size of the list.
		 */
		
		Node<E> first;
		Node<E> last;
		Node<E> currentNode;
		private int length;
		
		/**
		 * This default constructor creates an empty Ten linked list. 
		 */
		
		public TenLinkedList() {
			this.first = null;
			this.last = null;
			this.currentNode = null;
			this.length = 0;
		}
		
		//---------------------- nested Node class ------------------------
			private static class Node<E> {
				
				//---------------------- nested Node class ------------------------
				/**
				 * The purpose of this class is to create a Node resembling that of the ones in the image in the pdf. 
				 *
				 * @param <E>: E refers to generics, meaning that a client could create a node of any type, without the programmer having 
				 * to code the class over and over again.
				 */
				
				
				private E element;
				private Node<E> next;
				private Node<E> previous;
				private Node<E> nextTen;
				private Node<E> prevTen;
				
				/**
				 * This overloaded constructor creates the Node.	
				 * @param p4 refers to the link connecting the node to the node that is 4 before the current one. 
				 * @param p refers to the link connecting the node to the node that is before the current one.
				 * @param e refers to the element that the node being created is carrying.
				 * @param n refers to the link connecting this node to the next one. 
				 * @param n4 refers to the link connecting this node to the one that is 4 nodes after it. 
				 */
				
				
				public Node(Node<E> p10, Node<E> p, E e, Node<E> n, Node<E> n10) {
				this.element  = e;
				this.next = n;
				this.previous = p;
				this.nextTen = n10;
				this.prevTen = p10;
				}
				
				
			} // ---------------------- end of nested Node class -----------------
		
	    public Node<E> getFirst() {
	    	return first;
	    }
	    
	    public Node<E> getLast() {
	    	return last;
	    }
	    
	    /**
		 * This method adds a new node to the end of the four linked list, and returns true if successful. 
		 * @param E refers to the type of the element being added. Generics was used here to avoid redundant code. 
		 */
			
		public boolean add(E object) {
			
	        currentNode = last;
	     // if the list is empty, the first element will be the new element and also the last element. 
	        if (first == null) {
	            first = new Node<E>(null, null, object, null, null);  
	            last = first;  
	        }
	        
	        // length < 4 not length < 5
	        else if (length < 10){
	        	
	        	Node<E> newNode = new Node<E>(null, currentNode, object, null, null);
	            currentNode.next = newNode;
	            last = newNode;

	        }
	        
	        // add this part for previous 4 to exist
	        // if length is exactly 4, after the new node is added, the link connecting the new node to the previous 4th node will be 3 before
	        // the last node before the new node was added.
	        
	        else if (length == 10){
	        	
	        	Node<E> newNode = new Node<E>(currentNode.previous.previous.previous.previous.previous.previous.previous.previous.previous, currentNode, object, null, null);
	            currentNode.next = newNode;
	            newNode.prevTen = currentNode.previous.previous.previous.previous.previous.previous.previous.previous.previous;
	            last = newNode;

	        }
	        
	     // in every other case, the last node before the new node was added will have to update it's next link, the new node's previous 4 link will be updated,
	        // and the next four of the node that is 4 before the last node before the new node was added will have to be updated. 
	        
	        else {
	            Node<E> newNode = new Node<E>(null, currentNode, object, null, null);
	            currentNode.next = newNode;
	            newNode.prevTen = currentNode.prevTen.next;
	            currentNode.prevTen.next.nextTen = newNode;
	            
	            last = newNode;

	        }
	        length++;
	        return true;    
	    }

		
		/**
	     * This method was created because it was needed in the add (int index, E element) method. It adds an object to the beginning of the linked list. 
	     * @param object refers to the element being added at the beginning of the list. 
	     * At the beginning of this method, currentNode has been set to the first node. 
	     */
		
		public void appendBegin(E object){
	        currentNode = first;
	        
	     // if the list is empty, then the element being added to the list will be the first, and the last, and will have no links referencing any other nodes. 
	        if (first == null) {
	            first = new Node<E>(null, null, object, null, null);  
	            last = first;  
	        }
	        
	     // if the length of the list is less than 5, the next link of the new node will be currentNode, and there will be no links referencing the previous and previous 4 nodes. 
	        else if (length < 11){
	        	
	        	Node<E> newNode = new Node<E>(null, null, object, currentNode, null);
	            currentNode.previous = newNode;
	            first = newNode;

	        }
	        
	     // in every other case, the new node will have to update its next and next four links, and the node that is 4 ahead of the new node will have to update its previous 4.
	        
	        else {
	            Node<E> newNode = new Node<E>(null, null, object, currentNode, currentNode.next.next.next.next.next.next.next.next.next);
	            currentNode.previous = newNode;
	    
	            first = newNode;

	        }
	        
	         
	    }
		
		/**
		 * This method adds a node at a specific index and updates the links to all affected nodes. 
		 * @param index refers to the index of the list where the new node will be added. 
		 * @param element refers to the data that is being added to the new node. 
		 */
		
		public void add(int index, E element) {
			
			// if the index is bigger than the list, it cannot be added and will throw an UnsupportedOperationException.
		        if(index>length){
		            throw new UnsupportedOperationException();
		        }
	        
		       if(index==length) {
					add(element);
					length--;
				}
				
				
				else if(index>0 && index<length) {
				Node<E> current = getNode(index);
				Node<E> newN = new Node<E>(null, current.previous, element, current, null);
				
				current.previous.next= newN;
				current.previous=newN;
				
				Node<E> temp2 = current;
				
				try{newN.prevTen=current.prevTen;}
				catch(Exception e2) {}
				
				
				
				
				
				for(int i=0 ; i<10; i++) {
					try {
						temp2.prevTen = temp2.previous.previous.previous.previous.previous.previous.previous.previous.previous.previous;
						temp2=temp2.next;
						
					}
					catch(Exception e){
						try{temp2=temp2.next;}
						catch(Exception e2) {}
					}
				}
				Node<E> temp = getNode(index-2);
				
				for(int i=0 ; i<10; i++) {
					try {
					
						temp.nextTen = temp.next.next.next.next.next.next.next.next.next.next; 
						temp=temp.previous;
					}
					catch(Exception e){
						try{temp=temp.previous;}
						catch(Exception e2) {}
						
					}
				}
				
			
				
				
				
				}
				
				else if(index==0) {
					appendBegin(element);
				}
				
				
				length++;


			}
		
		/**
		 * This method removes an element from the linked list at a sepcific index. 
		 * @param index refers to the element that is being removed from the linked list. 
		 */
		
		public E remove(int index) {
	        Node<E> currentNode = getNode(index);
	        if(first == null || index > (length - 1)) {
	            // error
	        }
	        else if(index < 10) {
	            if(index == 0 && length < 5) {
	                first = null;
	                last = null;
	            }
	            else if(index == 0) {
	            	first.next.previous = null;
	                if(first.nextTen != null) {
	                	first.nextTen.prevTen = null;
	                }
	                first = first.next;
	            }
	            else if(index != (length - 1)) {
	            	currentNode.previous.next =  currentNode.next;
	            	currentNode.next.previous = currentNode.previous;
	            	if(currentNode.nextTen != null) {
	        			currentNode.previous.nextTen = currentNode.nextTen;
	        			currentNode.nextTen.prevTen = currentNode.previous;
	        		}
	        		else {
	        			currentNode.previous.nextTen = null;
	        		}
	        		currentNode = currentNode.previous;
	            	for(int i = 0; i < index - 1; i++) {
	            		if(currentNode.nextTen != null) {
	            			currentNode.previous.nextTen = currentNode.nextTen.previous;
	            			currentNode.nextTen.previous.prevTen = currentNode.previous;
	            		}
	            		else {
	            			currentNode.previous.nextTen = null;
	            		}
	            		currentNode = currentNode.previous;
	            	}
	            }
	            else {
	            	last.previous.next = null;
	            	last = last.previous;
	            }
	        }
	        
	        // fix 
	        else if(index > (length - 5)) {
	            if(index == length - 1) {
	            	last.prevTen.nextTen = null;
	            	last.previous.next = null;
	                last = last.previous;
	                

	            }
	            else if(index != (length - 1)) {
	            	for(int i = 1; i < length - index; i ++) {
	            		getNode((index+i)).prevTen=getNode((index+i)).prevTen.previous;
	            		getNode((index+i)).prevTen.nextTen=getNode((index+i));
	            	}
	            	currentNode.previous.next =  currentNode.next;
	            	currentNode.next.previous = currentNode.previous;
	            }
	            
	        }
	        else if (index == 4) {
	        	currentNode.previous.next =  currentNode.next;
	        	currentNode.next.previous = currentNode.previous;
	        	if(currentNode.nextTen != null) {
	    			currentNode.prevTen.nextTen = currentNode.nextTen;
	    			currentNode.nextTen.prevTen = currentNode.prevTen;
	    		}
	        }
	        else {
	        	for(int i = 1; i < 5; i ++) {
	        		getNode((index+i)).prevTen=getNode((index+i)).prevTen.previous;
	        		getNode((index+i)).prevTen.nextTen=getNode((index+i));
	        	}
	        	currentNode.previous.next =  currentNode.next;
	        	currentNode.next.previous = currentNode.previous;
	        	
	        	
	        	
	        }
			length--;
			return null;
		}
		
		

	    /**
	     * This method returns the node at a specific index. 
	     * @param index refers to the index at which the desired node is located. 
	     * @return value is the desired node. In this case it is held by currentNode. 
	     */
	    
		public Node<E> getNode(int index) {
			int counter = 0;
			
			if(index >= length || index < 0) {
				currentNode = null;
			}
			else if(index == 0) {
				currentNode = first;
			}
			else if(index == (length - 1)) {
				currentNode = last;
			}
			else if(index < 10) {
				currentNode = first;
				for(int i = 0; i < index; i++) {
	                currentNode = currentNode.next;
			    }
			}
			else if((length - 1 - index) < 10) {
				currentNode = last;
				for(int i = length - 1; i > index; i--) {
	                currentNode = currentNode.previous;
			    }
			}
			else {
				if(index < (length/2)) {
					currentNode = first;
		            for(int i = 10; i < index; i += 10) {
		            	currentNode = currentNode.next.next.next.next.next.next.next.next.next.next;
		            	counter = i;
		            }
		            if(index == 10) {
		            	currentNode = currentNode.nextTen;
		            }
		            int remainder = (index - counter) % 10;
		            for(int i = 0; i < remainder; i++) {
		            	currentNode = currentNode.next;
		            }
		        }
				else if(index >= (length/2)) {
		            currentNode = last;
		            for(int i = length - 1; i > index + 9; i -= 10) {
	                    currentNode = currentNode.prevTen;
	                    counter = i;
	    		    }
	                counter -= 10;
	                int remainder = (counter - index) % 10;
	                for(int i = 0; i < remainder; i++) {
	                    currentNode = currentNode.previous;
	    		    }
		        }
			}
	                        
			return currentNode;
		}
		
		
		/**
		 * This method calls on the getNode method and just returns the element of the node. 
		 * @param index refers to the index of the element of the desired node. 
		 */
		
		public E get(int index) {
			return getNode(index).element;
		}
		

	    /**
	     * This method returns the size of the list. 
	     */
		
		public int size() {
			return length;
		}
		
		/**
		 * This method clears the entire list by setting the links and elements of the first and last nodes to null and assigning the length to equal zero.
		 */
		
		public void clear() {
			
			this.first = null;
			this.last = null;
			this.length = 0;
		}
		
		/**
		 * This method converts list into a string and returns it. This can be used to ouput the list to a user or for JUnit testing purposes. 
		 * @return value is a string variable named result. 
		 */
		
		public String toString() {
			String result = "[";
			
			// add an if for when first is null
			if (first == null) {
				result += "]";
			}
			else {
				currentNode = first;
				// length not length - 1
				for(int i = 0; i < length; i++) {
					//currentNode = currentNode.next;  get rid of
					
					// length - 1 not length - 2
					if (i < length - 1) {
						result += currentNode.element+" ";
					}
					else {
						result += currentNode.element + "]";
					}
					// add this
					currentNode = currentNode.next;
				} 
			}
			return result;
		}
		
		/**
		 * All of the following methods are part of the List interface and have not been implemented, so when called, will throw an UnsupportedOperationException. 
		 */
		
		
		public boolean addAll(AbstractCollection<? extends E> c) {
			throw new UnsupportedOperationException();
		}
		
		
		public boolean addAll(int index, AbstractCollection<? extends E> c) {
			throw new UnsupportedOperationException();
		}
		
		
		public boolean contains(Object o) {
			throw new UnsupportedOperationException();
		}
		
		
		public boolean containsAll(AbstractCollection<?> c) {
			throw new UnsupportedOperationException();
		}
		
		
		public boolean equals(Object o) {
			throw new UnsupportedOperationException();
		}
		
		
		public int hashCode() {
			throw new UnsupportedOperationException();
		}
		
		
		public int indexOf(Object o) {
			throw new UnsupportedOperationException();
		}
		
		
		public boolean isEmpty() {
			throw new UnsupportedOperationException();
		}
		
		public Iterator<E> iterator1() {
			throw new UnsupportedOperationException();
		}
		
		
		public int lastIndexOf(Object o) {
			throw new UnsupportedOperationException();
		}
		
		
		public ListIterator<E> listIterator1() {
			throw new UnsupportedOperationException();
		}
		
		
		public ListIterator<E> listIterator1(int index) {
			throw new UnsupportedOperationException();
		}
		
		
//		public boolean remove(Object o) {
//			throw new UnsupportedOperationException();
//		}
		
		
		public boolean removeAll(AbstractCollection<?> c) {
			throw new UnsupportedOperationException();
		}
		
		
		public boolean retainAll(AbstractCollection<?> c) {
			throw new UnsupportedOperationException();
		}
		
		
		public E set(int index, E element) {
			throw new UnsupportedOperationException();
		}
		
		
		public List<E> subList(int fromIndex, int toIndex) {
			throw new UnsupportedOperationException();
		}
		
		
		public Object[] toArray() {
			throw new UnsupportedOperationException();
		}
		
		
		public <T> T[] toArray(T[] a) {
			throw new UnsupportedOperationException();
		}
		
		
		public E getNext(int index) {
			return this.getNode(index).next.element;
		}
		
		public E getnextTen(int index) {
			return this.getNode(index).nextTen.element;
		}
		public E getPrev(int index) {
			return this.getNode(index).previous.element;
		}
		
		public E getprevTen(int index) {
			return this.getNode(index).prevTen.element;
		}

		public boolean remove(Object o) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Iterator<E> iterator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean addAll(Collection<? extends E> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean addAll(int index, Collection<? extends E> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public ListIterator<E> listIterator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ListIterator<E> listIterator(int index) {
			// TODO Auto-generated method stub
			return null;
		}


	}

