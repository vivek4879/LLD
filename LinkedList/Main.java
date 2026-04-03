package LinkedList;



public class Main{
    public static void main(String[] args){
        LinkedList myLinkedList = new LinkedList();
        myLinkedList.displayList();
        myLinkedList.insertHead(0);
        myLinkedList.displayList();
        myLinkedList.insertTail(1);
        myLinkedList.displayList();
        myLinkedList.insertTail(2);
        myLinkedList.displayList();
        myLinkedList.deleteAtIndex(9);
        myLinkedList.deleteAtIndex(myLinkedList.getSize()-1);
        myLinkedList.displayList();
    }
}



//   - Create a list, insert a few values at head/tail/index
//   - Display it — does the order look right?
//   - Delete from head, tail, by index, by value
//   - Display again — is the node gone?
//   - Call getSize() — does it match what you expect?
//   - Try contains() for a value that exists and one that doesn't
//   - Try an edge case — delete from a 1-element list, call getValueByIndex on an empty list (should throw)