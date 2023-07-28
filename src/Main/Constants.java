package Main;

import java.time.Duration;

public class Constants {
  public static final int MAX_RELOAD_ATTEMPTS = 3;

  public static final Duration MAX_PAGE_LOAD_TIME = Duration.ofSeconds(20);
  public static final Duration MAX_ELEMENT_LOAD_TIME = Duration.ofSeconds(10);

  public static final boolean STACK_TRACE = true;
}
