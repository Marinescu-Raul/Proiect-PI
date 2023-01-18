
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//my
public class DbUtil {
	static String password = "";
	static String user = "root";


	
	public static  void dispaySQLExceptions(SQLException ex) {
		while (ex != null) {
			System.out.println("SQL State:" + ex.getSQLState());
			System.out.println("Error Code:" + ex.getErrorCode());
			System.out.println("Message:" + ex.getMessage());
			Throwable t = ex.getCause();
			while (t != null) {
				System.out.println("Cause:" + t);
				t = t.getCause();
			}
			ex = ex.getNextException();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost/db_proiect";
		return  DriverManager.getConnection(url,user,password);
	}

}

