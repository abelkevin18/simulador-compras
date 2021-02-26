package com.bcp.simulator.business.impl;

import com.bcp.simulator.business.ShoppingSimulatorService;
import com.bcp.simulator.dao.ClienteRepository;
import com.bcp.simulator.dao.ProductoRepository;
import com.bcp.simulator.model.api.DetalleSimulador;
import com.bcp.simulator.model.api.Formulario;
import com.bcp.simulator.model.api.ResponseBody;
import com.bcp.simulator.model.api.simulator.SimulatorOperator;
import com.bcp.simulator.model.entity.Cliente;
import com.bcp.simulator.model.entity.Producto;
import com.bcp.simulator.util.Commons;
import com.bcp.simulator.util.Constants;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

    SimulatorOperator operator = validateRequest(request, mensajes);

    if (!operator.isValidRequest()) {
      responseBody.setMensajes(mensajes);
      responseBody.setEstado(Constants.MSG_ERROR_STATUS);
    } else {
      responseBody = calculateQuotas(operator, mensajes);
    }
    return  responseBody;
  }

  private ResponseBody calculateQuotas(SimulatorOperator operator, List<String> mensajes) {
    ResponseBody responseBody = new ResponseBody();

    Double cuotaMensual = Commons
            .calcularImporteMensual(operator.getTea(),  operator.getCuota(), operator.getMonto());

    cuotaMensual = Math.round(cuotaMensual * 100d) / 100d;
    List<DetalleSimulador> detalles = new ArrayList<>();

    for (Integer i = 1; i <= operator.getCuota(); i++) {
      LocalDate fechaCuota = LocalDate
              .of(operator.getFechaCompra().getYear()
                      , operator.getFechaCompra().getMonthValue()
                      , operator.getDiaPago())
              .plusMonths(i);

      DetalleSimulador detalleSimulador = new DetalleSimulador();
      detalleSimulador.setMoneda(operator.getMoneda());
      detalleSimulador.setNumeroCuota(i.toString());
      detalleSimulador.setMontoCuota(cuotaMensual.toString());
      detalleSimulador.setFechaPagoCuota(fechaCuota.toString());

      detalles.add(detalleSimulador);
    }


    mensajes.add(Constants.MSG_SUCCESS_PROCESS);
    mensajes.add(Constants.MSG_SUCCESS_DETAIL1);
    mensajes.add(Constants.MSG_SUCCESS_DETAIL2);

    responseBody.setMensajes(mensajes);
    responseBody.setEstado(Constants.MSG_SUCCESS_STATUS);
    responseBody.setDetalles(detalles);

    return responseBody;
  }


  private SimulatorOperator validateRequest(Formulario request, List<String> mensajes) {

    SimulatorOperator data = new SimulatorOperator();
    boolean isValidRequest = true;

    List<Producto> productos = new ArrayList<>();
    if (StringUtils.isNotBlank(request.getDni())) {
      if (Commons.isValidDni(request.getDni())) {
        Optional<Cliente> clienteOpt = clienteRepository.findByDni(request.getDni());
        if (clienteOpt.isPresent()) {
          Cliente cliente = clienteOpt.get();
          productos = productoRepository.findByIdCliente(cliente.getId()).collect(Collectors.toList());
        } else {
          mensajes.add(Constants.MSG_CLIENT_DOES_NOT_EXIST);
          isValidRequest = false;
        }
      } else {
        mensajes.add(Constants.MSG_INVALID_DNI);
        isValidRequest = false;
      }
    } else {
      mensajes.add(Constants.MSG_EMPTY_DNI);
      isValidRequest = false;
    }

    String tarjeta = null;
    if (StringUtils.isNotBlank(request.getTarjeta())) {
      tarjeta = request.getTarjeta();
    } else {
      mensajes.add(Constants.MSG_EMPTY_CREDIT_CARD);
      isValidRequest = false;
    }


    if (!isValidRequest) {
      data.setValidRequest(isValidRequest);
      return data;
    }

    Producto producto = null;

    if (!productos.isEmpty() && tarjeta != null) {
      for (Producto p: productos) {
        if (p.getTarjeta().getTipoTarjeta().toUpperCase()
                .equals(request.getTarjeta().toUpperCase())) {
          producto = p;
        }
      }
      if (producto == null) {
        mensajes.add(Constants.MSG_INVALID_CREDIT_CARD);
        isValidRequest = false;
      }
    } else {
      mensajes.add(Constants.MSG_DONT_HAVE_ANY_CREDIT_CARD);
      isValidRequest = false;
    }

    String moneda = null;
    Double tea = null;
    if (producto != null) {
      if (StringUtils.isNotBlank(request.getMoneda())) {
        moneda = request.getMoneda();
        if (!moneda.equals(producto.getMoneda())) {
          mensajes.add(Constants.MSG_CURRENCY_NO_ASSOCIATE_PRODUCT);
          isValidRequest = false;
        }
      } else {
        mensajes.add(Constants.MSG_EMPTY_CURRENCY);
        isValidRequest = false;
      }

      if (StringUtils.isNotBlank(request.getTea())) {
        if (Commons.isDecimal(request.getTea())) {
          tea = Double.parseDouble(request.getTea());
          if (Commons.isValidTea(tea)) {
            if (!tea.equals(producto.getTea())) {
              mensajes.add(Constants.MSG_TEA_NO_ASSOCIATE_PRODUCT);
              isValidRequest = false;
            }
          } else {
            mensajes.add(Constants.MSG_INVALID_TEA_1);
            isValidRequest = false;
          }
        } else {
          mensajes.add(Constants.MSG_INVALID_TEA_2);
          isValidRequest = false;
        }
      } else {
        mensajes.add(Constants.MSG_EMPTY_TEA);
        isValidRequest = false;
      }
    }

    Double monto = null;
    if (StringUtils.isNotBlank(request.getMonto())) {
      if (Commons.isDecimal(request.getMonto())) {
        monto = Double.parseDouble(request.getMonto());
        if (!Commons.isValidAmount(monto)) {
          mensajes.add(Constants.MSG_INVALID_AMOUNT_1);
          isValidRequest = false;
        }
      } else {
        mensajes.add(Constants.MSG_INVALID_AMOUNT_2);
        isValidRequest = false;
      }
    } else {
      mensajes.add(Constants.MSG_EMPTY_AMOUNT);
      isValidRequest = false;
    }

    Integer cuota = null;
    if (StringUtils.isNotBlank(request.getCuota())) {
      if (Commons.isInteger(request.getCuota())) {
        cuota = Integer.parseInt(request.getCuota());
        if (!Commons.isValidQuota(cuota)) {
          mensajes.add(Constants.MSG_INVALID_QUOTA_1);
          isValidRequest = false;
        }
      } else {
        mensajes.add(Constants.MSG_INVALID_QUOTA_2);
        isValidRequest = false;
      }
    } else {
      mensajes.add(Constants.MSG_EMPTY_QUOTA);
      isValidRequest = false;
    }

    Integer diaPago = null;

    if (StringUtils.isNotBlank(request.getDiaPago())) {
      if (Commons.isInteger(request.getDiaPago())) {
        diaPago = Integer.parseInt(request.getDiaPago());
        if (!Commons.isValidPaymentDate(diaPago)) {
          mensajes.add(Constants.MSG_INVALID_PAYMENT_DAY_1);
          isValidRequest = false;
        }
      } else {
        mensajes.add(Constants.MSG_INVALID_PAYMENT_DAY_2);
        isValidRequest = false;
      }
    } else {
      mensajes.add(Constants.MSG_EMPTY_PAYMENT_DAY);
      isValidRequest = false;
    }

    LocalDate fechaCompra = null;

    if (StringUtils.isNotBlank(request.getFechaCompra())) {
      if (Commons.isDate(request.getFechaCompra())) {
        Calendar calendar = Calendar.getInstance();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.FORMAT_DATE);
        fechaCompra = LocalDate.parse(request.getFechaCompra(), formatter);
        log.info("Fecha actual: " + calendar.get(Calendar.YEAR)+"/"+ (calendar.get(Calendar.MONTH) + 1)+ "/"+ calendar.get(Calendar.DATE));
        LocalDate fechaActual = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE));

        if (fechaActual.compareTo(fechaCompra) > 0) {
          mensajes.add(Constants.MSG_INVALID_PURCHASE_DATE);
          isValidRequest = false;
        }
      } else {
        mensajes.add(Constants.MSG_INVALID_FORMAT_PURCHASE_DATE);
        isValidRequest = false;
      }

    } else {
      mensajes.add(Constants.MSG_EMPTY_PURCHASE_DATE);
      isValidRequest = false;
    }

    data.setValidRequest(isValidRequest);
    data.setCuota(cuota);
    data.setMonto(monto);
    data.setTea(tea);
    data.setFechaCompra(fechaCompra);
    data.setDiaPago(diaPago);
    data.setMoneda(moneda);

    return data;
  }


}
