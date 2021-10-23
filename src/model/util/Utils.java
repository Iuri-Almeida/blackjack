package model.util;

public class Utils {
	
	public static Integer tryParseToInt(String str) {
		
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return null;
		}
		
	}

}
