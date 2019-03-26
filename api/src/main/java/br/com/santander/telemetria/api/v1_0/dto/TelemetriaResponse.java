package br.com.santander.telemetria.api.v1_0.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

import java.util.List;

@Data
@Builder
@Wither
@NoArgsConstructor
@AllArgsConstructor
public class TelemetriaResponse {
    private long allEvents;
    private List<String> events;
}