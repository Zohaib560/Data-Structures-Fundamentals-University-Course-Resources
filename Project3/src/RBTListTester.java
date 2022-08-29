import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class RBTListTester {

	@Test
	public void emptyTest() {
		RBTList<Integer> l = new RBTList<>();
		assertTrue("List is initializing properly", l.size() == 0);
	}
	
	@Test
	public void invalidAddTest() {
		RBTList<Integer> l = new RBTList<>();
		try{
			l.add(1, 0);
			fail("Exception was to be thrown");
		}catch (IndexOutOfBoundsException e){
			//OK
		}catch (Exception ex) {
			fail("Wrong type of exception");
		}
	}
	
	@Test
	public void invalidRemoveTest() {
		RBTList<Integer> l = new RBTList<>();
		try{
			l.remove(0);
			fail("Exception was to be thrown");
		}catch (IndexOutOfBoundsException e){
			//OK
		}catch (Exception ex) {
			fail("Wrong type of exception");
		}
	}
	
	@Test
	public void invalidGetTest() {
		RBTList<Integer> l = new RBTList<>();
		try{
			l.get(0);
			fail("Exception was to be thrown");
		}catch (IndexOutOfBoundsException e){
			//OK
		}catch (Exception ex) {
			fail("Wrong type of exception");
		}
	}

	@Test
	public void addSizeTest() {
		RBTList<Integer> l = new RBTList<>();
		l.add(10);
		l.add(20);
		l.add(30);
		l.add(40);
		assertTrue("List size is calculating size properly", l.size() == 4);
	}
	
	@Test
	public void getTest() {
		RBTList<Integer> l = new RBTList<>();
		l.add(10);
		l.add(20);
		l.add(30);
		l.add(40);
		assertTrue("List size is calculating size properly", l.get(1) == 20);
	}
	
	@Test
	public void getTest2() {
		RBTList<Integer> l = new RBTList<>();
		l.add(10);
		l.add(20);
		l.add(30);
		l.add(40);
		l.add(50);
		assertTrue("List size is calculating size properly", l.get(1) == 20);
	}
	
	@Test
	public void addDataTest() {
		RBTList<Integer> l = new RBTList<>();
		int[] l2 = {10,20,30,40,50,60};
		l.add(10);
		l.add(20);
		l.add(30);
		l.add(40);
		l.add(50);
		l.add(60);
		int i = 0;
		for (int num : l2) {
			assertTrue("List has correct data", l.get(i) == num);
			i++;
		}
	}
	
	@Test
	public void insertStartTest() {
		RBTList<Integer> l = new RBTList<>();
		l.add(0, 0);
		l.add(40);
		assertTrue("0 is inserted at beggining", l.get(0) == 0);
	}
	
	@Test
	public void insertMidTest() {
		RBTList<Integer> l = new RBTList<>();
		//System.out.println(l.toString());
		l.add(10);
		//System.out.println(l.toString());
		l.add(20);
		//System.out.println(l.toString());
		l.add(30);
		//System.out.println(l.toString());
		l.add(40);
		//System.out.println(l.toString());
		l.add(1, 0);
		//System.out.println(l.toString());
		assertTrue("0 is inserted at middle", l.get(1) == 0);
	}
	
	@Test
	public void insertEndTest() {
		RBTList<Integer> l = new RBTList<>();
		l.add(10);
		l.add(20);
		l.add(30);
		l.add(40);
		l.add(4, 0);
		assertTrue("0 is inserted at end", l.get(4) == 0);
	}
	
	@Test
	public void clearTest() {
		RBTList<Integer> l = new RBTList<>();
		l.add(10);
		l.add(20);
		l.add(30);
		l.clear();
		assertTrue("List is clearing properly", l.size() == 0);
	}
	
	@Test
	public void removeTest() {
		RBTList<Integer> l = new RBTList<>();
		l.add(10);
		l.add(20);
		l.add(30);
		l.add(40);
		l.add(50);
		l.add(60);
		l.add(70);
		assertTrue("List is removing correct data start", l.remove(4) == 50);
	}
	
	@Test
	public void removeAllTest() {
		RBTList<Integer> l = new RBTList<>();
		int[] l2 = {10,20,30,40,50,60,70};
		l.add(10);
		l.add(20);
		l.add(30);
		l.add(40);
		l.add(50);
		l.add(60);
		l.add(70);
		int s = l.size();
		assertTrue("List removing correct data", l.remove(6) == 70);
		//l.add(70);
		assertTrue("List removing correct data", l.remove(2) == 30);
	}
	
	@Test
	public void toStringTest() {
		RBTList<Integer> l = new RBTList<>();
		int[] l2 = {10, 20, 30, 40, 50, 60, 70};
		l.add(10);
		l.add(20);
		l.add(30);
		l.add(40);
		l.add(50);
		l.add(60);
		l.add(70);
		String ls = l.toString();
		String l2s = Arrays.toString(l2);
		assertTrue("List toString is working properly", ls.equals(l2s));
	}

	

}
