package com.bcp.expose.web;

import com.bcp.simulator.business.ShoppingSimulatorService;
import com.bcp.simulator.model.api.Formulario;
import com.bcp.simulator.model.api.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/simulador")
@Slf4j
public class SimulatorController {

  @Autowired
  private ShoppingSimulatorService simulatorService;

  @PostMapping(value = "/cuotas", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
  public ResponseBody simulator(@RequestBody Formulario body) throws ParseException {

    return simulatorService.simulatorProcess(body);

  }
}
