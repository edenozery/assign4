

public class Link {
	private String data;
	private Link next;
	private BTreeNode child; // will be the subTree between this link and the next
	
	public Link(String data)
	{
		this.data = data;
		next = null;
		child = null;
	}
	
	public void setNext(Link next)
	{
		this.next = next;
	}
	
	public Link getNext()
	{
		return next;
	}
	
	public String getData()
	{
		return data;
	}
	
	public BTreeNode getChild()
	{
		return child;
	}
	
	public void setChild(BTreeNode node)
	{
		child = node;
	}
}
