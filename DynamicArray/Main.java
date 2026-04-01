package DynamicArray;

public class Main{
public static void main(String Args[]){
    DynamicArray dynamicArray = new DynamicArray(2);

    dynamicArray.addElement(2);
    dynamicArray.addElement(3);
    dynamicArray.addElement(4);
    System.out.println("length is " + dynamicArray.arrayLength());
    int cur = dynamicArray.getElementAtIndex(1);
    System.out.println(cur);
    System.out.println(dynamicArray.removeElement());
    System.out.println("length is " + dynamicArray.arrayLength());
}
}