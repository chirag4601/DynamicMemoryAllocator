// Class: Height balanced AVL Tree
// Binary Search Tree

public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.
    private int height(AVLTree node) { 
        if (node == null) 
            return -1; 
  
        return node.height; 
    }
    private AVLTree rightRotate(AVLTree y) { 
        AVLTree x = y.left; 
        AVLTree T2 = null;
        if(x != null)T2 = x.right; 
        if(x != null)x.right = y;
        y.parent = x;
        y.left = T2;
        if(T2 != null)T2.parent = y;
  

        y.height = Math.max(height(y.left), height(y.right)) + 1; 
        if(x != null)x.height = Math.max(height(x.left), height(x.right)) + 1; 
  
        return x; 
    }
    private AVLTree leftRotate(AVLTree x) { 
    	AVLTree y = x.right; 
    	AVLTree T2 = null;
    	if(y != null)T2 = y.left; 
        if(y != null)y.left = x; 
        x.parent = y;
        x.right = T2; 
        if(T2 != null)T2.parent = x;
  
        x.height = Math.max(height(x.left), height(x.right)) + 1; 
        if(y != null)y.height = Math.max(height(y.left), height(y.right)) + 1; 
  
        return y; 
    }
    private int getBalance(AVLTree node) { 
        if (node == null) 
            return 0; 
  
        return height(node.left)-height(node.right);  
    }
    private AVLTree insert(AVLTree node, int key, int address, int size) { 
    	  
        if (node == null) 
            return (new AVLTree(address, size, key)); 
  
        if (key < node.key) {
            node.left = insert(node.left, key, address, size);
            if(node.left != null)node.left.parent = node;
        }
        
        else if (key > node.key) {
            node.right = insert(node.right, key, address, size); 
            if(node.right != null)node.right.parent = node;
        }
        
        else {
        	if(address <= node.address) {
        		node.left = insert(node.left, key, address, size);
        		if(node.left != null)node.left.parent = node;
        	}else {
        		node.right = insert(node.right, key, address, size);
        		if(node.right != null)node.right.parent = node;
        	}
        }
        node.height = 1 + Math.max(height(node.left), height(node.right)); 
        int balance = getBalance(node); 
        // Left Left Case 
        if (balance > 1 && key < node.left.key) 
            return rightRotate(node); 
        if (balance > 1 && (key == node.left.key && address <= node.left.address)) 
            return rightRotate(node);
        // Right Right Case 
        if (balance < -1 && key > node.right.key) 
            return leftRotate(node); 
        if (balance < -1 && (key == node.right.key && address > node.right.address)) 
            return leftRotate(node);
        // Left Right Case 
        if (balance > 1 && key > node.left.key) { 
            node.left = leftRotate(node.left); 
            return rightRotate(node); 
        }
        if (balance > 1 && (key == node.left.key && address > node.left.address)) { 
            node.left = leftRotate(node.left); 
            return rightRotate(node); 
        }
        // Right Left Case 
        if (balance < -1 && key < node.right.key) { 
            node.right = rightRotate(node.right); 
            return leftRotate(node); 
        }
        if (balance < -1 && (key == node.right.key && address <= node.right.address)) { 
            node.right = rightRotate(node.right); 
            return leftRotate(node); 
        }

        return node; 
    } 
    
    public AVLTree Insert(int address, int size, int key) 
    { 
    	
    	AVLTree temp = this;
    	while(temp.parent != null)temp = temp.parent;
    	if(temp.right == null) {
    		AVLTree new_node = new AVLTree(address, size, key);
    		temp.right = new_node;
    		new_node.parent = temp;
    		return new_node;
    	}
    	temp.right = insert(temp.right, key, address, size);
    	if(temp.right != null)temp.right.parent =temp;
        return null;
    }

    private AVLTree delete(AVLTree temp, Dictionary e)  
    {  
        if (temp == null)  
            return temp;  

        if (e.key < temp.key) {
            temp.left = delete(temp.left, e);  
        	if(temp.left != null)temp.left.parent = temp;
        }

        else if (e.key > temp.key) {
            temp.right = delete(temp.right, e);  
        	if(temp.right != null)temp.right.parent = temp;
        }

        else
        {  
        	if(temp == e) {
        		if(temp.left == null && temp.right == null) {
		    		if(temp.parent.left == temp) {
		    			temp.parent.left = null;
		    			temp = null;
		    		}
		    		else {
		    			temp.parent.right = null;
		    			temp = null;
		    		}
		    	}
		    	else if(temp.left == null) {
		    		if(temp.parent.left == temp) {
		    			temp.right.parent = temp.parent;
		    			temp.parent.left = temp.right;
		    			temp = temp.right;
		    		}else {
		    			temp.right.parent = temp.parent;
		    			temp.parent.right = temp.right;
		    			temp = temp.right;
		    		}
		    	}
		    	else if(temp.right == null) {
		    		if(temp.parent.left == temp) {
		    			temp.left.parent = temp.parent;
		    			temp.parent.left = temp.left;
		    			temp = temp.left;
		    		}else {
		    			temp.left.parent = temp.parent;
		    			temp.parent.right = temp.left;
		    			temp = temp.left;
		    		}
		    	}else {
		    		AVLTree found_next = temp.getNext();
		    		AVLTree temp1 = temp.right;
		        	while(temp1.left != null)temp1 = temp1.left;
		        	if(temp1.right == null) {
		        		if(temp1.parent.left == temp1) {
		        			temp1.parent.left = null;
		        			temp1 = null;
		        		}
		        		else {
		        			temp1.parent.right = null;
		        			temp1 = null;
		        		}
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
		        	temp = found_next;
		        	
		    	}
        	}else if(e.address <= temp.address) {
        		temp.left = delete(temp.left, e);
        		if(temp.left != null)temp.left.parent = temp;
        	}else {
        		temp.right = delete(temp.right, e);
        		if(temp.right != null)temp.right.parent = temp;
        	}
  
            
        }  
  
        if (temp == null)  
            return temp;  

        temp.height = Math.max(height(temp.left), height(temp.right)) + 1;  

        int balance = getBalance(temp);  
        
        // Left Left Case  
        if (balance > 1 && getBalance(temp.left) >= 0)  
            return rightRotate(temp);  
  
        // Left Right Case  
        if (balance > 1 && getBalance(temp.left) < 0)  
        {  
            temp.left = leftRotate(temp.left);  
            return rightRotate(temp);  
        }  
  
        // Right Right Case  
        if (balance < -1 && getBalance(temp.right) <= 0)  
            return leftRotate(temp);  
  
        // Right Left Case  
        if (balance < -1 && getBalance(temp.right) > 0)  
        {  
        	temp.right = rightRotate(temp.right);  
            return leftRotate(temp);  
        }  
  
        return temp;  
    }
    public boolean Delete(Dictionary e)
    {
    	AVLTree temp = this;
    	while(temp.parent != null)temp = temp.parent;
    	temp.right = delete(temp.right, e);
    	if(temp.right!=null)temp.right.parent = temp;
        return false;
    }
        
    public AVLTree Find(int k, boolean exact)
    { 
    	AVLTree temp = this;
    	while(temp.parent != null)temp = temp.parent;
    	temp = temp.right;
    	if(temp == null) {
    		return null;
    	}
    	if(exact == true) {
    		temp = temp.getFirst();
    		while(temp != null && temp.key < k) {
    			temp = temp.getNext();
    		}
    		if(temp == null)return null;
    		if(temp.key == k)return temp;
    		else return null;
    		
    	}else {
    		temp = temp.getFirst();
    		while(temp != null && temp.key < k) {
    			temp = temp.getNext();
    		}
    		if(temp == null)return null;
    		return temp;

    	}
    }

    public AVLTree getFirst()
    { 
    	AVLTree temp = this;
    	while(temp.parent != null)temp = temp.parent;
    	temp = temp.right;
    	if(temp == null)return null;
    	while(temp.left != null)temp = temp.left;
    	return temp;
    }

    public AVLTree getNext()
    {
    	AVLTree temp = this;
    	if(temp.parent == null)return null;
    	if(temp.right != null) {
    		temp = temp.right;
    		while(temp.left != null) {
    			temp = temp.left;
    		}
    		return temp;
    	}
        AVLTree temp_par = temp.parent;
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
    	AVLTree temp_san = this;
    	for(AVLTree d = temp_san.getFirst(); d!= null;) {
    		AVLTree d1 = d.getNext();
    		int x = getBalance(d);
    		int y;
    		if(d1 != null) {
    			y = getBalance(d1);
    			if(y > 1 || y <-1)return false;
    		}
    		if(x > 1 || x <-1)return false;
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


