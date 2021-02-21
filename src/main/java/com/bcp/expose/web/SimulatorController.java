package com.bcp.expose.web;

import com.bcp.simulator.business.ShoppingSimulatorService;
import com.bcp.simulator.model.api.Formulario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producto")
@Slf4j
public class SimulatorController {

  @Autowired
  private ShoppingSimulatorService simulatorService;

  @PostMapping(value = "/simulador", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
  public String simulator(@RequestBody Formulario body) {

    simulatorService.simulatorProcess(body);
    return "procesado";
  }
}
