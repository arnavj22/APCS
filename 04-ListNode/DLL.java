public class DLL        //DoubleLinkedList
{
   private int size;
   private DLNode head = new DLNode(); //dummy node--very useful--simplifies the code
   
   public int size()
   {
	   return size;
   }
   
   /* appends obj to end of list; increases size;
   	  @return true  */
   public boolean add(Object obj)
   {
      addLast(obj);
      return true;   
   }
   
   /* inserts obj at position index.  increments size. 	*/
   public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real LinkedList is coded
   {
      if( index > size || index < 0 )
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      DLNode pointer = head.getNext();
      if(index == 0) {
    	  addFirst(obj);
    	  return;
      }
      for(int i = 0 ; i < index - 1; i++) {
    	  pointer = pointer.getNext();
      }
      DLNode temp = new DLNode(obj, pointer, pointer.getNext());
      pointer.setNext(temp);
      pointer.getNext().getNext().setPrev(temp);
      size++;
                    
                    
   }
   
   /* return obj at position index. 	*/
   public Object get(int index)
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode pointer = head.getNext();
      for(int i = 0 ; i < index; i++) {
    	  pointer = pointer.getNext();
      }
      return pointer.getValue();
      
   }
   
   /* replaces obj at position index. 
        returns the obj that was replaced*/
   public Object set(int index, Object obj)
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode pointer = head.getNext();
      for(int i = 0 ; i < index ; i++) {
    	  pointer = pointer.getNext();
      }
      Object x = pointer.getValue();
      pointer.setValue(obj);
      return x;
   }
   
   /*  removes the node from position index (zero-indexed).  decrements size.
       @return the object of the node that was removed.    */
   public Object remove(int index)
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      DLNode pointer = head.getNext();
      for(int i = 0 ; i < index - 1; i++) {
    	  pointer = pointer.getNext();
      }
      
      Object x = pointer.getNext().getValue();
      pointer.setNext(pointer.getNext().getNext());
      pointer.getNext().setPrev(pointer);
      size--;
      return x;
      
   }
   
   /* inserts obj at front of list, increases size   */
   public void addFirst(Object obj)
   {
	   DLNode temp = new DLNode(obj, head, head.getNext());
	   head.getNext().setPrev(temp);
   head.setNext(temp);
   size++;
   }
   
   /* appends obj to end of list, increases size    */
   public void addLast(Object obj)
   {
	   DLNode newnode = new DLNode(obj, head.getPrev(), head);
	   head.getPrev().setNext(newnode);
	   head.setPrev(newnode);
	   size++;
   }
   
   /* returns the first element in this list  */
   public Object getFirst()
   {
   return head.getNext().getValue();
   }
   
   /* returns the last element in this list  */
   public Object getLast()
   {
   return head.getPrev().getValue();
   }
   
   /* returns and removes the first element in this list, or
       returns null if the list is empty  */
   public Object removeFirst()
   {
	   if(head.getNext() == null) {
		   return null;
	   }
	   Object out = head.getNext().getValue();
	   head.setNext(head.getNext().getNext());
	   head.getNext().setPrev(head);
   return out;
   }
   
   /* returns and removes the last element in this list, or
       returns null if the list is empty  */
   public Object removeLast()
   {
	   if(head.getNext() == null) {
		   return null;
	   }
		   Object x = head.getPrev().getValue();
		   DLNode temp = head.getPrev().getPrev();
		   temp.setNext(head);
		   head.setPrev(temp);
	   
	   return x;
   }
   
   /*  returns a String with the values in the list in a 
       friendly format, for example   [Apple, Banana, Cucumber]
       The values are enclosed in [], separated by one comma and one space.
    */
   public String toString()
   {
	   String str = "[";
	   DLNode pointer;
	   for(pointer = head.getNext(); pointer.getNext() != head; pointer = pointer.getNext()) {
		   str  = str + pointer.getValue() + ", ";
	   }
	   return str + pointer.getValue() + "]";
   
   }
}