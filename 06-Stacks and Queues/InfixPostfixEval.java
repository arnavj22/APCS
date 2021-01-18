// Name:
// Date:
//uses PostfixEval

import java.util.*;
public class InfixPostfixEval
{
   public static final String LEFT  = "([{<";
   public static final String RIGHT = ")]}>";
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args)
   {
      System.out.println("Infix  \t-->\tPostfix\t\t-->\tEvaluate");
      /*build your list of Infix expressions here  */
      List<String> infixExp = new ArrayList<String>();
      infixExp.add("3 * ( 4 * 5 + 6 )");
         
         
         
         
         
      for( String infix : infixExp )
      {
         String pf = infixToPostfix(infix);  //get the conversion to work first
         //System.out.println(infix + "\t\t\t" + pf );  
         System.out.println(infix + "\t\t\t" + pf + "\t\t\t" + PostfixEval.eval(pf));  //PostfixEval must work!
      }
   }
   
   public static String infixToPostfix(String infix)
   {
      List<String> nums = new ArrayList<String>(Arrays.asList(infix.split(" ")));
            /* enter your code here  */
      Stack<String> expressions = new Stack<String>();
      List<String> postfixParts = new ArrayList<String>();
      String operators = "+-*/%";
      for(int i = 0; i < nums.size(); i++) {
    	  String s = nums.get(i);
    	  
    	  if(operators.contains(s)) {
    		  if(expressions.size() == 0) {
    			  expressions.push(s);
    		  }
    		  else {
    			  while(expressions.size() != 0 && isLower(s.charAt(0), expressions.peek().charAt(0))) {
    				  postfixParts.add(expressions.pop());
    			  }
    			  expressions.push(s);
    		  }
    	  }
    	  else if(s.equals("(")) {
    		  expressions.push(s);
    	  }
    	  else if(s.equals(")")) {
    		  while(!expressions.peek().equals("(")) {
    			  postfixParts.add(expressions.pop());
    		  }
    		  expressions.pop();
    	  }
    	  else {
    		  postfixParts.add(s);
    	  }
      }
      while(expressions.size() != 0) {
    	  postfixParts.add(expressions.pop());
      }
      String out = "";
      for(int i = 0; i < postfixParts.size(); i++) {
    	  out += postfixParts.get(i) + " ";
      }
      return out;
   }
   
   //returns true if c1 has strictly lower precedence than c2
   public static boolean isLower(char c1, char c2)
   {
	   String lower = "-+";
	   String higher = "*/%";
	   if(higher.contains("" + c1) && lower.contains("" + c2) || c2 == ('(')) {
		   return false;
	   }
	   return true;
   }
}


/********************************************

 Infix  	-->	Postfix		-->	Evaluate
 3 + 4 * 5			3 4 5 * + 			23.0
 3 * 4 + 5			3 4 5 + * 			27.0
 1.3 + 2.7 + -6 * 6			1.3 2.7 + -6 6 * + 			-32.0
 ( 33 + -43 ) * ( -55 + 65 )			33 -43 + -55 65 + * 			-100.0
 3 * 4 + 5 / 2 - 5			3 4 5 2 5 - / + * 			6.999999999999999
 8 + 1 * 2 - 9 / 3			8 1 2 9 3 / - * + 			7.0
 3 * ( 4 * 5 + 6 )			3 4 5 6 + * * 			132.0
 3 + ( 4 - 5 - 6 * 2 )			3 4 5 - 6 2 * - + 			-10.0
 2 + 7 % 3			2 7 3 % + 			3.0
 ( 2 + 7 ) % 3			2 7 + 3 % 			0.0
     
***********************************************/