// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }
    public A2DynamicMem(int size, int dict_type) { 
    	
    	super(size, dict_type);
    }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees.

    public void Defragment() {
    	//System.out.println("Defragment called");
    	Dictionary for_defrag;
    	if(this.type == 2)for_defrag = new BSTree();
    	else for_defrag = new AVLTree();
    	for(Dictionary d = freeBlk.getFirst(); d != null; d = d.getNext()) {
    		for_defrag.Insert(d.address, d.size, d.address);
    	}
    	for(Dictionary d= for_defrag.getFirst(); d != null;) {
    		Dictionary d1 = d.getNext();
    		if(d1 == null)break;
    		if(d.address+d.size == d1.address) {
    			for_defrag.Delete(d1);
    			for_defrag.Delete(d);
    			for_defrag.Insert(d.address, d.size+d1.size, d.address);
    			
    			d = for_defrag.getFirst();
    		}else {
    			d = d.getNext();
    		}
    	}
    	while(freeBlk.getFirst() != null) {
    		freeBlk.Delete(freeBlk.getFirst());
    	}
    	for(Dictionary d = for_defrag.getFirst(); d != null; d = d.getNext()) {
    		freeBlk.Insert(d.address, d.size, d.size);
    	}
    	
    	
    	
        return;
    }
}