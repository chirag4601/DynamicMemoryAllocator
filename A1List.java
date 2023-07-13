// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    public A1List next; // Next Node
    public A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel
        
        A1List tailSentinel = new A1List(-1,-1,-1); // Initiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {
    	//create a new A1List to inserted
        A1List new_list = new A1List(address, size, key);
        //change the corresponding pointers
        new_list.next = this.next;
        this.next = new_list;
        new_list.prev = this;
        new_list.next.prev = new_list;
        
        return new_list;
    }

    public boolean Delete(Dictionary d) 
    {
    	A1List temp = this;
    	//check if the current element is the required element
    	if(temp.key == d.key) {
    		if(temp == d) {
    			temp.next.prev = temp.prev;
    			temp.prev.next = temp.next;
    			return true;
    		}
    	}
    	A1List temp_af = temp.next;//it will traverse the list towards right
    	A1List temp_bf = temp.prev;//it will traverse the list towards left
    	//check if the required element is to the right of the current element
    	while(temp_af != null && temp_af.key != -1 && temp_af.address != -1) {
    		if(temp_af.key == d.key) {
        		if(temp_af == d) {
        			temp_af.next.prev = temp_af.prev;
        			temp_af.prev.next = temp_af.next;
        			return true;
        		}
    		}
    	
    		temp_af = temp_af.next;
    	}
    	//check if the required element is to the left of the current element
    	while(temp_bf != null && temp_bf.key != -1 && temp_bf.address != -1) {
    		if(temp_bf.key == d.key) {
        		if(temp_bf == d) {
        			temp_bf.next.prev = temp_bf.prev;
        			temp_bf.prev.next = temp_bf.next;
        			return true;
        		}
    		}
    	
    		temp_bf = temp_bf.prev;
    	}
    	//if not found return false
        return false;
    }

    public A1List Find(int k, boolean exact)
    { 
    	A1List temp = this;
    	//if exact is true, search for the exact key match
    	if(exact == true) {
    		//check the current element
    		if(temp != null && temp.key != -1 && temp.address != -1 && temp.key == k) {
        		return temp;
        	}
    		A1List temp_bf = temp.prev;
    		temp = temp.next;
    		//check towards right
        	while(temp != null && temp.key != -1 && temp.address != -1) {
        		if(temp.key == k) {
            		return temp;
        		}
        		temp = temp.next;
        	}
        	//check towards left
        	while(temp_bf != null && temp_bf.key != -1 && temp_bf.address != -1) {
        		if(temp_bf.key == k) {
            		return temp_bf;
        		}
        		temp_bf = temp_bf.prev;
        	}
    		
    	}
    	//if exact is false, search key with value >= k
    	if(exact == false) {
        	A1List temp_bf = temp.prev;
        	//check the current element
        	if(temp != null && temp.key != -1 && temp.address != -1 && temp.key >= k) {
        		return temp;
        	}
        	temp = temp.next;
        	//traverse the list towards right of the current element
        	while(temp != null && temp.key != -1 && temp.address != -1) {
        		if(temp.key >= k) {
            		return temp;
        		}
        	
        		temp = temp.next;
        	}
        	//traverse the list towards left if element is not found towards right
        	while(temp_bf != null && temp_bf.key != -1 && temp_bf.address != -1) {
        		if(temp_bf.key >= k) {
            		return temp_bf;
        		}
        		temp_bf = temp_bf.prev;
        	}
    		
    	}
    	//if not found, then return null
        return null;
    }

    public A1List getFirst()
    {
    	A1List temp = this;
    	//check if the current element is head sentinel
    	if(this.address == -1 && this.prev == null) {
    		//if it is head sentinel, and next element is not the tail sentinel, then return the next element
    		if(this.next.address != -1) {
    			return this.next;
    		}else {
    			return null;
    		}
    		//check if the current element is the tail sentinel
    	}else if(this.address == -1 && this.next == null) {
    		//if it is tail sentinel, and just previous element is the head sentinel, then return null, as the list is empty
    		if(this.prev.address == -1) {
    			return null;
    		}
    	}
    	//if the current element is neither head nor tail, traverse towards left till the previous element is head sentinel
    	while(temp.prev.address != -1 || temp.prev.prev != null) {
    		temp = temp.prev;
    	}
    	//return the element
        return temp;
    }
    
    public A1List getNext() 
    {
    	A1List temp = this;
    	//if the current element is tail sentinel, the return null
    	if(temp.address == -1 && temp.next == null) {
    		return null;
    	}
    	//if the next element is tail sentinel, the return null
    	if(temp.next.address == -1 && temp.next.next == null) {
    		return null;
    	}
    	//else return the next element
    	return this.next;
    }

    public boolean sanity()
    {
    	A1List temp = this;
    	//check if the current element is a sentinel 
    	if(temp.key == -1 && temp.address == -1 && temp.size == -1) {
    		//Either next or previous element should be null
    		if(temp.next != null && temp.prev != null) {
    			return false;
    			//if current element is head sentinel
    		}else if(temp.prev == null) {
    			//For any node, which is not head or tail if node.next.prev != node
    			//traverse towards right of current node
    			if(temp.next.prev != temp)return false;
    			temp = temp.next;
    			while(temp.key != -1 && temp.address != -1 && temp.size != -1) {
    				if(temp.next.prev != temp)return false;
    				temp = temp.next;
    			}
    			if(temp.next != null)return false;
    			//if current element is tail sentinel
    		}else if(temp.next == null) {
    			if(temp.prev.next != temp) return false;
    			//traverse towards left of current node
    			temp = temp.prev;
    			while(temp.key != -1 && temp.address != -1 && temp.size != -1) {
    				if(temp.prev.next != temp)return false;
    				temp = temp.prev;
    			}
    			if(temp.prev != null)return false;
    		}
    	}else {
    		//if the current element is not sentinel
    		A1List temp1 = temp.prev;
    		if(temp.prev.next != temp)return false;
    		//traverse towards right
    		while(temp.key != -1 && temp.address != -1 && temp.size != -1) {
    			if(temp.next.prev != temp)return false;
    			temp = temp.next;
    		}
    		if(temp.next != null)return false;
    		//traverse towards left
    		while(temp1.key != -1 && temp1.address != -1 && temp1.size != -1) {
    			if(temp1.prev.next != temp1)return false;
    			temp1 = temp1.prev;
    		}
    		if(temp.prev != null)return false;
    	}
    	return true;
    }

}