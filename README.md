# Dynamic Memory Allocator

<b>Problem Statement: </b> <br />
<b>·</b> Implement a memory allocation system that dynamically allocates and frees memory blocks using First Fit and Best Fit algorithms. The system should handle fragmentation through periodic defragmentation.

<b>Objective: </b> <br />
<b>·</b> Design and implement an efficient memory allocation system to manage dynamic memory allocation, optimize space utilization, and minimize fragmentation. Utilize doubly linked lists, binary search trees, and AVL trees for storing and managing memory blocks.

<b>Constraints:</b> <br />
<b>·</b> Memory size ranges from 10 million to 100 million.<br />
<b>·</b> Each memory address is represented as 1 Byte.<br />
<b>·</b> Dynamic memory allocation is used with variants of the First Fit and Best Fit algorithms.<br />
<b>·</b> Fragmentation is handled through periodic defragmentation.<br />

<b>Complexity: </b> <br />
<b>·</b> Doubly linked list implementation: O(n) for Insert, Delete, Find, getFirst, and getNext operations.<br />
<b>·</b> Binary search tree implementation: O(log n) for Allocate and Free operations.<br />
<b>·</b> AVL tree implementation: O(log n) for Allocate, Free, and Defragment operations.<br />
