
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Post extends ActionSupport {
	private String content;
	private int id;
	private String userEmail= (String) ActionContext.getContext().getSession().get("currentUserEmail");;
	private String currentDateTime = convertCurrentDateToString();
	private String INSERT_SQL = "INSERT INTO POSTS (content, date, user_email) VALUES(?,?,?)";
	
	public Post() {
		
	}
		
	public Post(String content, String date, String email) {
		this.content = content;
		this.currentDateTime = date;
		this.userEmail = email;
		this.id = id;
	}
	
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getCurrentDateTime() {
		return currentDateTime;
	}
	
	
	public void setCurrentDateTime(String currentDateTime) {
		this.currentDateTime = currentDateTime;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static String convertCurrentDateToString() {
		Date thisDate = new Date();
		SimpleDateFormat dateForm = new SimpleDateFormat("dd/MM/YYYY HH:mm");
		
		String dateTime= dateForm.format(thisDate);
		
		return dateTime;
	}
	
	
	//this method will allow user to make a post
	public String execute() {
		
		if (!(content.equals(""))){
			//make database connection
			try {
				//establishing database connection
				Connection con;
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/old_timers?serverTimezone=UTC", "root", "root");
				
				//prepared statement to insert new user into database
				PreparedStatement createUser = con.prepareStatement(INSERT_SQL);
				
				//set paramaters
				createUser.setString(1, content);
				createUser.setString(2, currentDateTime);
				createUser.setString(3, userEmail);
				
				//executing update (surrounding code in try catch for error handling)
				createUser.executeUpdate();
				
			} catch (Exception e) {
				return "FAILURE";
			}
		}
			return "SUCCESS";
			
	}

	
	public String toString() {
		return
				"\nPost content: " + this.content
				+ "\nPost date: " + this.currentDateTime
				+ "\nPost email: " + this.userEmail;
	}
	
	
	
	
	
	

}
