
public class OODealFinder {
	
	public static final boolean LOWEST_PRICED_CAR_AT_TOP = true;
	public static final boolean LOWEST_PRICED_CAR_AT_BOTTOM = false;
	
	private static CarListing[] initializeCarListings() {
		CarListing[] carListings = new CarListing[4];
		
		CarListing listing1 = new CarListing();
		listing1.setFilename("audi-listings-2.html");
		listing1.setLowestPricedAtBottom(LOWEST_PRICED_CAR_AT_BOTTOM);
		listing1.setMake("Audi");
		carListings[0] = listing1;
		
		CarListing listing2 = new CarListing();
		listing2.setFilename("bmw-listings.html");
		listing2.setLowestPricedAtBottom(LOWEST_PRICED_CAR_AT_TOP);
		listing2.setMake("BMW");
		carListings[1] = listing2;
		
		CarListing listing3 = new CarListing();
		listing3.setFilename("mercedes-listings-2.html");
		listing3.setLowestPricedAtBottom(LOWEST_PRICED_CAR_AT_BOTTOM);
		listing3.setMake("Mercedes");
		carListings[2] = listing3;
		
		CarListing listing4 = new CarListing();
		listing4.setFilename("porsche-listings.html");
		listing4.setLowestPricedAtBottom(LOWEST_PRICED_CAR_AT_TOP);
		listing4.setMake("Porsche");
		carListings[3] = listing4;
		
		return carListings;
	}
	
	public static void main(String[] args) {
		CarListing[] carListings = initializeCarListings();
		
		CarProcessor processor = new CarProcessor();
		
		Car[] lowestPricedCars = new Car[carListings.length];
		
		for (int i = 0; i < carListings.length; i++) {
			Car lowestPricedCar = processor.processCarFile(carListings[i]);
			lowestPricedCars[i] = lowestPricedCar;
		}
		
		for (int i = 0; i < lowestPricedCars.length; i++) {
			Car car = lowestPricedCars[i];
			System.out.println("The " + car.getYear() + " " + car.getMake() +
					           " " + car.getModel() + " sells for $" + car.getPrice());
		}
	}

}
