package Data;

import java.util.*;

import static java.lang.Integer.min;

public class Similarity {
	
	public static double CalcuJaccard(String str1, String str2, int q) {
		HashSet<String> str1Tokens = Tokenize(str1, q); 
		HashSet<String> str2Tokens = Tokenize(str2, q); 

        HashSet<String> allTokens = new HashSet<String>(); 
        allTokens.addAll(str1Tokens);
        allTokens.addAll(str2Tokens);
 
        return (double) ((str1Tokens.size() + str2Tokens.size()) - allTokens.size()) / (double) (allTokens.size()); 
	}
	
	private static HashSet<String> Tokenize(String str, int q) {
		HashSet<String> tokens = new HashSet<String>();
		if (q == 0) {
			String[] temp = str.split(" ");
			for (int i = 0; i < temp.length; i++)
				tokens.add(temp[i]);
		} else {
			for (int i = 0; i < str.length() - q + 1; i++)
				tokens.add(str.substring(i, i + q));
		}
		return tokens;
	}
	
	public static double CalcuEDSim(String str1, String str2) {
		if (str1.equals(str2))
			return 1;
		
		int ed = CalcuED(str1, str2);
		return (1 - (double) ed / Math.max(str1.length(), str2.length()));
	}
	
	public static int CalcuED(String str1, String str2) {
		int ed = 0;
		int r = str1.length() + 1 ;
		int s = str2.length() + 1 ;
		int [][] E = new int[r][s] ;
		E[0][0] = 0;
		char[] string1 = str1.toCharArray();
		char[] string2 = str2.toCharArray();
		for (int x = 1; x <= string1.length; x++) {
			E[x][0] = x;
		}
		for (int y = 1; y <= string2.length; y++) {
			E[0][y] = y;
		}
		for(int j = 0; j < string2.length;j++){
			for (int i = 0; i < string1.length ; i++){
				if(string1[i] == string2[j]){
					E[i+1][j+1] = E[i][j];
				}else {
					int temp = Math.min(E[i][j+1],E[i+1][j]);
					int min = Math.min(E[i][j],temp);
					E[i+1][j+1] = min+1;
				}
			}
		}
		ed = E[r-1][s-1];
		return ed;
		/*
		 * Please implement the calculation of edit distance between two strings
		 * Dynamic programming should be used
		 */
		

	}
	
	// Un-comment following block of code to check the Edit distance and Jaccard similarity.
	

	  public static void main(String[] args) {

		  String str1 = "University";
		  String str2 = "Unvesty";
		  int out = Similarity.CalcuED(str1, str2);
		  System.out.println("Edit Distance = " + out);
//	  double out2 = Similarity.CalcuJaccard(str1, str2, 2);
//	 	System.out.println("Jaccard Coefficient = "+ out2);
//	 	}

	  }
}



