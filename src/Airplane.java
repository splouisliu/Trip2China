public class Airplane extends Transportation
{
	public static final double PRICE = 15; // price of airplane is 15 dollars per unit.
	
   // calculate the airplane cost between cities 
   public double cost (double distance)
	{
		return PRICE*distance;
	} // cost method
	
   // return the information of airplane
   public String toString ()
   {
      return "Airplane";
   } // toString method
} // Airplane class