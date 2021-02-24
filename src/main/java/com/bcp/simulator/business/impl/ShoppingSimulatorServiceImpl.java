package com.bcp.simulator.business.impl;

import com.bcp.simulator.business.ShoppingSimulatorService;
import com.bcp.simulator.dao.ClienteRepository;
import com.bcp.simulator.dao.ProductoRepository;
import com.bcp.simulator.model.api.Formulario;
import com.bcp.simulator.model.api.ResponseBody;
import com.bcp.simulator.model.entity.Cliente;
import com.bcp.simulator.model.entity.Producto;
import com.bcp.simulator.util.Commons;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ShoppingSimulatorServiceImpl implements ShoppingSimulatorService {

  @Autowired
  ClienteRepository clienteRepository;

  @Autowired
  ProductoRepository productoRepository;

  @Override
  @Transactional(readOnly = true)
  public void simulatorProcess(Formulario request) {


    ResponseBody responseBody = new ResponseBody();
    List<String> mensajes = new ArrayList<>();

    Double monto = null;
    if (StringUtils.isNotBlank(request.getMonto())) {
      if (Commons.isDecimal(request.getMonto())) {
        monto = Double.parseDouble(request.getMonto());
        if (!Commons.isValidAmount(monto)) {
          mensajes.add("El monto debe ser positivo y mayor a 0.00");
        }
      } else {
        mensajes.add("El monto debe ser un número decimal");
      }
    } else {
      mensajes.add("El monto no debe ser un valor vacío");
    }

    Integer cuota = null;
    if (StringUtils.isNotBlank(request.getCuota())) {
      if (Commons.isInteger(request.getCuota())) {
        cuota = Integer.parseInt(request.getCuota());
        if (!Commons.isValidQuota(cuota)) {
          mensajes.add("La cuota debe ser mayor a cero y un máximo de 36");
        }
      } else {
        mensajes.add("La cuota debe ser un número entero");
      }
    } else {
      mensajes.add("La cuota no debe ser un valor vacío");
    }


    Integer diaPago = null;

    if (StringUtils.isNotBlank(request.getDiaPago())) {
      if (Commons.isInteger(request.getDiaPago())) {
        diaPago = Integer.parseInt(request.getDiaPago());
      } else {
        mensajes.add("El día de pago debe ser un número entero");
      }
    } else {
      mensajes.add("El día de pago no debe ser un valor vacío");
    }

    //validar fecha pago

    List<Producto> productos = null;
    if (StringUtils.isNotBlank(request.getDni())) {
      if (Commons.isValidDni(request.getDni())) {
        Optional<Cliente> clienteOpt = clienteRepository.findByDni(request.getDni());
        if (clienteOpt.isPresent()) {
          Cliente cliente = clienteOpt.get();
          productos = productoRepository.findByIdCliente(cliente.getId()).collect(Collectors.toList());
        } else {
          mensajes.add("Usted aún no es parte de la familia BCP");
        }
      } else {
        mensajes.add("El número de dni debe tener dígitos y de tamaño 8");
      }
    } else {
      mensajes.add("El número de dni no debe ser un valor vacío");
    }

    Producto producto = null;

    String tarjeta = null;
    if (StringUtils.isNotBlank(request.getTarjeta())) {
      tarjeta = request.getTarjeta();
    } else {
      mensajes.add("La TARJETA no debe ser un valor vacío");
    }

    if (!productos.isEmpty() && tarjeta != null) {
      for (Producto p: productos) {
        if (p.getTarjeta().getTipoTarjeta().equals(request.getTarjeta())) {
          producto = p;
        }
      }
      if (producto != null) {

      } else {
        mensajes.add("Tarjeta inválida");
      }
    } else {
      mensajes.add("Usted no dispone de ninguna tarjeta");
    }

    String moneda = null;

    if (StringUtils.isNotBlank(request.getMoneda()) && producto != null) {
      moneda = request.getMoneda();
      if (!moneda.equals(producto.getMoneda())) {
        mensajes.add("La MONEDA no está asociada al producto");
      }
    } else {
      mensajes.add("La MONEDA no debe ser un valor vacio");
    }

    Double tea = null;

    if (StringUtils.isNotBlank(request.getTea()) && producto != null) {
      if (Commons.isDecimal(request.getTea())) {
        if (Commons.isValidTea(tea)) {
          tea = Double.parseDouble(request.getTea());
          if (!tea.equals(producto.getTea())) {
            mensajes.add("La TEA no está asociada al producto");
          }
        } else {
          mensajes.add("La TEA debe ser positivo y mayor a 0.00");
        }
      } else {
        mensajes.add("La TEA debe ser un número decimal");
      }
    } else {
      mensajes.add("La TEA no debe ser un valor vacio");
    }



  }
}
