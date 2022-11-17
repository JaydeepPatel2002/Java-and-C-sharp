import java.util.function.Predicate;

public class LinkedListR<T extends Comparable<T>> {
	private Node<T> head;
	
	public LinkedListR()
	{
		head = null;
	}
	
	public int count()
	{
		return count(head);
	}
	private int count(Node<T> cur)
	{
		if(cur==null) return 0;
		return 1+ count(cur.getNext());
	}
	
	public T tail()
	{
		if(this.head==null) return null;
		return tail(head);
	}
	
	private T tail(Node<T> cur)
	{
		if(cur.getNext()==null)
		{
			return cur.Data();
		}
		return tail(cur.getNext());
	}
	
	public LinkedListR<T> reverse()
	{
		if(this.head==null) return null;
		LinkedListR<T> rs = new LinkedListR<>();
		resverse(this.head,rs);
		return rs;
	}
	
	private void resverse(Node<T> cur, LinkedListR<T> rs)
	{
		if(cur == null) return;
		resverse(cur.getNext(),rs);
		rs.add(cur.Data());
		return;
	}
	
	
	//Add to the tail
	public void add(T data)
	{
		this.head = add(this.head,new Node<T>(data));
	}
	
	private Node<T> add(Node<T> cur, Node<T> node)
	{
		if(cur==null)
		{
			return node;
		}
		else
		{
			cur.setNext(add(cur.getNext(),node));
			return cur;
		}
	}
	
	public Node<T> find(T data)
	{
		//This is home work
			
	}
	
	public void delete(T data)
	{
		this.head = delete(this.head,data);
	}
	
	private Node<T> delete(Node<T> cur, T data)
	{
		if(cur==null)
		{
			return null;
		}
		if(cur.Data().equals(data))
		{
			return cur.getNext();
		}
		else
		{
			cur.setNext(delete(cur.getNext(),data));
			return cur;
		}
	}
	
	public LinkedListR<T> sublist(Predicate<T> p)
	{
		if(this.head==null) return null;
		LinkedListR<T> rs = new LinkedListR<>();
		sublist(this.head,p,rs);
		return rs;
	}
	
	private void sublist(Node<T> cur, Predicate<T> p,LinkedListR<T> rs)
	{
		
	}
}

