// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.

    public BSTree(){
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }

    public BSTree(int address, int size, int key){
        super(address, size, key);
    }

    public BSTree Insert(int address, int size, int key)
    {
    	BSTree new_node = new BSTree(address, size, key);
    	BSTree temp = this;
    	while(temp.parent != null)temp = temp.parent;
    	if(temp.right == null) {
    		temp.right = new_node;
    		new_node.parent = temp;
    		return new_node;
    	}
    	temp = temp.right;
    	while(true) {
    		if(new_node.key < temp.key) {
    			if(temp.left == null) {
    				temp.left = new_node;
    				new_node.parent = temp;
    				return new_node;
    			}
    			else temp = temp.left;
    		}
    		else if(new_node.key == temp.key) {
    			if(new_node.address <= temp.address) {
    				if(temp.left == null) {
    					temp.left = new_node;
    					new_node.parent = temp;
    					return new_node;
    				}else temp = temp.left;
    			}else {
    				if(temp.right == null) {
    					temp.right = new_node;
    					new_node.parent = temp;
    					return new_node;
    				}else temp = temp.right;
    			}
    		}else {
    			if(temp.right == null) {
    				temp.right = new_node;
    				new_node.parent = temp;
    				return new_node;
    			}else temp = temp.right;
    		}
    	}
    }
    
    
    public boolean Delete(Dictionary e)
    {
    	BSTree temp = this;
    	while(temp.parent != null)temp = temp.parent;
    	temp = temp.right;
    	if(temp == null)return false;
    	while(true) {
    		if(temp.key == e.key) {
    			if(temp == e) {
    				if(temp.left == null && temp.right == null) {
    		    		if(temp.parent.left == temp)temp.parent.left = null;
    		    		else {
    		    			temp.parent.right = null;
    		    		}
    		    		return true;
    		    	}
    		    	else if(temp.left == null) {
    		    		if(temp.parent.left == temp) {
    		    			temp.right.parent = temp.parent;
    		    			temp.parent.left = temp.right;
    		    		}else {
    		    			temp.right.parent = temp.parent;
    		    			temp.parent.right = temp.right;
    		    		}
    		    		return true;
    		    	}
    		    	else if(temp.right == null) {
    		    		if(temp.parent.left == temp) {
    		    			temp.left.parent = temp.parent;
    		    			temp.parent.left = temp.left;
    		    		}else {
    		    			temp.left.parent = temp.parent;
    		    			temp.parent.right = temp.left;
    		    		}
    		    		return true;
    		    	}else {
    		    		BSTree found_next = temp.getNext();
    		        	BSTree temp1 = temp.right;
    		        	while(temp1.left != null)temp1 = temp1.left;
    		        	if(temp1.right == null) {
    		        		if(temp1.parent.left == temp1)temp1.parent.left = null;
    		        		else temp1.parent.right = null;
    		        	}
    		        	else {
    		        		if(temp1.parent.left == temp1) {
    		        			temp1.right.parent = temp1.parent;
    		        			temp1.parent.left = temp1.right;
    		        		}else {
    		        			temp1.right.parent = temp1.parent;
    		        			temp1.parent.right = temp1.right;
    		        		}
    		        	}
    		        	found_next.parent = temp.parent;
    		        	found_next.left = temp.left;
    		        	found_next.right = temp.right;
    		        	if(temp.parent.left == temp)temp.parent.left = found_next;
    		        	else temp.parent.right = found_next;
    		        	if(temp.left != null)temp.left.parent = found_next;
    		        	if(temp.right != null)temp.right.parent = found_next;
    		        	return true;
    		        	
    		    	}
    			}else if(e.address <=  temp.address) {
    				if(temp.left == null)return false;
    				temp = temp.left;
    			}else {
    				if(temp.right == null)return false;
    				temp = temp.right;
    			}
    		}else if(e.key < temp.key) {
    			if(temp.left == null)return false;
    			temp = temp.left;
    		}else {
    			if(temp.right == null)return false;
    			temp = temp.right;
    		}
    	}
    }

    public BSTree Find(int key, boolean exact)
    {
    	BSTree temp = this;
    	while(temp.parent != null)temp = temp.parent;
    	temp = temp.right;
    	if(temp == null) {
    		return null;
    	}
    	if(exact == true) {
    		temp = temp.getFirst();
    		while(temp != null && temp.key < key) {
    			temp = temp.getNext();
    		}
    		if(temp == null)return null;
    		if(temp.key == key)return temp;
    		else return null;
    		
    	}else {
    		temp = temp.getFirst();
    		while(temp != null && temp.key < key) {
    			temp = temp.getNext();
    		}
    		if(temp == null)return null;
    		return temp;

    	}


    }

    public BSTree getFirst()
    {
    	BSTree temp = this;
    	while(temp.parent != null)temp = temp.parent;
    	temp = temp.right;
    	if(temp == null)return null;
    	while(temp.left != null)temp = temp.left;
    	return temp;
    }

    public BSTree getNext()
    {
    	BSTree temp = this;
    	if(temp.parent == null)return null;
    	if(temp.right != null) {
    		temp = temp.right;
    		while(temp.left != null) {
    			temp = temp.left;
    		}
    		return temp;
    	}
        BSTree temp_par = temp.parent;
        while(temp_par != null && temp_par.right == temp) {
        	temp = temp_par;
        	temp_par = temp_par.parent;
        }
        if(temp_par == null)return null;
        if(temp_par.parent == null)return null;
        return temp_par;
    }

    public boolean sanity()
    {
    	BSTree temp_san = this;
    	for(BSTree d = temp_san.getFirst(); d!= null;) {
    		BSTree d1 = d.getNext();
    		if(d1 == null)break;
    		if(d1.key < d.key)return false;
    		else if(d1.key == d.key) {
    			if(d1.address < d.address) {
    				return false;
    			}
    		}
    		d = d.getNext();
    	}
    	
        return true;
    }
    
    
}
