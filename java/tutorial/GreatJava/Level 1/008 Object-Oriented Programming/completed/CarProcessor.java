import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CarProcessor {
	
	private String readFile(String filename) {
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
	
	private String getLowestPricedCarType(String content,
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

	private double getLowestPricedCarPrice(String content,
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
		Double price = Double.parseDouble(carPrice.substring(1));
		return price;
}

	public Car processCarFile(CarListing listing) {
		String fileContent = readFile(listing.getFilename());
		String topCar = 
			getLowestPricedCarType(fileContent, listing.isLowestPricedAtBottom());
		double topCarPrice = 
			getLowestPricedCarPrice(fileContent, listing.isLowestPricedAtBottom());
		
		String yearString = topCar.substring(0, 4);
		int year = Integer.parseInt(yearString);
		int modelIndex = topCar.indexOf(listing.getMake());
		modelIndex += listing.getMake().length() + 1;
		String model = topCar.substring(modelIndex);
		
		Car car = new Car();
		car.setMake(listing.getMake());
		car.setYear(year);
		car.setModel(model);
		car.setPrice(topCarPrice);
		return car;
	}
}