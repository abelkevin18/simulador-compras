package com.bcp.simulator.model.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Formulario {
  private String dni;
  private String tarjeta;
  private String moneda;
  private String monto;
  private String cuota;
  private String tea;
  private String diaPago;
  private String fechaPago;
}
