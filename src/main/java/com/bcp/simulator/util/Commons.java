package com.bcp.simulator.util;

public class Commons {
  /**
   * Validate if string value is Integer.
   */
  public static boolean isInteger(String value) {
    try {
      Integer.parseInt(value);
      return true;
    } catch (NumberFormatException nfe) {
      return false;
    }
  }

  /**
   * Validate if string value is Decimal.
   */
  public static boolean isDecimal(String value) {
    try {
      Double.parseDouble(value);
      return true;
    } catch (NumberFormatException nfe) {
      return false;
    }
  }

  /**
   * Validate DNI.
   */
  public static boolean isValidDni(String value) {
    if (value.matches("[0-9]*")
            && value.length() == 8) {
      return true;
    } else {
      return false;
    }
  }
}
