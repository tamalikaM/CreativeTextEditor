package textgen;

import java.util.AbstractList;

/** A class that implements a doubly linked list
 * 
 * @author TamalikaM
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// COMPLETED: Implement this method
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next= tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		add(size,element);
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) throws IndexOutOfBoundsException
	{
		if((size > 0 && (index < 0 || index >= this.size)) || this.size == 0 )
			throw new IndexOutOfBoundsException();
		int i = 0;
		LLNode<E> node = this.head.next;
		if(index<=size/2) {
			while(index > i) {
				node = node.next;
				i++;
			}
		}
		else {
			node = this.tail.prev;
			i = size-1;
			while(index < i) {
				node = node.prev;
				i--;
			}
		}
		return node.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if(index < 0 || index > this.size) {
			throw new IndexOutOfBoundsException();
		}
		if(element == null) {
			throw new NullPointerException();
		}
		int i = 0;
		LLNode<E> newNode = new LLNode(element);
		LLNode<E> node = this.head.next;
		while(index>i) {
			node = node.next;
			i++;
		}
		newNode.next = node;
		newNode.prev = node.prev;
		node.prev.next = newNode;
		node.prev = newNode;
		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if((size > 0 && (index < 0 || index >= this.size)) || this.size == 0 )
			throw new IndexOutOfBoundsException();
		
		LLNode<E> node = this.head.next;
		int i = 0;
		while(index>i) {
			node = node.next;
			i++;
		}
		node.prev.next = node.next;
		node.next.prev = node.prev;
		size --;
		return node.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if((size > 0 && (index < 0 || index >= this.size)) || this.size == 0 )
			throw new IndexOutOfBoundsException();

		if(element == null) {
			throw new NullPointerException();
		}
		
		int i = 0;
		LLNode<E> node = this.head.next;
		while(index>i) {
			node = node.next;
			i++;
		}
		E previousVal = node.data;
		node.data = element;
		return previousVal;
	}
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;
	public LLNode(E e)
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
}
