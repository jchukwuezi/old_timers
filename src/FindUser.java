import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

//seperating the code to make it easier
public class FindUser extends ActionSupport {
	User user;
	private ArrayList userList = new ArrayList();
	private final String FILTER_SQL = "SELECT name, birth_date, email_address FROM USERS";
	
	public FindUser() {
		
	}
	
	public ArrayList getUserList() {
		return userList;
	}


	public void setUserList(ArrayList userList) {
		this.userList = userList;
	}


	public String execute() {
		
		try {
			Connection con;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/old_timers?serverTimezone=UTC", "root", "root");
			
			//prepared statement to search for user name and password
			//It might be better to use a normal statment
			//PreparedStatement filterUser = con.prepareStatement(FILTER_SQL);
			//ResultSet rs = filterUser.executeQuery();
			
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(FILTER_SQL);
			
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
			return "SUCCESS";
				
		} catch (Exception e) {
			return "FAILURE";
		}
		
	}

}
