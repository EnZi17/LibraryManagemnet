package myUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ognl.ASTBitNegate;

public class Util {
	
	public static Date StringToDate(String string) {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		 try {
			return dateFormat.parse(string);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	}
	
	public static String DateTotring(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormat.format(date);
	}
	
	public static int StringToDay(String string) {
		
		return Integer.parseInt(string.substring(0,2));
		
	}
	
	public static int StringToMonth(String string) {
		return Integer.parseInt(string.substring(3,5));
	}
	
	public static int StringToYear(String string) {
		return Integer.parseInt(string.substring(6,10));
	}
}
