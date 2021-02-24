package com.bcp.simulator.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {
  public static final List<String> CURRENCY_TYPE = getCardsType();

  private static List<String> getCardsType() {
    return new ArrayList<>(Arrays.asList("SOLES", "DOLARES"));
  }

}
