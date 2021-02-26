package com.bcp.simulator.model.api.simulator;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SimulatorOperator {

  private boolean isValidRequest;
  private Double tea;
  private Integer cuota;
  private Double monto;
  private LocalDate fechaCompra;
  private Integer diaPago;
  private String moneda;
}
