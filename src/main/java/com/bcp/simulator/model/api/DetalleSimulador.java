package com.bcp.simulator.model.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DetalleSimulador {
  private String numeroCuota;
  private String montoCuota;
  private String moneda;
}
