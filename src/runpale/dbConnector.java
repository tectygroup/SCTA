package runpale;
import java.sql.*;
import java.util.Properties ;

public class dbConnector {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/";
	private static final String USER = "root";
	private static final String PASS = "hrf031006";
	
	public static void main(String [] args)  {
		
	}
	//This method is to initialize the Database Connection
	
	public static Connection StartCon() {
		Connection conn;
		System.out.println("Connecting to database...");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			return conn;
		} catch (Exception e) {
			 e.printStackTrace() ;
			// TODO: handle exception
		}
		return null;
	    
	}
	public static void SendSql(String sql) {
		if (conn==null){
			//If there hasn't a connection, then create one.
			try {
				StartCon();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Object stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ResultSet rs = stmt.executeQuery(sql);
		//return rs;
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
				{"Gender","bit NOT NULL"},
				{"TOP100","bit NOT NULL"},
				{"UniversityName","varchar(255)"},
				{"UniversityCountry","varchar(255)"},
				{"UniversityCity","varchar(255)"},
				{"CompanyName","varchar(255)"},
				{"CompanyCountry","varchar(255)"},
				{"CompanyCity","varchar(255)"}
		};
		CreateTable("Student", Type);
		//form the table of message for the program
		String[][] Type1={
			{"Class","varchar(255)"},
			{"dbItemName","varchar(255)"},
			{"enShowName","varchar(255)"},
			{"cnShowName","varchar(255)"}
		};
		CreateTable("dbMessage",Type1);

	}
	public static Void SetDB(){
		SetDB("CSTA");
	}
}
