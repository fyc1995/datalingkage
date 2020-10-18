package Data;

import Oracle.DBConnection;

import java.util.HashSet;

public class DataStatistics {

	public static void main(String[] args) {
		/*
		 * Please implement the Task 1 code here, including both two sub-tasks
		 */
		int count = 0;
		HashSet<String> citySet = new HashSet();
		DBConnection.OpenConnection();
		String query = "SELECT * FROM RESTAURANT";
		Restaurant[] restaurants = DBConnection.ExecuteQuery(query);
		//DBConnection.CloseConnection();
		for (Restaurant r1:restaurants) {
			if(r1.GetCity().equals("new york")){
				count++;
			}else if(r1.GetCity().equals("new york city")){//字符串比较相等用equals
				count++;
			}
		}
		System.out.println("the quantity  is "+count);
//		String query1 = "SELECT City FROM RESTAURANT group by City";
//		Restaurant[] restaurants1 = DBConnection.ExecuteQuery(query1);
		for (Restaurant r2:restaurants) {
			citySet.add(r2.GetCity());//hashset 自动覆盖重复值
		}
		System.out.println("total number of distinct values in city attribute:"+citySet.size());
		System.out.println("Student_ID S45846610");
	}
}
