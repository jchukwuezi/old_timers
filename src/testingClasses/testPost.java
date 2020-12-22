package testingClasses;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;



public class testPost {
	private String content;
	
	//setting it to an email in the database, afterwards using cookies, I will grab the user email
	private String userEmail= "jchukwuezi@gmail.com";
	private String currentDateTime = convertCurrentDateToString();
	private String INSERT_SQL = "INSERT INTO POSTS (content, date, user_email) VALUES(?,?,?)";
	private int ID;
	
	
	public testPost() {
		
	}
	
	public testPost(int id, String content, String date, String email) {
		this.ID = id;
		this.content = content;
		this.currentDateTime = date;
		this.userEmail = email;
	}
	
	public testPost(String content, String date, String email) {
		this.content = content;
		this.currentDateTime = date;
		this.userEmail = email;
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
	
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public static String convertCurrentDateToString() {
		Date thisDate = new Date();
		SimpleDateFormat dateForm = new SimpleDateFormat("dd/MM/YYYY HH:mm");
		
		String dateTime= dateForm.format(thisDate);
		
		return dateTime;
	}
	
	public String toString() {
		return
				"\nPost ID: " + this.ID
				+"\nPost content: " + this.content
				+ "\nPost date: " + this.currentDateTime
				+ "\nPost email: " + this.userEmail;
	}
	
	
}
