# Dynamic Memory Allocator

<b>Problem Statement: </b> <br />
Implement a memory allocation system that dynamically allocates and frees memory blocks using First Fit and Best Fit algorithms. The system should handle fragmentation through periodic defragmentation.

<b>Objective: </b> <br />
Design and implement an efficient memory allocation system to manage dynamic memory allocation, optimize space utilization, and minimize fragmentation. Utilize doubly linked lists, binary search trees, and AVL trees for storing and managing memory blocks.

<b>Constraints:</b> <br />
Memory size ranges from 10 million to 100 million.<br />
Each memory address is represented as 1 Byte.<br />
Dynamic memory allocation is used with variants of the First Fit and Best Fit algorithms.<br />
Fragmentation is handled through periodic defragmentation.<br />

<b>Complexity: </b> <br />
Doubly linked list implementation: O(n) for Insert, Delete, Find, getFirst, and getNext operations.<br />
Binary search tree implementation: O(log n) for Allocate and Free operations.<br />
AVL tree implementation: O(log n) for Allocate, Free, and Defragment operations.<br />
