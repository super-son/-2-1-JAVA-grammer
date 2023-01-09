package layout.demo.tool;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Holder {
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	public static void pause() {
		System.out.println("Holder.pause(): press [ENTER]...");
		try {
			in.readLine();
		} catch (java.io.IOException e) {
		}
	}
	
	public static void pause(String message) {
		System.out.println(message);
		try {
			in.readLine();
		} catch (java.io.IOException e) {
		}
	}
}
