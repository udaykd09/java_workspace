import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//import org.apache.catalina.storeconfig.IStoreConfig;

public class Login_DAO {

	static final String USER = "root";
	static final String PASS = "root";

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/login_data";

	public static String isUserValid(String Username, String password) {
		String my_return = "";
		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Get data from database for given user
			Statement stmt = conn.createStatement();
			String sql, update_Count, update_isLocked;
			sql = "SELECT * FROM login_info WHERE Username='" + Username + "';";
			ResultSet rs = stmt.executeQuery(sql);

			int count = 0;
			boolean isLocked = false;
			String pass_Check = "";

			// Extract data from result set
			while (rs.next()) {
				count = rs.getInt("count");
				isLocked = rs.getBoolean("isLocked");
				pass_Check = rs.getString("Password");
			}

			// If password is correct and account is unlocked redirect with Valid and set count = 0
			if (password.equals(pass_Check) && !isLocked) {
				update_Count = "UPDATE login_info SET count=0 WHERE Username='" + Username + "';";
				stmt.executeUpdate(update_Count);
				my_return = "Valid";
			} else {
				
				// Lock account if count >= 2
				if (count >= 2) {
					update_isLocked = "UPDATE login_info SET isLocked=1 WHERE Username='" + Username + "';";
					stmt.executeUpdate(update_isLocked);
					my_return = "Deny";
				}
				// Warn the user for wrong password and increment the count of incorrect attempts
				else {
					count++;
					update_Count = "UPDATE login_info SET count=" + count + " WHERE Username='" + Username + "';";
					stmt.executeUpdate(update_Count);
					my_return = "Warn";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			;
		}

		return my_return;

	}
}
