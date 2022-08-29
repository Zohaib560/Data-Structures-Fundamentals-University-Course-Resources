import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class FourLinkedList<E> implements List<E> {

	//attributes of FourLinkedList.
	private int size;
	private Node<E> head;
	private Node<E> tail;
	
	//private class that create node entries
	private static class Node<E>{
		//attributes of Node, the links are set as null and will be
		//properly assigned in FourLinkedList methods.
		E data;
		Node<E> next = null;
		Node<E> next4 = null;
		Node<E> prev = null;
		Node<E> prev4 = null;
		
		//create a Node with data entry.
		public Node(E entry) {
			this.data = entry;
		}
	}
	
	//creates empty four linked list.
	public FourLinkedList() {
		size = 0;
		head = null;
		tail = null;
	}
	
	
	/**
	 * private helper method
	 * extra method which traverses the list and returns a node at index.
	 * assumes index is valid
	 * 
	 * @param index : the index to find node at
	 * @return a node at index
	 */
	private Node<E> find(int index){		
		int i;
		Node<E> curr;
		if (index <= (size - 1) / 2) { //start search from head
			curr = head;
			i = 0; //start index of list (keeps track of current position)
			while (i < index) { //loops through list before index
				if (i + 4 <= index) { //iterate in jumps of 4 if it won't overshoot
					i = i + 4;
					curr = curr.next4;
				}
				else { //iterate in jumps of 1 (since jump of 4 overshot)
					i++;
					curr = curr.next;
				}
			}
		}
		else { //start search from tail
			curr = tail;
			i = size - 1; //final index of list (keeps track of current position)
			while (i > index) { //loop through list after index
				if (i - 4 >= index) { //iterate in jumps of 4 if it won't overshoot
					i = i - 4;
					curr = curr.prev4;
				}
				else { //iterate in jumps of 1 (since jump of 4 overshot)
					i--;
					curr = curr.prev;
				}
			}
		}
		return curr;
	}
	
	
	/**
	 * private helper method
	 * This method makes sure that the 4 nodes (if applicable) after and before given node curr at index
	 * have their 4 apart links correctly linked. 
	 * 
	 * @param index : the position to start correction from
	 * @param curr : node to start correction from
	 */
	private void link4Correction(int index, Node<E> current) {
		if (size > 4) { //if size <= 4, nothing is 4 apart so no need to do links
			Node<E> curr = current;
			int c = 0; //tracks number of nodes that require link corrections
			//set the next4 to correct links
			while (curr.prev != null && c < 4) { //sets curr to the node furthest back that needs its next4 updated (at most this will be 4 nodes back)
				curr = curr.prev;
				c++;
			}
			for (int i = index - c; i <= index; i++) { //if a node exists 4 indexes after current position update next4
				if (i + 4 < size) {
					curr.next4 = curr.next.next.next.next;
				}
				curr = curr.next;
			}
			curr = current; //resets position back to inserted entry
			c = 0; //reset counter
			//set the prev4 to correct links
			while (curr.next != null && c < 4) { //sets curr to the node furthest forward that needs its prev4 updated (at most this will be 4 nodes forward)
				curr = curr.next;
				c++;
			}
			for (int i = index + c; i >= index; i--) { //if a node exists 4 indexes before current position update prev4
				if (i - 4 >= 0) {
					curr.prev4 = curr.prev.prev.prev.prev;
				}
				curr = curr.prev;
			}
		}
		else { //list is too small to have next4 and prev4 links so set them to null.
			Node<E> curr = current;
			curr = head;
			while (curr != null) { //loops through list to null next4 and prev4
				curr.prev4 = null;
				curr.next4 = null;
				curr = curr.next;
			}
		}
	}
	
	
	/**
	 * takes in entry of any type and adds it to end of list.
	 * 
	 * @param e : data to be added to list
	 * @return true if item was added
	 */
	public boolean add(E e) {
		//create new entry node
		Node<E> entry = new Node<>(e);
		
		//adding to empty list
		if (size == 0) {
			head = entry;
			tail = entry;
			size++;
		}
		else { //all other cases
			entry.prev = tail;
			tail.next = entry;
			tail = entry; 
			size++;
			if (size > 4) { //after adding size will be 5 or more meaning there has to be a 4 apart link
				entry.prev4 = tail.prev.prev.prev.prev;
				entry.prev4.next4 = entry; 
			}
		}
		return true;
	}
	
	
	/**
	 * adds element at specified index if index is valid else throws exception.
	 * 
	 * @param index : position to add item
	 * @param element : item to be added
	 */
	public void add(int index, E element) {	
		if (index < 0 || index > size) { //checks if index is valid
			//throw exception
			throw new IndexOutOfBoundsException();
		}
		else if (index == size) { //adding to end of list
			add(element);
		}
		else { //adding in the middle
			//insert and link the new node with next and prev
			Node<E> entry = new Node<>(element); //create node to insert
			if (index == 0) { //inserting at start
				entry.next = head;
				head.prev = entry;
				head = entry;
				size++;
				if (size > 4) {
					entry.next4 = head.next.next.next.next;
					entry.next4.prev4 = entry;
				}
			}
			else { //inserting in middle, and do the doubly links
				//traverse list to index of insertion
				Node<E> curr = find(index); //helper method
				entry.next = curr;
				entry.prev = curr.prev;
				curr.prev.next = entry;
				curr.prev = entry;
				size++;
				link4Correction(index, entry); //corrects the 4 apart links if necessary
			}		
		}
	}
	
	
	/**
	 * if index is valid removes item at specified index and returns item data, else throws exception
	 * 
	 * @param index : position of item to be removed
	 * @return returns the data of removed item
	 */
	public E remove(int index) {
		if (index < 0 || index >= size) { //checks if index is valid
			//throw exception
			throw new IndexOutOfBoundsException();
		}
		else if (size == 1){
			Node<E> curr = head;
			clear();
			return curr.data;
		}
		else { 
			Node<E> curr;
			if (index == 0) { //remove from front
				curr = head;
				head = head.next;
				head.prev = null;
				size--;
				link4Correction(index, head); //corrects the 4 apart links if necessary
			}
			else if (index == size - 1) {//remove from back
				curr = tail;
				tail = tail.prev;
				tail.next = null;
				size--;
				link4Correction(index - 1, tail); //corrects the 4 apart links if necessary
			}
			else { //remove from middle
				curr = find(index); //node to remove
				curr.next.prev = curr.prev;
				curr.prev.next = curr.next;
				size--;
				link4Correction(index, curr.next); //corrects the 4 apart links if necessary
			}
			return curr.data;
		}
	}
	
	
	/**
	 * return item at specified index, throws exception if index is not valid.
	 * 
	 * @param index : position of item to get data from
	 * @return returns the data of item at index
	 */
	public E get(int index) {
		if (index < 0 || index >= size) { //checks if index is valid
			//throw exception
			throw new IndexOutOfBoundsException();
		}
		else { //find node at specified index
			Node<E> curr = find(index); //helper method
			return curr.data;
		}
		
	}
	
	
	/**
	 * gets size of the list
	 * 
	 * @return size of the list as integer
	 */
	public int size() {
		return size;
	}
	
	
	/**
	 * clear the list to empty list
	 */
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}
	
	
	/**
	 * produces a string representation of the list
	 * 
	 * @return String representation of the list
	 */
	public String toString() {
		if (size == 0) {
			return "[]";
		}
		else {
			String ans = "[";
			Node<E> curr = head;
			while (curr.next != null) {
				ans += curr.data + ", ";
				curr = curr.next;
			}
			ans += curr.data + "]";
			return ans;
		}
	}

	
	//Below are methods of the interface I did not need to implement.
	//-----------------------------------------------------------------------------------------------
	
	@Override
	public boolean isEmpty() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<E> iterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}
	
	
}
