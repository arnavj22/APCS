// Name: Arnav Jain
// Date: 9/9/2020
 
import java.text.DecimalFormat;
import java.lang.*;
public class SmartCard_Driver{
   public static void main(String[] args) {
	   Station downtown = new Station("Downtown", 1);
	   Station center = new Station("Center City", 1);
	   Station uptown = new Station("Uptown", 2);
	   Station suburbia = new Station("Suburb", 4);
	   
       SmartCard jimmy = new SmartCard(20.00); 
	   jimmy.board(center);                    //Boarded at Center City.  SmartCard has $20.00
	   jimmy.exit(suburbia);              //From Center City to Suburb costs $2.75.  SmartCard has $17.25
	   jimmy.exit(uptown);				//Error:  did not board?!
	   System.out.println();

	   SmartCard susie = new SmartCard(1.00); 
	   susie.board(uptown);            		//Boarded at Uptown.  SmartCard has $1.00
	   susie.exit(suburbia);				//Insuffient funds to exit. Please add more money.
	   System.out.println();
   
	   SmartCard kim = new SmartCard(.25);    
	   kim.board(uptown);            		    //Insuffient funds to board. Please add more money.
	   System.out.println();

	   SmartCard b = new SmartCard(10.00);   
       b.board(center);            		    //Boarded at Center City.  SmartCard has $10.00
	   b.exit(downtown);					//From Center City to Downtown costs $0.50.  SmartCard has $9.50
	   System.out.println();
	      
	   SmartCard mc = new SmartCard(10.00);  
	   mc.board(suburbia);            		    //Boarded at Suburbia.  SmartCard has $10.00
	   mc.exit(downtown);					//From Suburbia to Downtown costs $2.75.  SmartCard has $7.25
	   System.out.println();
    
   }
} 	

class SmartCard {
   public final static DecimalFormat df = new DecimalFormat("$0.00");
   public final static double MIN_FARE = 0.5;
   private boolean boarded;
   private double amount;
   private Station bStation;
   public SmartCard(double a) {
	   bStation = null;
	   amount = a;
	   boarded = false;
   }

   public void board (Station s){
	   if(boarded) {
		   System.out.println("Error: already boarded?!");
	   }
	   else if(amount >= 0.5) {
			boarded = true;
			bStation = s;
		}
		else{
			System.out.println("Insufficient funds to board. Please add more money.");
		}
		
	}
	public void exit (Station s){
		if(boarded) {
			if(amount-cost(s) < 0) {
				System.out.println("Insufficient funds to exit. Please add more money.");
			}
			else {
				amount -= cost(s);
				System.out.println(df.format(amount));
			}
			boarded = false;
			bStation = null;
		}
		else {
			System.out.println("Error: Did not board?!");
		}
	}
	public double cost (Station s){
		double cost = 0.5 + (0.75 * Math.abs( bStation.getZone() - s.getZone() ) );
		return cost;

	}
	public boolean isBoarded (){
		return boarded;

	}
	public void addMoney (double d){
		amount += d;

	}
	public String getBalance (){
		return df.format(amount);

	}
   double getMoneyRemaining()
   {
      return amount;
   }
   
   Station getBoardedAt()
   {
      return bStation;   
   }
  
   boolean getIsOnBoard()
   {
      return boarded;
   }
}

class Station {
	private String name;
	private int zone;
	public Station(String n, int z) {
		zone = z;
	}
	public int getZone() {
		return zone;
	}
	public String getName() {
		return name;
	}
     
     
}
