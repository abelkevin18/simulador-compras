package com.bcp.simulator.business.impl;

import com.bcp.simulator.business.ShoppingSimulatorService;
import com.bcp.simulator.dao.ClienteRepository;
import com.bcp.simulator.dao.ProductoRepository;
import com.bcp.simulator.model.api.DetalleSimulador;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
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
  public ResponseBody simulatorProcess(Formulario request) {

    ResponseBody responseBody = new ResponseBody();
    List<String> mensajes = new ArrayList<>();

    boolean isValidRequest = true;
    List<Producto> productos = null;
    //boolean validDni = true;
    if (StringUtils.isNotBlank(request.getDni())) {
      if (Commons.isValidDni(request.getDni())) {
        Optional<Cliente> clienteOpt = clienteRepository.findByDni(request.getDni());
        if (clienteOpt.isPresent()) {
          Cliente cliente = clienteOpt.get();
          productos = productoRepository.findByIdCliente(cliente.getId()).collect(Collectors.toList());
        } else {
          mensajes.add("Usted aún no es parte de la familia BCP");
          isValidRequest = false;
        }
      } else {
        mensajes.add("El número de dni debe tener dígitos y de tamaño 8");
        isValidRequest = false;
      }
    } else {
      mensajes.add("El número de dni no debe ser un valor vacío");
      isValidRequest = false;
    }

    if (!isValidRequest) {
      responseBody.setMensajes(mensajes);
      return responseBody;
    }

    String tarjeta = null;
    if (StringUtils.isNotBlank(request.getTarjeta())) {
      tarjeta = request.getTarjeta();
    } else {
      mensajes.add("La TARJETA no debe ser un valor vacío");
      isValidRequest = false;
    }

    Producto producto = null;

    if (productos != null && tarjeta != null) {
      for (Producto p: productos) {
        if (p.getTarjeta().getTipoTarjeta().equals(request.getTarjeta())) {
          producto = p;
        }
      }
      if (producto == null) {
        mensajes.add("Valor de la TARJETA inválida");
        isValidRequest = false;
      }
    } else {
      mensajes.add("Usted no dispone de ninguna TARJETA");
      isValidRequest = false;
    }

    String moneda = null;
    Double tea = null;
    if (producto != null) {
      if (StringUtils.isNotBlank(request.getMoneda())) {
        moneda = request.getMoneda();
        if (!moneda.equals(producto.getMoneda())) {
          mensajes.add("La MONEDA no está asociada al producto");
          isValidRequest = false;
        }
      } else {
        mensajes.add("La MONEDA no debe ser un valor vacio");
        isValidRequest = false;
      }

      if (StringUtils.isNotBlank(request.getTea())) {
        if (Commons.isDecimal(request.getTea())) {
          tea = Double.parseDouble(request.getTea());
          if (Commons.isValidTea(tea)) {
            if (!tea.equals(producto.getTea())) {
              mensajes.add("La TEA no está asociada al producto");
              isValidRequest = false;
            }
          } else {
            mensajes.add("La TEA debe ser positivo y mayor a 0.00");
            isValidRequest = false;
          }
        } else {
          mensajes.add("La TEA debe ser un número decimal");
          isValidRequest = false;
        }
      } else {
        mensajes.add("La TEA no debe ser un valor vacio");
        isValidRequest = false;
      }
    }

    Double monto = null;
    if (StringUtils.isNotBlank(request.getMonto())) {
      if (Commons.isDecimal(request.getMonto())) {
        monto = Double.parseDouble(request.getMonto());
        if (!Commons.isValidAmount(monto)) {
          mensajes.add("El monto debe ser positivo y mayor a 0.00");
          isValidRequest = false;
        }
      } else {
        mensajes.add("El monto debe ser un número decimal");
        isValidRequest = false;
      }
    } else {
      mensajes.add("El monto no debe ser un valor vacío");
      isValidRequest = false;
    }

    Integer cuota = null;
    if (StringUtils.isNotBlank(request.getCuota())) {
      if (Commons.isInteger(request.getCuota())) {
        cuota = Integer.parseInt(request.getCuota());
        if (!Commons.isValidQuota(cuota)) {
          mensajes.add("La cuota debe ser mayor a cero y un máximo de 36");
          isValidRequest = false;
        }
      } else {
        mensajes.add("La cuota debe ser un número entero");
        isValidRequest = false;
      }
    } else {
      mensajes.add("La cuota no debe ser un valor vacío");
      isValidRequest = false;
    }


    Integer diaPago = null;

    if (StringUtils.isNotBlank(request.getDiaPago())) {
      if (Commons.isInteger(request.getDiaPago())) {
        diaPago = Integer.parseInt(request.getDiaPago());
        if (!Commons.isValidPaymentDate(diaPago)) {
          mensajes.add("El día de pago debe ser igual o mayor a uno y un máximo de 30");
          isValidRequest = false;
        }
      } else {
        mensajes.add("El día de pago debe ser un número entero");
        isValidRequest = false;
      }
    } else {
      mensajes.add("El día de pago no debe ser un valor vacío");
      isValidRequest = false;
    }

    LocalDate localFechaCompra = null;

    if (StringUtils.isNotBlank(request.getFechaCompra())) {
      if (Commons.isDate(request.getFechaCompra())) {
        Calendar calendar = Calendar.getInstance();
        //LocalDate localFechaCompra = LocalDate.parse(request.getFechaCompra());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        localFechaCompra = LocalDate.parse(request.getFechaCompra(), formatter);
        log.info("Fecha actual: " + calendar.get(Calendar.YEAR)+"/"+ (calendar.get(Calendar.MONTH) + 1)+ "/"+ calendar.get(Calendar.DATE));
        LocalDate fechaActual = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE));

        if (fechaActual.compareTo(localFechaCompra) <= 0) {

        } else {
          mensajes.add("La fecha de compra debe ser igual o mayor a la fecha actual");
          isValidRequest = false;
        }
      } else {
        mensajes.add("La fecha de pago no tiene el formato yyyy/MM/dd");
        isValidRequest = false;
      }

    } else {
      mensajes.add("La fecha de pago no debe ser un valor vacío");
      isValidRequest = false;
    }

    if (!isValidRequest) {
      responseBody.setMensajes(mensajes);
      responseBody.setEstado("ERROR");
      return responseBody;
    }

    //calculo de las cuotas

    Double cuotaMensual = Commons.calcularImporteMensual(tea,  cuota, monto);

    cuotaMensual = Math.round(cuotaMensual * 100d) / 100d;
    List<DetalleSimulador> detalles = new ArrayList<>();

    for (Integer i = 1; i <= cuota; i++) {
      LocalDate fechaCuota = LocalDate.of(localFechaCompra.getYear(), localFechaCompra.getMonthValue(), diaPago)
              .plusMonths(i);

      DetalleSimulador detalleSimulador = new DetalleSimulador();
      detalleSimulador.setMoneda(moneda);
      detalleSimulador.setNumeroCuota(i.toString());
      detalleSimulador.setMontoCuota(cuotaMensual.toString());
      detalleSimulador.setFechaPagoCuota(fechaCuota.toString());

      detalles.add(detalleSimulador);
    }


    mensajes.add("Se procesó la información correctamente.");
    mensajes.add("Cuotas INCLUYEN ITF del 0.005%");
    mensajes.add("Cuotas NO incluyen seguro de Desgravamen ni multas");

    responseBody.setMensajes(mensajes);
    responseBody.setEstado("EXITOSO");
    responseBody.setDetalles(detalles);
    return  responseBody;
  }

}
