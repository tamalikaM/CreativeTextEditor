/**
 * 
 */
package textgen;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author TamalikaM
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	MyLinkedList<Double> emptyList2;;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		emptyList2 = new MyLinkedList<Double>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		a = list1.remove(list1.size-1);
		assertEquals("Remove",a, 42);
		
		try {
			list1.remove(-1);
			fail("Removal from negative indices not allowed");
		}
		catch(IndexOutOfBoundsException e) {}
		
		try {
			list1.remove(list1.size);
			fail("Removal from higher indices not allowed");
		}
		catch(IndexOutOfBoundsException e) {}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		//test empty list, get should throw an exception
		emptyList.add(0);
		assertEquals("Check addition to empty list", (Integer)0, emptyList.get(0));
		emptyList.remove(0);
		
		try {
			shortList.add(null);	
			fail("Check for null value addition");
			}
		catch (NullPointerException e) {}
		assertEquals("Check list element addition at the end", (Integer)42, list1.get(list1.size-1));
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals("Check empty list's size", 0, emptyList.size());
		assertEquals("Check long list's size", LONG_LIST_LENGTH, longerList.size());
		emptyList.add(0);
		assertEquals("Check empty list's size after adding a value", 1, emptyList.size());
		emptyList.remove(0);
		assertEquals("Check empty list's size after removing only element", 0, emptyList.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		emptyList.add(0,0);
		assertEquals("Check", (Integer)0, emptyList.get(0));
		emptyList.remove(0);
		
		try {
			list1.add(list1.size()+1,5);
			fail("Element added at wrong index");
		}
		catch(IndexOutOfBoundsException e) {}
		
		try {
			list1.add(null);
			fail("Attempt to add null elementt");
		}
		catch(NullPointerException e ) {}
		
		try {
			list1.add(-1,5);
			fail("Element added at wrong index");
		}
		catch(IndexOutOfBoundsException e) {}
		
		list1.add(0,15);
		assertEquals("Check", (Integer)15, list1.get(0));
		
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i,i);
		}
		
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		list1.add(1,21);
		assertEquals("Check", (Integer)21, list1.get(1));
		
		list1.add(2,45);
		assertEquals("Check", (Integer)45, list1.get(2));
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		try {
			list1.set(-1,5);
			fail("Element added at wrong index");
		}
		catch(IndexOutOfBoundsException e) {}
		
		try {
			list1.set(list1.size()+1,5);
			fail("Element added at wrong index");
		}
		catch(IndexOutOfBoundsException e) {}
		int a = list1.get(0);
		int b = list1.set(0,28);
		assertEquals("Check",a,b);
		
		a=list1.get(list1.size-1);
		b = list1.set(list1.size-1, 40);
		assertEquals("Check",a,b);
	    
	}
}
