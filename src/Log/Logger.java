package Log;

import java.time.format.DateTimeFormatter;

import Main.Constants;

import java.time.LocalDateTime;

public class Logger {
	private static final String RED = "\033[0;31m";
	private static final String GREEN = "\033[0;32m";
	private static final String YELLOW = "\033[0;33m";
	private static final String RESET = "\033[0m";

	public static void log(String message) {
		if (!Constants.LOGGING) return;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String dateTime = dtf.format(LocalDateTime.now());
		System.out.print("[" + dateTime + "]: ");

		if (message.contains("unsuccessful")) {
			System.out.println(toRed(message));
		}
		else if (message.contains("successful")) {
			System.out.println(toGreen(message));
		}
		else if (message.contains("Testing")) {
			System.out.println(toYellow(message));
		}
		else {
			System.out.println(message);
		}
	}

	public static String toRed (String message) {
		return RED + message + RESET;
	}

	public static String toGreen (String message) {
		return GREEN + message + RESET;
	}

	public static String toYellow (String message) {
		return YELLOW + message + RESET;
	}
}
