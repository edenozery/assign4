

public class LinkedList {	
	private Link first;
	
	public LinkedList()
	{
		first = null;
	}
	
	public LinkedList(Link first)
	{
		this.first = first;
	}
	
	public void add(Link toAdd)
	{
		if(first==null)
			first = toAdd;
		else
		{
			if(compare(toAdd.getData(),first.getData())==1) // if needed to add before the first
			{
				Link temp = first;
				first = toAdd;
				first.setNext(temp);
			}
			else
			{
				Link p = first;
				while(p.getNext() != null && compare(toAdd.getData(),p.getNext().getData())==2)
				{
					p = p.getNext();
				}
				
				Link temp = p.getNext();
				p.setNext(toAdd);
				p.getNext().setNext(temp);
			}
		}
	}
	
	public Link getFirst()
	{
		return first;
	}
	
	public BTreeNode getCorrectChild(String data)
	{
		Link p = first;
		while(p.getNext() != null && data.compareTo(p.getNext().getData())>0)
		{
			p = p.getNext();
		}
		
		return p.getChild();
	}
	
	
	
	
	
	
	static int compare(String name1, String name2)
	{
		int index = 0;
		while(index<name1.length() && index<name2.length())
		{
			if(name1.charAt(index)<name2.charAt(index))
				return 1;
			if(name1.charAt(index)>name2.charAt(index))
				return 2;
			
			index++;
		}
		if(name1.length()<name2.length())
			return 1;
		else
			return 2;
	}
}
