package snapfood;

public class Restaurant {

	String nameOfRest;
	String type;
	Double serviceFee;
	public Restaurant(String nameOfRest, String type, Double serviceFee) {
		this.nameOfRest = nameOfRest;
		this.type = type;
		this.serviceFee = serviceFee;
	}
	public String getNameOfRest() {
		return nameOfRest;
	}
	public void setNameOfRest(String nameOfRest) {
		this.nameOfRest = nameOfRest;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(Double serviceFee) {
		this.serviceFee = serviceFee;
	}
	@Override
	public String toString() {
		return "Restaurant [nameOfRest=" + nameOfRest + ", type=" + type + ", serviceFee=" + serviceFee + "]";
	}
	
	
	

	
	
	
	
}
