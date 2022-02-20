package user;

public class UserDTO {
	
	private String userID;
	private String userPassword;
	private String userEmail;
	private String userEmailHash;
	private boolean userEmailcheked;
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserEmailHash() {
		return userEmailHash;
	}
	public void setUserEmailHash(String userEmailHash) {
		this.userEmailHash = userEmailHash;
	}
	public boolean isUserEnmailcheked() {
		return userEmailcheked;
	}
	public void setUserEnmailcheked(boolean userEnmailcheked) {
		this.userEmailcheked = userEnmailcheked;
	}
	public UserDTO() {
		
	}
	public UserDTO(String userID, String userPassword, String userEmail, String userEmailHash,
			boolean userEmailcheked) {
		super();
		this.userID = userID;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.userEmailHash = userEmailHash;
		this.userEmailcheked = userEmailcheked;
	}
	

}
