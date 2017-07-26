package test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class App {

	public static void main(String[] args) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();

		System.out.println(dateFormat.format(date));

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		System.out.println(dateFormat.format(cal.getTime()));

		Date expirate = cal.getTime();
		Calendar a = Calendar.getInstance();
		Date now = a.getTime();

		if ((expirate.getTime() - now.getTime()) <= 0) {
			System.out.println("espirado");
		} else {
			System.out.println("vigente");
		}
	}
}
