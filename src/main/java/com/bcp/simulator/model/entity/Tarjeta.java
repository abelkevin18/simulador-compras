package com.bcp.simulator.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tarjeta")
public class Tarjeta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "tipo_tarjeta")
  private String tipoTarjeta;

  @Column(name = "numero_tarjeta")
  private BigInteger numeroTarjeta;

  @Column(name = "fecha_expiracion")
  private LocalDateTime fechaExpiracion;
}
