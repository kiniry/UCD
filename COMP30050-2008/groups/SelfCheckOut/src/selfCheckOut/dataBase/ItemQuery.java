package selfCheckOut.dataBase;
//Query.java
//@author : Patrick McDonagh

import java.sql.*;
import selfCheckOut.BarCode;

public class ItemQuery {
	BarCode barcode;
	public String name = null;;
	public int price;
	public int minweight;
	public int weight;
	public int maxweight;
	String soundfile;
	String imagefile;
	public String allergy = null;
	int primeitem;
	
	String obj;
	

	public ItemQuery (BarCode bc){
	//
	
	
	//public ItemQuery (long barcode) {
		try{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
//			
//			//String url  = "jdbc:mysql://sql104.hostwq.net:3306/hq_1793448_SelfCheckout";
//			//String url  = "jdbc:mysql://169.254.117.255:3306/SelfCheckout";
		String url  = "jdbc:mysql://localhost:3306/SelfCheckout";
	//	Connection conn = DriverManager.getConnection(url, "hq_1793448", "password");
			Connection conn = DriverManager.getConnection(url, "me", "pass");
			//Two Following prints are verification data
			//System.out.println("URL: " +url);
			//System.out.println("Connection: " +conn);
			System.out.println("CONNECTED TO DATABASE");
			System.out.println("");
			
			System.out.println(bc.getBarCodeLong());
			Statement stmt = conn.createStatement();
			
			ResultSet rs;
			
			//System.out.println(bc.getBarCodeLong());
			//rs = stmt.executeQuery("SELECT Name,Price,MinWeight FROM Items WHERE Barcode = "+bc.asLong +"");
			rs = stmt.executeQuery("SELECT Name,Price,MinWeight,Weight,MaxWeight"
					+",SoundFileLoc,ImageFileLoc,Allergy,PrimeItem FROM Items WHERE Barcode = "+bc.getBarCodeLong() +"");
			
			while ( rs.next() ) {
				name = rs.getString("Name");
				price = rs.getInt("Price");
				minweight = rs.getInt("MinWeight");
				weight = rs.getInt("Weight");
				maxweight = rs.getInt("MaxWeight");
				soundfile = rs.getString("SoundFileLoc");
				imagefile = rs.getString("ImageFileLoc");
				allergy = rs.getString("Allergy");
				primeitem = rs.getInt("PrimeItem");
				this.barcode = bc;
				
				System.out.println(name);
				
				}
			conn.close();
			//System.out.println("CONNECTION TERMINATED");
		} 
		catch (Exception e) {
			System.err.println("Got an Exception");
			System.err.println(e.getMessage());
			}
		
	}
	
public String toString(){
	

	obj = "Product Barcode = "+barcode.getBarCodeLong()+"\n"+"Product Name = "+name
	+"\n"+"Product Price = "+price+"\n"+"Product MinWeight = "+minweight+"\n"
	+"Product MaxWeight = "+maxweight+"\n"+"Product Actual Weight = "+weight+"\n"+"Product Sound and Image Files " +
			"are located @ "+soundfile+"\n"+"Associated Allergy = "+allergy+"\n";
	
	return obj;
	
	


}
}