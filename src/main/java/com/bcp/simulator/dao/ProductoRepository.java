package com.bcp.simulator.dao;

import com.bcp.simulator.model.entity.Producto;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
  @Query("select p from Producto p where p.cliente.id = :idCliente")
  Stream<Producto> findByIdCliente(@Param("idCliente") Integer idCliente);
}
