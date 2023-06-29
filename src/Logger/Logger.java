package Logger;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Logger {
  private static final String RED = "\033[0;31m";
  private static final String GREEN = "\033[0;32m";
  private static final String RESET = "\033[0m";

  public static void log(String message) {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    if (message.contains("successful")) {
      System.out.println(dtf.format(LocalDateTime.now()) + ": " + GREEN + message + RESET);
    }
    else if (message.contains("unsuccessful")) {
      System.out.println(dtf.format(LocalDateTime.now()) + ": " + RED + message + RESET);
    }
    else {
      System.out.println(dtf.format(LocalDateTime.now()) + ": " + message);
    }
  }
}
