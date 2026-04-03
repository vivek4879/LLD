package LinkedList;


public class LinkedList{
    private Node head;
    private int size;

    public LinkedList(){
        this.head = null;
        this.size = 0;
    }

    public LinkedList(int value){
        this.head = new Node(value, null);
        this.size = 1;
    }

    public void insertHead(int value){
        Node newNode = new Node(value,head);
        head = newNode;
        size++;
    }

    public void insertTail(int value){
        if(head == null){
            insertHead(value);
            return;
        }
        Node curr = head;
        while(curr.next != null){
            curr = curr.next;
        }
        curr.next = new Node(value,null);
        size++;
    }

    public boolean insertAtIndex(int value, int index){
        if(index > size || index < 0)return false;
        else if(index == 0){
            insertHead(value);
        }
        else if(index == size){
            insertTail(value);
        }
        else{
            Node curNode = head;
            Node nexNode = head.next;
            while(index>1){
                curNode = curNode.next;
                nexNode = nexNode.next;
                index--;
            }
            curNode.next = new Node(value,nexNode);
            size++;
        }
        return true;
    }

    public int deleteHead(){
        if(head == null){
            throw new IllegalStateException();
        }
        int returnVal = head.value;
        head = head.next;
        size--;
        return returnVal;
    }

    public int deleteTail(){
        if(head == null){
            throw new IllegalStateException();
        }
        else if(size == 1){
            return deleteHead();
        }
        else{
            Node curNode = head;
            Node prev = null;
            while(curNode.next != null){
                prev = curNode;
                curNode = curNode.next;
            }
            prev.next = null;
            size--;
            return curNode.value;
        }
    }

    public int deleteAtIndex(int index){
        if(head == null || index >= size || index <0){
            throw new IllegalStateException();
        }
        else if(index == size-1){
            return deleteTail();
        }
        else if(index == 0){
            return deleteHead();
        }
        else{
            Node curNode = head;
            Node prev = null;
            while(index > 0){
                prev = curNode;
                curNode = curNode.next;
                index--;
            }
            prev.next = curNode.next;
            size--;
            return curNode.value;
        }
    }

    public boolean deleteByValue(int value){
        if(head ==null){
            return false;
        }
        if(head.value == value){
            deleteHead();
            return true;
        }
        Node curNode = head.next;
        Node prev = head;
        while(curNode != null){
            if(curNode.value == value){
                prev.next = curNode.next;
                size--;
                return true;
            }
            prev = curNode;
            curNode = curNode.next;
        }

        return false;
    }

    public int getSize(){
        return size;

    }

    public int getValueByIndex(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException(); 
        }
        Node curNode = head;
        while(index > 0){
            curNode = curNode.next;
            index--;
        }
        return curNode.value;
    }

    public boolean contains(int value){
        Node curNode = head;

        while(curNode != null){
            if(curNode.value == value){
                return true;
            }
            curNode = curNode.next;
        }
        return false;
    }

    public void displayList(){
        Node curNode = head;

        while(curNode != null){
            System.out.print(curNode.value);
            System.out.print("->");
            curNode = curNode.next;
        }
        System.out.println("null");
    }


    private static class Node {
        int value;
        Node next;

        public Node(int value, Node next){
            this.value = value;
            this.next = next;
        }
    }
}