/*
 * Class name: User.java
 * Description: This class will create users according to the business logic
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class User extends ActionSupport {
	
	private String emailAddress;
	private String name;
	private List<User> userList = new ArrayList<User>(); 
	
	//Taking in the date as a string for now and coming back
	//private Date birthDate;
	
	private String birthDateString;
	private String password;
	private String confirmedPassword;
	private final String INSERT_SQL = "INSERT INTO USERS (email_address, name, birth_date, password) VALUES(?,?,?,?)";
	private final String SELECT_SQL = "SELECT * FROM USERS WHERE email_address =? AND password =?";
	private final String FILTER_SQL = "SELECT name, birth_date, email_address FROM USERS WHERE name=? AND birth_date=? AND email_address=?";
	
	public User() {
		
	}
	
	public User(String name, String DOB, String email) {
		this.name = name;
		this.birthDateString = DOB;
		this.emailAddress = email;
	}
	
	//method will attempt to connect to database and write the new users into it
	public String register() {
		String result = "FAILURE";
		
		if(emailAddress.equalsIgnoreCase("")){
			return result;
		}
		
		else if(name.equalsIgnoreCase("")){
			return result;
		}
		
		else if(birthDateString.equalsIgnoreCase("")){
			return result;
		}
		
		
		else if (password.equalsIgnoreCase("")){
			return result;
		}
		
		else if(password.equalsIgnoreCase("")) {
			return result;
		}
		
		else if(!(password.equalsIgnoreCase(confirmedPassword))) {
			return result;
		}
		
		else {
				try {
					//establishing database connection
					Connection con;
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/old_timers?serverTimezone=UTC", "root", "root");
					
					//prepared statement to insert new user into database
					PreparedStatement createUser = con.prepareStatement(INSERT_SQL);
					
					//set paramaters
					createUser.setString(1, emailAddress);
					createUser.setString(2, name);
					//createUser.setDate(3, (java.sql.Date) getBirthDate());
					createUser.setString(3, birthDateString);
					createUser.setString(4, password);
					
					//executing update (surrounding code in try catch for error handling)
					createUser.executeUpdate();
					
				} catch (Exception e) {
					return result;
				}
				
				setEmailAddress("");
				setPassword("");
				result = "SUCCESS";	
		}
				
		return result;
	}
	
	
	public String login() {
		//this will check the email address and password of the user
		String result = "FAILURE";
		
		if (emailAddress.equalsIgnoreCase("")) {
			return result;
		}
		
		else if(password.equalsIgnoreCase("")) {
			return result;
		}
		
		else {
			//establishing database connection
			try {
				Connection con;
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/old_timers?serverTimezone=UTC", "root", "root");
				
				//prepared statement to search for user name and password
				PreparedStatement searchUser = con.prepareStatement(SELECT_SQL);
				
				//pass in parameters
				searchUser.setString(1, emailAddress);
				searchUser.setString(2, password);
				
				ResultSet rs = searchUser.executeQuery();
				
				//if there is a result of the SELECT_SQL query 
				if(rs.next()) {
					result = "SUCCESS";
				}
				else {
					return result;
				}
					
			} catch (Exception e) {
				return result;
			}
		}
			
		return result;
	}
	
	public String getSystemUsers() {
		String result = "FAILURE";
		
		try {
			Connection con;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/old_timers?serverTimezone=UTC", "root", "root");
			
			//prepared statement to search for user name and password
			PreparedStatement filterUser = con.prepareStatement(FILTER_SQL);
			
			//pass in parameters
			filterUser.setString(1, name);
			filterUser.setString(2, birthDateString);
			filterUser.setString(3, emailAddress);
			
			ResultSet rs = filterUser.executeQuery();
			
			//if there is a result of the SELECT_SQL query 
			while(rs.next()) {
				String userName = rs.getString(1);
				String userDOB = rs.getString(2);
				String userEmail = rs.getString(3);
				
				//User aUser = new User(userName, userDOB, userEmail);
				User aUser = new User();
				aUser.setName(userName);
				aUser.setBirthDateString(userDOB);
				aUser.setEmailAddress(userEmail);
				userList.add(aUser);
			}
			result = "SUCCESS";
				
		} catch (Exception e) {
			return result;
		}
		
		return result;
	}
	
	
	
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	public Date getBirthDate() {
		return convertBirthDate();
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	*/

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBirthDateString() {
		return birthDateString;
	}

	public void setBirthDateString(String birthDateString) {
		this.birthDateString = birthDateString;
	}

	public String getConfirmedPassword() {
		return confirmedPassword;
	}

	public void setConfirmedPassword(String confirmedPassword) {
		this.confirmedPassword = confirmedPassword;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	
	
	
	/*
	public Date convertBirthDate() {
		Date dateConvert = null;
		try {
			dateConvert = new SimpleDateFormat("dd/MM/yyyy").parse(this.birthDateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateConvert;
	}
	*/

	
	/*Will try and use this method below to stop myself from connecting to the database everytime I want to do an operation*/
	/*
	public Connection createConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/old_timers?serverTimezone=UTC", "root", "root");
		} catch (SQLException e) {
			
		}
		
		return con;
	}
	*/
	
		
	
}
