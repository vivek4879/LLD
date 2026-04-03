Public class LinkedList



class Node
int value
Node next



//fields
Node head
int size

//will insert at head
public void insertHead(int value)

//insert at tail
public void insertTail(int value)

//insert at the given index
public void insertIndex(int index, int value)


//delete from head and return deleted value
public int deleteHead()

//delete from tail and return deleted value
public int deleteTail()

//delete node at given index and return value
public int deleteIndex(int Index)

//delete by value
public boolean deleteValue(int value)


//get node by index
public int getNode(int index)

//get node by value
public boolean contains(int value)

//get the size
public int getSize()

//display list
public void displayList()