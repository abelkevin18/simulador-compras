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
  private Double monto;
  private Integer cuota;
  private Double tea;
  private String diaPago;
}
