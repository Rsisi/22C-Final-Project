/**
 * Defines a doubly-linked list class
 * @author Alyssa Reyes
 * 
 * This class defines a doubly-linked list class whose methods will 
 * be utilized in the hash class to achieve separate chaining for collision 
 * occurrences.
 * 
 * CIS 22C, Course Project
 */

import java.util.NoSuchElementException;

public class List<T extends Comparable<T>>  {
    private class Node {
        private T data;
        private Node next;
        private Node prev;
       
        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
   
    private int length;
    private Node first;
    private Node last;
    private Node iterator; 
   
    /****CONSTRUCTOR****/
   
    /**
     * Instantiates a new List with default values
     * @postcondition fields of the List class are initialized with default values
     */
    public List() {
    	first = last = iterator = null; 
    	length = 0; 
    }
    
    /**
     * Instantiates a new List by copying another List
     * @param original the List to make a copy of
     * @postcondition a new List object, which is an identical
     * but separate copy of the List original
     */
    public List(List<T> original) {
        if (original == null) {
          	return;
        }
        if (original.length == 0) {
            this.length = 0;
            this.first = null;
            this.last = null;
            this.iterator = null;
        } else {
            Node temp = original.first;
            while (temp != null) {
                addLast(temp.data);
                temp = temp.next;
            }
            iterator = null;
        }
    }
    
    
    
   
    /****ACCESSORS****/
   
    /**
     * Returns the value stored in the first node
     * @precondition !isEmpty()
     * @return the value stored at node first
     * @throws NoSuchElementException when precondition is violated
     */
    public T getFirst() throws NoSuchElementException{
    	if(isEmpty()) { 
    		throw new NoSuchElementException("getFirst(): List is empty.");
    	}
        return first.data;
    }
   
    /**
     * Returns the value stored in the last node
     * @precondition !isEmpty()
     * @return the value stored in the node last
     * @throws NoSuchElementException when precondition is violated
     */
    public T getLast() throws NoSuchElementException{
    	if(isEmpty()) { 
    		throw new NoSuchElementException("getLast(): List is empty.");
    	}
        return last.data;
    }
   
    /**
     * Returns the current length of the list
     * @return the length of the list from 0 to n
     */
    public int getLength() {
        return length;
    }
   
    /**
     * Returns whether the list is currently empty
     * @return whether the list is empty
     */
    public boolean isEmpty() {
        return length == 0;
    }
    
    /**
     * Returns the data that the iterator points to in the list 
     * @precondition iterator cannot be null
     * @return the data of the Node that the iterator points to 
     * @throws NullPointerException when precondition is violated
     */
    public T getIterator() throws NullPointerException {
    	if(offEnd()) { //precondition 
    		throw new NullPointerException("getIterator(): Iterator is off the end of the list!");
    	}
    	return iterator.data; 
    }
    
    /**
     * Returns if the iterator is off the end of the list
     * @return whether or not the iterator is null 
     */
    public boolean offEnd() { 
    	return iterator == null; 
    }
   
    /**
     * Determines whether two Lists have the same data
     * in the same order
     * @param L the List to compare to this List
     * @return whether the two Lists are equal
     */
    @SuppressWarnings("unchecked")
    @Override public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if (!(o instanceof List)) {
            return false;
        } else {
            List<T> L = (List<T>) o;
            if (this.length != L.length) {
                return false;
            } else { //Lists are same length
                Node temp1 = this.first;
                Node temp2 = L.first;
                while (temp1 != null) { 
                    if (!temp1.data.equals(temp2.data)) {
                        return false;
                    }
                    temp1 = temp1.next;
                    temp2 = temp2.next;
                }
                return true;
            }
        }
    }
    
    
    /**
     * Determines whether a List is sorted
     * by calling its recursive helper method
     * isSorted
     * Note: An empty List can be
     * considered to be (trivially) sorted
     * @return whether this List is sorted
     */
    public boolean inSortedOrder() {
        return inSortedOrder(first);
    }

    /**
     * Helper method to inSortedOrder
     * Determines whether a List is
     * sorted in ascending order recursively
     * @return whether this List is sorted
     */
    private boolean inSortedOrder(Node node) {
    	if(node == null) { 
    		return true;     		
    	} else if(node.next == null) { 
    		return node.data.compareTo(node.prev.data) > 0; 
    	} else { 
    		Node temp = node; 
    		node = node.next;
    		if(temp.data.compareTo(node.data) < 0) {
    			return inSortedOrder(node); 
    		} else {
    			return false;
    		}
    	}
    }
    
    
    /**
     * Returns the index of the iterator
     * from 1 to n. Note that there is
     * no index 0. Does not use recursion.
     * @precondition iterator != null
     * @return the index of the iterator
     * @throws NullPointerException when
     * the precondition is violated
     */
    public int getIndex() throws NullPointerException{
    	if(offEnd()) { 
    		throw new NullPointerException("getIndex(): Iterator is off end. "
    				+ "Cannot get index.");
    	} else { 
    		Node temp = first; 
    		int counter = 1; 
        	while(temp.next != iterator.next) { 
        		counter++; 
        		temp = temp.next; 
        	}
            return counter;
    	}
    	
    }
    

    /**
     * Uses the iterative linear search
     * algorithm to locate a specific
     * element in the list
     * @param element the value to search for
     * @return the location of value in the
     * List or -1 to indicate not found
     * Note that if the List is empty we will
     * consider the element to be not found
     * @postcondition: position of the iterator remains
     * unchanged!
     */
	public int linearSearch(T element) {
		Node temp = first; 
		for (int i = 1; i <= length; i++) {
			if (element.equals(temp.data)){
				return i;
			}
			temp = temp.next;
		}
		return -1;
	}

	
	/**
     * Returns the index from 1 to length
     * where value is located in the List
     * by calling the private helper method
     * binarySearch
     * @param value the value to search for
     * @return the index where value is
     * stored from 1 to length, or -1 to
     * indicate not found
     * @precondition isSorted()
     * @postcondition the position of the
     * iterator must remain unchanged!
     * @throws IllegalStateException when the
     * precondition is violated.
     */
    public int binarySearch(T value) throws IllegalStateException {
    	if(!inSortedOrder()) { 
    		throw new IllegalStateException("binarySearch(): Cannot search an"
    				+ " unsorted list.");
    	} else { 	
    		return binarySearch(0, length, value);
    	}
    }
   
    /**
     * Searches for the specified value in
     * the List by implementing the recursive
     * binarySearch algorithm
     * @param low the lowest bounds of the search
     * @param high the highest bounds of the search
     * @param value the value to search for
     * @return the index at which value is located
     * or -1 to indicate not found
     * @postcondition the location of the iterator
     * must remain unchanged
     */
    private int binarySearch(int low, int high, T value) {
    	if(high < low) { 
    		return -1; 
    	} 
    	int mid = low + (high - low) / 2; 
    	Node temp = first; 
    	int counter = 1; 
    	while (counter < mid) { 
    		temp = temp.next; 
    		counter++; 
    	}
    	if(temp.data.equals(value)) { 
    		return mid; 
    	} else if (temp.data.compareTo(value) < 0) { 
    		return binarySearch(mid + 1, high, value); 
    	} else { 
    		return binarySearch(low, mid - 1, value); 
    	}
        
    }
    
   
    /****MUTATORS****/
   
    /**
     * Creates a new first element
     * @param data the data to insert at the
     * front of the list
     * @postcondition a new element is at the beginning of the list 
     */
    public void addFirst(T data) {
    	Node n = new Node(data); 
    	if(isEmpty()) { 
    		first = last = n; 
    	} else { 
    		n.next = first;
    		first.prev = n; 
    		first = n; 
    	}
        length++; 
    }
   
    /**
     * Creates a new last element
     * @param data the data to insert at the
     * end of the list
     * @postcondition a new element is at the end of the list 
     */
    public void addLast(T data) {
    	Node n = new Node(data); 
    	if (isEmpty()) { 
    		first = last = n; 
    	} else { 
    		last.next = n; 
    		n.prev = last; 
    		last = n;
    	}
        length++;
    }
   
    /**
    * removes the element at the front of the list
    * @precondition !isEmpty()
    * @postcondition the second node becomes the first node
    * @throws NoSuchElementException when precondition is violated
    */
    public void removeFirst() throws NoSuchElementException{
    	if(isEmpty()) { 
    		throw new NoSuchElementException("removeFirst(): Cannot remove from an empty list!");
    	} else if(length == 1) { 
    		first = last = iterator = null; 
    	} else { 
    		if (iterator == first) {
    			iterator = null; 
    		}
    		first = first.next; 
    		first.prev = null; 
    	}
        length--;
    }
   
    /**
     * removes the element at the end of the list
     * @precondition !isEmpty()
     * @postcondition the last element is changed 
     * @throws NoSuchElementException when precondition is violated
     */
    public void removeLast() throws NoSuchElementException{
        if(isEmpty()) { //precondition 
        	throw new NoSuchElementException("removeLast(): Cannot remove from an empty list!");
        } else if (length == 1) { //edge case 
        	first = last = iterator = null; 
        } else { //general case 
        	if (iterator == last) { 
        		iterator = last.prev;
        	}
        	last = last.prev; 
        	last.next = null; 
        }
        length--; 
    }
    
    /**
     *  Places the iterator at the beginning of the list
     */
    public void placeIterator() { 
    	iterator = first; 
    }
    
    /**
     * Moves the iterator to the next node in the list 
     * @precondition iterator cannot be null
     * @postcondition iterator points to the next element in the list 
     * @throws NullPointerException when precondition is violated
     */
    public void advanceIterator() throws NullPointerException{ 
    	if(iterator == null) { 
    		throw new NullPointerException("advanceIterator(): Iterator is "
    				+ "null and cannot advance!");
    	}
    	iterator = iterator.next; 
    }
    
    
    /**
     * Moves the iterator down by one node or to the previous node
     * in the list 
     * @precondition iterator cannot be null 
     * @postcondition iterator points to the previous element in the list
     * @throws NullPointerException when precondition is violated 
     */
    public void reverseIterator() throws NullPointerException{ 
    	if(iterator == null) { 
    		throw new NullPointerException("reverseIterator(): Iterator is "
    				+ "null and cannot reverse!");
    	}
    	iterator = iterator.prev; 
    }
    
    
    /**
     * Inserts new data into the list after the iterator
     * @param data the new data to insert
     * @precondition iterator != null
     * @throws NullPointerExcpetion when the precondition is violated
     */
    public void addIterator(T data) throws NullPointerException {
    	if(offEnd()) { 
    		throw new NullPointerException("addIterator(): Iterator is off "
    				+ "end. Cannot add.");
    	} else if (iterator == last) { 
    		addLast(data);
    	} else { 
    		Node n = new Node(data);
            n.next = iterator.next;
            n.prev = iterator.prev; 
            iterator.next.prev = n;
            iterator.next = n;
            length++;
    	}
    	
    }
    
    
    /**
     * Removes the data in the list that the iterator points to 
     * @precondition iterator != null
     * @postcondition iterator points to null 
     * @throws NullPointerExcpetion when the precondition is violated
     */
    public void removeIterator() throws NullPointerException {
    	if(offEnd()) { 
    		throw new NullPointerException("removeIterator(): Iterator is "
    				+ "off end. Cannot remove."); 
    	} else if (iterator == first) { 
    		removeFirst(); 
    	} else if (iterator == last) { 
    		removeLast(); 
    	} else { 
    		iterator.prev.next = iterator.next; 
    		iterator.next.prev = iterator.prev;
    		length--; 
    	}
    	iterator = null; 
    	
    }
    

    /**
     * Places the iterator at first
     * and then iteratively advances
     * it to the specified index
     * no recursion
     * @param index the index where
     * the iterator should be placed
     * @precondition 1 <= index <= length
     * @throws IndexOutOfBoundsException
     * when precondition is violated
     */
    public void advanceToIndex(int index) throws IndexOutOfBoundsException{
       if(index < 1 || index > length) { 
    	   throw new IndexOutOfBoundsException("advanceToIndex(): Index is "
    	   		+ "out of bounds!");
       } else { 
    	   placeIterator(); 
    	   int counter = 1; 
    	   while (counter < index) { 
    		   advanceIterator(); 
    		   counter++;
    	   }
       }
    }
    
    
    
    /****ADDITIONAL OPERATIONS****/
   
    /**
     * List with each value on its own line
     * At the end of the List a new line
     * @return the List as a String for display
     */
    @Override public String toString() {
       String result = "";
       Node temp = first; 
       while(temp != null) { 
    	   result += temp.data + "\n"; 
    	   temp = temp.next; 
       }
       return result; 
    }
    
    
    /**
     * Prints a linked list to the console
     * in reverse by calling the private
     * recursive helper method printInReverse
     */
    public void printInReverse() throws  NoSuchElementException {
       printInReverse(last); 
    }
   
    
    /**
     * Recursively prints a linked list to the console
     * in reverse order from last to first (no loops)
     * Each element separated by a space
     * Should print a new line after all
     * elements have been displayed
     */   
    private void printInReverse(Node node) {
    	if(node == null) {  	  
        	System.out.println(); 
    	} else if(node == first){ 
    		System.out.print(node.data + " ");
    		System.out.println();
    	} else {
    		System.out.print(node.data + " ");
        	printInReverse(node.prev); 
        }
    } 
     
   
}