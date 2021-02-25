package com.bcp.simulator.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "cuenta_bancaria")
public class CuentaBancaria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "numero_cuenta")
  private BigInteger numeroCuenta;

  @Column(name = "fecha_apertura")
  private LocalDateTime fechaApertura;

}
