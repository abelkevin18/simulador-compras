package com.bcp.simulator.business;

import com.bcp.simulator.model.api.Formulario;
import com.bcp.simulator.model.api.ResponseBody;

import java.text.ParseException;

public interface ShoppingSimulatorService {

  ResponseBody simulatorProcess(Formulario body) throws ParseException;

}
