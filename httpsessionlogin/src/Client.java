import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.catalina.storeconfig.IStoreConfig;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


final String USER = "root";
final String PASS = "root";

final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
final String DB_URL = "jdbc:mysql://localhost/login_data";

try {
	// Register JDBC driver
	Class.forName("com.mysql.jdbc.Driver");

	// Open a connection
	Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	
	if(!conn.isClosed()){
		System.out.println("wgjf");
	}
}
catch(Exception e){
	e.printStackTrace();
}


	}

}
