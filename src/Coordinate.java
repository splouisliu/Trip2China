public class Coordinate
{
	private double latitude;      // latitude of the city
	private double longtitude;    // longtitude of the city
	
   // constructor
	public Coordinate (double latitude, double longtitude)
	{
		this.longtitude = longtitude;
		this.latitude = latitude;
	} // Coordinate method
   
   // accessor
	public double getLatitude()
	{
		return latitude;
	} // getLatitude method
		
	public double getLongtitude()
	{
		return longtitude;
	} // getLongtitude method
	
   // mutator
	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	} // setLatitude method

	public void setLongtitude(double longtitude)
	{
		this.longtitude = longtitude;
	} // setLongtitude method
	
   // calculate the distance between two cities
	public double distance (Coordinate other)
	{
      // Formula Used: sqrt ((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2))
		double changeLat = latitude - other.latitude;                  // y1 - y2
		double changeLong = longtitude - other.longtitude;             // x1 - x2
		
		return Math.sqrt(changeLat*changeLat + changeLong*changeLong); // return the distance bewteen two cities
	} // distance method
} // Coordinate class