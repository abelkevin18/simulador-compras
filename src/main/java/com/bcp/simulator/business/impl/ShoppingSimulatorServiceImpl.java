package com.bcp.simulator.business.impl;

import com.bcp.simulator.business.ShoppingSimulatorService;
import com.bcp.simulator.model.api.Formulario;
import com.bcp.simulator.util.Commons;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShoppingSimulatorServiceImpl implements ShoppingSimulatorService {

  @Override
  public void simulatorProcess(Formulario request) {
    //procesar
    //validar body

    StringBuilder msg = new StringBuilder();
    if (request.getDni() != null) {
      Commons.isValidDni(request.getDni());
      //validar que el dni se encuentra en la bd
    }


  }
}
