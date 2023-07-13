// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).

    public int Allocate(int blockSize) {
    	Dictionary temp;
    	//System.out.println("Allocate: "+blockSize);
    	//blocksize cannot be 0 or negative
    	if(blockSize < 1) {
    		return -1;
    	}
    	//find a valid element in the freeBlk dictionary
    	temp = freeBlk.Find(blockSize, false);
    	if (temp == null)return -1;
    	//if it is of exact size
    	
    	/*
    	System.out.print("FreeBlk bf : ");
    	for(Dictionary d = freeBlk.getFirst(); d != null; d = d.getNext()) {
    		System.out.print(d.address+" "+(d.address+d.size)+"    ");
    	}
    	System.out.println();
    	System.out.print("AllocBlk bf : ");
    	for(Dictionary d = allocBlk.getFirst(); d != null; d = d.getNext()) {
    		System.out.print(d.address+" "+(d.address+d.size)+"    ");
    	}
    	System.out.println();*/
    	if(temp.size == blockSize) {
    		allocBlk.Insert(temp.address, blockSize, temp.address);
    		freeBlk.Delete(temp);
    		return temp.address;
    	}
    	//if it's size is greater than the block size
    	allocBlk.Insert(temp.address, blockSize, temp.address);
    	freeBlk.Insert(temp.address+blockSize, temp.size-blockSize, temp.size-blockSize);
    	freeBlk.Delete(temp);
    	/*
    	System.out.print("FreeBlk af : ");
    	for(Dictionary d = freeBlk.getFirst(); d != null; d = d.getNext()) {
    		System.out.print(d.address+" "+(d.address+d.size)+"    ");
    	}
    	System.out.println();
    	System.out.print("AllocBlk af : ");
    	for(Dictionary d = allocBlk.getFirst(); d != null; d = d.getNext()) {
    		System.out.print(d.address+" "+(d.address+d.size)+"    ");
    	}
    	System.out.println();*/
        return temp.address;
    } 
    
    public int Free(int startAddr) {
    	Dictionary temp;
    	//System.out.println("Free: "+startAddr);
    	//find the element in the allocBlk with the given starting address
    	
    	/*
    	System.out.print("FreeBlk bf : ");
    	for(Dictionary d = freeBlk.getFirst(); d != null; d = d.getNext()) {
    		System.out.print(d.address+" "+(d.address+d.size)+"    ");
    	}
    	System.out.println();
    	System.out.print("AllocBlk bf : ");
    	for(Dictionary d = allocBlk.getFirst(); d != null; d = d.getNext()) {
    		System.out.print(d.address+" "+(d.address+d.size)+"    ");
    	}
    	System.out.println();*/
    	
    	
    	
    	temp = allocBlk.Find(startAddr, true);
    	//return -1 if not found
    	if(temp == null)return -1;
    	freeBlk.Insert(startAddr, temp.size, temp.size);
    	allocBlk.Delete(temp);
    	
    	/*
    	System.out.print("FreeBlk af : ");
    	for(Dictionary d = freeBlk.getFirst(); d != null; d = d.getNext()) {
    		System.out.print(d.address+" "+(d.address+d.size)+"    ");
    	}
    	System.out.println();
    	System.out.print("AllocBlk af : ");
    	for(Dictionary d = allocBlk.getFirst(); d != null; d = d.getNext()) {
    		System.out.print(d.address+" "+(d.address+d.size)+"    ");
    	}
    	System.out.println();*/
    	
    	
        return 0;
    }
    
}