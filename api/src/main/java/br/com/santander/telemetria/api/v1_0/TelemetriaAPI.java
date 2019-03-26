package br.com.santander.telemetria.api.v1_0;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
//import org.springframework.format.annotation.DateTimeFormat;

public interface TelemetriaAPI {

        @RequestMapping(
            method=POST,
            consumes="application/json",
            produces = "application/json",
            name="",
            path="/telemetria/charger")
        ResponseEntity<String> telemetriaCharger(
            @RequestBody String json
        );
}
