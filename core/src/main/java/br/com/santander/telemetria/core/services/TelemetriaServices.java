package br.com.santander.telemetria.core.services;

import br.com.santander.telemetria.core.domain.TelemetriaLite;
import br.com.santander.telemetria.core.events.TelemetriaIngestorEvents;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CompletableFuture;

/**
 * @author Jefferson Marchetti Ferreira
 *
 * Aqui conterá os códigos de implementação dos serviços.. baseado nas interfaces
 * da API.
 */
public class TelemetriaServices {

    @Autowired
    TelemetriaIngestorEvents telemetriaIngestorEvents;

    public JSONPObject telemetriaIngestor(TelemetriaLite telemetriaJson) {

        return null;
    }

    public CompletableFuture<TelemetriaLite> parserData(TelemetriaLite telemetriaLite) {

        return null;
    }

    public void startIngestorProcess(String avroInputData) {

        //telemetriaIngestorEvents.postAsyncData();
    }
}
