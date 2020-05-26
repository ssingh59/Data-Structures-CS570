package DS_LABS;

//SHWETA SINGH
import java.util.ArrayList;


public class IDLList<E> {
	
	//declare private variables
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	@SuppressWarnings("hiding")
	//Nodes to use in doubly linked lists.
	class Node<E>{
		private E data;
		private Node<E> next;
		private Node<E> prev;
		
		//Creates a new node with the given data and null next and previous.
		public Node(E elem)
		{
			data = elem;
			prev = null;
			next = null;
		}
		
		//Creates a new node with the given next and previous nodes.
		public Node(E elem, Node<E> prev,Node<E> next)
		{
			data = elem;
			this.prev = prev;
		    this.next = next;
		}
	}

	public IDLList()
	{
		
		//head = null;
		//tail = null;
		
		head = new Node<E>(null);
	    tail = new Node<E>(null);
	    head.next = tail;
	    tail.prev = head;
		size = 0;
		indices = new ArrayList<Node<E>>();//Instantiate
		
	}
	
	// Inserts the given element at the given position in this list.
	public boolean add(int index,E elem)
	{
		if(index < 0 || index > size) {
			throw  new ArrayIndexOutOfBoundsException();
		}
		
		
		
		if(index == 0) {
            add(elem);
			//System.out.println("append at end:"+newNode.data+"|"+newNode.next+"|"+newNode.prev+ "size:"+size);
	        }
		
		if(index == size)
		{
			append(elem);
		}
		
		Node<E> newNode = new Node<E>(elem);
		if(index >= 1 && index < size)
			{
			Node<E> current = indices.get(index);
			current.prev.next = newNode;
			newNode.prev = current.prev;
			newNode.next = current;
			current.prev = newNode;
			size++;
			indices.add(index, newNode);
			//System.out.println("add element at given index:"+newNode.data+"|"+newNode.next+"|"+newNode.prev+ "size:"+size);
				
			}
		
		return true;
	}
	
	
	public boolean add(E elem) // Adds element at head
	{
		Node<E> newNode = new Node<E>(elem);
		
		if(size == 0) {
			head = tail = newNode;
			size++;
			indices.add(0, newNode);
		}
		else if (!(size == 0)) {
		Node<E> first = head;
		newNode = new Node<E>(elem, head, first);
		head.prev = newNode;
		newNode.next = head;
		head = newNode;
		size++;
		//System.out.println("add element at head:"+newNode.data+"|"+newNode.next+"|"+newNode.prev+ "size:"+size);
		indices.add(0, newNode);
		}
		
		return true;
	}
	
	public boolean append(E elem) //Adds element at tail
	{
		Node<E> newNode = new Node<E>(elem);
		if(size == 0) {
			head = tail = newNode;
			size++;
			indices.add(0, newNode);
		}
		
		else if (!(size == 0)) {
			Node<E> last = tail;
			last.next = newNode;
			newNode.prev = last;
	        tail = newNode;
	        indices.add(size, newNode);
			size++;
			
		}	
		//System.out.println("append at end:"+newNode.data+"|"+newNode.next+"|"+newNode.prev+ "size:"+size);
		return true;
		
	}
	
	@SuppressWarnings("unchecked")
	public E get(int index) // gets element at index
	{ 
		if (index < 0 || index >= size)
	    {
		throw new IndexOutOfBoundsException("Invalid index: " + index);
	    }
		
		else {
			Node<E> getNodeData = indices.get(index);
			//System.out.println("gets element at index: "+getNodeData.data);
			return getNodeData.data;
		}	
    }
		
	// gets element at head
	public E getHead() 
	{
		if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        return head.data;
	}
	
	// gets element at tail
	public E getLast() 
	{
		if(size == 0)
			throw  new ArrayIndexOutOfBoundsException();
		else
		{
			return  indices.get(size-1).data;
		}
	}
	
	 // returns size of list
	public int size()
	{
		return size;
	}
	
	//removes element at front of list
	public E remove() 
	{

		if(size == 0) {
			throw new IndexOutOfBoundsException();
		}
		
		Node<E> removedNode = indices.get(0);
		
		if(size == 1){
			head = tail = null;
		}
		
		if(size > 1) {
			head = removedNode.next;
            removedNode.next.prev = null;
		}
		
		indices.remove(0);	
		size--;
		return removedNode.data;
	}
	
	//Removes last element of list and returns the deleted element 
	public E removeLast() 
	{
		if(size == 0) {
			throw new IndexOutOfBoundsException();
		}
		
		Node<E> current = tail;
		
		if(size == 1){
			E data = remove();
            return data;

		}
		
		if(size > 1) {
			tail = tail.prev;
			tail.next = null;
			indices.remove(current);
		}
		
		indices.remove(0);	
		size--;
		return current.data;
		
	}
	
	
	public E removeAt (int index) // removes element at index
	{
		if(index < 0 || index >= size)
		{
			throw new ArrayIndexOutOfBoundsException("Index error at: "+index);
		}
		
		if(index == 0) {//remove element at first 
			E data = remove();
            return data;
		}
		
		if (index == (size - 1)) {//remove last node
            E data = removeLast();
            return data;
        }
		
		Node<E> removeAt = indices.get(index);
		E data = removeAt.data;
		if(index > 1) { //any other index
			removeAt.next.prev = removeAt.prev;
			removeAt.prev.next = removeAt.next;
			indices.remove(removeAt);
			size--;
		}
		return data;
	}

	// remove particular element
	public boolean remove(E elem) 
	{
		
		if (size == 0) {
            throw new ArrayIndexOutOfBoundsException("Invalid operation for size: "+size);
        }
		int i=0;
		while(i < size) {
			Node<E> currentNode = indices.get(i);
			if(currentNode.data.equals(elem)) {
				if(i == 0) {
					@SuppressWarnings("unused")
					E data = remove();
                    return true;
				}
				else if(i == (size - 1)) {
					@SuppressWarnings("unused")
					E data = removeLast();
                    return true;
				}
				else if(i != 0 && i != (size - 1)) {
					@SuppressWarnings("unused")
					E data = removeAt(i);
                    return true;
				}
				
			}
			i++;
		}
		return false;
    }
	
	//print data in string format
	@Override
	public String toString(){

			String temp = "";
			Node<E> nodeRef = head;
			if(head == null) {
				System.out.print("The list does not contain any node.");
			}
			while(nodeRef!= null) {
				temp = temp + " | "+ nodeRef.data;
				nodeRef = nodeRef.next;
			}
			return temp;
		}
	public static void main(String[] args) {

    }
	
}

