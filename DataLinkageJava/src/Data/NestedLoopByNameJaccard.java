package Data;

import java.util.ArrayList;
import java.util.List;

import Oracle.DBConnection;

public class NestedLoopByNameJaccard {

	private static double threshold = 0.95;//0.75;
	private static int q = 3;//3;

	public static void main(String[] args) {
//		// option1: read data from database
//		DBConnection.OpenConnection();
//		String query = "SELECT * FROM RESTAURANT";
//		Restaurant[] restaurants = DBConnection.ExecuteQuery(query);
//		DBConnection.CloseConnection();
		
		// option2: read data from csv file, switch to this option by commenting the above code and uncommenting next line
		Restaurant[] restaurants = CSVLoader.restaurantLoader("data\\restaurant.csv");
		
		List<String> result = new ArrayList<String>();
		Restaurant restaurant1 = null, restaurant2 = null;
		int id1 = 0, id2 = 0;
		String name1 = null, name2 = null;
		long start = System.currentTimeMillis();
		for (int i = 0; i < restaurants.length - 1; i++) {
			restaurant1 = restaurants[i];
			id1 = restaurant1.GetID();
			name1 = restaurant1.GetName();
			for (int j = i + 1; j < restaurants.length;j++) {
				restaurant2 = restaurants[j];
				id2 = restaurant2.GetID();
				name2 = restaurant2.GetName();				
				double sim = Similarity.CalcuJaccard(name1, name2, q);
				if (sim >= threshold)
					result.add(id1 + "_" + id2);
			}
		}
		long end = System.currentTimeMillis();
		long time = end - start;
		System.out.println("Time=" + time + "ms");
		System.out.println("threshold:"+ threshold+" q:"+q);
		System.out.println("SID S45846610");
		Measurement.LoadBenchmark();
		Measurement.CalcuMeasure(result);
	}

}
