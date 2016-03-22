package runpale;
import java.sql.*;

public class dbConnector {
	private static final String DB_URL = "localhost";
	private static final String USER = "root";
	private static final String PASS = "hrf031006";
	
	public static void main(String [] args)  {
		
	}
	//This method is to initialize the Database Connection
	static Connection conn;
	public static void StartCon() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Connecting to database...");
	    conn = DriverManager.getConnection(DB_URL,USER,PASS);
	}
	public static ResultSet  SendSql(String sql) {
		if (conn==null){
			//If there hasn't a connection, then create one.
			StartCon();
		}
		Object stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}
	public static ResultSet Select(String TableName,String Name, String Value) {
		String sql = null;
		if(TableName!=null&(Name!=null&Value!=null)){
			sql="SELECT * FROM "+TableName+" WHERE "+Name+" = "+Value;
		}
		
		return SendSql(sql);
	}
	private static String ExplainType(String[][] Type) {
		String Temp = null;
		for (int i = 0; i < Type.length; i++) {
			
			String[] strings = Type[i];
			Temp=Temp+strings[0]+" "+strings[1];
			if (i==Type.length){
				Temp=Temp+",";
			}
		}
		return Temp;
		
	}
	private static void CreateTable(String TableName,String[][] Type) {
		String sql;
		//Formate the CreateTable SQL Sentence
		sql="CREATE TABLE "+TableName +"("+ExplainType(Type)+")";
		SendSql(sql);
	}
	public static void SetDB(String dbName) {
		
		
		//The code below is to create the table of Student, the Main table
		String[][] Type={
				{"ID","varchar(10) NOT NULL PRIMARY KEY"},
				{"EnglishName","varchar(255) NOT NULL"},
				{"ChineseName","varchar(255) NOT NULL"},
				{"Major","varchar(255) NOT NULL"},
				{"Status","varchar(10)"},
				{"Gender","bit"},
				{"",""},
		};
		CreateTable("Student", ExplainType(Type));
	}
}
