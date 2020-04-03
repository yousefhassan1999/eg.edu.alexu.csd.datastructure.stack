package stake_classes;

public class Node {
	private Object data;
	private Node next;
	
	public Node(Object element) {
		data=element;
		next=null;
	}
	 
	public Object get_element() {
		return data;
	}
	
	public Node get_next() {
		return next;
	}
	
	public void set_element(Object new_data) {
		data=new_data;
	}
	
	public void set_next(Node new_next) {
		next=new_next;
	}
}
