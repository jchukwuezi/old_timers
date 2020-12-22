import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;




import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

//this class will be used to form and find friendships between two users
public class Friendship extends ActionSupport {
	private String userEmail1;
	private String userEmail2;
	private String emailAddress;
	private final String INSERT_SQL = "INSERT INTO FRIENDSHIPS (user1email, user2email) VALUES (?,?)";
	private final String CHECK_SQL = "SELECT * FROM USERS WHERE email_address =?";
	
	
	//when add button is pressed a friendship will be created
		
	public String createFriendship() {
		//user1email will be the current email address in the session
		userEmail1 = (String) ActionContext.getContext().getSession().get("currentUserEmail");
		
		//checking if the name entered in the form is in the data base
		//making database connection
		try {
			Connection con;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/old_timers?serverTimezone=UTC", "root", "root");
			
			//statement to search for content, date, user email
			PreparedStatement checkUser = con.prepareStatement(CHECK_SQL);
			
			//pass in parameters
			checkUser.setString(1, emailAddress);
			
			ResultSet rs = checkUser.executeQuery();
			
			if(rs.next()) {
				userEmail2 = emailAddress;
				//create friendship between userEmail1 and userEmail2
				PreparedStatement createFriends = con.prepareStatement(INSERT_SQL);
				
				//pass in parameters
				createFriends.setString(1, userEmail1);
				createFriends.setString(2, userEmail2);
				
				createFriends.executeUpdate();
			}
			
			else {
				return "FAILURE";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "SUCCESS";
	}


	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	
	
	
	

}
