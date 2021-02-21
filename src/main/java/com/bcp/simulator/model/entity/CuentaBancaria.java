package com.bcp.simulator.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(schema = "fnel", name = "cuenta_bancaria")
public class CuentaBancaria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "numero_cuenta")
  private Integer numeroCuenta;

  @Column(name = "fecha_apertura")
  private LocalDateTime fechaApertura;

}
