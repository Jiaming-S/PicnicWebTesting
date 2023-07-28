package Sel;

public class TestCaseFailedException extends Exception {
  public TestCaseFailedException(String message, StackTraceElement[] stackTrace) {
    super(message + "\n" + formatStackTrace(stackTrace));
  }

  private static String formatStackTrace(StackTraceElement[] stackTrace) {
    StringBuilder sb = new StringBuilder();
    for (StackTraceElement element : stackTrace) sb.append("\tat ").append(element.toString()).append("\n");
    
    return sb.toString();
}
}