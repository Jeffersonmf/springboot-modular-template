package br.com.santander.telemetria.core.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

@Wither @AllArgsConstructor @NoArgsConstructor
public class GeoLocationInfo {

    private String Status;
    private String Timestamp;
}