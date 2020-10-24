package snapfood;

public class Food {

	int id;//id of restInfo
	String nameOfFood;
	String type;
	Double price;
	public Food(int id,String nameOfFood, String type, Double price) {
		this.id=id;
		this.nameOfFood = nameOfFood;
		this.type = type;
		this.price = price;
	}
	public String getNameOfFood() {
		return nameOfFood;
	}
	public void setNameOfFood(String nameOfFood) {
		this.nameOfFood = nameOfFood;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Food [nameOfFood=" + nameOfFood + ", type=" + type + ", price=" + price + "]";
	}
	
	
	
}
