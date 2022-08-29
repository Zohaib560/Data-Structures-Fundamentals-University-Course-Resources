import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class RBTList<E> implements List<E> {
	
	private static final boolean RED   = true;
    private static final boolean BLACK = false;
    private Node<E> root;
    
    private static class Node<E>{
    	/**
    	 * Class adapted from https://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html
    	 */
    	int size = 1;
    	E val;
    	Node<E> left;
    	Node<E> right;
    	boolean color;
    	
    	
    	public Node(E val, boolean color) {
    		this.val = val;
    		this.color = color;
    	}
    }
    
	public RBTList() {}
	
	/*
	 * helper method adapted from https://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html
	 */
	private boolean isRed(Node<E> x) {
        if (x == null) return false;
        return x.color == RED;
    }
	
	/*
	 * helper method adapted from https://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html
	 */
	private int size(Node<E> x) {
        if (x == null) return 0;
        return x.size;
    } 
	
	/**
	 * @param h : location of rotation
	 * @return corrected node
	 * 
	 * adapted from https://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html
	 */
    private Node<E> rotateRight(Node<E> h) {
        assert (h != null) && isRed(h.left);
        Node<E> x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }
	
    /**
	 * @param h : location of rotation
	 * @return corrected node
	 * 
	 * adapted from https://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html
	 */
	private Node<E> rotateLeft(Node<E> h) {
        assert (h != null) && isRed(h.right);
        Node<E> x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }
	
	/**
	 * @param h : location of color flip
	 * 
	 * adapted from https://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html
	 */
    private void flipColors(Node<E> h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }
	
    /**
     * adapted from https://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html
     */
    private Node<E> moveRedLeft(Node<E> h) {
        flipColors(h);
        if (isRed(h.right.left)) { 
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }
    
    /**
     * adapted from https://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html
     */
    private Node<E> moveRedRight(Node<E> h) {
        flipColors(h);
        if (isRed(h.left.left)) { 
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }
    
    /**
     * adapted from https://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html
     */
    private Node<E> balance(Node<E> h) {
        if (isRed(h.right) && !isRed(h.left))    h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     flipColors(h);

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }
    
    /**
     * adapted from https://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html
     */
    private Node<E> min(Node<E> x) { 
        if (x.left == null) return x; 
        else                return min(x.left); 
    } 
    
    /**
     * adapted from https://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html
     */
    private Node<E> deleteMin(Node<E> h) { 
        if (h.left == null)
            return null;

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }
    
	/**
	 * @return the size of the tree
	 */
	public int size() {
		return size(root);
	}
	
	/*
	 * clears the tree of all nodes
	 */
	public void clear() {
		root = null;
	}
	
	/*
	 * @param e : element to be added
	 * @return true if e is added else false
	 */
	public boolean add(E e) {
		if (root == null) { //adding to empty tree
			root = new Node<>(e, BLACK);
			return true;
		}
		//adding to non-empty tree
		add(size(root), e);
		return true;
	}
	
	/*
	 * @param index : index of insertion
	 * @param element : element to be added
	 * @return true if e is added else false
	 * adapted from https://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html
	 */
	public void add(int index, E element) {
		if (index < 0 || index > size(root)) {
			throw new IndexOutOfBoundsException();
		}
		
		if (size(root) == 0) {
			root = new Node<>(element, BLACK);
		}
		else {
			root = addRec(root, index + 1 - 0.5, element);
			root.color = BLACK;
		}
	}
	
	/**
	 * recursively adds new element while maintaining the tree
	 * @param root : root of tree
	 * @param pos : rank of search
	 * @return node at specified pos
	 * adapted from https://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html
	 */
	private Node<E> addRec(Node<E> h, double pos, E element) {
		if (h == null) { //base
			return new Node<>(element, RED);
		}
		int rank = size(h) - size(h.right);
		if (rank > pos) {
			h.left = addRec(h.left, pos, element);
		}
		else if (rank < pos) {
			h.right = addRec(h.right, pos - size(h.left) - 1, element);
		}
		//else {
		//	h = new Node<>(element, RED);
		//}
		
		if (isRed(h.right) && !isRed(h.left))      h = rotateLeft(h);
        if (isRed(h.left)  &&  isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left)  &&  isRed(h.right))     flipColors(h);
        h.size = size(h.left) + size(h.right) + 1;
		
        return h;
	}
	
	/*
	 * removes node at specified index
	 * @param index : index of removal
	 * @return the item that was removed
	 * 
	 * adapted from https://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html
	 */
	public E remove(int index) {
		if (index < 0 || index > size(root) - 1) {
			throw new IndexOutOfBoundsException();
		}
		if (size(root) == 1) {
			E ans = root.val;
			root = null;
			return ans;
		}
		else {
			if (!isRed(root.left) && !isRed(root.right)) {
				root.color = RED;
			}
			E ans = get(index);
			root = delete(root, index + 1);
			if (!isEmpty()) root.color = BLACK;
			return ans;
		}
	}
	
	/**
	 * helper function to remove node
	 * @param root : current root
	 * @param pos : position in search
	 * @return node
	 * adapted from https://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html
	 */
	private Node<E> delete(Node<E> h, int pos){
		int cmp = size(h) - size(h.left);
		if (pos < cmp)  {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, pos);
        }
        else {
            if (isRed(h.left))
                h = rotateRight(h);
            if ((pos == size(h)) && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (pos == size(h)) {
                Node<E> x = min(h.right);
                h.val = x.val;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, pos - size(h.left) - 1);
        }
        return balance(h);
	}
	
	
	/*
	 * @param index : index of item
	 * @return the item at index
	 */
	public E get(int index) {
		//index out of bounds
		if (size(root) == 0) {
			throw new IndexOutOfBoundsException();
		}
		else if (index > size(root) - 1 || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		//index in bounds
		return getRec(root, index + 1).val;
	}
	
	/**
	 * @param root : current root of subtree
	 * @param pos : rank to look for
	 * @return node at specified pos
	 */
	private Node<E> getRec(Node<E> root, int pos) {
		if (root == null) { //base
			return null;
		}
		if (size(root) - size(root.right) > pos) { //search left subtree
			return getRec(root.left, pos);
		}
		else if (size(root) - size(root.right) < pos){ // search right subtree
			return getRec(root.right, pos - size(root.left) - 1);
		}
		else { //search current node
			return root;
		}
		
	}
	
	/*
	 * @return the string representation of RBTList
	 */
	public String toString() {
		if (root == null) {
			return "[]";
		}
		String ans = toString(root);
		return ("[" + ans.substring(0, ans.length() - 2) + "]");
	}
	
	/**
	 * @param root root of tree
	 * @return string which contains tree vals using inorder traversal
	 */
	private String toString(Node<E> root) {
		//in order traversal
		String ans = "";
		if (root == null) {
			return "";
		}
		else {
			ans += toString(root.left);
			ans += root.val + ", ";
			ans += toString(root.right);
			return ans;
		}
	}

	//all other unimplemented methods
	//==========================================================================================//
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return root == null;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}
}
