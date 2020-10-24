package snapfood;

public class CartManager {

	int id;
	String nameOfFood;
	String type;
	Double price;
	int count;
	public CartManager(int id,String nameOfFood, String type, Double price, int count) {
		this.id=id;
		this.nameOfFood = nameOfFood;
		this.type = type;
		this.price = price;
		this.count = count;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "CartManager [nameOfFood=" + nameOfFood + ", type=" + type + ", price=" + (price*count) + ", count=" + count
				+ "]";
	}
	
	
}
