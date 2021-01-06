// Name: 
// Date:

/**
 * Implements the cheat sheet's List interface.  Implements generics.
 * The backing array is an array of (E[]) new Object[10];
 * @override toString()
 */ 
public class TJArrayList<E>
{
   private int size;							//stores the number of objects
   private E[] myArray;
   public TJArrayList()                //default constructor makes 10 cells
   {
      myArray = (E[]) new Object[10];
      size = 0;
   }
   public int size()
   {
      return size;
   }
 	/* appends obj to end of list; increases size;
      must be an O(1) operation when size < array.length, 
         and O(n) when it doubles the length of the array.
	  @return true  */
   public boolean add(E obj)
   {
   if(size < myArray.length) {
	   myArray[size] = obj;
   }
   else {
	   E[] newArray = (E[]) new Object[myArray.length * 2];
	   for(int i = 0; i < myArray.length; i++) {
		   newArray[i] = myArray[i];
	   }
	   myArray = newArray;
	   myArray[size] = obj;
   }
   size++;
   return true;
   }
   /* inserts obj at position index.  increments size. 
		*/
   public void add(int index, E obj) throws IndexOutOfBoundsException  //this the way the real ArrayList is coded
   {
      if(index > size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

      if(size + 1 >= myArray.length) {
    	  E[] newArray = (E[]) new Object[myArray.length * 2];
   	   		for(int i = 0; i < myArray.length; i++) {
   	   			newArray[i] = myArray[i];
   	   		}
   	   		myArray = newArray;
      }
      
      E temp = myArray[index];
      myArray[index] = obj;
      E nexttemp;
      for(int i = index + 1; i < size + 1; i++) {
    	  nexttemp = temp;
    	  temp = myArray[i];
    	  myArray[i] = nexttemp;

      }
      size++;
      
      
      


   
   }

   /* return obj at position index.  
		*/
   public E get(int index) throws IndexOutOfBoundsException
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      return myArray[index];
   
   }
   /**
    * Replaces obj at position index. 
    * @return the object is being replaced.
    */  
   public E set(int index, E obj) throws IndexOutOfBoundsException  
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      E temp = myArray[index];
      myArray[index] = obj;
      return temp;
   }
 /*  removes the node from position index. shifts elements 
     to the left.   Decrements size.
	  @return the object at position index.
	 */
   public E remove(int index) throws IndexOutOfBoundsException  
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      
      E temp = myArray[index];
     for(int i = index; i < size; i++) {
    	 myArray[i] = myArray[i + 1];
     }
     size--;
     return temp;
   }
	   /*
		   This method compares objects.
         Must use .equals(), not ==
     	*/
   public boolean contains(E obj)
   {
   for(int i = 0; i < size; i++) {
	   if(obj.equals(myArray[i])) {
		   return true;
	 }
   }
   return false;
   }
	 /*returns a String of E objects in the array with 
       square brackets and commas.
     	*/
   public String toString()
   {
   String str = "[";
   for(int i = 0; i < size - 1; i++) {
	   str = str + myArray[i] + ", ";
   }
   return str + myArray[size-1] + "]";
   }
}