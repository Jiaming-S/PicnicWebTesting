package Logger;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Logger {
	private static final String RED = "\033[0;31m";
	private static final String GREEN = "\033[0;32m";
	private static final String YELLOW = "\033[0;33m";
	private static final String RESET = "\033[0m";

	public static void log(String message) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String dateTime = dtf.format(LocalDateTime.now());
		System.out.print("[" + dateTime + "]: ");

		if (message.contains("unsuccessful")) {
			System.out.println(RED + message + RESET);
		}
		else if (message.contains("successful")) {
			System.out.println(GREEN + message + RESET);
		}
		else if (message.contains("Testing")) {
			System.out.println(YELLOW + message + RESET);
		}
		else {
			System.out.println(message);
		}
	}
}
