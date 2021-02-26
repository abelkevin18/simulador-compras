package com.bcp.simulator.util;

import java.time.format.DateTimeFormatter;

public class Constants {
  public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");
  public static final String MSG_EMPTY_DNI = "El número de dni no debe ser un valor vacío";
  public static final String MSG_INVALID_DNI = "El número de dni debe tener dígitos y de tamaño 8";
  public static final String MSG_CLIENT_DOES_NOT_EXIST = "Usted aún no es parte de la familia BCP";
  public static final String MSG_EMPTY_CREDIT_CARD = "La TARJETA no debe ser un valor vacío";
  public static final String MSG_INVALID_CREDIT_CARD = "Usted no cuenta con este tipo de tarjeta";
  public static final String MSG_DONT_HAVE_ANY_CREDIT_CARD = "Usted no dispone de ninguna TARJETA";
  public static final String MSG_CURRENCY_NO_ASSOCIATE_PRODUCT = "La MONEDA no está asociada al producto";
  public static final String MSG_EMPTY_CURRENCY = "La MONEDA no debe ser un valor vacio";
  public static final String MSG_TEA_NO_ASSOCIATE_PRODUCT = "La TEA no está asociada al producto";
  public static final String MSG_INVALID_TEA_1 = "La TEA debe ser positivo y mayor a 0.00";
  public static final String MSG_INVALID_TEA_2 = "La TEA debe ser un número decimal";
  public static final String MSG_EMPTY_TEA = "La TEA no debe ser un valor vacio";
  public static final String MSG_INVALID_AMOUNT_1 = "El monto debe ser positivo y mayor a 0.00";
  public static final String MSG_INVALID_AMOUNT_2 = "El monto debe ser un número decimal";
  public static final String MSG_EMPTY_AMOUNT = "El monto no debe ser un valor vacío";
  public static final String MSG_INVALID_QUOTA_1 = "La cuota debe ser mayor a cero y un máximo de 36";
  public static final String MSG_INVALID_QUOTA_2 = "La cuota debe ser un número entero";
  public static final String MSG_EMPTY_QUOTA = "La cuota no debe ser un valor vacío";
  public static final String MSG_INVALID_PAYMENT_DAY_1 = "El día de pago debe ser igual o mayor a uno y un máximo de 30";
  public static final String MSG_INVALID_PAYMENT_DAY_2 = "El día de pago debe ser un número entero";
  public static final String MSG_EMPTY_PAYMENT_DAY = "El día de pago no debe ser un valor vacío";
  public static final String MSG_INVALID_PURCHASE_DATE = "La fecha de compra debe ser igual o mayor a la fecha actual";
  public static final String MSG_INVALID_FORMAT_PURCHASE_DATE = "La fecha de pago no tiene el formato yyyy/MM/dd";
  public static final String MSG_EMPTY_PURCHASE_DATE = "La fecha de pago no debe ser un valor vacío";

  public static final String MSG_SUCCESS_PROCESS = "Se procesó la información correctamente.";
  public static final String MSG_SUCCESS_DETAIL1 = "Cuotas INCLUYEN ITF del 0.005%";
  public static final String MSG_SUCCESS_DETAIL2 = "Cuotas NO incluyen seguro de Desgravamen ni multas";

  public static final String MSG_ERROR_STATUS = "ERROR";
  public static final String MSG_SUCCESS_STATUS = "EXITOSO";

  public static final String FORMAT_DATE = "yyyy/MM/dd";

}
