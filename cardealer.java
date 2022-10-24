package javaCw;
import java.security.MessageDigest;
import java.util.regex.*;
import java.util.Scanner;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.io.*;
import java.time.LocalDate;

//numberPlate,model,car type,size(van),colour,milage,accHist,trans,price,available/sold,dateOfArrive,sellingDate
//0          ,1    ,2       ,3        ,4     ,5     ,6      ,7    ,8    ,9             ,10          ,11

//SORT OUT LOGIN SYSTEM - USE ANOTHER WAY OF ENCRYPTION, CAUSES TOO MANY ERRORS - SUCH AS NEWLINES, OR CHARACTERS CHANGING.



public class cardealer{
	public static void main(String[] agrs) throws IOException{
		boolean loggedOn = false;
		//Admin a = new Admin("a");
		//a.createNewCredentials("staff1", "orange", 1);
		//a.createNewCredentials("kate", "disco", 1);  >>>>>>>>>>>REASONS FOR PASSWORDS TO NOT BE USED AND SAY THEY ARE NOT CORRECT<<<<<<<<<<<<<
		//a.createNewCredentials("customer1", "lemon", 0);       -password doesnt go between files and says its not the same. Have no idea how to fix
		//a.createNewCredentials("admin1", "apple", 2);          -creates an error in file with newline. Error caused by encryption algorithm and text files
		//a.createNewCredentials("james", "jaffacake", 1);
		//a.createNewCredentials("jim", "disco", 0);
		Scanner userInput = new Scanner(System.in);
		while(!loggedOn) {
			System.out.println("---Welcome to James' Car Dealer---");
			System.out.println("---Please log in to your account---");
			System.out.print("Username (enter quit to exit): ");
			String username = userInput.nextLine();
			if(username.contentEquals("quit")) {
				break;
			}
			System.out.print("Password: ");
			String[] userInfo = Login(username, userInput.nextLine());
			if(userInfo != null) {
				loggedOn = true;
				switch(userInfo[1]) {
				case "1":
					Staff userS = new Staff(userInfo[0]);
					loggedOn = showUI(userInfo[1], userS, userInput);
					break;
				case "2":
					Admin userA = new Admin(userInfo[0]);
					loggedOn = showUI(userInfo[1], userA, userInput);
					break;
				default:
					role user = new role(userInfo[0]);
					loggedOn = showUI(userInfo[1], user, userInput);
				}
			} else {
				loggedOn = false;
			}
		}
		userInput.close();
		
	}
	private static boolean showUI(String role, Object user, Scanner a) throws IOException {
		boolean[] ui = {true,false,false,false,false,false,false,false};
		boolean carryOn = true;
		int choice = 0;
		String choicestr = "0";
		int max = 2;
		switch(role) {
		case "2":
			for(int i=1; i<7;i++) {
				ui[i] = true;
			}
			max = 8;
			break;
		case "1":
			for(int i=1; i<6;i++) {
				ui[i] = true;
			}
			max = 7;
			break;
		}
		while (carryOn) {
			if (ui[0]) {
				System.out.println("\n1 - Search cars");
			}
			if (ui[1]) {
				System.out.println("2 - Add car");
			}
			if (ui[2]) {
				System.out.println("3 - Add cars");
			}
			if (ui[3]) {
				System.out.println("4 - Sell car");
			}
			if (ui[4]) {
				System.out.println("5 - Print cars");
			}
			if (ui[5]) {
				System.out.println("6 - Calculate revenue");
			}
			if (ui[6]) {
				System.out.println("7 - Create new credentials");
			}
			System.out.println(max + " - Log off");
			System.out.print("\nMake your choice (1-" + max + "): ");
			choicestr = a.nextLine();
			if(choicestr.contentEquals("")) {
				choicestr="0";
			}
			choice = Integer.parseInt(choicestr);
			while (choice > max || choice < 1) {
				System.out.print("\nPlease choose a value between (1-" + max + "): ");
				choicestr = a.nextLine();
				if(choicestr.contentEquals("")) {
					choicestr="0";
				}
				choice = Integer.parseInt(choicestr);
			}
			System.out.println();
			switch (max) {
			case 2:
				switch (choice) {
				case 1:
					searchUI(user, role, a);
					break;
				case 2:
					carryOn = false;
					break;
				}
				break;
			case 7:
				switch (choice) {
				case 1:
					searchUI(user, role, a);
					break;
				case 2:
					addCarUI(user, a);
					break;
				case 3:
					addCarsUI(user, a);
					break;
				case 4:
					sellCarUI(user, a);
					break;
				case 5:
					printCarsUI(user, a);
					break;
				case 6:
					calcRevUI(user, a);
					break;
				case 7:
					carryOn = false;
					break;
				}
				break;
			case 8:
				switch (choice) {
				case 1:
					searchUI(user, role, a);
					break;
				case 2:
					addCarUI(user, a);
					break;
				case 3:
					addCarsUI(user, a);
					break;
				case 4:
					sellCarUI(user, a);
					break;
				case 5:
					printCarsUI(user, a);
					break;
				case 6:
					calcRevUI(user, a);
					break;
				case 7:
					newCredUI(user, a);
					break;
				case 8:
					carryOn = false;
					break;
				}
				break;
			}
		}
		return false;
	}
	
	private static void searchUI(Object user, String role, Scanner a) throws IOException {
		String choice = "0";
		int choiceX = 0;
		System.out.println("What would you like the search by?");
		System.out.println("1 - Model + Transmission");
		System.out.println("2 - Colour");
		System.out.println("3 - Number of Seats");
		System.out.println("4 - Van size");
		System.out.println("5 - Exit");
		System.out.print(">>> ");
		choice = a.nextLine();
		if(choice.contentEquals("")) {
			choice="0";
		}
		choiceX = Integer.parseInt(choice);
		while(choiceX > 5 || choiceX < 1) {
			System.out.println("\nPlease choose a value between (1-5)");
			System.out.print(">>> ");
			choice = a.nextLine();
			if(choice.contentEquals("")) {
				choice="0";
			}
			choiceX = Integer.parseInt(choice);
		}
		switch(choiceX) {
		case 1:
			System.out.print("Enter the model you wish to search: ");
			String model = a.nextLine();
			System.out.print("Enter the transmission you wish to search: ");
			String trans = a.nextLine();
			while(!validate(trans, new String[] {"manual","automatic"})) {
				System.out.print("Enter the transmission type (manual or automatic): ");
				trans = a.nextLine();
			}
			if(Integer.parseInt(role) >= 1) {
				((Staff) user).searchCar(model, trans);
			} else {
				((role) user).searchCar(model, trans);
			}
			break;
		case 2:
			System.out.print("Enter the colour you wish to search: ");
			String colour = a.nextLine();
			if(Integer.parseInt(role) >= 1) {
				((Staff) user).searchColour(colour);
			} else {
				((role) user).searchColour(colour);
			}
			break;
		case 3:
			System.out.print("Enter the mininum seats you wish to search: ");
			String minSeatsStr = a.nextLine();
			while(!Pattern.matches("[0-9]+", minSeatsStr)) {
				System.out.println("Please enter a number");
				System.out.print("Enter the mininum seats you wish to search: ");
				minSeatsStr = a.nextLine();
			}
			int minSeats = Integer.parseInt(minSeatsStr);
			System.out.print("Enter the maximum seats you wish to search: ");
			String maxSeatsStr = a.nextLine();
			while(!Pattern.matches("[0-9]+", maxSeatsStr)) {
				System.out.println("Please enter a number");
				System.out.print("Enter the maximum seats you wish to search: ");
				maxSeatsStr = a.nextLine();
			}
			int maxSeats = Integer.parseInt(maxSeatsStr);
			if(Integer.parseInt(role) >= 1) {
				((Staff) user).searchSeats(minSeats, maxSeats);
			} else {
				((role) user).searchSeats(minSeats, maxSeats);
			}
			break;
		case 4:
			System.out.print("Enter the van size you wish to search for (small, medium, large): ");
			String size = a.nextLine();
			while(!validate(size, new String[] {"small","medium", "large"})) {
				System.out.print("Enter the van size - (small, medium, large): ");
				size = a.nextLine();
			}
			if(Integer.parseInt(role) >= 1) {
				((Staff) user).searchVans(size);
			} else {
				((role) user).searchVans(size);
			}
			break;
		case 5:
			break;
		}
	}
	
	private static void addCarUI(Object user, Scanner a) throws IOException {
		boolean notHappy = true;;
		String numberPlate = null;
		String model = null;
		String carType = null;
		String size = "";
		String colour = null;
		String millage = null;
		String accHist = "";
		String transmission = null;
		String price = null;
		String arrivalDate = null;
		String sellingDate = null;
		while(notHappy) {
			System.out.print("Enter the number plate: ");
			numberPlate = a.nextLine().toUpperCase();
			while(numberPlate.contentEquals("")) {
				System.out.println("Number plate cannot be empty");
				System.out.print("Enter the number plate: ");
				numberPlate = a.nextLine().toUpperCase();
			}
			System.out.print("Enter the model of the car: ");
			model = a.nextLine();
			while(model.contentEquals("")) {
				System.out.println("Model cannot be empty");
				System.out.print("Enter the model of the car: ");
				model = a.nextLine();
			}
			System.out.print("Enter the car type: ");
			carType = a.nextLine();
			while(!validate(carType, new String[] {"Coupe","MPV","Van","Saloon","SUV","Hatchback"})) {
				System.out.println("Please enter one of the following: Coupe, MPV, Van, Saloon, SUV, Hatchback");
				System.out.print("Enter the car type: ");
				carType = a.nextLine();
			}
			if(carType.contentEquals("Van")) {
				System.out.print("Enter the van size (small, medium, large): ");
				size = a.nextLine();
				while(!validate(size, new String[] {"small","medium", "large"})) {
					System.out.print("Enter the van size - (small, medium, large): ");
					size = a.nextLine();
				}
			}

			System.out.print("Enter the colour of the car: ");
			colour = a.nextLine();
			while(colour.contentEquals("")) {
				System.out.println("Colour cannot be empty");
				System.out.print("Enter the colour of the car: ");
				colour = a.nextLine();
			}
			System.out.print("Enter the millage: ");
			millage = a.nextLine();
			while(!Pattern.matches("[0-9]+", millage)) {
				System.out.println("Please enter a number");
				System.out.print("Enter the millage: ");
				millage = a.nextLine();
			}
			System.out.print("Enter the accident history (if none leave blank): ");
			accHist = a.nextLine();
			System.out.print("Enter the transmission type (manual or automatic): ");
			transmission = a.nextLine();
			while(!validate(transmission, new String[] {"manual","automatic"})) {
				System.out.print("Enter the transmission type (manual or automatic): ");
				transmission = a.nextLine();
			}
			System.out.print("Enter the price of the car: ");
			price = a.nextLine();
			while(!Pattern.matches("[0-9]+", price)) {
				System.out.println("Please enter a number");
				System.out.print("Enter the price of the car: ");
				price = a.nextLine();
			}
			System.out.print("Enter the arrival date with format YYYY-MM-DD (leave blank for today): ");
			arrivalDate = a.nextLine();
			while(!Pattern.matches("\\d\\d\\d\\d-[0-1]\\d-[0-3]\\d", arrivalDate) && !arrivalDate.contentEquals("")) {
				System.out.println("Please use the format  YYYY-MM-DD");
				System.out.print("Enter the arrival date with format YYYY-MM-DD (leave blank for today): ");
				arrivalDate = a.nextLine();
			}
			System.out.print("Enter selling date with format YYYY-MM-DD (if not sold leave blank): ");
			sellingDate = a.nextLine();
			while(!Pattern.matches("\\d\\d\\d\\d-[0-1]\\d-[0-3]\\d", sellingDate) && !sellingDate.contentEquals("")) {
				System.out.println("Please use the format  YYYY-MM-DD");
				System.out.print("Enter selling date with format YYYY-MM-DD (if not sold leave blank): ");
				sellingDate = a.nextLine();
			}
			System.out.println("\n"+numberPlate+","+model+","+carType+","+size+","+colour+","+millage+","+accHist+","+transmission+","+price+","+arrivalDate+","+sellingDate);
			System.out.print("THis is the car you are adding, are you happy with it? y/n: ");
			String happy = a.nextLine();
			while(!validate(happy, new String[] {"y","n","Y","N"})) {
				System.out.print("Please enter y/n: ");
				happy = a.nextLine();
			}
			if(happy.contentEquals("y") || happy.contentEquals("Y")) {
				notHappy = false;
				((Staff) user).addCar(numberPlate, model, carType, size, colour.toLowerCase(), millage, accHist.toLowerCase(), transmission.toLowerCase(), price, arrivalDate, sellingDate);			
			} else {
				System.out.println("Do you wish to continue? y/n: ");
				String yn = a.nextLine();
				while(!validate(yn, new String[] {"y","n","Y","N"})) {
					System.out.print("Please enter y/n: ");
					yn = a.nextLine();
				}
				if(yn.contentEquals("y") || yn.contentEquals("Y")) {
					notHappy = true;
				} else {
					notHappy = false;
				}
			}
		}
		
	}
	
	private static void addCarsUI(Object user, Scanner a) throws IOException {
		System.out.print("Are you sure you want to add cars from 'cars-import.txt'? y/n: ");
		String yn = a.nextLine();
		while(!validate(yn, new String[] {"y","Y","n","N"})) {
			System.out.print("Please enter y/n: ");
			yn = a.nextLine();
		}
		if(yn.contentEquals("y") || yn.contentEquals("Y")) {
			((Staff) user).addCars();
		}
	}
	
	private static void sellCarUI(Object user, Scanner a) throws IOException {
		System.out.print("Enter number plate of car you wish to sell: ");
		String numberPlate = a.nextLine();
		((Staff) user).sellCar(numberPlate);
	}
	
	private static void printCarsUI(Object user, Scanner a) throws IOException {
		System.out.print("Are you sure you want to print cars to 'car-output.txt'? y/n: ");
		String yn = a.nextLine();
		while(!validate(yn, new String[] {"y","Y","n","N"})) {
			System.out.print("Please enter y/n: ");
			yn = a.nextLine();
		}
		if(yn.contentEquals("y") || yn.contentEquals("Y")) {
			((Staff) user).printCars();
		}
	}
	
	private static void calcRevUI(Object user, Scanner a) throws IOException {
		int day = 0;
		int month = 0;
		int year = 0;
		int total = 0;
		System.out.print("Enter the year you wish to calculate revenue for (YYYY): ");
		String yearStr = a.nextLine();
		if(yearStr.contentEquals("")) {
			yearStr="0";
		}
		year = Integer.parseInt(yearStr);
		while(year < 2000 || year > 2019) {
			System.out.println("Please enter a year between 2000-2019");
			System.out.print("Enter the year you wish to calculate revenue for (YYYY): ");
			year = Integer.parseInt(a.nextLine());
		}
		System.out.print("Enter the month you wish to calculate revenue for (MM): ");
		String monthStr = a.nextLine();
		if(monthStr.contentEquals("")) {
			monthStr="0";
		}
		month = Integer.parseInt(monthStr);
		while(month < 1 || month > 12) {
			System.out.println("Please enter a month between 1-12");
			System.out.print("Enter the month you wish to calculate revenue for (MM): ");
			month = Integer.parseInt(a.nextLine());
		}
		System.out.print("Do you wish to choose a specific day? y/n");
		String yn = a.nextLine();
		if(yn.contentEquals("y") || yn.contentEquals("Y")) {
			System.out.print("Enter the day you wish to calculate revenue for (DD): ");
			String dayStr = a.nextLine();
			if(dayStr.contentEquals("")) {
				dayStr="0";
			}
			day = Integer.parseInt(dayStr);
			while(day < 1 || day > 31) {
				System.out.println("Please enter a day between 1-31");
				System.out.print("Enter the day you wish to calculate revenue for (DD): ");
				dayStr = a.nextLine();
				if(dayStr.contentEquals("")) {
					dayStr="0";
				}
				day = Integer.parseInt(dayStr);
			}
			total = ((Staff) user).calcRevenue(day, month, year);
			System.out.println("The total ammount made on " + year + "-" +month+"-"+day+ " was: " +total);
		} else {
			total =((Staff) user).calcRevenue(month, year);
			System.out.println("The total ammount made in " + year + "-" +month+ " was: " +total);
		}
		
	}
	
	private static void newCredUI(Object user, Scanner a) throws IOException {
		String roleStr = "0";
		System.out.print("Enter username: ");
		String username = a.nextLine();
		while(username.contains(",")) {
			System.out.println("Username cannot contain ','");
			System.out.print("Enter username: ");
			username = a.nextLine();
		}
		System.out.print("Enter password: ");
		String password = a.nextLine();
		while(password.contentEquals("")) {
			System.out.println("Password cannot be empty");
			System.out.print("Enter password: ");
			password = a.nextLine();
		}
		int role = 0;
		System.out.print("Enter role number (0-customer, 1-Staff, 2-Admin):");
		roleStr = a.nextLine();
		while(!validate(roleStr,new String[] {"0","1","2"})) {
			System.out.print("Enter role number (0-customer, 1-Staff, 2-Admin):");
			roleStr = a.nextLine();
		}
		role = Integer.parseInt(roleStr);
		System.out.print("Are you sure you want to add this user? y/n: ");
		String yn = a.nextLine();
		while(!validate(yn, new String[] {"y","Y","n","N"})) {
			System.out.print("Please enter y/n: ");
			yn = a.nextLine();
		}
		if(yn.contentEquals("y") || yn.contentEquals("Y")) {
			
			//maybe have something check password to see if it works in the system
			((Admin) user).createNewCredentials(username, password, role);
		}
	}
	
	
	public static boolean validate(Object s, Object validateObj) {
		return s.equals(validateObj);
	}
	
	public static boolean validate(Object s, Object[] validateObjs) {
		boolean validate = false;
		for(Object obj : validateObjs) {
			if(s.equals(obj)) {
				validate = true;
	
			}
			//System.out.println(obj + " : " + s + " : " + validate);
		}
		return validate;
	}
	
	
	private static String[] Login(String user, String pass) throws IOException{
		String encryptedPass = null;
		MessageDigest password;
		try {
			password = MessageDigest.getInstance("SHA-256");
			password.update(pass.getBytes());
			encryptedPass = new String(password.digest());
		} catch (NoSuchAlgorithmException e) {
			System.out.println("No such algorithm 'SHA-256'");
		}
		ReadFile usersFileLogin = new ReadFile("cars-users.txt");
		usersFileLogin.getFile();
		String[] userCheck = usersFileLogin.searchUser(user);
		if(userCheck == null) {
			System.out.println("User not found\n");
			encryptedPass = null;
			return null;
		} else if (!(encryptedPass.equals(userCheck[1]))){
			System.out.println("Password is not correct");
			encryptedPass = null;
			pass = null;
			return null;
		} else {
			String[] info = {userCheck[0], userCheck[2]};
			encryptedPass = null;
			pass = null;
			return info;
		}
	}
}



class role {
	//default role is customer
	String username;
	int role; //0=cutomer, 1=Staff, 2=Admin
	public role(String user) {
		this.username = user;
		this.role = 0;
	}
	
	public void searchCar(String model, String trans) throws IOException {
		ReadFile cardb = new ReadFile("cars-database.txt");
		List<String[]> cars = cardb.getFile();
		List<String[]> result = new ArrayList<>();
		for(String[] car : cars) {
			if(car[9].contentEquals("available")) {
				if(car[1].contentEquals(model) && car[7].contentEquals(trans)) {
					result.add(car);
				}
			}
		}
		if(result.size() == 0) {
			System.out.println("No cars can be found with the prequistes given");
		} else {
			printList(result);
		}
	}
	public void searchColour(String colour) throws IOException{
		ReadFile cardbColour = new ReadFile("cars-database.txt");
		List<String[]> cars = cardbColour.getFile();
		List<String[]> result = new ArrayList<>();
		for(String[] car : cars) {
			if(car[9].contentEquals("available")) {
				if(car[4].contentEquals(colour)) {
					result.add(car);
				}
			}
		}
		if(result.size() == 0) {
			System.out.println("No cars can be found with the prequistes given");
		} else {
			printList(result);
		}
	}
	public void searchSeats(int minSeats, int maxSeats) throws IOException{
		ReadFile cardbSeats = new ReadFile("cars-database.txt");
		List<String[]> cars = cardbSeats.getFile();
		List<String[]> result = new ArrayList<>();
		for(String[] car : cars) {
			if(car[9].contentEquals("available")) {
				int[] seatsInfo = typeToSeats(car[2]);
				if(seatsInfo[1] <= maxSeats && seatsInfo[1] >= minSeats) {
					result.add(car);
				}
			}
		}
		if(result.size() == 0) {
			System.out.println("No cars can be found with the prequistes given");
		} else {
			printList(result);
		}
	}
	public void searchVans(String size) throws IOException{
		ReadFile cardbVan = new ReadFile("cars-database.txt");
		List<String[]> cars = cardbVan.getFile();
		List<String[]> result = new ArrayList<>();
		for(String[] car : cars) {
			if(car[9].contentEquals("available")) {
				if(car[2].contentEquals("Van") && car[3].contentEquals(size)) {
					result.add(car);
				}
			}
		}
		if(result.size() == 0) {
			System.out.println("No cars can be found with the prequistes given");
		} else {
			printList(result);
		}
	}
	
	private void printList(List<String[]> x) {
		if(this.role == 0) {
			for(String[] y : x) {
				for(Integer i = 0; i < 11; i++) {
					if(!i.equals(6)) {
						if(!y[i].contentEquals("")) {
							System.out.print(y[i] + ", ");
						}
					}
				}
				System.out.print("\n");
			}
		} else {
		for(String[] y : x) {
				for(Integer i = 0; i < 11; i++) {
					if(!y[i].contentEquals("")) {
						System.out.print(y[i] + ", ");
					}
				}
				System.out.print("\n");
			}
		}
	}
	
	private int[] typeToSeats(String type) {
		int[] result = {5, 5};
		if(type.contentEquals("Coupe")) {
			result[0] = 2;
			result[1] = 2;
		} else {
			result[0] = 5;
			if(type.contentEquals("MPV")) {
				result[1] = 7;
			} else if(type.contentEquals("Van")) {
				result[1] = 3;
			} else if(type.contentEquals("Hatchback")) {
				result[1] = 4;
			} else {
				result[1] = 5;
			}
		}
		return result;
	}
}

class Staff extends role {
	public Staff(String user) {
		super(user);
		this.role = 1;
	}
	
	public void addCars() throws IOException {
		ReadFile importCars = new ReadFile("cars-import.txt");
		List<String[]> carsImport = importCars.getFile();		
		for(String[] carImport : carsImport) {
			ReadFile cardata = new ReadFile("cars-database.txt");
			List<String[]> cars = cardata.getFile();
			boolean found = false;
			for(String[] car : cars) {
				if(car[0].contentEquals(carImport[0])) {
					found = true;
				}
			}
			if(!found) {
				WriteFile cardb = new WriteFile("cars-database.txt");
				String available = "sold";
				if(carImport[10].contentEquals("")){
					available = "available";
				}
				if(carImport[9].contentEquals("")) {
					carImport[9] = LocalDate.now().toString();
				}
				String[] carWrite = {carImport[0],carImport[1],carImport[2],carImport[3],carImport[4].toLowerCase(),carImport[5],carImport[6].toLowerCase(),carImport[7].toLowerCase(),carImport[8],available,carImport[9],carImport[10]};
				cardb.append(String.join(",", carWrite));
				cardb.close();
			}
		}
		
	}
	
	public void addCar(String numberPlate, String model, String carType, String size, String colour, String millage, String accHist, String transmission, String price, String arrivalDate, String sellingDate) throws IOException {
		ReadFile cardbAdd = new ReadFile("cars-database.txt");
		List<String[]> cars = cardbAdd.getFile();
		boolean found = false;
		for(String[] car : cars) {
			if(numberPlate.contentEquals(car[0])) {
				found = true;
			}
		}
		if(!found) {
			WriteFile carPrint = new WriteFile("cars-database.txt");
			String available;
			if(accHist.contentEquals("")){
				accHist = "none";
			}
			if(sellingDate.contentEquals("")) {
				available = "available";
			} else {
				available = "sold";
			}
			if(arrivalDate.contentEquals("")) {
				arrivalDate = LocalDate.now().toString();
			}
			String[] car = {numberPlate, model, carType, size, colour, millage, accHist, transmission, price, available, arrivalDate, sellingDate};
			carPrint.append(String.join(",", car));
			carPrint.close();
		} else {
			System.out.println("Car alrady exists in database");
		}
	}
	
	public void sellCar(String numberPlate) throws IOException {
		ReadFile cardbSell = new ReadFile("cars-database.txt");
		List<String[]> cars = cardbSell.getFile();
		boolean found = false;
		int i = 0;
		for(String[] car : cars) {
			if(numberPlate.contentEquals(car[0])) {
				found = true;
				if(car[9].contentEquals("available")) {
					WriteFile change = new WriteFile("cars-database.txt");
					//System.out.println(car[0]+","+car[1]+","+car[2]+","+car[3]+","+car[4]+","+car[5]+","+car[6]+","+car[7]+","+car[8]+","+car[9]+","+car[10]+","+car[11]);
					car[9] = "sold";
					car[11] = LocalDate.now().toString();
					//System.out.println(car[0]+","+car[1]+","+car[2]+","+car[3]+","+car[4]+","+car[5]+","+car[6]+","+car[7]+","+car[8]+","+car[9]+","+car[10]+","+car[11] + "\n");
					change.changeLine(car , i);
					change.close();
				} else {
					System.out.println("This car has already been sold");
				}
			}
			i++;
		}
		if(!found) {
			System.out.println("No car has this number plate");
		}
	}
		
	public void printCars() throws IOException {
		ReadFile cardbPrint = new ReadFile("cars-database.txt");
		WriteFile carPrint = new WriteFile("cars-output.txt");
		List<String[]> cars = cardbPrint.getFile();
		List<String[]> result = new ArrayList<>();
		String[] available = {"Available cars: "};
		String[] sold = {"Sold Cars: "};
		result.add(sold);
		for(String[] car : cars) {
			if(car[9].contentEquals("sold")) {
				result.add(car);
			}
		}
		result.add(available);
		for(String[] car : cars) {
			if(car[9].contentEquals("available")) {
				result.add(car);
			}
		}
		for(String[] x : result) {
			carPrint.write(x);
		}
		carPrint.close();
	}
	
	public int calcRevenue(int month, int year) throws IOException{
		if(month > 12 || month < 0) {
			System.out.println("Month out of bounds");
			return 0;
		} else if(year > 2019 || year < 0) {
			System.out.println("Year out of bounds");
			return 0;
		} else {
			ReadFile cardbCalc1 = new ReadFile("cars-database.txt");
			List<String[]> cars = cardbCalc1.getFile();
			int total = 0;
			for(String[] car : cars) {
				if(car[9].contentEquals("sold")) {
					String[] date = car[11].split("-");
					if(Integer.parseInt(date[1]) == month && Integer.parseInt(date[0]) == year) {
						total = total + Integer.parseInt(car[8]);
					}
				}
			}
			return total;
		}
	}
	
	public int calcRevenue(int day, int month, int year) throws IOException{
		if(day > 31 || day < 0) {
			System.out.println("Day out of bounds");
			return 0;
		}else if(month > 12 || month < 0) {
			System.out.println("Month out of bounds");
			return 0;
		} else if(year > 2019 || year < 0) {
			System.out.println("Year out of bounds");
			return 0;
		} else {
			ReadFile cardbCalc2 = new ReadFile("cars-database.txt");
			List<String[]> cars = cardbCalc2.getFile();
			int total = 0;
			for(String[] car : cars) {
				if(car[9].contentEquals("sold")) {
					String[] date = car[11].split("-");
					if(Integer.parseInt(date[2]) == day && Integer.parseInt(date[1]) == month && Integer.parseInt(date[0]) == year) {
						total = total + Integer.parseInt(car[8]);
					}
				}
			}
			return total;
		}
	}
}

class Admin extends Staff {

	public Admin(String user) {
		super(user);
		this.role = 2;
	}
	
	public boolean createNewCredentials(String username, String pass, int role) throws IOException{
		ReadFile usersStuff = new ReadFile("cars-users.txt");
		List<String[]> users = usersStuff.getFile();
		boolean found = false;
		for(String[] user : users) {
			if(user[0].contentEquals(username)) {
				found = true;
			}
		}
		if(found) {
			System.out.println("Invaild username - already in the system");
			return false;
		} else {
			//encrytps password with SHA-256
			MessageDigest password;
			try {
				password = MessageDigest.getInstance("SHA-256");
				password.update(pass.getBytes());
				String encryptedPass = new String(password.digest());

				//write to file cars-users.txt
				String credientials = username + "," + encryptedPass + "," + Integer.toString(role);
				WriteFile file = new WriteFile("cars-users.txt");
				file.append(credientials);
				file.close();

			} catch (NoSuchAlgorithmException e) {
				System.out.println("No such algorithm 'SHA-256'");
			}
			return true;
		}
	}
}

class ReadFile{
	//making an instance of the class enabled you to read fomr the file specified.
	//reads file using csv, uses buffered reader to read lines at a time.
	private List<String[]> csv = new ArrayList<>();
	private BufferedReader fr;
	public ReadFile(String file){
		try {
			fr = new BufferedReader(new FileReader(file));
			this.read(fr);
		} catch (IOException e) {
			System.out.println("Error 404 - "+ file +" not Found");
		}
	}
	private void read(BufferedReader reader) throws IOException {
		csv = new ArrayList<>();
		String line;
		while((line = reader.readLine()) != null) {
			String[] csvLine = line.split(",",-1);
			csv.add(csvLine);
		}
		reader.close();
	}
	public List<String[]> getFile() throws IOException {
		/*for(String[] a : csv) {
			for(String b : a) {
				System.out.println(b);
			}
			System.out.println();
		}*/
		return csv;
	}

	public String[] searchUser(String user) {
		int line = 0;
		boolean found = false;
		for(String[] userInfo : csv) {
			
			if(user.contentEquals(userInfo[0]) || found) {
				found = true;
			} else {
				line++;
			}
		}
		
		if(found) {
			return csv.get(line);
		} else {
			return null;
		}
	}
}

class WriteFile{
	//writes to a file, make an instance and you need only to use the method.
	private BufferedWriter writer;
	private ReadFile reader;
	public WriteFile(String file) {
		try {
			reader = new ReadFile(file);
			writer = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			System.out.println("Error 404 - File not Found");
		}
	}
	public void append(String s) throws IOException {
		//writer trundicates, so have to rewrite everything
		//as there wont be a massive ammount of cars this is fine
		//to do, but in a real world senario you this would
		//be bad as there would be a large ammount of cars.
		for(String[] line : reader.getFile()) {
			writer.write(String.join(",", line));
			writer.newLine();
		}
		writer.write(s);
	}
	public void close() throws IOException {
		writer.flush();
		writer.close();
	}
	
	public void write(String[] s) throws IOException {
		writer.write(String.join(",", s));
		writer.newLine();
	}
	
	public void changeLine(String[] newLine, int index) throws IOException {
		List<String[]> cars = reader.getFile();
		for(int i=0; i < cars.size(); i++) {
			if(i == index) {
				//System.out.println(String.join(",", newLine));
				writer.write(String.join(",", newLine));
			} else {
				//System.out.println(String.join(",", cars.get(i)));
				writer.write(String.join(",", cars.get(i)));
			}
			writer.newLine();
		}
	}
}





















