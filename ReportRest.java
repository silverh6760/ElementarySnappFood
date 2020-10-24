package snapfood;

public class ReportRest {

	String nameOfFood;
	String nameOfRest;
	String region;
	Double totalFee;
	public ReportRest(String nameOfFood, String nameOfRest, String region, Double totalFee) {
		super();
		this.nameOfFood = nameOfFood;
		this.nameOfRest = nameOfRest;
		this.region = region;
		this.totalFee = totalFee;
	}
	public String getNameOfFood() {
		return nameOfFood;
	}
	public void setNameOfFood(String nameOfFood) {
		this.nameOfFood = nameOfFood;
	}
	public String getNameOfRest() {
		return nameOfRest;
	}
	public void setNameOfRest(String nameOfRest) {
		this.nameOfRest = nameOfRest;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Double getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}
	@Override
	public String toString() {
		return "ReportRest [nameOfFood=" + nameOfFood + ", nameOfRest=" + nameOfRest + ", region=" + region
				+ ", totalFee=" + totalFee + "]";
	}
	
	
}
