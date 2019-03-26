package br.com.santander.telemetria.core.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

@Wither @AllArgsConstructor @NoArgsConstructor
public class WiFiNetworksData {

    private String Channel;
    private String SignalStrength;
}
