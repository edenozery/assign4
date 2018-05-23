

public class BTree {
	private BTreeNode root;
	private int t;
	
	public BTree(int t)
	{
		this.t = t; 
		root = new BTreeNode(t); // creating the root node
		root.setLeaf(true);
	}
	
	public void insert(String toInsert)
	{
		Link p= new Link(toInsert);
		insert(p);
	}
	
	public void insert(Link toInsert)
	{
		BTreeNode node = root;
		while(!node.isLeaf()) // finding the leaf needed
		{
			if(node.isFull()){
				splitNode(node); // splitting the node if it's full
				node = node.getFather();
			}
			else{
				node = node.getCorrectChild(toInsert.getData());
			}
		}
		
		if(!node.isFull()) // node is the leaf
			node.insert(toInsert);
		else{
			splitNode(node);
			node = node.getFather().getCorrectChild(toInsert.getData());
			node.insert(toInsert);
		}
	}
	
	public boolean search(String key)
	{
		BTreeNode node = root;
		while(!node.isLeaf())
		{
			Link ans = node.search(key);
			if(ans==null)
				node=node.getFirstChild();
			else if(ans.getData().equals(key))
				return true;
			else
				node = ans.getChild();
		}
		if(node.search(key)!=null && node.search(key).getData().equals(key))
			return true;
		return false;
	}
	
	
	public void splitNode(BTreeNode node)
	{
		
		Link middleLink = node.getMiddle();
		BTreeNode newNode = new BTreeNode(t,middleLink.getNext(),middleLink.getChild(),node.getFather()); // the new node we create(the right node of the split), 'node' is the original
		middleLink.setNext(null); // "cutting" the links after the middle
		node.getPreMiddle().setNext(null); // "cutting" the links after the PreMiddle
		node.updateMiddle(); // updating the middle and the PreMiddle
		node.setMinSize(); // updating the size of the node to be t-1
		middleLink.setChild(newNode); // setting the new node to be the child of the middle
		
		if(node==root){
			root = new BTreeNode(t);
			root.insert(middleLink);
			root.setFirstChild(node);
			node.setFather(root);
			newNode.setFather(root);
		}
		else{
			newNode.setFather(node.getFather());
			node.getFather().insert(middleLink);
		}
		
	}
}
