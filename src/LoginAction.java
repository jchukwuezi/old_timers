import java.util.Map;
import java.util.HashMap;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

//This class will be responsible for holding the user's email address in a session
public class LoginAction extends ActionSupport implements SessionAware {
	private String emailAddress, password;
	private SessionMap session;
	
	public LoginAction() {	
		
	}

	@Override
	public void setSession(Map map) {
		session = (SessionMap) map;	
	}
	
	
	public String addToSession() {
		session.put("currentUserEmail", emailAddress);
		session.put("currentUserPassword", password);
		return "SUCCESS";
	}
	
	public String getFromSession() {
		String loggedUserEmail = (String) session.get("currentUserEmail");
		String loggedUserPassword = (String) session.get("currentUserPassword");
		return "SUCCESS";
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
