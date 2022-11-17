
public class LinkedList<T extends Comparable<T>> {
	private Node<T> head;
	
	public LinkedList()
	{
		head = null;//Empty list
	}
	
	public void addHead(T data)
	{
		//Add before head
		if(head == null)
		{
			head = new Node<T>(data);
		}
		else
		{
			Node<T> node = new Node<>(data);
			node.setNext(head);
			head = node;
		}
	}
	
	public void addTail(T data)
	{
		//Add after the tail
		if(head==null)
		{
			addHead(data);
		}
		else 
		{
			Node<T> node = head;
			while(node.getNext()!=null)
			{
				node = node.getNext();
			}
			node.setNext(new Node<T>(data));
			
		}
	}
	
	public void insert(T data, Node<T> node)
	{
		//Add after node if node exist in the list
		if(head==null)
		{
			return; 
		}
		else
		{
			Node<T> anode = head;
			while(anode.getNext()!=null && anode!=node)
			{
				anode = anode.getNext();
			}
			if(anode.getNext()==null)
			{
				addTail(data);
			}
			else
			{
				Node<T> newNode = new Node<>(data);
				newNode.setNext(anode.getNext());
				anode.setNext(newNode);
			}
		}
	}
	
	public T removeHead()
	{
		if(head==null)
		{
			return null; 
		}
		else
		{
			Node<T> node = head;
			head = head.getNext();
			return node.Data();
		}
	}
	
	public T removeTail()
	{
		if(head==null)
		{
			return null; 
		}
		else
		{
			if(head.getNext()==null)
			{
				return removeHead();
			}
			else
			{
				Node<T> node = head;
				Node<T> nodeBefore = null;
				while(node.getNext()!=null)
				{
					nodeBefore =  node;
					node = node.getNext();
				}
				nodeBefore.setNext(null);
				return node.Data();
			}
		}
	}
	
	public T remove(Node<T> node)
	{
		//Remove the node if it is existing
		if(head==null)
		{
			return null; 
		}
		else
		{
			Node<T> anode = head;
			Node<T> nodeBefore = null;
			while(anode.getNext()!=null && anode!=node)
			{
				nodeBefore =  anode;
				anode = anode.getNext();
			}
			if(anode!=node)
			{
				//At tail but no match
				return null;
			}
			else
			{
				nodeBefore.setNext(node.getNext());
				return node.Data();
			}
	
		}
	}

	public Node<T> search(T data)
	{
		if(head==null)
		{
			return null; 
		}
		else
		{
			Node<T> node = head;
			while(node.getNext()!=null)
			{
				if(node.compareTo(new Node<T>(data))==0)
				{
					return node;
				}
				node = node.getNext();
			}
			if(node.compareTo(new Node<T>(data))==0)
			{
				return node;
			}
			else
			{
				return null;
			}
		}
		
	}
	
	@Override
	public String toString()
	{
		StringBuilder oSB = new StringBuilder();
		Node<T> node = head;
		while(node.getNext()!=null)
		{
			oSB.append(node);
			node = node.getNext();
		}
		oSB.append(node);
		return oSB.toString();
	}
	
	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<>();
		list.addHead("head");
		list.addTail("1");
		list.addTail("2");
		list.addTail("3");
		list.addTail("tail");
		System.out.println(list);
		Node<String> node = list.search("2");
		list.insert("2.5", node);
		System.out.println(list);
		list.removeHead();
		System.out.println(list);
		list.removeTail();
		System.out.println(list);
		node = list.search("2");
		list.remove(node);
		System.out.println(list);
	}
}

