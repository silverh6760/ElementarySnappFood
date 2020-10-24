package snapfood;

import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cli {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-genera9ted method stub
	try {
	
		Logger logger=LoggerFactory.getLogger(Cli.class);
		Connection con=ConnectionManager.connectionManager.getConnection();
//	ReadRestaurantFile readFile =new ReadRestaurantFile();
//		readFile.readRestFile(con);
		logger.info("New Restaurant and Food Added to DB");
		Scanner scan=new Scanner(System.in);
		outer:while(true) {
			System.out.println("Enter your phone Number:");
			String phoneNumber=scan.next();
			UserManager userManager =new UserManager();
			if(userManager.checkPhoneNumber(phoneNumber)==true) {
				
				RestaurantManager restManager =new RestaurantManager();
				
				boolean bool=true;
				outer2:while(bool) {
					System.out.println("Enter Your Region:");
					String region=scan.next();
					try {
					int re=Integer.parseInt(region);
					}catch(NumberFormatException e) {
						e.getMessage();
					}
					System.out.println("Enter an option:");
					System.out.println("1-See The Restaurant Near by...");
					System.out.println("2-Search The Restaurant Based On Type Of Food...");
					System.out.println("3-Return");
					int number=scan.nextInt();
					switch(number) {//switch 1
					case(1):{
						

					    List<Restaurant> restArr;
						
							restArr = restManager.searchRest(con,region);
						
					    if(restArr.size()==0) {
					    	System.out.println("No Result in Your Region!");
					    	continue outer2;
					    }
						
						for(Restaurant rest:restArr) {
							int i=1;
							System.out.println(i+"-"+rest);
							i++;
						}
						System.out.println("Enter an Option:");
						System.out.println("1-Order Food...");
						System.out.println("2-Return");
						int number2=scan.nextInt();
						switch(number2) {//switch1->switch1
						case 1:{//1-order food
					  outer3:while(true) {
						  Double serviceFee=0.0;
								System.out.println("Enter the name Of Restaurant:");
								String nameOfRest=scan.next();
								for(int i=0;i<restArr.size();i++) {
									if(restArr.get(i).nameOfRest.equals(nameOfRest)) {
										serviceFee=restArr.get(i).serviceFee;
										break;
									}
									if(i==restArr.size()) {
										System.out.println("Wrong Name Of Restaurant!try again!");
										logger.error("Wrong Name Of Restaurant");
										continue outer2;
									}
								}
								
								
									List<Food> foodArr = restManager.searchFoodOfRest(con,nameOfRest);
									int ic=1;
									for(Food rest:foodArr) {
										
										System.out.println(ic+"-"+rest);
										System.out.println(rest.id);
										ic++;
									}
									System.out.println("Enter an Option:");
									System.out.println("1-Order...");
									System.out.println("2-Enter another Restaurant");
									System.out.println("3-Return to main Menu");
									int number3=scan.nextInt();
									
										switch(number3) {
										case 1:{//order food from list of food
											
											List<CartManager> cartArr=new ArrayList<CartManager>();
											
											boolean addFood=true;
									addfood:while(addFood) {
												System.out.println("Enter the name Of  Food");
												int foodIndex=-1;
												scan.nextLine();
												String nameOfFood=scan.nextLine();
												for(int i=0;i<foodArr.size();i++) {
													if(foodArr.get(i).nameOfFood.equals(nameOfFood)) {
														foodIndex=i;
														break;
													}
													
												}
												
												if(foodIndex==-1) {
													System.out.println("wrong Food!");
													continue outer3;
												}
												
												System.out.println("Enter number of this food");
												int num=scan.nextInt();
												cartArr.add(new CartManager(foodArr.get(foodIndex).id,nameOfFood,foodArr.get(foodIndex).type,foodArr.get(foodIndex).price,num));
												
												System.out.println("Enter an Option:");
												System.out.println("1-see Your shopping cart");
												System.out.println("2-continue to add food");
												int num2=scan.nextInt();
												switch(num2) {
												case 1:{//see shopping cart
													double totalPrice=0;
													if(cartArr.isEmpty()==false) {
														for(CartManager element:cartArr) {
															
															System.out.println(element);
															System.out.println(element.id);
															totalPrice=totalPrice+(element.count)*(element.price);
															
														}
														totalPrice=totalPrice+serviceFee;
														System.out.println(totalPrice);
													}else{
														System.out.println("Your Shopping cart is Empty!");
													}
													
													////////////////////////
													System.out.println("Enter an Option:");
													System.out.println("1-delete any Of the order");
													System.out.println("2-continue to bye food");
													System.out.println("3-confirm your shopping");
													int num3=scan.nextInt();
													switch(num3) {
													case 1:{//delete food
														outer4:	while(true) {
															if(cartArr.isEmpty()==true) {
																System.out.println("Your Shopping Cart is Empty!");
																break outer4;
															}
															System.out.println("Enter the name of food");
															String foodstr=scan.next();
															for(int i=0;i<cartArr.size();i++) {
																if(cartArr.get(i).nameOfFood.equals(foodstr)) {
																	cartArr.remove(i);
																	break;
																}
															}
															System.out.println("Your New Shopping cart:");
															totalPrice=0;
															if(cartArr.isEmpty()==false) {
																for(CartManager element:cartArr) {
																	
																	System.out.println(element);
																	totalPrice=totalPrice+(element.count)*(element.price);
																	
																}
																System.out.println(totalPrice);
															}else{
																System.out.println("Your Shopping cart is Empty!");
															}
															System.out.println("Whould You Like to Delete More Food From Your Shopping Cart");
															System.out.println("Please type yes or no");
															String answer;
															while (true) {
															  answer = scan.nextLine().trim().toLowerCase();
															  if (answer.equals("y")) {
															    continue outer4; 
															  } else if (answer.equals("n")) {
															    break outer4;
															   
															  } else {
															     System.out.println("Sorry, I didn't catch that. Please answer y/n");
															  }
															}
														
														}//end of while outer 4
													}
													case 2://continue to bye food
														break;
													case 3:{//confirm shopping
														
														String nameOfCustomer;
														String postalCode;
														UserManager userManage =new UserManager();
														OrderManager order=new OrderManager();
														User userDb=userManage.checkUserInDb(phoneNumber,con);
														if(userDb!=null) {
															confirm:while(true) {
																System.out.println("Enter an Option:");
																System.out.println("1-Confirm Your Shopping");
																System.out.println("2-Edit User Information");
																System.out.println("3-Return to main");
																int key=scan.nextInt();
																switch (key) {
																case 1:{
																	order.insertOrderDb(cartArr,phoneNumber,con);
																	logger.trace("the shopping info:");
																	for(CartManager element:cartArr) {
																		logger.trace("{}",element);
																	}
																	userManage.userFile(userDb, cartArr, serviceFee);
																	
																	
																	continue outer;
																	}
																case 2:{
																	System.out.println("Your Information is:");
																	System.out.println("Username: "+userDb.getName());
																	System.out.println("Postal Code: "+userDb.getPostalCode());
																	System.out.println("Enter Your New Username:");
																	String newUsername=scan.next();
																	System.out.println("Enter Your New Postal Code:");
																	String newPostalcode=scan.next();
																	userManage.editUserInformation(con, phoneNumber, newUsername, newPostalcode);
																	System.out.println("Your Information has been modified successfully!");
																	logger.info("the information for "+phoneNumber+" has been modified!");
																	userDb.name=newUsername;
																	userDb.postalCode=newPostalcode;
																	continue confirm;
																	
																}
																case 3:{
																	continue outer2;
																}

																}
																}//end while
																
														}
														else {
														System.out.println("Enter your name:");
														nameOfCustomer=scan.next();
														System.out.println("Enter your Postal Code:");
														postalCode=scan.next();
														User user=new User(phoneNumber,nameOfCustomer,postalCode);
														
														userManage.addUserToDb(con, user);
														logger.info("new user of "+phoneNumber+" added in data base");
														order.insertOrderDb(cartArr,phoneNumber,con);
														userManage.userFile(user, cartArr, serviceFee);
														
														continue outer;
														}
													}
													}
													
												}
												case 2://continue to add food
													break;
												}
												
												
												System.out.println("Would You like to add more food?");
												System.out.println("Please type yes or no");
												String answer;
												while (true) {
												  answer = scan.nextLine().trim().toLowerCase();
												  if (answer.equals("y")) {
												    continue addfood; 
												  } else if (answer.equals("n")) {
												    addFood=false;
												    break addfood;
												   
												  } else {
												     System.out.println("Sorry, I didn't catch that. Please answer y/n");
												  }
												}
											}//end of while(addfood)
											
											System.out.println("Enter an Option:");
											System.out.println("1-see Your shopping cart:");
											System.out.println("2-confirm the shopping");
											int num4=scan.nextInt();
											switch(num4) {
											case 1:{//see shopping cart
												double totalPrice=0;
												if(cartArr.isEmpty()==false) {
													for(CartManager element:cartArr) {
														
														System.out.println(element);
														totalPrice=totalPrice+(element.count)*(element.price);
														
													}
													totalPrice=totalPrice+serviceFee;
													System.out.println(totalPrice);
												}else{
													System.out.println("Your Shopping cart is Empty!");
												}
												break;
											}
											case 2:{
												//confirm shopping
//												
												
												String nameOfCustomer;
												String postalCode;
												UserManager userManage =new UserManager();
												OrderManager order=new OrderManager();
												User userDb=userManage.checkUserInDb(phoneNumber,con);
												if(userDb!=null) {
													confirm:while(true) {
														System.out.println("Enter an Option:");
														System.out.println("1-Confirm Your Shopping");
														System.out.println("2-Edit User Information");
														System.out.println("3-Return to main");
														int key=scan.nextInt();
														switch (key) {
														case 1:{
															order.insertOrderDb(cartArr,phoneNumber,con);
															userManage.userFile(userDb, cartArr, serviceFee);
															
															logger.trace("the shopping info:");
															for(CartManager element:cartArr) {
																logger.trace("{}",element);
															}
															continue outer;
															}
														case 2:{
															System.out.println("Your Information is:");
															System.out.println("Username: "+userDb.getName());
															System.out.println("Postal Code: "+userDb.getPostalCode());
															System.out.println("Enter Your New Username:");
															String newUsername=scan.next();
															System.out.println("Enter Your New Postal Code:");
															String newPostalcode=scan.next();
															userManage.editUserInformation(con, phoneNumber, newUsername, newPostalcode);
															System.out.println("Your Information has been modified successfully!");
															logger.info("the information for "+phoneNumber+" has been modified!");
															userDb.name=newUsername;
															userDb.postalCode=newPostalcode;
															continue confirm;
															
														}
														case 3:{
															continue outer2;
														}

														}
														}//end while
														
												}
												else {
												System.out.println("Enter your name:");
												nameOfCustomer=scan.next();
												System.out.println("Enter your Postal Code:");
												postalCode=scan.next();
												User user=new User(phoneNumber,nameOfCustomer,postalCode);
												
												userManage.addUserToDb(con, user);
												logger.info("new user of "+phoneNumber+" added in data base");
												order.insertOrderDb(cartArr,phoneNumber,con);
												userManage.userFile(user, cartArr, serviceFee);
												
												continue outer;
												}
												
											}
											}
											break;//switch
											
										}
										case 2://enter another name of restaurant
											continue outer3;
										case 3:{//return to main menu
											continue outer2;
										}
										}
									
									
									break;
								

							}//end of while outer 3
							
							
							break;
						}
						case 2:
							continue outer2;
						}
						
						
						break;
					}
					///////////////////////////////////////food type//////////////////////////////////////
///////////////////////////////////////food type//////////////////////////////////////
///////////////////////////////////////food type//////////////////////////////////////
///////////////////////////////////////food type//////////////////////////////////////
					case(2):{
						//based on food type
						System.out.println("Enter Type Of Restaurant:");
						String foodstr=scan.next();
						 List<Restaurant> restArr2=restManager.searchRestFoodType(con, region, foodstr);

							

	
						    if(restArr2.size()==0) {
						    	System.out.println("No Result in Your Region!");
						    	continue outer2;
						    }
							
							for(Restaurant rest:restArr2) {
								
								System.out.println(rest);
	
							}
							System.out.println("Enter an Option:");
							System.out.println("1-Order Food...");
							System.out.println("2-Return");
							int number2=scan.nextInt();
							switch(number2) {//switch1->switch1
							case 1:{//1-order food
						  outer3:while(true) {
							  Double serviceFee=0.0;
									System.out.println("Enter the name Of Restaurant:");
									String nameOfRest=scan.next();
									for(int i=0;i<restArr2.size();i++) {
										if(restArr2.get(i).nameOfRest.equals(nameOfRest)) {
											serviceFee=restArr2.get(i).serviceFee;
											break;
										}
										if(i==restArr2.size()) {
											System.out.println("Wrong Name Of Restaurant!try again!");
											continue outer2;
										}
									}
									
									
										List<Food> foodTypeArr = restManager.searchFoodOfRestByType(con,nameOfRest,foodstr);
										for(Food rest:foodTypeArr) {
											int i=1;
											System.out.println(i+"-"+rest);
											i++;
										}
										System.out.println("Enter an Option:");
										System.out.println("1-Order...");
										System.out.println("2-Enter another Restaurant");
										System.out.println("3-Return to main Menu");
										int number3=scan.nextInt();
										if(number3==1 || number3==2 || number==3) {
											switch(number3) {
											case 1:{//order food from list of food
												
												List<CartManager> cartArr=new ArrayList<CartManager>();
												
												boolean addFood=true;
												addfood:while(addFood) {
													System.out.println("Enter the name Of  Food");
													int foodIndex=-1;
													String nameOfFood=scan.next();
													for(int i=0;i<foodTypeArr.size();i++) {
														if(foodTypeArr.get(i).nameOfFood.equals(nameOfFood)) {
															foodIndex=i;
															break;
														}
														
													}
													
													if(foodIndex==-1) {
														System.out.println("wrong Food!");
														continue outer3;
													}
													
													System.out.println("Enter number of this food");
													int num=scan.nextInt();
													cartArr.add(new CartManager(foodTypeArr.get(foodIndex).id,nameOfFood,foodTypeArr.get(foodIndex).type,foodTypeArr.get(foodIndex).price,num));
													
													System.out.println("Enter an Option:");
													System.out.println("1-see Your shopping cart");
													System.out.println("2-continue to add food");
													int num2=scan.nextInt();
													switch(num2) {
													case 1:{//see shopping cart
														double totalPrice=0;
														if(cartArr.isEmpty()==false) {
															for(CartManager element:cartArr) {
																
																System.out.println(element);
																totalPrice=totalPrice+(element.count)*(element.price);
																
															}
															totalPrice=totalPrice+serviceFee;
															System.out.println(totalPrice);
														}else{
															System.out.println("Your Shopping cart is Empty!");
														}
														
														////////////////////////
														System.out.println("Enter an Option:");
														System.out.println("1-delete any Of the order");
														System.out.println("2-continue to bye food");
														System.out.println("3-confirm your shopping");
														int num3=scan.nextInt();
														switch(num3) {
														case 1:{//delete food
															outer4:	while(true) {
																if(cartArr.isEmpty()==true) {
																	System.out.println("Your Shopping Cart is Empty!");
																	break outer4;
																}
																System.out.println("Enter the name of food");
																String foodstr2=scan.next();
																for(int i=0;i<cartArr.size();i++) {
																	if(cartArr.get(i).nameOfFood.equals(foodstr2)) {
																		cartArr.remove(i);
																		break;
																	}
																}
																System.out.println("Your New Shopping cart:");
																totalPrice=0;
																if(cartArr.isEmpty()==false) {
																	for(CartManager element:cartArr) {
																		
																		System.out.println(element);
																		totalPrice=totalPrice+(element.count)*(element.price);
																		
																	}
																	System.out.println(totalPrice);
																}else{
																	System.out.println("Your Shopping cart is Empty!");
																}
																System.out.println("Whould You Like to Delete More Food From Your Shopping Cart");
																System.out.println("Please type yes or no");
																String answer;
																while (true) {
																  answer = scan.nextLine().trim().toLowerCase();
																  if (answer.equals("y")) {
																    continue outer4; 
																  } else if (answer.equals("n")) {
																    break outer4;
																   
																  } else {
																     System.out.println("Sorry, I didn't catch that. Please answer y/n");
																  }
																}
															
															}//end of while outer 4
														}
														case 2://continue to bye food
															break;
														case 3:{//confirm shopping
															
//														
															
															String nameOfCustomer;
															String postalCode;
															UserManager userManage =new UserManager();
															OrderManager order=new OrderManager();
															User userDb=userManage.checkUserInDb(phoneNumber,con);
															if(userDb!=null) {
																confirm:while(true) {
																	System.out.println("Enter an Option:");
																	System.out.println("1-Confirm Your Shopping");
																	System.out.println("2-Edit User Information");
																	System.out.println("3-Return to main");
																	int key=scan.nextInt();
																	switch (key) {
																	case 1:{
																		order.insertOrderDb(cartArr,phoneNumber,con);
																		userManage.userFile(userDb, cartArr, serviceFee);
																		
																		logger.trace("the shopping info:");
																		for(CartManager element:cartArr) {
																			logger.trace("{}",element);
																		}
																		continue outer;
																		}
																	case 2:{
																		System.out.println("Your Information is:");
																		System.out.println("Username: "+userDb.getName());
																		System.out.println("Postal Code: "+userDb.getPostalCode());
																		System.out.println("Enter Your New Username:");
																		String newUsername=scan.next();
																		System.out.println("Enter Your New Postal Code:");
																		String newPostalcode=scan.next();
																		userManage.editUserInformation(con, phoneNumber, newUsername, newPostalcode);
																		System.out.println("Your Information has been modified successfully!");
																		logger.info("the information for "+phoneNumber+" has been modified!");
																		userDb.name=newUsername;
																		userDb.postalCode=newPostalcode;
																		continue confirm;
																		
																	}
																	case 3:{
																		continue outer2;
																	}

																	}
																	}//end while
																	
															}
															else {
															System.out.println("Enter your name:");
															nameOfCustomer=scan.next();
															System.out.println("Enter your Postal Code:");
															postalCode=scan.next();
															User user=new User(phoneNumber,nameOfCustomer,postalCode);
															
															userManage.addUserToDb(con, user);
															logger.info("new user of "+phoneNumber+" added in data base");
															order.insertOrderDb(cartArr,phoneNumber,con);
															userManage.userFile(user, cartArr, serviceFee);
															order.insertOrderDb(cartArr,phoneNumber,con);
															continue outer;
															}
														}
														}
														
													}
													case 2://continue to add food
														break;
													}
													
													
													System.out.println("Would You like to add more food?");
													System.out.println("Please type yes or no");
													String answer;
													while (true) {
													  answer = scan.nextLine().trim().toLowerCase();
													  if (answer.equals("y")) {
													    continue addfood; 
													  } else if (answer.equals("n")) {
													    addFood=false;
													    break addfood;
													   
													  } else {
													     System.out.println("Sorry, I didn't catch that. Please answer y/n");
													  }
													}
												}//end of while(add food)
												
												System.out.println("Enter an Option:");
												System.out.println("1-see Your shopping cart:");
												System.out.println("2-confirm the shopping");
												int num4=scan.nextInt();
												switch(num4) {
												case 1:{//see shopping cart
													double totalPrice=0;
													if(cartArr.isEmpty()==false) {
														for(CartManager element:cartArr) {
															
															System.out.println(element);
															totalPrice=totalPrice+(element.count)*(element.price);
															
														}
														totalPrice=totalPrice+serviceFee;
														System.out.println(totalPrice);
													}else{
														System.out.println("Your Shopping cart is Empty!");
													}
													break;
												}
												case 2:{
													//confirm shopping
													
													String nameOfCustomer;
													String postalCode;
													UserManager userManage =new UserManager();
													OrderManager order=new OrderManager();
													User userDb=userManage.checkUserInDb(phoneNumber,con);
													if(userDb!=null) {
														confirm:while(true) {
															System.out.println("Enter an Option:");
															System.out.println("1-Confirm Your Shopping");
															System.out.println("2-Edit User Information");
															System.out.println("3-Return to main");
															int key=scan.nextInt();
															switch (key) {
															case 1:{
																order.insertOrderDb(cartArr,phoneNumber,con);
																userManage.userFile(userDb, cartArr, serviceFee);
																
																logger.trace("the shopping info:");
																for(CartManager element:cartArr) {
																	logger.trace("{}",element);
																}
																continue outer;
																}
															case 2:{
																System.out.println("Your Information is:");
																System.out.println("Username: "+userDb.getName());
																System.out.println("Postal Code: "+userDb.getPostalCode());
																System.out.println("Enter Your New Username:");
																String newUsername=scan.next();
																System.out.println("Enter Your New Postal Code:");
																String newPostalcode=scan.next();
																userManage.editUserInformation(con, phoneNumber, newUsername, newPostalcode);
																System.out.println("Your Information has been modified successfully!");
																logger.info("the information for "+phoneNumber+" has been modified!");
																userDb.name=newUsername;
																userDb.postalCode=newPostalcode;
																continue confirm;
																
															}
															case 3:{
																continue outer2;
															}

															}
															}//end while
															
													}
													else {
													System.out.println("Enter your name:");
													nameOfCustomer=scan.next();
													System.out.println("Enter your Postal Code:");
													postalCode=scan.next();
													User user=new User(phoneNumber,nameOfCustomer,postalCode);
													
													userManage.addUserToDb(con, user);
													logger.info("new user of "+phoneNumber+" added in data base");
													order.insertOrderDb(cartArr,phoneNumber,con);
													userManage.userFile(user, cartArr, serviceFee);
													
													continue outer;
													}
													
												}
												}
												break;//switch
												
											}
											case 2://enter another name of restaurant
												continue outer3;
											case 3:{//return to main menu
												continue outer2;
											}
											}
										}else {
											System.out.println("Wrong Option");
											continue outer3;
										}
										
										break;
									

								}//end of while outer 3
								
								
								break;
							}
							case 2:
								continue outer2;
							}
							
							
							//break;
					}
					case(3):{
						continue outer2;
					}
					}//switch 1
				}//end of while outer 2
			}
			else {
				System.out.println("Your phone number is invalid!Please try again");
				continue outer;
			}	
			
			break outer;
			
	}//end of while outer
	
		con.close();
	
	}catch(Exception e){
		System.out.println(e);} 
		
	}
		
		
		
	}


