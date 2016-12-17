import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DealFinder {
	
	public static final boolean LOWEST_PRICED_CAR_AT_TOP = true;
	public static final boolean LOWEST_PRICED_CAR_AT_BOTTOM = false;
	
	public static String readFile(String filename) {
		// Read the contents of a file
		// Put the results, as a String, into the content variable
		String content = "";
		try {
			File file = new File(filename);
			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null)
				sb.append(line);
			content = sb.toString();
		} catch (IOException e) {
			System.err.println("There's been an error: " + e.getMessage());
			System.exit(-1);
		}
		
		return content;
	}
	
	public static String getLowestPricedCarType(String content,
			                                    boolean lowestPricedFirst) {
		// Get the type of car
		int start;
		if (lowestPricedFirst)
		    start = content.indexOf("vehicle\">");
		else
			start = content.lastIndexOf("vehicle\">");
		String carType = content.substring(start + 9);
		int end = carType.indexOf("</td>");
		carType = carType.substring(0, end);
		return carType;
	}
	
	public static String getLowestPricedCarPrice(String content,
                                                 boolean lowestPricedFirst) {
		// Get the price of the car
		int start;
		if (lowestPricedFirst)
			start = content.indexOf("price\">");
		else
			start = content.lastIndexOf("price\">");
		String carPrice = content.substring(start + 7);
		int end = carPrice.indexOf("</td>");
		carPrice = carPrice.substring(0, end);
		return carPrice;
	}
	
	public static void main(String[] args) {
		// Somehow... get the type and price into topAudiPrice and topAudi
		String fileContent = readFile("audi-listings-2.html");
		String topAudi =
			getLowestPricedCarType(fileContent, LOWEST_PRICED_CAR_AT_BOTTOM);
		String topAudiPrice =
			getLowestPricedCarPrice(fileContent, LOWEST_PRICED_CAR_AT_BOTTOM);
		
		System.out.println("The lowest priced Audio this week sells for: " +
				topAudiPrice);
		System.out.println("It's a " + topAudi);
		
		// Somehow... get the type and price into topBmwPrice and topBmw
		fileContent = readFile("bmw-listings.html");
		String topBmw =
			getLowestPricedCarType(fileContent, LOWEST_PRICED_CAR_AT_TOP);
		String topBmwPrice =
			getLowestPricedCarPrice(fileContent, LOWEST_PRICED_CAR_AT_TOP);
		
		System.out.println("The lowest priced BMW this week sells for: " +
				topBmwPrice);
		System.out.println("It's a " + topBmw);
		
		// Somehow... get the type and price into topMercedesPrice and topMercedes
		fileContent = readFile("mercedes-listings.html");
		String topMercedes =
			getLowestPricedCarType(fileContent, LOWEST_PRICED_CAR_AT_TOP);
		String topMercedesPrice =
			getLowestPricedCarPrice(fileContent, LOWEST_PRICED_CAR_AT_TOP);
		
		System.out.println("The lowest priced Mercedes this week sells for: " +
				topMercedesPrice);
		System.out.println("It's a " + topMercedes);

		/*
		Double audiPrice = Double.parseDouble(topAudiPrice.substring(1));
		Double bmwPrice = Double.parseDouble(topBmwPrice.substring(1));
		Double mercedesPrice = Double.parseDouble(topMercedesPrice.substring(1));
		
		if (audiPrice < bmwPrice) {
			if (audiPrice < mercedesPrice) {
				System.out.println("The Audi, at " + topAudiPrice + ", is the best deal.");
			} else {
				System.out.println("The Mercedes, at " + topMercedesPrice + ", is the best deal.");
			}
		} else {
			if (bmwPrice < mercedesPrice) {
				System.out.println("The BMW, at " + topBmwPrice + ", is the best deal.");
			} else {
				System.out.println("The Mercedes, at " + topMercedesPrice + ", is the best deal.");
			}
		}
		*/
	}

}
