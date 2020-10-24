package snapfood;



public class User {

	String phoneNumber;
	String name;
	String postalCode;
	java.sql.Date date;
	Double total;
	public User(String phoneNumber, String name, String postalCode) {
		super();
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.postalCode = postalCode;
	}
	
	//constructor for administrator
	public User(String name,String phoneNumber, java.sql.Date date,Double total) {
		super();
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.date=date;
		this.total=total;
	}
	///////////////////////
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		return "User [phoneNumber=" + phoneNumber + ", name=" + name + ", date=" + (date.getMonth()+1) + ", total=" + total + "]";
	}
	
	
	
	
}
