package com.bcp.simulator.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(schema = "fnel", name = "tarjeta")
public class Tarjeta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "numero_tarjeta")
  private Integer numeroTarjeta;

  @Column(name = "fecha_expiracion")
  private LocalDateTime fechaExpiracion;
}
