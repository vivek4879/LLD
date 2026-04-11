package Deque;

public class Deque<T>{

    private T[] array;
    private int head;
    private int tail;
    private int size;

    @SuppressWarnings("unchecked")
    public Deque(int capacity){
        this.array = (T[]) new Object[capacity];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return this.size;
    }

    public void addLast(T value){
        // if(size ==array.length)resize();
        array[tail] =  value;
        tail = (tail+1) % array.length;
        size++;
        

    }


}