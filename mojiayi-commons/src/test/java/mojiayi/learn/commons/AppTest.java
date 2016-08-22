package mojiayi.learn.commons;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("d");
		System.out.println(sdf.format(new Date()));
	}
}
