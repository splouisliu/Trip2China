public class Food{
   private String name;    // name of the food
   private double price;   // price of the food
   private double rating;  // rating of the food
	
   // constructor
   public Food(String name, double price, double rating){
      this.name = name;
      this.price = price;
      this.rating = rating;
   } // Food method
   
   // accessor
   public String getName(){
      return name;
   } // getName method

   public double getPrice(){
      return price;
   } // getPrice method

   public double getRating(){
      return rating;
   } // getRating method
   
   // mutator
   public void setName(String name){
      this.name = name;
   } // setName method

   public void setPrice(double price){
      this.price = price;
   } // setPrice method

   public void setRating(double rating){
      this.rating = rating;
   } // setRating
   
   // return the information of the food
   public String toString(){
      return "\tPrice: $" + price;
   } // toString method
} // Food class