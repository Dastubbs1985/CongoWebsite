/*-----------------------------------------------------------------
 *  Author:         Dale Stubbs
 *	MMU Id:			14024149
 *  Written:    	19/11/2015
 *  Last updated:	15/12/2015
 *
 *  Servlet for the connection to the corresponding database.
 *
 *	Code formatted to the eclipse standard.
 *------------------------------------------------------------------*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@SuppressWarnings("serial")
@WebServlet("/DBConnection")
public class DBConnection extends HttpServlet {
	String driverClassName = "com.mysql.jdbc.Driver";
// Connection variables for my at home database connection
//	 String dBase = "congodatabase";
//	 String dbUser = "DaleStubbs";
//	 String dbPwd = "vikkij09";
//	 String connectionUrl = "jdbc:mysql://localhost:3306/" + dBase;
// Connection variables for my University database connection
	String dbUser = "stubbsd";
	String dbPwd = "kerGfeil9";
	String dBase = "stubbsd";
	String connectionUrl = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk/" + dBase;

	private static DBConnection DBConn = null;

	private DBConnection() {
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
		return conn;
	}

	public static DBConnection getInstance() {
		if (DBConn == null) {
			DBConn = new DBConnection();
		}
		return DBConn;
	}
}