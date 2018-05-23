
public class Test {

	public static void main(String[] args) {
		
			    BTree B = new BTree(2);
				B.insert("A");
				B.insert("B");
				B.insert("C");
				B.insert("D");
				B.insert("G");
				B.insert("H");
				B.insert("K");
				B.insert("M");
				B.insert("R");
				B.insert("W");
				B.insert("Z");
                System.out.println("D#B|H,M#A|C^G|K|R,W,Z");
				System.out.println(B.toString());
	}

}
