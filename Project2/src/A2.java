
public class A2 {
	
	/**
	 * Used to compare 2 easy to create objects when testing the sorting methods
	 * 
	 * @param a : object 1
	 * @param b : object 2
	 * @return int which follows traditional comparable convention
	 */
	private static int compareTo (Object a, Object b) {
		if (a instanceof Integer && b instanceof Integer) {
			Integer a1 = (Integer) a;
			Integer b1 = (Integer) b;
			return a1.compareTo(b1);
		}
		else if (a instanceof String && b instanceof String){
			String a1 = (String) a;
			String b1 = (String) b;
			return a1.compareTo(b1);
		}
		else if (a instanceof Double && b instanceof Double) {
			Double a1 = (Double) a;
			Double b1 = (Double) b;
			return a1.compareTo(b1);
		}
		else {
			throw new ClassCastException();
		}
	}
	
	/**
	 * Sink the specified item in a
	 * 
	 * @param a : array
	 * @param i : position of sinking
	 * @param n : length of array
	 * 
	 * Adapted from https://algs4.cs.princeton.edu/24pq/Heap.java.html
	 */
	private static void sink(Object[] a, int i, int n) {
		while (2 * i <= n) {
			int j = 2 * i;
			if (j < n && (compareTo(a[j - 1], a[j]) < 0)) {
				j++;
			}
			if (!(compareTo(a[i - 1], a[j - 1]) < 0)) {
				break;
			}
			exchange(a, i, j);
			i = j;
		}
	}
	
	/**
	 * Sink the specified item in a
	 * 
	 * @param a : array
	 * @param i : position of sinking
	 * @param n : length of array
	 * 
	 * Adapted from https://algs4.cs.princeton.edu/24pq/Heap.java.html
	 */
	private static void sink(int[] a, int i, int n) {
		while (2 * i <= n) {
			int j = 2 * i;
			if (j < n && (a[j - 1] < a[j])) {
				j++;
			}
			if (!(a[i - 1] < a[j - 1])) {
				break;
			}
			exchange(a, i, j);
			i = j;
		}
	}
	
	/**
	 * swaps objects in a at i and j
	 * 
	 * @param a : array to do swapping
	 * @param i : position of swap
	 * @param j : position of swap
	 * 
	 * Adapted from https://algs4.cs.princeton.edu/24pq/Heap.java.html
	 */
	private static void exchange(Object[] a, int i, int j) {
		Object temp = a[i - 1];
		a[i - 1] = a[j - 1];
		a[j - 1] = temp;
	}
	
	private static void exchange(int[] a, int i, int j) {
		int temp = a[i - 1];
		a[i - 1] = a[j - 1];
		a[j - 1] = temp;
	}
	
	/**
	 * Uses heap sort to sort the given array of integers.
	 * Uses bottom up approach
	 * 
	 * @param a : array to be sorted
	 * 
	 * Adapted from https://algs4.cs.princeton.edu/24pq/Heap.java.html
	 */
	public static void heapSort(int[] a) {
		int n = a.length;
		//heapify the list using down up with sinking
		for (int i = n/2; i >= 1; i--) {
			sink(a, i, n);
		}
		//sortdown
		int i = n;
		while (i > 1) {
			exchange(a, 1, i--);
			sink(a, 1, i);
		}
	}
	
	/**
	 * Uses heap sort to sort the given array of objects.
	 * 
	 * @param a : array to be sorted
	 * 
	 * Adapted from https://algs4.cs.princeton.edu/24pq/Heap.java.html
	 */
	public static void heapSort(Object[] a) {
		int n = a.length;
		//heapify the list using down up with sinking
		for (int i = n/2; i >= 1; i--) {
			sink(a, i, n);
		}
		//sortdown
		int i = n;
		while (i > 1) {
			exchange(a, 1, i--);
			sink(a, 1, i);
		}
	}
	
	
	/**
	 * Uses shell sort to sort the given array of integers.
	 * 
	 * @param a : array to be sorted
	 * 
	 * adapted from https://algs4.cs.princeton.edu/21elementary/Shell.java.html
	 */
	public static void shellSort(int[] a) {
		int n = a.length;
		int h = 1;
		while (h < n/3) {
			h = 3*h + 1;
		}
		while (h >= 1) {
			for (int i = h; i < n; i++) {
				for (int j = i; j >= h && a[j] < a[j - h]; j -= h) {
					int temp = a[j];
					a[j] = a[j-h];
					a[j-h] = temp;
				}
			}
			h = h/3;
		}
	}
	
	/**
	 * Uses shell sort to sort the given array of objects.
	 * 
	 * @param a : array to be sorted
	 * 
	 * adapted from https://algs4.cs.princeton.edu/21elementary/Shell.java.html
	 */
	public static void shellSort(Object[] a) {
		int n = a.length;
		int h = 1;
		while (h < n/3) {
			h = 3*h + 1;
		}
		while (h >= 1) {
			for (int i = h; i < n; i++) {
				for (int j = i; j >= h && (compareTo(a[j], a[j - h]) < 0); j -= h) {
					Object temp = a[j];
					a[j] = a[j-h];
					a[j-h] = temp;
				}
			}
			h = h/3;
		}
	}
}
