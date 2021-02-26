package com.bcp.simulator.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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

  public static boolean isValidAmount(Double value) {
    if (value > 0.0) {
      return true;
    } else {
      return false;
    }
  }

  public static boolean isValidQuota(Integer value) {
    if (value > 0 && value <= 36) {
      return true;
    } else {
      return false;
    }
  }

  public static boolean isValidPaymentDate(Integer value) {
    if (value > 0 && value <= 30) {
      return true;
    } else {
      return false;
    }
  }

  public static boolean isValidTea(Double value) {
    if (value > 0.0) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Validate if string value is Date.
   */
  public static boolean isDate(String value) {
    try {
      LocalDate.parse(value, Constants.DATE_FORMATTER);
      return true;
    } catch (DateTimeParseException ex) {
      return false;
    }
  }

  public static Double calcularImporteMensual(Double tea, Integer numeroCuotas, Double monto) {
    Double tem = calcularTem(tea);
    Double frc = calcularFrc(numeroCuotas, tem);
    return calcularCuota(monto, frc);
  }

  private static Double calcularTem(Double tea) {
    tea= tea/100;
    float expTem = (float) 1/12;
    return Math.pow(1+tea,expTem) - 1;
  }

  private static Double calcularFrc(Integer numeroCuotas, Double tem) {
    Double dividendoFrc = tem*Math.pow(1+tem, numeroCuotas);
    Double divisorFrc = Math.pow(1+tem, numeroCuotas) - 1;
    return dividendoFrc / divisorFrc;
  }

  private static Double calcularCuota(Double monto, Double frc){
    Double cuotaPrevia = monto*frc;
    Double itf = (0.005/100)*cuotaPrevia;
    return cuotaPrevia + itf;
  }



}
