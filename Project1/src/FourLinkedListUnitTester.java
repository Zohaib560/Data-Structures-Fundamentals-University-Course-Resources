import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

public class FourLinkedListUnitTester {

	@Test
	public void emptyTest() {
		FourLinkedList<Integer> l = new FourLinkedList<>();
		assertTrue("List is initializing properly", l.size() == 0);
	}
	
	@Test
	public void invalidAddTest() {
		FourLinkedList<Integer> l = new FourLinkedList<>();
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
		FourLinkedList<Integer> l = new FourLinkedList<>();
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
		FourLinkedList<Integer> l = new FourLinkedList<>();
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
		FourLinkedList<Integer> l = new FourLinkedList<>();
		l.add(10);
		l.add(20);
		l.add(30);
		l.add(40);
		assertTrue("List size is calculating size properly", l.size() == 4);
	}
	
	@Test
	public void addDataTest() {
		FourLinkedList<Integer> l = new FourLinkedList<>();
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
		FourLinkedList<Integer> l = new FourLinkedList<>();
		l.add(0, 0);
		l.add(40);
		assertTrue("0 is inserted at beggining", l.get(0) == 0);
	}
	
	@Test
	public void insertMidTest() {
		FourLinkedList<Integer> l = new FourLinkedList<>();
		l.add(10);
		l.add(20);
		l.add(30);
		l.add(2, 0);
		l.add(40);
		assertTrue("0 is inserted at middle", l.get(2) == 0);
	}
	
	@Test
	public void insertEndTest() {
		FourLinkedList<Integer> l = new FourLinkedList<>();
		l.add(10);
		l.add(20);
		l.add(30);
		l.add(40);
		l.add(4, 0);
		assertTrue("0 is inserted at end", l.get(4) == 0);
	}
	
	@Test
	public void clearTest() {
		FourLinkedList<Integer> l = new FourLinkedList<>();
		l.add(10);
		l.add(20);
		l.add(30);
		l.clear();
		assertTrue("List is clearing properly", l.size() == 0);
	}
	
	public void removeTest() {
		FourLinkedList<Integer> l = new FourLinkedList<>();
		l.add(10);
		l.add(20);
		l.add(30);
		l.add(40);
		l.add(50);
		l.add(60);
		l.add(70);
		assertTrue("List is removing correct data start", l.remove(0) == 10);
		assertTrue("List is removing correct data end", l.remove(l.size() - 1) == 70);
		assertTrue("List is removing correct data middle", l.remove(2) == 40);
		assertTrue("List removing is keeping track of size correctly", l.size() == 4);
	}
	
	@Test
	public void removeAllTest() {
		FourLinkedList<Integer> l = new FourLinkedList<>();
		int[] l2 = {10,20,30,40,50,60,70};
		l.add(10);
		l.add(20);
		l.add(30);
		l.add(40);
		l.add(50);
		l.add(60);
		l.add(70);
		int s = l.size();
		for (int i = 0; i < s; i++) {
			assertTrue("List removing correct data", l.remove(0) == l2[i]);
		}
		assertTrue("List removing all items correctly", l.size() == 0);
	}
	
	@Test
	public void toStringTest() {
		FourLinkedList<Integer> l = new FourLinkedList<>();
		int[] l2 = {30,40,50,60,70,80};
		l.add(10);
		l.add(20);
		l.add(30);
		l.add(40);
		l.add(50);
		l.add(60);
		l.add(70);
		l.remove(6);
		l.add(6, 70);
		l.remove(1);
		l.add(80);
		l.remove(0);
		String ls = l.toString();
		String l2s = Arrays.toString(l2);
		assertTrue("List toString is working properly", ls.equals(l2s));
	}
	
	@Test
	public void nodeTraversalTest() {
		FourLinkedList<Integer> l = new FourLinkedList<>();
		for (int i = 1; i <= 20; i++) {
			l.add(i);
		}
		l.remove(6);
		l.add(6, 70);
		l.remove(1);
		l.add(80);
		l.remove(0);
		l.add(0, 1);
		l.add(4, l.remove(4));
		
		l.get(0); //find at start
		l.get(19); //find at end
		l.get(7); //find in middle closer to start
		l.get(13); //find in middle closer to end
		l.get(4); //find at an exact jump of 4
	}
	
	@Test
	public void addMilTest() {
		FourLinkedList<Integer> l = new FourLinkedList<>();
		l.add(-1);
		l.add(0);
		l.add(-1);
		for (int i = 1; i <= 1000000; i++) {
			l.add(1,i);
		}
		assertTrue("", l.size() == 1000003);
	}
	
	@Test
	public void removeMilTest() {
		FourLinkedList<Integer> l = new FourLinkedList<>();
		l.add(-1);
		l.add(0);
		l.add(-1);
		for (int i = 1; i <= 1000000; i++) {
			l.add(1,i);
		}
		int s = l.size();
		for (int i = 1; i <= s; i++) {
			l.remove(0);
		}
		assertTrue("", l.size() == 0);
	}
	
	@Test
	public void getMilTest() {
		FourLinkedList<Integer> l = new FourLinkedList<>();
		l.add(-1);
		l.add(0);
		l.add(-1);
		for (int i = 1; i <= 1000000; i++) {
			l.add(1,i);
		}
		l.get(500001);
	}
	
	//NOTE: Everything seems to work including the links being proper!!!
}
