package test;
import runpale.*;
public class UniteTest {
	public static void main(String []args) {
		System.out.println("Unite test is start");
		testDBconnection();
	}
	private void testSetDB() {
		dbConnector.SetDB();
	}
	private static void testDBconnection() {
		try {
			dbConnector.StartCon();
		} catch (Exception e) {
			 e.printStackTrace();
			// TODO: handle exception
		}
		
	}
}
