package com.bcp.simulator.model.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ResponseBody {
  private String codigo;
  private List<String> mensajes;
  private List<DetalleSimulador> detalles;

}
