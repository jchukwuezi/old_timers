
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

public class FindPost extends ActionSupport {
	private ArrayList postList = new ArrayList();
	private final String FILTER_SQL = "SELECT * FROM POSTS";
	private final String GET_ID_SQL = "SELECT id FROM posts ORDER BY id ASC";
	
	
	public String execute() {
		try {
			Connection con;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/old_timers?serverTimezone=UTC", "root", "root");
			
			//statement to search for content, date, user email
			PreparedStatement filterUser = con.prepareStatement(FILTER_SQL);
			ResultSet rs = filterUser.executeQuery();
			
			//statement to find the id and set current post to the id
			PreparedStatement getId = con.prepareStatement(GET_ID_SQL);
			ResultSet idSet = getId.executeQuery();
			
			//if there is a result of the SELECT_SQL query 
			while(rs.next()) {
				int id = 0;
				
				if (idSet.next()) {
					id = idSet.getInt(1);
				}
				String content = rs.getString(1);
				String date = rs.getString(2);
				String email = rs.getString(3);
				
				Post aPost = new Post();
				aPost.setId(id);
				aPost.setContent(content);
				aPost.setCurrentDateTime(date);
				aPost.setUserEmail(email);
				
				postList.add(aPost);
			}
			return "SUCCESS";
				
		} catch (Exception e) {
			return "FAILURE";
		}
		
	}
	
	
	public ArrayList getPostList() {
		return postList;
	}


	public void setPostList(ArrayList postList) {
		this.postList = postList;
	}
		
	
}
