package DynamicArray;


public class DynamicArray {
    private int[] data;
    private int capacity;
    private int size;

    public DynamicArray(int inputCapacity){
        if(inputCapacity <=0)throw new IllegalArgumentException();
        this.size = 0;
        this.capacity = inputCapacity;
        this.data = new int[capacity];

    }

    //will add element at the end
    public void addElement(int cur){
        if(size == capacity){
            int[] newArray = new int[capacity * 2];
            for(int i = 0; i < size; i++){
                newArray[i] = data[i];
            }
            data = newArray;
            capacity = capacity * 2;
        }
        data[size] = cur;
        size++;
        

    }
    
    //will remove element at the end
    public int removeElement(){
        if(size == 0)throw new IllegalStateException();
        int removed = data[size - 1];
        size = size -1;
        return removed;

    }

    //get element ata particular index
    public int getElementAtIndex(int index){
        if(index >= size || index <0){
            throw new IndexOutOfBoundsException("Index our of bounds"); 
        }
        return data[index];
    }


    //returns the size of the array
    public int arrayLength(){
        return size;
    }


}


