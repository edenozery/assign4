

public class BTreeNode {
	public int n;
	private LinkedList keys; //array of all the names
	private BTreeNode firstChild;
	private boolean leaf;
	private int t;
	private Link middle; // will need it for the split function
	private Link preMiddle; // will need it for the split function
	private BTreeNode father;
	
	
	public BTreeNode(int t) // creating an empty node
	{
		this.t = t;
		keys = new LinkedList();
		this.n = 0;
		middle = null;
		preMiddle = null;
		firstChild = null;
		father = null;
	}
	
	public BTreeNode(int t, Link first, BTreeNode firstChild, BTreeNode father) // creating the node after splitting(getting the first key of the linked list)
	{
		this.t = t;
		keys = new LinkedList(first); // creating the list with the first link(and the links after it)
		this.n = t-1; // because the node is splitting
		middle = null;
		preMiddle = null;
		updateMiddle(); // updating the middle and preMiddle fields
		this.father = father;
		
		if(firstChild != null){ // updating the firstChild and the leaf
			this.firstChild = firstChild;
			leaf = false;
		}
		else
			leaf = true;
	}
	
	public boolean insert(Link toInsert)
	{
		if(n==t+t-1)
			return false;
		
		keys.add(toInsert); // adding the key to the list
		n++; // updating the number of keys
		
		if(n%2==1) // updating the middle and preMiddle fields
		{
			if(n==1)
				middle=keys.getFirst();
			else{
				preMiddle=middle;
				middle=middle.getNext();
			}
		}
		return true;
	}
	
	public boolean isLeaf()
	{
		return leaf;
	}
	
	public void setLeaf(boolean input)
	{
		leaf = input;
	}
	
	public Link getMiddle()
	{
		return middle;
	}
	
	public Link getPreMiddle()
	{
		return preMiddle;
	}
	
	public void setMinSize() // putting t-1 at the n field
	{
		n = t-1;
	}
	
	public boolean isFull()
	{
		return (n==t+t-1);
	}
	
	public void setFirstChild(BTreeNode firstChild)
	{
		this.firstChild = firstChild;
	}
	
	public BTreeNode getFirstChild()
	{
		return firstChild;
	}
	
	public BTreeNode getFather()
	{
		return father;
	}
	
	public void setFather(BTreeNode father)
	{
		this.father = father;
	}
	
	public Link search(String key)
	{
		if(key.compareTo(keys.getFirst().getData())<0)
			return null;
		
		Link p = keys.getFirst();
		while(p.getNext()!=null && key.compareTo(p.getNext().getData())>0)
			p=p.getNext();
		
		return p;
	}
	
	
	
	
	public BTreeNode getCorrectChild(String data)
	{
		if(data.compareTo(keys.getFirst().getData())<0) // checking if the child is the first
			return firstChild;
		
		return keys.getCorrectChild(data);
	}
	
	public void updateMiddle() // updating the preMiddle and the middle fields(happens after splitting)
	{
		if(t==2|t==3)
			middle = keys.getFirst(); // and the preMiddle stays null
		else{
			Link p = keys.getFirst(); 
			for(int i=1;i<(t/2)-1;i++) // finding the preMiddle
				p=p.getNext();
			preMiddle = p;
			middle = preMiddle.getNext();
		}
	}
}