
public class CarListing {

	private String filename;
	private boolean lowestPricedAtBottom;
	private String make;
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public void setLowestPricedAtBottom(boolean lowestPricedAtBottom) {
		this.lowestPricedAtBottom = lowestPricedAtBottom;
	}
	
	public boolean isLowestPricedAtBottom() {
		return lowestPricedAtBottom;
	}
	
	public void setMake(String make) {
		this.make = make;
	}
	
	public String getMake() {
		return make;
	}	
}
