//Updated on 12.14.2020 v2

//Name:   Date:
import java.util.*;
import java.io.*;
public class McRonald
{
   public static final int TIME = 1080;     //18 hrs * 60 min
   public static double CHANCE_OF_CUSTOMER = .2;
   public static int customers = 0;
   public static int totalMinutes = 0;
   public static int longestWaitTime = 0;
   public static int longestQueue = 0;
   public static int serviceWindow = 0;      // to serve the front of the queue
   public static int thisCustomersTime;
   public static PrintWriter outfile = null; // file to display the queue information
      
   public static int timeToOrderAndBeServed()
   {
      return (int)(Math.random() * 6 + 2);
   }
  
   public static void displayTimeAndQueue(Queue<Customer> q, int min)
   { 
      //Billington's
      outfile.println(min + ": " + q);	
      //Jurj's
      //outfile.println("Customer#" + intServiceAreas[i] + 
      //                            " leaves and his total wait time is " + (intMinute - intServiceAreas[i]));                     	
      
   }
   
   public static int getCustomers()
   {
      return customers;
   }
   public static double calculateAverage()
   {
      return (int)(1.0 * totalMinutes/customers * 10)/10.0;
   }
   public static int getLongestWaitTime()
   {
      return longestWaitTime;
   }
   public static int getLongestQueue()
   {
      return longestQueue;
   }
            
   public static void main(String[] args)
   {     
    //set up file      
      try
      {
         outfile = new PrintWriter(new FileWriter("McRonald 1 Queue 1 ServiceArea.txt"));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
      
      mcRonald(TIME, outfile);   //run the simulation
      
      outfile.close();	
   }
   
   public static void mcRonald(int TIME, PrintWriter of)
   {
	  int t = 0;
      /***************************************
           Write your code for the simulation   
      **********************************/
	   boolean first = true;
	   Queue<Customer> q = new LinkedList<Customer>();
      for(int i = 0; i < TIME; i++) {
    	  outfile.print(i + ": ");
    	  if(Math.random() <= 0.2) {
    		  Customer c = new Customer(i);
    		  q.add(c);
    		  if(first) {
    			  first = false;
    			  t = timeToOrderAndBeServed();
    		  }
    		  
    	  }
    	  if(t == 0 && q.size() > 0) {
    		  t = timeToOrderAndBeServed();
    		  if(q.peek().getTime() > longestWaitTime) {
    			  longestWaitTime = q.peek().getTime();
    		  }
    		  totalMinutes = totalMinutes + q.peek().getTime();
    		  q.remove();
    		  customers++;   
    		  if(q.size() == 0) {
  		  		outfile.println("no one is being served");
    		  }
    		  else {
    		  outfile.println(q);
        	  outfile.println( "\t" + q.peek() + " is now being served for " + t + " minutes");
    		  }
    		  
    		  
    	  }
    	  
    	  else {
    		  if(q.size() > 0)
    			  t--;
    		  	for(int j = 0; j < q.size(); j++) {
    		  		q.peek().setTime(q.peek().getTime()+1);
    		  		q.add(q.remove());
    		  	
    		  	}
    		  	if(q.size() == 0) {
    		  		outfile.println();
    		  	}
    		  	else if(t > 0) {
    	    	  outfile.println(q);
    	    	  outfile.println( "\t" + q.peek() + " is now being served for " + t + " minutes");
    		  	}
    	  }

    	  if(q.size() > longestQueue) {
    		  longestQueue = q.size();
    	  }
 
      }
      while(q.size() != 0) {
    	  
    	  if(t == 0 && q.size() > 0) {
    		  t = timeToOrderAndBeServed();
    		  if(q.peek().getTime() > longestWaitTime) {
    			  longestWaitTime = q.peek().getTime();
    		  }
    		  totalMinutes = totalMinutes + q.peek().getTime();
    		  q.remove();
    		  customers++;   
    		  if(q.size() == 0) {
  		  		outfile.println("no one is being served");
    		  }
    		  else {
    		  outfile.println(q);
        	  outfile.println( "\t" + q.peek() + " is now being served for " + t + " minutes");
    		  }
    		  
    		  
    	  }
    	  
    	  else {
    		  if(q.size() > 0)
    			  t--;
    		  	for(int j = 0; j < q.size(); j++) {
    		  		q.peek().setTime(q.peek().getTime()+1);
    		  		q.add(q.remove());
    		  	
    		  	}
    		  	if(q.size() == 0) {
    		  		outfile.println();
    		  	}
    		  	else if(t > 0) {
    	    	  outfile.println(q);
    	    	  outfile.println( "\t" + q.peek() + " is now being served for " + t + " minutes");
    		  	}
    	  }
      }
        
              
      /*   report the data to the screen    */  
      System.out.println("1 queue, 1 service window, probability of arrival = "+ CHANCE_OF_CUSTOMER);
      System.out.println("Total customers served = " + getCustomers());
      System.out.println("Average wait time = " + calculateAverage());
      System.out.println("Longest wait time = " + longestWaitTime);
      System.out.println("Longest queue = " + longestQueue);
   }
   
   static class Customer      
   {
      private int arrivedAt;
      private int orderAndBeServed;
      public Customer(int t){
          orderAndBeServed = 0;
          arrivedAt = t;
       }
       public int getTime() {
          return orderAndBeServed;
       }
       public void setTime(int t) {
    	   orderAndBeServed = t;
       }
       public int getArrival() {
          return arrivedAt;
       }
       public String toString(){
          return String.valueOf(arrivedAt);
       }
    /**********************************
       Complete the Customer class with  
       constructor, accessor methods, toString.
    ***********************************/

    
    
   }
}