package com.bcp.simulator.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "cliente")
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String dni;
  private String nombre;

  @Column(name = "apellido_paterno")
  private String apellidoPaterno;

  @Column(name = "apellido_materno")
  private String apellidoMaterno;

  @Column(name = "fecha_nacimiento")
  private LocalDateTime fechaNacimiento;
}
