package gestori;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;



public class GestoreDatiPersistenti {

	public static Connection getConnection()
	{
		Connection con = null;
		try 
		{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fgrtournament?user=root&password=nonnonia9");
		}
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		
		}
		return con;
	}

	}
