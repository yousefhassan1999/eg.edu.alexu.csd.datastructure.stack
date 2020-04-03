package stake_classes;

import stake_interface.IStack;

public class stack implements IStack {
	private Node top;
	private int size;
	
	public stack() {
		top=null;
		size=0;
	}
	
	/**
	 *Removes the element at the top of stack and returns that element.
	 * @return top of stack element, or through exception if empty
	 */
	@Override
	public Object pop() throws RuntimeException {
		if(top==null)
			throw new RuntimeException(" stack is empty ");
		Object temp=top.get_element();
		top=top.get_next();
		size--;
		return temp;
	}

	/**
	 * Get the element at the top of stack without removing it from stack.
	 * @return top of stack element, or through exception if empty
	 */
	@Override
	public Object peek() throws RuntimeException  {
		if(top==null)
			throw new RuntimeException(" stack is empty ");		
		return top.get_element();
	}

	/**
	 * Pushes an item onto the top of this stack.
	 * @param element to insert
	 */
	@Override
	public void push(Object element) {
		Node push=new Node(element);
		push.set_next(top);
		top=push;
		size++;
	}

	/**
	 * Tests if this stack is empty 
	 * @return true if stack empty
	 */
	@Override
	public boolean isEmpty() {
		if(top==null)
			return true;
		return false;
	}

	/**
	 * Returns the number of elements in the stack.
	 * @return number of elements in the stack
	 */
	@Override
	public int size() {		
		return size;
	}

}
