package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
  public static Connection getConnection() {
	  try {
		  String dbURL=   "jdbc:mysql://localhost:3306/LectureEvaluation?characterEncoding=UTF-8&serverTimezone=UTC";
		  String dbID="root";
		  String dbPassword = "000000";
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  return DriverManager.getConnection(dbURL,dbID,dbPassword);
	  }catch(Exception e) {
		  e.printStackTrace();
	  }
	  return null;
  }
}
