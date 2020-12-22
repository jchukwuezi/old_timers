package testingClasses;

	

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.Statement;
	import java.util.ArrayList;

	public class PopulatePostList {

		//list to store posts
		ArrayList postList = new ArrayList();
		String FILTER_SQL = "SELECT content, date, user_email FROM POSTS";
		String GET_ID_SQL = "SELECT id FROM posts ORDER BY id ASC";
		testPost post;
		
		//execution method to put posts from database to arraylist
		public void execute() {
			
			try {
				//establishing database connection
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/old_timers?serverTimezone=UTC", "root", "root");
				
					try {
						//PreparedStatement filterUser = con.prepareStatement(FILTER_SQL);
						//ResultSet rs = filterUser.executeQuery();

						Statement statement = con.createStatement();
						ResultSet rs = statement.executeQuery(FILTER_SQL);
						PreparedStatement getId = con.prepareStatement(GET_ID_SQL);
						ResultSet idSet = getId.executeQuery();
						
						while(rs.next()) {
							int id = 0;
							if(idSet.next()) {
								id = idSet.getInt(1);
							}
							String content = rs.getString(1);
							String date = rs.getString(2);
							String email = rs.getString(3);
							
							
							testPost aPost = new testPost();
							
							aPost.setID(id);
							aPost.setContent(content);
							aPost.setCurrentDateTime(date);
							aPost.setUserEmail(email);
							
							System.out.println("ToString method on aPost . . . ");
							System.out.print(aPost.toString());
							
							postList.add(aPost);
						}
						
					} catch (Exception e) {
						System.out.println("Couldn't create aPost");
					}
			
				
			} catch (Exception e) {
				System.out.println("Unsuccessful connecting to the database");
			}
		}
		
		public PopulatePostList() {
			execute();
		
			if (postList.isEmpty()) {
				System.out.println("List of posts are empty");
			}
			else {
				System.out.println("There are posts in the lists");
			}
			
			
		}
		
		
		public ArrayList getPostList() {
			return postList;
		}


		public void setPostList(ArrayList postList) {
			this.postList = postList;
		}
		
		
		public static void main(String[] args) {
			new PopulatePostList();
		}
		
}


