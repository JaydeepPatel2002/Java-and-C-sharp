
public class Node<T extends Comparable<T>> 
implements Comparable<Node<T>>{
	private T data;
	private Node<T> next;
	public Node(T data) 
	{ 
		this.data = data;
		this.next = null;
	}
	
	public T Data() { return this.data;}
	public void setData(T data) {this.data = data;}
	public Node<T> getNext() {return this.next;}
	public void setNext(Node<T> next) {this.next = next;}

	@Override
	public int compareTo(Node<T> o) {
		// TODO Auto-generated method stub
		return this.data.compareTo(o.data);
	}
	
	@Override
	public String toString()
	{
		return String.format("[%s]", data.toString());
	}
}
