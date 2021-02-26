package com.bcp.simulator.dao;

import com.bcp.simulator.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>  {
  @Query("select c from Cliente c where c.dni = :dni")
  Optional<Cliente> findByDni(@Param("dni") String dni);
}
