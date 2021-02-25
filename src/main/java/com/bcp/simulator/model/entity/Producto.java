package com.bcp.simulator.model.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "producto")
public class Producto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cuenta_bancaria_id")
  private CuentaBancaria cuentaBancaria;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tarjeta_id")
  private Tarjeta tarjeta;

  @Column(name = "codigo_producto")
  private Integer codigoProducto;

  private Double tea;

  private String moneda;


}
