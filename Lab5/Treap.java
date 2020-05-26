package HW5;

import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {
	
	private Random priorityGenerator;
	private Node <E> root;
	
	//cretae empty treap
	public Treap () {
		root = null;
		priorityGenerator = new Random();
	}
	
	//create empty treap with random prority
	public Treap ( long seed ) {
		root = null;
	}
	
	//add key with random priority
	boolean add (E key ) {
		int randomPriority = priorityGenerator.nextInt();
		return add(key, randomPriority);
	}
	
	//add key witha key and proirity
	boolean add (E key , int priority ) {
		
		Node<E> newNode = new Node<E>(key, priority);
		if(root == null) {
			root = newNode;
			return true;
		}
		Stack<Node<E>> traversePath = new Stack<Node<E>>();	
		Node<E> currentNode = root;
		
		while(root != null) {
			
			int equality = key.compareTo(currentNode.data);
			traversePath.push(currentNode);
			
			if(equality < 0 && currentNode.left == null) //add to left sub tree
			{
					Node<E> newnode = new Node<E>(key,priority);
					currentNode.left = newnode;
					reheap(traversePath, newnode);
					return true;
			}	
			else if(equality < 0 && currentNode.left != null){
				currentNode = currentNode.left;
			}
			
			if(equality > 0 && currentNode.right == null) //add to right sub tree
			{
					Node<E> newnode = new Node<E>(key,priority);
					currentNode.right = newnode;
					reheap(traversePath, newnode);
					return true;
				
			}
			else if(equality > 0 && currentNode.right != null){
				currentNode = currentNode.right;
			}
			
			//key is already present in the tree
			if(equality == 0) {
				//System.out.println("\nduplicate key\n");
				return false;
			}
		}
		return false;
	}
		
	//this method helps to adjust the tree to satisfy heap property
	public void reheap(Stack<Node<E>> traversePath, Node<E> newNode) {
		
		while (!traversePath.isEmpty()) {
			
			Node<E> parent = traversePath.pop();
			int equality = parent.data.compareTo(newNode.data);
			
			// max heap
			if (parent.priority < newNode.priority) { 
				
				//if parent data is greater than newnode data
				if (equality > 0) {
					newNode = parent.rotateRight();
				}
				//if parent data is less than newnode data
				else if(equality < 0) {
					newNode = parent.rotateLeft();
				}
				
				//if stack is empty, root node will be new node
				if(traversePath.isEmpty()) {
					this.root = newNode;
				}
				//if stack is not empty, arrange the newNode with left and right attachments
				else if(!traversePath.isEmpty()) {
					Node<E> nnode = traversePath.peek();
					
					if(nnode.right == parent) {
						nnode.right = newNode;
					}
					
					else if(nnode.left == parent){
						nnode.left = newNode;	
					}
				
				}
			}
			else {
				//no rotation required, treap is good
				break;
			}
		}
	}
		
	//helper function for deleting node with given key and root node
	private Node<E> delete(Node<E> root, E key){
		
		//if root is not null then recursively call this method to check for subsequent nodes
		if (root != null) {
			
			int equality = root.data.compareTo(key);
			
            if (equality < 0) {
            	root.right = delete(root.right, key);
            } 
            
            else if (equality > 0) {
            	root.left = delete(root.left, key);            
            } 
            
            //equality = 0
            
            else {
                if (root.right == null) {
                    return root.left;
                }
                else if (root.left == null) {
                    return root.right;
                }
                else {
                    Node<E> tempNode = root.right;
                    while(tempNode.left != null) {
                    	tempNode = tempNode.left;
                    }
                    root.data = tempNode.data;
                	root.right = delete(root.right, root.data);
                }
            }
        }
        return root;
		
	}		
						
	//delete the 					
	boolean delete (E key ) {
		if (!find(key)) {
			return false;
		}
    	root = delete(root, key);
    	return true;
		
	}
	
	//helper method to find the node with key
	private boolean find (Node <E> root , E key ) {
		
		Node<E> current = root;
		if(current == null) {
			return false;
		}
		if(key == null) {
			return false;
		}
		int equality = key.compareTo(current.data);
		if(equality < 0) {
			return find(current.left,key);
		}
		else if(equality > 0){
			return find(current.right,key);	
		}
		else  
			return true;
	}
	
	//find the node with key key
	public boolean find (E key) {
		boolean result = find(root,key);
		return result;
	}
	
	
	//append all nodes and print
	//reference DS textbook
	private void toString(Node<E> node, int depth,StringBuilder sb) {
			for (int i = 1; i < depth; i++) {
				sb.append(" ");
			}
				
			if (node == null) {
			sb.append("null\n");
			} 
			else {
				sb.append("(key=" + node.data +", priority=" + node.priority + ")" );
				sb.append("\n");
				toString(node.left, depth + 1, sb);
				toString(node.right, depth + 1, sb);
			}
	}
	
	
	//reference DS textbook
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toString(root, 1, sb);
		return sb.toString();
		}
	
	
	
	@SuppressWarnings("hiding")
	private class Node<E> {
		
		public E data; // key for the search
		public int priority; // random heap priority
		public Node <E> left;
		public Node <E> right;
		
		//creates node with the input data
		public Node (E data , int priority ) {
			
			if (data == null) {
				throw new IllegalArgumentException();
			}
			else {
				 this.data = data;
				 this.priority = priority;
				 left = right = null;
			}
		}
		
		//right rotation for satisfying heap property
		Node <E> rotateRight (){
			if (left == null) {
				return this;
			}
			else {
				 Node<E> leftNode = left;
				 left = left.right;
				 leftNode.right = this;
				 return leftNode;
			}
		}
		
		//left rotation for satisfying heap property
		Node <E> rotateLeft (){
			if (right == null) {
				return this;
			}
			else {
				Node<E> rightNode = right;
				right = right.left;
				rightNode.left = this;
				return rightNode;
			}
		}
	}
	
	public static void main(String[] args) {
		Treap<Integer> testTree = new Treap<Integer>();
		testTree.add (4 ,19);
		testTree.add (2 ,31);
		testTree.add (6 ,70);
		testTree.add (1 ,84);
		testTree.add (3 ,12);
		testTree.add (5 ,83);
		testTree.add (7 ,26);
		System.out.println(testTree.add (7 ,26));
		System.out.println("_______After Adding nodes______\n");
		System.out.println(testTree.toString());
		testTree.delete(1);
		System.out.println(testTree.find(6));
		System.out.println(testTree.find(1));
		System.out.println(testTree.delete(1));
		System.out.println("______After Deleting a node_____\n");
		System.out.println(testTree.toString());
		
		}

}
