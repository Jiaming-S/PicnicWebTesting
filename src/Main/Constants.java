package Main;

import java.time.Duration;

public class Constants {
  public static final int MAX_RELOAD_ATTEMPTS = 3;

  public static final int SHORT_WAIT_TIME = 1000;

  public static final Duration MAX_PAGE_LOAD_TIME = Duration.ofSeconds(20);
  public static final Duration MAX_ELEMENT_LOAD_TIME = Duration.ofSeconds(10);

  public static boolean LOGGING = true;
  public static boolean STACK_TRACE = true;
}
